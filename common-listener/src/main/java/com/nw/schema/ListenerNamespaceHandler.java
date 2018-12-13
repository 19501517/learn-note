package com.nw.schema;

import com.nw.constant.ElementNameConstants;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * listener namespace 处理器
 *
 * @Author liuyefeng
 * @Date 2018/12/12 11:13
 */
public class ListenerNamespaceHandler extends NamespaceHandlerSupport {
    @Override
    public void init() {
        registerBeanDefinitionParser(ElementNameConstants.DISPATCHER_ELEMENT, new ListenerDispatcherParser());
    }
}
