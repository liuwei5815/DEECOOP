package com.xy.vmes.deecoop.Interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private OperationlogInterceptor operationlog;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //addPathPatterns 用于添加拦截器规则
        //excludePathPatterns 用于排除拦截

        //配置系统操作日志-拦截器
        registry.addInterceptor(operationlog).addPathPatterns(
            "/*/*/add*",
            "/*/*/update*",
            "/*/*/delete*"
        ).excludePathPatterns("/*/updateDisable*");

        //配置状态变更日志-拦截器
        //registry.addInterceptor(statelog).addPathPatterns("/**");

        super.addInterceptors(registry);
    }
}
