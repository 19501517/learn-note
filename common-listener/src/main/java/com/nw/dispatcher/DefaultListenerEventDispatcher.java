package com.nw.dispatcher;


import com.nw.definition.ListenerDefinition;

import java.util.Collection;
import java.util.List;

/**
 * @Author liuyefeng
 * @Date 2018/12/11 18:07
 */
public class DefaultListenerEventDispatcher extends AbstractListenerEventDispatcher {
    public DefaultListenerEventDispatcher(Collection<ListenerDefinition> definitions) {
        super(definitions);
    }

    @Override
    protected void doFire(Object event, List<ReceiverInvoker> invokers) {
        for (ReceiverInvoker invoker : invokers) {
            invoker.invoke(event);
        }
    }
}
