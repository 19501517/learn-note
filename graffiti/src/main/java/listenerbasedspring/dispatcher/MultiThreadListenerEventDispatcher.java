package listenerbasedspring.dispatcher;

import listenerbasedspring.definition.ListenerDefinition;

import java.util.Collection;

/**
 * @Author liuyefeng
 * @Date 2018/12/11 18:07
 */
public class MultiThreadListenerEventDispatcher extends AbstractListenerEventDispatcher {

    public MultiThreadListenerEventDispatcher(Collection<ListenerDefinition> definitions) {
        super(definitions);
    }
}
