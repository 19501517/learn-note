package com.nw.definition;

import java.lang.reflect.Method;

/**
 * 监听器相关定义信息
 *
 * @Author liuyefeng
 * @Date 2018/12/11 18:28
 */
public class ListenerDefinition {

    private Class<?> receiverClass;

    private Class<?> eventClass;

    private Method listenerInvokeMethod;

    public ListenerDefinition(Class<?> receiverClass, Class<?> eventClass, Method listenerInvokeMethod) {
        this.receiverClass = receiverClass;
        this.listenerInvokeMethod = listenerInvokeMethod;
        this.eventClass = eventClass;
    }

    public Method getListenerInvokeMethod() {
        return listenerInvokeMethod;
    }

    public void setListenerInvokeMethod(Method listenerInvokeMethod) {
        this.listenerInvokeMethod = listenerInvokeMethod;
    }

    public Class<?> getEventClass() {
        return eventClass;
    }

    public void setEventClass(Class<?> eventClass) {
        this.eventClass = eventClass;
    }

    public Class<?> getReceiverClass() {
        return receiverClass;
    }

    public void setReceiverClass(Class<?> receiverClass) {
        this.receiverClass = receiverClass;
    }
}
