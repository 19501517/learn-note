package com.nw.dispatcher;

/**
 * 事件监听者/接受者增强类接口
 *
 * @Author liuyefeng
 * @Date 2018/12/13 15:08
 */
public interface ListenerInvoker {
    void invoke(Object event);
}
