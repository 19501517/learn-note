package com.nw.dispatcher;


import com.nw.definition.ListenerDefinition;

import java.util.Collection;
import java.util.List;

/**
 * @Author liuyefeng
 * @Date 2018/12/11 18:07
 */
public class MultiThreadListenerEventDispatcher extends AbstractListenerEventDispatcher {

    public MultiThreadListenerEventDispatcher(Collection<ListenerDefinition> definitions) {
        super(definitions);
    }

    @Override
    protected void doFire(Object event, List<ReceiverInvoker> invokers) {

    }
}
