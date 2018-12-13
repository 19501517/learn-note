package com.nw;

import com.nw.dispatcher.ListenerEventDispatcher;
import com.nw.listener.TestEvent;
import com.nw.listener.TestListener;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Author liuyefeng
 * @Date 2018/12/12 17:42
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:ApplicationContext-test.xml"})
public class ListenerTest {

    @Autowired
    private ApplicationContext context;

    @Test
    public void testDispatcherNotNull() {
        ListenerEventDispatcher bean = context.getBean("listenerDispatcher", ListenerEventDispatcher.class);
        System.out.println(bean);
        Assert.assertNotNull(bean);
    }

    @Test
    public void testListenerIsBean() {
        TestListener bean = context.getBean(TestListener.class);
        System.out.println(bean);
        Assert.assertNotNull(bean);
    }

    @Test
    public void testEvent() {
        ListenerEventDispatcher bean = context.getBean("listenerDispatcher", ListenerEventDispatcher.class);
        bean.fire(new TestEvent());
    }
}
