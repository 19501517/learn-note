package com.nw.exception;

/**
 * 事件转发异常
 *
 * @Author liuyefeng
 * @Date 2018/12/13 15:52
 */
public class EventDispatcherException extends RuntimeException {
    public EventDispatcherException(String s) {
        super(s);
    }

    public EventDispatcherException(String s, Exception e) {
        super(s, e);
    }
}
