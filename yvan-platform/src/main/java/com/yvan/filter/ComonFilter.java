package com.yvan.filter;

import com.yvan.RedisUtils;
import com.yvan.YvanUtil;
import com.yvan.cache.RedisClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by 46368 on 2018/7/23.
 */
@Configuration
@EnableConfigurationProperties(RedisProperties.class)
@ConditionalOnClass(RedisClient.class)
public class ComonFilter implements Filter {
    private Logger logger = LoggerFactory.getLogger(ComonFilter.class);



    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("过滤器初始化.......ComonFilter.init()");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {



        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

//        httpResponse.setHeader("Access-Control-Allow-Origin", "*");
//        httpResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
//        httpResponse.setHeader("Access-Control-Max-Age", "3600");
//        httpResponse.setHeader("Access-Control-Allow-Headers", "x-requested-with");

//        httpResponse.setHeader("Access-Control-Allow-Origin", "*");
//        httpResponse.setHeader("Access-Control-Allow-Methods", "*");
//        httpResponse.setHeader("Access-Control-Max-Age", "100");
//        httpResponse.setHeader("Access-Control-Allow-Headers", "Content-Type");
//        httpResponse.setHeader("Access-Control-Allow-Credentials", "false");


        // 允许客户端携带跨域cookie
        // 当Access-Control-Allow-Credentials设为true的时候，Access-Control-Allow-Origin不能设为星号
        httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
        // 允许指定域访问跨域资源
        //response.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:9006, http://127.0.0.1:8080");
        httpResponse.setHeader("Access-Control-Allow-Origin", httpRequest.getHeader("Origin"));// *
        // 允许浏览器发送的请求消息头
        httpResponse.setHeader("Access-Control-Allow-Headers", httpRequest.getHeader("Access-Control-Request-Headers"));// *
        // 允许浏览器在预检请求成功之后发送的实际请求方法名
        httpResponse.setHeader("Access-Control-Allow-Methods", httpRequest.getHeader("Access-Control-Request-Method"));
        // 设置响应数据格式
        httpResponse.setHeader("Content-Type", "application/json");

//        System.out.println("*********************************过滤器被使用**************************");

        logger.info("************getRequestURI:"+ httpRequest.getRequestURI());
        logger.info("************getHeader:"+ httpRequest.getHeader("referer"));
        String uri = httpRequest.getRequestURI();
        uri = uri.toLowerCase();
        ModifyParametersWrapper mParametersWrapper = new ModifyParametersWrapper(httpRequest);

        //请求地址中含有字符串“login”和“error”的不参与sessionId校验
        if(uri.indexOf("login".toLowerCase()) < 0 && uri.indexOf("home".toLowerCase()) < 0 && uri.indexOf("error".toLowerCase()) < 0 && uri.indexOf("file".toLowerCase())<0 && uri.indexOf("importExcel".toLowerCase())<0
                && uri.indexOf(".js".toLowerCase())<0 && uri.indexOf(".png".toLowerCase())<0 && uri.indexOf(".ttf".toLowerCase())<0 && uri.indexOf(".html".toLowerCase())<0
                && uri.indexOf(".css".toLowerCase())<0 && uri.indexOf("test".toLowerCase())<0 && uri.indexOf("mobile".toLowerCase())<0){
            if(uri.equals("/")){
//                    httpResponse.sendRedirect("/index.html/");
                request.getRequestDispatcher("/index.html/").forward(request,response);
                return;
            }else {
                if (!this.checkSession(httpRequest)) {
//                        httpResponse.sendRedirect(httpRequest.getHeader("referer") + "api/error/401");
//                        httpResponse.sendRedirect( "/error/401");
                    request.getRequestDispatcher("/error/401").forward(request,response);
                    return;
                }

                //延长 Session 时间
                this.extendSessionTime(httpRequest, httpResponse);
            }

        }
        chain.doFilter(mParametersWrapper, httpResponse);
    }

    /**
     * 延长Redis缓存Key(sessionID) 有效时间
     * Redis缓存Key: (uuid:用户ID:deecoop:userLoginMap)
     *
     * @param httpRequest
     * @param httpResponse
     * @throws IOException
     */
    public void extendSessionTime(HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws IOException, ServletException {
        try{
            //sessionID: (uuid:用户ID:deecoop:userLoginMap)
            String sessionID = httpRequest.getHeader("sessionID");
            if (sessionID == null || sessionID.trim().length() == 0) {
//            httpResponse.sendRedirect(httpRequest.getHeader("referer") + "api/error/401");
//            httpResponse.sendRedirect( "/error/401");
                httpRequest.getRequestDispatcher("/error/401").forward(httpRequest,httpResponse);
                return;
            }

            //获取(sessionID)值: Redis缓存Key: (uuid:用户ID:deecoop:userLoginMap)
            String sessionValue = "";
            if (redisClient.get(sessionID) != null && redisClient.get(sessionID).trim().length() > 0) {
                sessionValue = redisClient.get(sessionID).trim();
            }
            redisClient.setWithExpireTime(sessionID, sessionValue, 30*60*1000);
        }catch (Exception e){
           throw new  ServletException(e);
        }

    }

    /**
     * 根据userID-获取Redis缓存中的会话ID(Uuid)
     * (手机端)Redis缓存Key:   (uuid:用户ID:企业ID:deecoop:userLoginMap:app)
     * (web端)Redis缓存Key:   (uuid:用户ID:企业ID:deecoop:userLoginMap:web)
     *
     */
    public boolean checkSession(HttpServletRequest httpRequest) {
        //1. 客户端-获取历史sessionID
        String loginType = "";
        String sessionID = httpRequest.getHeader("sessionID");
        if (sessionID == null || sessionID.trim().length() == 0) {
            return false;
        } else {
            if (sessionID.lastIndexOf("app") != -1) {
                loginType = "app";
            } else if (sessionID.lastIndexOf("web") != -1) {
                loginType = "web";
            }
        }

        String[] str_arry = sessionID.split(":");
        String uuid = str_arry[0];
        String userID = str_arry[1];
        //System.out.println("userID: " + userID);

        //2. 通过(userID)-Redis缓存中获取最新的会话id(uuid)
        //String uuid_new = this.findRedisUuidByUserID(userID);
        String uuid_new = RedisUtils.findRedisUuidByUserID(redisClient, userID, loginType);

        if (uuid_new == null || uuid_new.trim().length() == 0) {
            return false;
        } else if (!uuid_new.toLowerCase().equals(uuid.toLowerCase())) {
            return false;
        }

        return true;
    }

    @Override
    public void destroy() {
        System.out.println("****************************************ComonFilter.destroy()");
    }






    @Autowired
    private RedisProperties prop;

    @Autowired
    RedisClient redisClient;

    @Bean(name = "jedisPool")
    public JedisPool jedisPool() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(500);
        config.setMaxIdle(50);
        config.setMaxWaitMillis(5000);
        return new JedisPool(config, prop.getHost(), prop.getPort(), prop.getTimeout(), prop.getPassword());
    }

    @Bean
    @ConditionalOnMissingBean(RedisClient.class)
    public RedisClient redisClient(@Qualifier("jedisPool") JedisPool pool) {
        logger.info("初始化……Redis Client==Host={},Port={}", prop.getHost(), prop.getPort());
        RedisClient redisClient = new RedisClient();
        redisClient.setJedisPool(pool);
        return redisClient;
    }

}
