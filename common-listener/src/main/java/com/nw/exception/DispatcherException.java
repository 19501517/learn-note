package com.nw.exception;

/**
 * @Author liuyefeng
 * @Date 2018/12/13 15:52
 */
public class DispatcherException extends RuntimeException {
    public DispatcherException(String s) {
        super(s);
    }

    public DispatcherException(String s, Exception e) {
        super(s, e);
    }
}
