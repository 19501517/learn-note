package com.nw.utils;

import com.nw.definition.ListenerDefinition;
import com.nw.dispatcher.ReceiverInvoker;
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
public class ReceiverInvokerEnhanceUtils {

    private static final ClassPool CLASS_POOL = ClassPool.getDefault();
    private static final AtomicInteger INDEX = new AtomicInteger(0);

    public static ReceiverInvoker createInvoker(Object receiver, ListenerDefinition definition)
            throws NotFoundException, CannotCompileException, NoSuchMethodException, IllegalAccessException,
            InvocationTargetException, InstantiationException {
        Method invokeMethod = definition.getListenerInvokeMethod();
        Class<?> eventClass = definition.getEventClass();
        CtClass receiverCtClass = CLASS_POOL.get(receiver.getClass().getCanonicalName());
        CtClass objectCtClass = CLASS_POOL.get(Object.class.getCanonicalName());
        CtClass voidCtClass = CLASS_POOL.get(void.class.getCanonicalName());

        CtClass invokerCtClass = CLASS_POOL.makeClass(
                ReceiverInvoker.class.getSimpleName() + "Enhance$" + INDEX.getAndIncrement());
        invokerCtClass.addInterface(CLASS_POOL.get(ReceiverInvoker.class.getCanonicalName()));

        CtField receiverClassField = new CtField(receiverCtClass, "receiver", invokerCtClass);
        receiverClassField.setModifiers(Modifier.PRIVATE + Modifier.FINAL);
        invokerCtClass.addField(receiverClassField);

        CtConstructor ctConstructor = new CtConstructor(new CtClass[]{receiverCtClass}, invokerCtClass);
        ctConstructor.setBody("{this.receiver = $1;}");
        ctConstructor.setModifiers(Modifier.PUBLIC);
        invokerCtClass.addConstructor(ctConstructor);

        CtMethod invokeCtMethod = new CtMethod(voidCtClass, "invoke", new CtClass[]{objectCtClass}, invokerCtClass);
        invokeCtMethod.setModifiers(Modifier.PUBLIC + Modifier.FINAL);
        StringBuilder methodBody = new StringBuilder();
        methodBody.append("{this.receiver.").append(invokeMethod.getName())
                .append("((").append(eventClass.getCanonicalName()).append(") $1);}");
        invokeCtMethod.setBody(methodBody.toString());
        invokerCtClass.addMethod(invokeCtMethod);

        Class<?> invokerClass = invokerCtClass.toClass();
        Constructor<?> constructor = invokerClass.getConstructor(receiver.getClass());
        return (ReceiverInvoker) constructor.newInstance(receiver);
    }
}
