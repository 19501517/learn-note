package com.nw.dispatcher;

import com.nw.definition.ListenerDefinition;
import com.nw.exception.DispatcherInitException;
import com.nw.exception.DispatcherNotInitializationException;
import com.nw.exception.EventDispatcherException;
import com.nw.utils.ListenerInvokerEnhanceUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 监听事件转发器父类
 * 这里其实可以通过不同的监听器信息存储方式，抽象出各种不同的AbstractListenerDispatcher
 * 例如使用map存储的AbstractMapListenerDispatcher
 * 为了简单，默认所有的dispatch都使用map存储
 *
 * @Author liuyefeng
 * @Date 2018/12/11 18:07
 */
public abstract class AbstractListenerEventDispatcher implements ListenerEventDispatcher, ApplicationContextAware {

    /**
     * 监听器增加删除锁
     */
    private final Object eventInvokerMonitor = new Object();
    /**
     * 原始监听器信息
     */
    private final List<ListenerDefinition> originDefinition;
    /**
     * 事件对应的监听器处理信息
     */
    private final MultiValueMap<Class<?>, ListenerInvoker> eventToInvokers;
    /**
     * 是否初始化完毕
     */
    private final AtomicBoolean init = new AtomicBoolean(false);

    private ApplicationContext springContext;

    public AbstractListenerEventDispatcher(Collection<ListenerDefinition> definitions) {
        originDefinition = new ArrayList<>(definitions);
        eventToInvokers = new LinkedMultiValueMap<>();
    }

    @PostConstruct
    private void initListenerInvoker() {
        synchronized (eventInvokerMonitor) {
            for (ListenerDefinition definition : originDefinition) {
                try {
                    Object receiverBean = springContext.getBean(definition.getReceiverClass());
                    Class<?> eventClass = definition.getEventClass();
                    ListenerInvoker invoker = ListenerInvokerEnhanceUtils.createInvoker(receiverBean, definition);
                    eventToInvokers.add(eventClass, invoker);
                } catch (Exception e) {
                    throw new DispatcherInitException(
                            "event [" + definition.getEventClass().getName() + "] init failed.", e);
                }
            }
            init.set(true);
        }
    }

    @Override
    public void fire(Object event) {
        if (!init.get()) {
            throw new DispatcherNotInitializationException();
        }
        if (event == null) {
            throw new EventDispatcherException("event is null", new NullPointerException());
        }

        List<ListenerInvoker> receiverInvokers = eventToInvokers.get(event.getClass());
        if (receiverInvokers == null) {
            throw new EventDispatcherException(String.format("event[%s] is not register.", event.getClass()));
        }

        doFire(event, receiverInvokers);
    }

    /**
     * 交给实现类实现具体的通知规则
     *
     * @param event    事件
     * @param invokers 接受者
     */
    protected abstract void doFire(Object event, List<ListenerInvoker> invokers);

    /**
     * TODO
     * 增加监听器
     *
     * @return
     */
    public boolean addListener() {
        if (!init.get()) {
            throw new DispatcherNotInitializationException();
        }
        synchronized (eventInvokerMonitor) {

        }
        return false;
    }

    /**
     * TODO
     * 移除现有的监听器
     *
     * @return
     */
    public boolean removeListener() {
        if (!init.get()) {
            throw new DispatcherNotInitializationException();
        }
        synchronized (eventInvokerMonitor) {

        }
        return true;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.springContext = applicationContext;
    }
}
