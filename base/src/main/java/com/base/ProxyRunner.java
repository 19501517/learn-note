package com.base;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Optional;

public class ProxyRunner {

    public static void run(RunType type, Object[] methodArgs, Object expectResult, Object executor) {
        try {
            Optional<Method> first = Arrays.stream(executor.getClass().getDeclaredMethods()).filter(method -> method.isAnnotationPresent(Solution.class)).findFirst();
            if (first.isEmpty()) {
                throw new RuntimeException("can not found a method with @Solution");
            }

            long start = System.currentTimeMillis();
            Method method = first.get();
            method.setAccessible(true);
            Object result = method.invoke(executor, methodArgs);
            long end = System.currentTimeMillis();
            System.out.println("----------------------------------------");
            System.out.println("given s : " + type.getMethodArgsString(methodArgs));
            System.out.println("actual result : " + type.getResultString(result));
            System.out.println("expect result : " + type.getResultString(expectResult));
            System.out.println("time : " + (end - start));
            type.assertResult(expectResult, result);
        } catch (Exception e) {
            throw new RuntimeException("invoke error", e);
        }
    }
    public static void run(RunType type, Object methodArgs, Object expectResult, Object executor) {
        run(RunType.Base, new Object[]{methodArgs}, expectResult, executor);
    }

    public static void run(Object[] methodArgs, Object expectResult, Object executor) {
        run(RunType.Base, methodArgs, expectResult, executor);
    }

    public static void run(Object methodArgs, Object expectResult, Object executor) {
        run(RunType.Base, methodArgs, expectResult, executor);
    }
}
