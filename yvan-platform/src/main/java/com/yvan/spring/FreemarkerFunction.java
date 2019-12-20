package com.yvan.spring;

import java.lang.annotation.*;

/**
 * 标注某个方法要注入到freemarker函数中
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FreemarkerFunction {
    /**
     * freemarker 函数名称
     */
    String value() default "";
}
