package listenerbasedspring.schema;

import listenerbasedspring.constant.ElementNameConstants;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * @Author liuyefeng
 * @Date 2018/12/12 11:13
 */
public class ListenerNamespaceHandler extends NamespaceHandlerSupport {
    @Override
    public void init() {
        registerBeanDefinitionParser(ElementNameConstants.DISPATCHER_ELEMENT, new ListenerDispatcherParser());
    }
}
