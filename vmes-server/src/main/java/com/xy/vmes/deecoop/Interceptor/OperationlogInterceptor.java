package com.xy.vmes.deecoop.Interceptor;

import com.xy.vmes.entity.LogInfo;
import com.xy.vmes.deecoop.system.service.LogInfoService;
import com.yvan.PageData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 系统操作日志-拦截器(add,update,delete)
 * 拦截系统中所有(add,update,delete)前缀的方法
 * 写入日志表(vmes_loginfo)
 *
 */
@Component
public class OperationlogInterceptor implements HandlerInterceptor {
    private Logger logger = LoggerFactory.getLogger(OperationlogInterceptor.class);

    @Autowired
    private LogInfoService logInfoService;

    /**
     * 方法调用前
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    /**
     * 方法调用后
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        logger.info("操作日志拦截器-OperationlogInterceptor.afterCompletion()-执行开始");
        //获取Controller方法调用全路径
        String method_path = "";
        if (handler != null) {method_path = handler.toString();}
        if (method_path == null || method_path.trim().length() == 0) {return;}

        //获取调用方法名称前缀
        String prefix = logInfoService.findMethodPrefix(method_path);
        //获取业务表名
        String tableName = logInfoService.findTable(method_path);

        //获取调用参数
        PageData pageData = new PageData(request);
        String cuserId = (String)pageData.get("cuser");
        String companyId = pageData.getString("currentCompanyId");
        LogInfo loginfoDB = logInfoService.createLoginfo(null);
        String id = (String)pageData.get("id");
        if (id != null && id.trim().length() > 0) {
            loginfoDB.setBusinessId(id);
        }

        String ids = (String)pageData.get("ids");
        if (ids != null && ids.trim().length() > 0) {
            loginfoDB.setOperateValue(ids);
        }
        loginfoDB.setOperateUrl(request.getRequestURI());
        loginfoDB.setType("operate");
        loginfoDB.setSource("web");
        loginfoDB.setModelName(tableName);
        loginfoDB.setOperate(prefix);
        loginfoDB.setCuser(cuserId);
        loginfoDB.setCompanyId(companyId);
        logInfoService.save(loginfoDB);
        logger.info("操作日志拦截器-OperationlogInterceptor.afterCompletion()-执行结束");

    }
}
