package springschemalistener.schema;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;
import springschemalistener.ListenerDispatcherInitException;
import springschemalistener.annotation.Listener;
import springschemalistener.constant.ElementNameConstants;
import springschemalistener.constant.ListenerConstants;
import springschemalistener.definition.ListenerDefinition;

import java.util.*;

/**
 * 监听器解析，目前只有打了{@link springschemalistener.annotation.Listener}注解，
 * 方法只有一个参数且方法没有返回值的会被认为是监听器实现
 * 但是这个类内部预留了扩展空间，可以对方法名、方法参数进行过滤，新增对应的xml标签然后解析添加TypeFilter即可
 *
 * @Author liuyefeng
 * @Date 2018/12/12 11:24
 */
public class ListenerDispatcherParser implements BeanDefinitionParser {

    /**
     * 类级过滤，默认只有{@link springschemalistener.annotation.Listener}注解过滤
     */
    private List<TypeFilter> classfilters = new ArrayList<>();

    /**
     * 方法级过滤器，预留扩展
     */
    private List<TypeFilter> methodfilters = new ArrayList<>();

    @Override
    public BeanDefinition parse(Element element, ParserContext parserContext) {
        String activeListenerEventDispatcher = element.getAttribute(ElementNameConstants.CLASS_ATTRIBUTE);
        if (!StringUtils.hasText(activeListenerEventDispatcher)) {
            activeListenerEventDispatcher = ListenerConstants.DEFAULT_LISTENER_DISPATCHER;
        }

        String id = element.getAttribute(ElementNameConstants.ID_ATTRIBUTE);
        if (!StringUtils.hasText(id)) {
            id = activeListenerEventDispatcher;
        }

        // 构建dispatcherBeanDefinition
        Class<?> dispatcherClazz;
        try {
            dispatcherClazz = Class.forName(activeListenerEventDispatcher);
        } catch (ClassNotFoundException e) {
            throw new ListenerDispatcherInitException("not exist dispatcher " + activeListenerEventDispatcher, e);
        }

        // 扫描并获取所有的监听器实现
        Collection<ListenerDefinition> listenerDefinitions = scanListenerDefinitions(element, parserContext);

        // 定义Dispatcher的构造方法及参数，注册BeanDefinition到IOC容器
        BeanDefinitionBuilder eventDispatcherBeanDefinitionBuilder =
                BeanDefinitionBuilder.rootBeanDefinition(dispatcherClazz);
        eventDispatcherBeanDefinitionBuilder.addConstructorArgValue(listenerDefinitions);
        BeanDefinition eventDispatcherBeanDefinition = eventDispatcherBeanDefinitionBuilder.getBeanDefinition();
        parserContext.getRegistry().registerBeanDefinition(id, eventDispatcherBeanDefinition);

        return eventDispatcherBeanDefinition;
    }

    /**
     * 扫描路径下所有符合要求的监听器，对该类进行spring的bean注册
     * 并生成其对应的ListenerDefinition
     *
     * @param element
     * @param parserContext
     * @return
     */
    private Collection<ListenerDefinition> scanListenerDefinitions(Element element, ParserContext parserContext) {
        String basePackage = element.getAttribute(ElementNameConstants.BASE_PACKAGE_ATTRIBUTE);
        if (!StringUtils.hasText(basePackage)) {
            throw new ListenerDispatcherInitException("illegal base package : " + basePackage,
                    new IllegalArgumentException("illegal base package"));
        }

        initFilters(element);
        String[] packages = basePackage.split(",");
        Map<String, ListenerDefinition> definitionMap = new HashMap<>();

        for (String packagePath : packages) {
            Collection<ListenerDefinition> definitions = doScanListenerDefinitions(packagePath);
        }

        return definitionMap.values();
    }

    private Collection<ListenerDefinition> doScanListenerDefinitions(String path) {
        return null;
    }

    private void mergeDefinition(Map<String, ListenerDefinition> definitionMap,
                                 Collection<ListenerDefinition> addedDefinitions) {

    }

    /**
     * 初始化过滤器
     *
     * @param element xml dom
     */
    private void initFilters(Element element) {
        classfilters.add(new AnnotationTypeFilter(Listener.class));
    }
}
