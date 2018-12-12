package listenerbasedspring.definition;

import java.lang.reflect.Method;

/**
 * 监听器相关定义信息
 *
 * @Author liuyefeng
 * @Date 2018/12/11 18:28
 */
public class ListenerDefinition {

    private Class<?> clazz;

    private Method listenerInvokeMethod;

    public ListenerDefinition(Class<?> clazz, Method listenerInvokeMethod) {
        this.clazz = clazz;
        this.listenerInvokeMethod = listenerInvokeMethod;
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public void setClazz(Class<?> clazz) {
        this.clazz = clazz;
    }

    public Method getListenerInvokeMethod() {
        return listenerInvokeMethod;
    }

    public void setListenerInvokeMethod(Method listenerInvokeMethod) {
        this.listenerInvokeMethod = listenerInvokeMethod;
    }
}
