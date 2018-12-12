package listenerbasedspring;

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
@ContextConfiguration(locations = {"classpath:listenerbasedspring/ApplicationContext-test.xml"})
public class ListenerTest {

    @Autowired
    private ApplicationContext context;

    @Test
    public void testBeanNotNull() {
        Object bean = context.getBean("listenerDispatcher");
        System.out.println(bean);
        Assert.assertNotNull(bean);
    }
}
