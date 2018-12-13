package com.nw.filters;

import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * 判断是否是public且非static非abstract方法
 *
 * @Author liuyefeng
 * @Date 2018/12/13 14:25
 */
public class PublicMethodFilter implements ReflectionUtils.MethodFilter {
    @Override
    public boolean matches(Method method) {
        if (!Modifier.isPublic(method.getModifiers())) {
            return false;
        }
        if (Modifier.isAbstract(method.getModifiers())) {
            return false;
        }
        if (Modifier.isStatic(method.getModifiers())) {
            return false;
        }
        return true;
    }
}
