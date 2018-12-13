package com.nw.listener;


import com.nw.annotation.Listener;

/**
 * @Author liuyefeng
 * @Date 2018/12/12 17:30
 */
public class TestListener {

    @Listener
    public void testListener(TestEvent params) {
        System.out.println(params);
    }
}
