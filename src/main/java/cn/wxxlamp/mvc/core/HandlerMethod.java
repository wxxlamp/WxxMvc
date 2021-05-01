package cn.wxxlamp.mvc.core;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Map;

/**
 * 存储Controller的信息
 * @author wxxlamp
 * @date 2020/05/19~22:56
 */
public class HandlerMethod {
    /**
     * controller类
     */
    private Class<?> controllerClass;

    /**
     * 执行的方法
     */
    private Method invokeMethod;

    private Map<Class<?>, String> params;

    public HandlerMethod(Class<?> controllerClass, Method invokeMethod) {
        this.controllerClass = controllerClass;
        this.invokeMethod = invokeMethod;
    }

    public Class<?> getControllerClass() {
        return controllerClass;
    }

    public void setControllerClass(Class<?> controllerClass) {
        this.controllerClass = controllerClass;
    }

    public Method getInvokeMethod() {
        return invokeMethod;
    }

    public void setInvokeMethod(Method invokeMethod) {
        this.invokeMethod = invokeMethod;
    }
}
