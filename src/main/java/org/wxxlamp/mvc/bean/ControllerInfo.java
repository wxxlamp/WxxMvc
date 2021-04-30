package org.wxxlamp.mvc.bean;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * 存储Controller的信息
 * @author wxxlamp
 * @date 2020/05/19~22:56
 */
public class ControllerInfo {
    /**
     * controller类
     */
    private Class<?> controllerClass;

    /**
     * 执行的方法
     */
    private Method invokeMethod;

    /**
     * 方法参数别名对应参数类型
     */
    private Map<String, Class<?>> methodParameter;

    public ControllerInfo(Class<?> controllerClass, Method invokeMethod, Map<String, Class<?>> methodParameter) {
        this.controllerClass = controllerClass;
        this.invokeMethod = invokeMethod;
        this.methodParameter = methodParameter;
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

    public Map<String, Class<?>> getMethodParameter() {
        return methodParameter;
    }

    public void setMethodParameter(Map<String, Class<?>> methodParameter) {
        this.methodParameter = methodParameter;
    }
}
