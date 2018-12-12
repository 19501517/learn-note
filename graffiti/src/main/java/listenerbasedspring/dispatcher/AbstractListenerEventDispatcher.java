package listenerbasedspring.dispatcher;

import listenerbasedspring.definition.ListenerDefinition;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Collection;

/**
 * @Author liuyefeng
 * @Date 2018/12/11 18:07
 */
public abstract class AbstractListenerEventDispatcher implements ListenerEventDiapatcher, ApplicationContextAware {

    private ApplicationContext springContext;

    // 这里其实可以通过不同的监听器信息存储方式，抽象出各种不同的AbstractListenerDispatcher
    // 例如使用map存储的AbstractMapListenerDispatcher
    // 为了简单，默认所有的dispatch都使用map存储

    public AbstractListenerEventDispatcher(Collection<ListenerDefinition> definitions) {
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.springContext = applicationContext;
    }

    public ApplicationContext getContext() {
        return this.springContext;
    }
}
