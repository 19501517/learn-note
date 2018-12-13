package com.nw.dispatcher;

/**
 * 事件转发接口
 *
 * @Author liuyefeng
 * @Date 2018/12/11 18:07
 */
public interface ListenerEventDispatcher {
    void fire(Object event);
}
