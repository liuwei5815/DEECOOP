package com.xy.vmes.deecoop.error;

import com.yvan.BizException;
import com.yvan.HttpUtils;
import com.yvan.YvanUtil;
import com.yvan.springmvc.JsonView;
import com.yvan.springmvc.ResultModel;
import org.apache.commons.io.IOUtils;

import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Timestamp;
import java.util.*;

/**
 * 异常控制器，springmvc发生异常页面后会跳转到本控制器
 * Created by luoyifan on 2017/3/1.
 */
@RestController
@ControllerAdvice
public class ErrorController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ErrorController.class);

    @RequestMapping(value = "/domain", method = RequestMethod.GET)
    public Object domain(HttpServletRequest request) {

        Enumeration<String> enuma = request.getHeaderNames();
        Map<String, Object> vl = new LinkedHashMap<>();
        while (enuma.hasMoreElements()) {
            String key = enuma.nextElement();
            vl.put(key, request.getHeader(key));
        }

        Map<String, Object> r = new LinkedHashMap<>();
        r.put("HeaderNames", vl);
        r.put("ServerName", request.getServerName());
        r.put("Host", request.getHeader("Host"));
        r.put("X-Forwarded-For", request.getHeader("X-Forwarded-For"));
        r.put("RequestURI", request.getRequestURI());
        r.put("scheme", request.getScheme());
        r.put("url", request.getScheme() + "://" + request.getHeader("Host") + request.getRequestURI());

        r.put("currentHost", HttpUtils.currentHost());
        r.put("currentRemoteIp", HttpUtils.currentRemoteIp());
        r.put("currentUrl", HttpUtils.currentUrl());

        r.put("joda.DateTime", new DateTime());
        r.put("currentTimeMillis", System.currentTimeMillis());
        r.put("Date", new Date());
        r.put("Timestamp", new Timestamp(System.currentTimeMillis()));
        Calendar cal = Calendar.getInstance();
        TimeZone timeZone = cal.getTimeZone();
        r.put("timeZone.getID", timeZone.getID());
        r.put("timeZone.getDisplayName", timeZone.getDisplayName());

        return r;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleError(HttpServletRequest request, Exception ex) {
        Object errorData = PROCESS_ERROR(request, ex);
        return new ModelAndView(new JsonView(errorData));
    }

    public static Object PROCESS_ERROR(HttpServletRequest request, Exception ex) {
        Map<String, Object> r = new LinkedHashMap<>();
        String msg;
        if (ex instanceof NotImplementedException) {
            //未授权异常
            LOGGER.error(ex.toString(), ex);
            msg = "没做";
            HttpUtils.currentResponse().setStatus(500);

        } else if (ex instanceof UnauthenticatedException ||
                ex instanceof UnauthorizedException) {
            //未授权异常
            msg = "无权限";
            LOGGER.error(request.getRequestURI() + " 401!");
            HttpUtils.currentResponse().setStatus(401);

        }
//        else if (ex instanceof BizException ) {
//            //业务代码异常
//            msg = ex.getMessage();
//            LOGGER.error(ex.toString(), ex);
//            HttpUtils.currentResponse().setStatus(500);
//
//        }
        else {
            //其他异常
            LOGGER.error(ex.toString(), ex);
            HttpUtils.currentResponse().setStatus(500);
            Enumeration<String> enuma = request.getHeaderNames();
            Map<String, Object> vl = new LinkedHashMap<>();
            while (enuma.hasMoreElements()) {
                String key = enuma.nextElement();
                vl.put(key, request.getHeader(key));
            }

            msg = "发生异常:" + ex.getMessage();

            String trace = "";
            StringWriter sw = null;
            PrintWriter pw = null;
            try {
                sw = new StringWriter();
                pw = new PrintWriter(sw);
                ex.printStackTrace(pw);
                trace = sw.toString();
            } finally {
                IOUtils.closeQuietly(pw);
                IOUtils.closeQuietly(sw);
            }

            r.put("errorTrace", trace);
            r.put("errorMsg", msg);
            r.put("Headers", YvanUtil.toJsonPretty(vl));
            r.put("ServerName", request.getServerName());
            r.put("Host", request.getHeader("Host"));
            r.put("RemoteIP", HttpUtils.currentRemoteIp());
            r.put("url", request.getScheme() + "://" + request.getHeader("Host") + request.getRequestURI());
        }

        return ResultModel.newFail(msg).set("code", 1).putData(r);
    }

    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer() {

        return new EmbeddedServletContainerCustomizer() {
            @Override
            public void customize(ConfigurableEmbeddedServletContainer container) {
                ErrorPage error401Page = new ErrorPage(HttpStatus.UNAUTHORIZED, "/error/401");
                ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/error/404");
                ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error/500");

                container.addErrorPages(error401Page, error404Page, error500Page);
            }
        };
    }

    @RequestMapping("/error/500")
    public ModelAndView error500() {
        return new ModelAndView(new JsonView(new ResultModel().set("code","500").success(false).putMsg("服务器异常！")));
    }

    @RequestMapping("/error/401")
    public ModelAndView error401() {
        return new ModelAndView(new JsonView(new ResultModel().set("code","401").success(false).putMsg("凭证已过期，请重新登录！")));
    }

    @RequestMapping("/error/404")
    public ModelAndView error404() {
        return new ModelAndView(new JsonView(new ResultModel().set("code","404").success(false).putMsg("请求路径不存在！")));
    }
}
