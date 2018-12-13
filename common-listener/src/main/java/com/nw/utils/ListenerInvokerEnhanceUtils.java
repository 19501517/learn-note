package com.nw.utils;

import com.nw.definition.ListenerDefinition;
import com.nw.dispatcher.ListenerInvoker;
import javassist.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 事件接受者类增强工具
 *
 * @Author liuyefeng
 * @Date 2018/12/13 15:16
 */
public class ListenerInvokerEnhanceUtils {

    private static final ClassPool CLASS_POOL = ClassPool.getDefault();
    private static final AtomicInteger INDEX = new AtomicInteger(0);

    public static ListenerInvoker createInvoker(Object listener, ListenerDefinition definition)
            throws NotFoundException, CannotCompileException, NoSuchMethodException, IllegalAccessException,
            InvocationTargetException, InstantiationException {
        Method invokeMethod = definition.getListenerInvokeMethod();
        Class<?> eventClass = definition.getEventClass();
        CtClass listenerCtClass = CLASS_POOL.get(listener.getClass().getCanonicalName());
        CtClass objectCtClass = CLASS_POOL.get(Object.class.getCanonicalName());
        CtClass voidCtClass = CLASS_POOL.get(void.class.getCanonicalName());

        CtClass invokerCtClass = CLASS_POOL.makeClass(
                ListenerInvoker.class.getSimpleName() + "Enhance$" + INDEX.getAndIncrement());
        invokerCtClass.addInterface(CLASS_POOL.get(ListenerInvoker.class.getCanonicalName()));

        CtField listenerClassField = new CtField(listenerCtClass, "listener", invokerCtClass);
        listenerClassField.setModifiers(Modifier.PRIVATE + Modifier.FINAL);
        invokerCtClass.addField(listenerClassField);

        CtConstructor ctConstructor = new CtConstructor(new CtClass[]{listenerCtClass}, invokerCtClass);
        ctConstructor.setBody("{this.listener = $1;}");
        ctConstructor.setModifiers(Modifier.PUBLIC);
        invokerCtClass.addConstructor(ctConstructor);

        CtMethod invokeCtMethod = new CtMethod(voidCtClass, "invoke", new CtClass[]{objectCtClass}, invokerCtClass);
        invokeCtMethod.setModifiers(Modifier.PUBLIC + Modifier.FINAL);
        StringBuilder methodBody = new StringBuilder();
        methodBody.append("{this.listener.").append(invokeMethod.getName())
                .append("((").append(eventClass.getCanonicalName()).append(") $1);}");
        invokeCtMethod.setBody(methodBody.toString());
        invokerCtClass.addMethod(invokeCtMethod);

        Class<?> invokerClass = invokerCtClass.toClass();
        Constructor<?> constructor = invokerClass.getConstructor(listener.getClass());
        return (ListenerInvoker) constructor.newInstance(listener);
    }
}
