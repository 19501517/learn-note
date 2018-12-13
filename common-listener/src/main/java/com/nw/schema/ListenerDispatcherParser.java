package com.nw.schema;

import com.nw.annotation.Listener;
import com.nw.constant.ElementNameConstants;
import com.nw.constant.ListenerConstants;
import com.nw.definition.ListenerDefinition;
import com.nw.exception.ListenerDispatcherInitException;
import com.nw.filters.ListernerMethodFilter;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;
import org.springframework.util.SystemPropertyUtils;
import org.w3c.dom.Element;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * 监听器解析，目前只有打了{@link Listener}注解，
 * 方法只有一个参数且方法没有返回值的会被认为是监听器实现
 * 但是这个类内部预留了扩展空间，可以对方法名、方法参数进行过滤，新增对应的xml标签然后解析添加TypeFilter即可
 *
 * @Author liuyefeng
 * @Date 2018/12/12 11:24
 */
public class ListenerDispatcherParser implements BeanDefinitionParser {

    /**
     * 类级过滤，默认只有{@link Listener}注解过滤
     */
    private List<TypeFilter> classfilters = new ArrayList<>();

    /**
     * 方法级过滤器，预留扩展
     */
    private List<ListernerMethodFilter> methodfilters = new ArrayList<>();

    private ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
    private MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory(resourcePatternResolver);

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
        String[] packages = basePackage.split("[,;]");

        Map<String, ListenerDefinition> definitionMap = new HashMap<>();
        for (String packagePath : packages) {
            Collection<ListenerDefinition> definitions = doScanListenerDefinitions(packagePath);
            mergeDefinition(definitionMap, definitions);
        }

        return definitionMap.values();
    }

    private Collection<ListenerDefinition> doScanListenerDefinitions(String path) {
        String basePackage = ClassUtils.convertClassNameToResourcePath(
                SystemPropertyUtils.resolvePlaceholders(path));
        String packageToSearch = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + basePackage + "/**/*.class";
        List<ListenerDefinition> definitions = new ArrayList<>();

        try {
            Resource[] resources = resourcePatternResolver.getResources(packageToSearch);
            for (Resource resource : resources) {
                if (!resource.isReadable()) {
                    continue;
                }

                // 类校验
                MetadataReader metaReader = metadataReaderFactory.getMetadataReader(resource);
                if (!isClassMatch(metaReader, metadataReaderFactory)) {
                    continue;
                }

                Class<?> listenerClass = Class.forName(metaReader.getClassMetadata().getClassName());
                Method[] methods = listenerClass.getDeclaredMethods();
                // 方法校验
                for (Method method : methods) {
                    if (!isMethodMatch(method)) {
                        continue;
                    }
                    definitions.add(new ListenerDefinition(listenerClass, method));
                }
            }
        } catch (ClassNotFoundException e) {
            throw new ListenerDispatcherInitException("listener not found", e);
        } catch (IOException e) {
            throw new ListenerDispatcherInitException("scan listener filed.", e);
        }
        return definitions;

    }

    private boolean isClassMatch(MetadataReader metaReader, MetadataReaderFactory metadataReaderFactory)
            throws IOException {
        for (TypeFilter filter : classfilters) {
            if (!filter.match(metaReader, metadataReaderFactory)) {
                return false;
            }
        }
        return true;
    }

    private boolean isMethodMatch(Method method) {
        for (ListernerMethodFilter filter : methodfilters) {
            if (!filter.match(method)) {
                return false;
            }
        }
        return true;
    }

    private void mergeDefinition(Map<String, ListenerDefinition> definitionMap,
                                 Collection<ListenerDefinition> addedDefinitions) {
        if (addedDefinitions == null || addedDefinitions.isEmpty()) {
            return;
        }
        for (ListenerDefinition definition : addedDefinitions) {
            String key = definition.getClazz().getName() + "_" + definition.getListenerInvokeMethod().getName();
            if (definitionMap.containsKey(key)) {
                throw new ListenerDispatcherInitException("duplicated listener : " + key);
            }
            definitionMap.put(key, definition);
        }
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
