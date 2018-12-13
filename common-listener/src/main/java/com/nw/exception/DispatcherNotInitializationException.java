package com.nw.exception;

/**
 * 事件调度器还没有初始化完成
 *
 * @Author liuyefeng
 * @Date 2018/12/12 11:46
 */
public class DispatcherNotInitializationException extends RuntimeException {
    public DispatcherNotInitializationException() {
        super("The event dispatcher haven't finish initialized yet");
    }
}
