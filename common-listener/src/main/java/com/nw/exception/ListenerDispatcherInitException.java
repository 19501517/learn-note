package com.nw.exception;

/**
 * @Author liuyefeng
 * @Date 2018/12/12 11:46
 */
public class ListenerDispatcherInitException extends RuntimeException {
    public ListenerDispatcherInitException(String s, Exception e) {
        super(s, e);
    }

    public ListenerDispatcherInitException(String s) {
        super(s);
    }

    public ListenerDispatcherInitException(Exception e) {
        super(e);
    }
}
