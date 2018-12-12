package listenerbasedspring.listener;

import listenerbasedspring.annotation.Listener;

/**
 * @Author liuyefeng
 * @Date 2018/12/12 17:30
 */
@Listener
public class TestListener {

    public void testListener(String params) {
        System.out.println(params);
    }
}
