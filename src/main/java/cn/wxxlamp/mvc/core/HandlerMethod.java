package cn.wxxlamp.mvc.core;

import cn.wxxlamp.mvc.error.EnumException;
import cn.wxxlamp.mvc.error.WxxException;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * 存储Controller的信息
 * @author wxxlamp
 * @date 2020/05/19~22:56
 */
public class HandlerMethod {
    /**
     * 类
     */
    private Class<?> controllerClass;

    /**
     * 执行的方法
     */
    private Method invokeMethod;

    /**
     * 方法参数
     */
    private Parameter[] parameters;

    public HandlerMethod(Class<?> controllerClass, Method invokeMethod) {
        this.controllerClass = controllerClass;
        this.invokeMethod = invokeMethod;
        this.parameters = invokeMethod.getParameters();
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

    public Parameter[] getParameters() {
        return parameters;
    }

    public void setParameters(Parameter[] parameters) {
        this.parameters = parameters;
    }

    public Object[] getMethodArgumentValues(HttpServletRequest request, ArgumentResolver argumentResolvers) {
        Parameter[] parameters = getParameters();
        Object[] args = new Object[parameters.length];
        for (int i = 0; i < parameters.length; i ++) {
            Parameter parameter = parameters[i];
            if (argumentResolvers.supportsParameter(parameter)) {
                args[i] = argumentResolvers.resolveArgument(parameter, request);
            } else {
                throw new WxxException(EnumException.NO_SUITABLE_RESOLVER_FOR_PARAMETER);
            }
        }
        return args;
    }
}
