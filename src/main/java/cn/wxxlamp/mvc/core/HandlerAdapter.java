package cn.wxxlamp.mvc.core;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author wxxlamp
 * @date 2021/05/01~09:57
 */
public class HandlerAdapter {

    private HandlerMethod handlerMethod;

    private final ArgumentResolverComposite argumentResolvers = new ArgumentResolverComposite();

    public Object handle(HttpServletRequest request, HttpServletResponse response, HandlerMethod handlerMethod) {
        Object result = null;
        try {
            Object controller = handlerMethod.getControllerClass().newInstance();
            Method method = handlerMethod.getInvokeMethod();
            Object[] args = handlerMethod.getMethodArgumentValues(request, init(argumentResolvers));
            method.setAccessible(true);
            result = method.invoke(controller, args);
        } catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
            e.printStackTrace();
        }
        return result;
    }

    private ArgumentResolver init(ArgumentResolverComposite argumentResolvers) {
        argumentResolvers.addResolver(new RequestBodyArgumentResolver());
        return argumentResolvers;
    }
}
