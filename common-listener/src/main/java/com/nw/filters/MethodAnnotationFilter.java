package com.nw.filters;

import org.springframework.util.ReflectionUtils.MethodFilter;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @Author liuyefeng
 * @Date 2018/12/13 14:56
 */
public class MethodAnnotationFilter implements MethodFilter {

    private Class<? extends Annotation> annotationClass;

    public MethodAnnotationFilter(Class<? extends Annotation> annotationClass) {
        this.annotationClass = annotationClass;
    }

    @Override
    public boolean matches(Method method) {
        return method.isAnnotationPresent(annotationClass);
    }
}
