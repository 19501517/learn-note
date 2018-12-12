package springschemalistener.schema;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;
import springschemalistener.constant.ElementNameConstants;

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
