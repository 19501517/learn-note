package com.nw.listener;


import com.nw.annotation.Listener;
import org.springframework.stereotype.Component;

/**
 * @Author liuyefeng
 * @Date 2018/12/12 17:30
 */
@Listener
@Component
public class TestListener {

    public void testListener(String params) {
        System.out.println(params);
    }
}
