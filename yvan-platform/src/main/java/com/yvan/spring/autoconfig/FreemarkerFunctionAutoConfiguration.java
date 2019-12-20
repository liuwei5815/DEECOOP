package com.yvan.spring.autoconfig;

import com.yvan.spring.FreemarkerFunction;
import freemarker.template.SimpleScalar;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;
import org.apache.commons.lang.StringUtils;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class FreemarkerFunctionAutoConfiguration implements BeanPostProcessor {

    public final Map<String, Object> sharedVariables = new HashMap<>();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Class<?> objClz = bean.getClass();
        if (AopUtils.isAopProxy(bean)) {
            objClz = AopUtils.getTargetClass(bean);
        }
        if (bean.getClass().getName().contains("CGLIB")) {
            objClz = bean.getClass().getSuperclass();
        }

        for (Method method : objClz.getDeclaredMethods()) {
            FreemarkerFunction ff = method.getAnnotation(FreemarkerFunction.class);
            if (ff != null && !StringUtils.isEmpty(ff.value()))
                sharedVariables.put(ff.value(), new FunctionInvoker(bean, method));
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }


    public static class FunctionInvoker implements TemplateMethodModelEx {

        private final Object bean;
        private final Method method;

        public FunctionInvoker(Object bean, Method method) {
            this.bean = bean;
            this.method = method;
        }

        @Override
        public Object exec(List arguments) throws TemplateModelException {
            try {
                Object[] array = new Object[arguments.size()];
                arguments.toArray(array);
                for (int i = 0; i < array.length; i++) {
                    if (array[i] instanceof SimpleScalar) {
                        array[i] = ((SimpleScalar) array[i]).getAsString();
                    }
                }
                if (array.length == 1) {
                    return method.invoke(bean, array[0]);
                } else {
                    return method.invoke(bean, array);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
