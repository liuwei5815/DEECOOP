package com.yvan.mybatis;

import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.plugins.PerformanceInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by luoyifan on 2018/4/19.
 */
@Configuration
@EnableTransactionManagement
public class MyBatisConfiguration {

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor= new PaginationInterceptor();
        paginationInterceptor.setLocalPage(false);
        paginationInterceptor.setDialectType("mysql");
        return paginationInterceptor;
    }

    @Bean
    public YvanPerformanceInterceptor performanceInterceptor() {
        YvanPerformanceInterceptor performanceInterceptor = new YvanPerformanceInterceptor();
        performanceInterceptor.setWriteInLog(false);
        return performanceInterceptor;
    }
}
