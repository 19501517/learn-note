package com.nw.exception;

/**
 * 转发器初始化异常
 *
 * @Author liuyefeng
 * @Date 2018/12/12 11:46
 */
public class DispatcherInitException extends RuntimeException {
    public DispatcherInitException(String s, Exception e) {
        super(s, e);
    }

    public DispatcherInitException(String s) {
        super(s);
    }

    public DispatcherInitException(Exception e) {
        super(e);
    }
}
