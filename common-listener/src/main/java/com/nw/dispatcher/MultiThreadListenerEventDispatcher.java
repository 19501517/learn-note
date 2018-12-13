package com.nw.dispatcher;


import com.nw.definition.ListenerDefinition;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.Collection;
import java.util.List;

/**
 * 多线程事件转发器
 *
 * @Author liuyefeng
 * @Date 2018/12/11 18:07
 */
public class MultiThreadListenerEventDispatcher extends AbstractListenerEventDispatcher {

    private static final int CPU_CORE = Runtime.getRuntime().availableProcessors();

    private ThreadPoolTaskExecutor pool;

    public MultiThreadListenerEventDispatcher(Collection<ListenerDefinition> definitions) {
        super(definitions);
        pool = new ThreadPoolTaskExecutor();
        pool.setMaxPoolSize(CPU_CORE * 2);
        pool.setAllowCoreThreadTimeOut(true);
    }

    @Override
    protected void doFire(final Object event, final List<ListenerInvoker> invokers) {
        invokers.forEach(listenerInvoker -> pool.submit(
                () -> listenerInvoker.invoke(event))
        );
    }
}
