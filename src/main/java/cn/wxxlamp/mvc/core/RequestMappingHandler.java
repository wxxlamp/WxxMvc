package cn.wxxlamp.mvc.core;

import cn.wxxlamp.mvc.annotation.RequestMapping;
import cn.wxxlamp.mvc.error.EnumException;
import cn.wxxlamp.mvc.error.WxxException;
import cn.wxxlamp.mvc.util.ClazzUtils;
import cn.wxxlamp.mvc.annotation.RequestMethod;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wxxlamp
 * @date 2020/05/20~18:04
 */
public class RequestMappingHandler {

    private final Map<RequestMappingInfo, HandlerMethod> requestMap = new HashMap<>();

    private boolean clazzHasAnnotation;

    private boolean methodHasAnnotation;

    /**
     * 初始化时扫描含有{@link cn.wxxlamp.mvc.annotation.RequestMapping}的类和方法
     */
    public RequestMappingHandler() {
        // 包名暂时写死
        ClazzUtils.getClazzName("cn.wxxlamp.mvc.controller", false)
                .forEach(e -> {
                    try {
                        Class<?> clazz = Class.forName(e);
                        initRequestMapping(clazz);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                });
    }

    private void initRequestMapping(Class<?> clazz) {
        if (isHandler(clazz)) {
            registerRequestMapping(clazz);
        }
    }

    private boolean isHandler(Class<?> clazz) {
        if (!(this.clazzHasAnnotation = clazz.isAnnotationPresent(RequestMapping.class))) {
            return false;
        }
        for (Method method : clazz.getMethods()) {
            if (methodHasAnnotation = method.isAnnotationPresent(RequestMapping.class)) {
                return true;
            }
        }
        if (!methodHasAnnotation) {
            throw new WxxException(EnumException.METHOD_NEEDS_ANNOTATION);
        }
        return true;
    }

    private void registerRequestMapping(Class<?> clazz) {
        String basePath = null;
        if (clazzHasAnnotation) {
            basePath = clazz.getAnnotation(RequestMapping.class).value();
        }
        for (Method method : clazz.getMethods()) {
            if (method.isAnnotationPresent(RequestMapping.class)) {
                // 获得方法上RequestMapping的注解, 生成pathInfo
                RequestMapping methodMapping = method.getAnnotation(RequestMapping.class);
                String path = methodMapping.value();
                RequestMethod requestMethod = methodMapping.method();

                RequestMappingInfo requestMappingInfo = new RequestMappingInfo(requestMethod, basePath + path);

                if (requestMap.containsKey(requestMappingInfo)) {
                    throw new WxxException(EnumException.URL_REPEAT);
                }
                requestMap.put(requestMappingInfo, new HandlerMethod(clazz, method));
            }
        }
    }

    public HandlerMethod getControllerInfo(RequestMethod method, String path) {
        RequestMappingInfo requestMappingInfo = new RequestMappingInfo(method, path);
        return requestMap.get(requestMappingInfo);
    }

    public boolean hasMapping() {
        return !requestMap.isEmpty();
    }
}
