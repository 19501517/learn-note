package com.nw.filters;

import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;

/**
 * 判断方法返回类型是否符合
 *
 * @Author liuyefeng
 * @Date 2018/12/13 11:41
 */
public class MethodReturnTypeFilter implements ReflectionUtils.MethodFilter {

    private Class<?> targetClass;

    public MethodReturnTypeFilter(Class<?> targetClass) {
        this.targetClass = targetClass;
    }

    @Override
    public boolean matches(Method method) {
        return method.getReturnType() == targetClass;
    }
}
