package com.nw.dispatcher;


import com.nw.definition.ListenerDefinition;

import java.util.Collection;

/**
 * @Author liuyefeng
 * @Date 2018/12/11 18:07
 */
public class DefaultListenerEventDispatcher extends AbstractListenerEventDispatcher {
    public DefaultListenerEventDispatcher(Collection<ListenerDefinition> definitions) {
        super(definitions);
    }
}
