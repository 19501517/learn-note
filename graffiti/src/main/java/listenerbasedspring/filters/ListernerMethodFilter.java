package listenerbasedspring.filters;

import java.lang.reflect.Method;

/**
 * @Author liuyefeng
 * @Date 2018/12/12 16:45
 */
public interface ListernerMethodFilter {
    boolean match(Method method);
}
