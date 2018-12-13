package com.nw;

import com.nw.dispatcher.ListenerEventDispatcher;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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
    private ListenerEventDispatcher dispatcher;

    @Test
    public void testBeanNotNull() {
        System.out.println(dispatcher);
        Assert.assertNotNull(dispatcher);
    }
}
