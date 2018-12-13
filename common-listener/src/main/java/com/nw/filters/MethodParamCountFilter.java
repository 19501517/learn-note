package com.nw.filters;

import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;

/**
 * 判断方法的参数个数是否与给定的一致
 *
 * @Author liuyefeng
 * @Date 2018/12/13 10:59
 */
public class MethodParamCountFilter implements ReflectionUtils.MethodFilter {

    private int count;

    public MethodParamCountFilter(int count) {
        this.count = count;
    }

    @Override
    public boolean matches(Method method) {
        return method.getParameterCount() == count;
    }
}
