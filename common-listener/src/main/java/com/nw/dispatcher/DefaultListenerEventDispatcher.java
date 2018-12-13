package com.nw.dispatcher;


import com.nw.definition.ListenerDefinition;

import java.util.Collection;
import java.util.List;

/**
 * 默认事件转发器
 *
 * @Author liuyefeng
 * @Date 2018/12/11 18:07
 */
public class DefaultListenerEventDispatcher extends AbstractListenerEventDispatcher {
    public DefaultListenerEventDispatcher(Collection<ListenerDefinition> definitions) {
        super(definitions);
    }

    @Override
    protected void doFire(Object event, List<ListenerInvoker> invokers) {
        for (ListenerInvoker invoker : invokers) {
            invoker.invoke(event);
        }
    }
}
