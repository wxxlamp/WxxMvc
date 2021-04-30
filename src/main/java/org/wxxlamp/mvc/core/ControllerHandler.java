package org.wxxlamp.mvc.core;

import org.wxxlamp.mvc.annotation.RequestMapping;
import org.wxxlamp.mvc.annotation.RequestMethod;
import org.wxxlamp.mvc.annotation.RequestParam;
import org.wxxlamp.mvc.bean.ControllerInfo;
import org.wxxlamp.mvc.bean.PathInfo;
import org.wxxlamp.mvc.error.EnumException;
import org.wxxlamp.mvc.error.WxxException;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;

/**
 * TODO 此处需要getBean
 * @author wxxlamp
 * @date 2020/05/20~18:04
 */
public class ControllerHandler {

    private final Map<PathInfo, ControllerInfo> pathController = new HashMap<>();

    /**
     * TODO 待修改
     * 初始化时扫描含有{@link RequestMapping}的注解
     */
    public ControllerHandler() {
        try {
            Class<?> calzz = Class.forName("package");
            putControllerInfo(calzz);
        } catch (ClassNotFoundException | WxxException e) {
            e.printStackTrace();
        }
    }

    private void putControllerInfo(Class<?> clazz) throws WxxException {
        // 获得类上的RequestMapping注解
        RequestMapping requestMapping = clazz.getAnnotation(RequestMapping.class);
        String basePath = requestMapping.value();
        Method[] controllerMethods = clazz.getMethods();

        for (Method method : controllerMethods) {
            if (method.isAnnotationPresent(RequestMapping.class)) {
                // 获得方法上RequestMapping的注解, 生成pathInfo
                RequestMapping methodMapping = method.getAnnotation(RequestMapping.class);
                String path = methodMapping.value();
                RequestMethod requestMethod = methodMapping.method();

                PathInfo pathInfo = new PathInfo(requestMethod, basePath + path);

                if (pathController.containsKey(pathInfo)) {
                    throw new WxxException(EnumException.URL_REPEAT);
                }

                Parameter[] parameters = method.getParameters();
                Map<String, Class<?>> params = new HashMap<>(parameters.length);
                for (Parameter parameter : parameters) {
                    RequestParam requestParam;
                    // 对应注解的值和参数的类型
                    if ((requestParam = method.getAnnotation(RequestParam.class)) == null) {
                        throw new WxxException(EnumException.REQUEST_PARAM_ANNOTATION_IS_NULL);
                    }
                    params.put(requestParam.value(), parameter.getType());
                }

                // 生成controllerInfo
                ControllerInfo controllerInfo = new ControllerInfo(clazz, method, params);

                pathController.put(pathInfo, controllerInfo);
            }
        }
    }

    public ControllerInfo getControllerInfo(RequestMethod method, String path) {
        PathInfo pathInfo = new PathInfo(method, path);
        return pathController.get(pathInfo);
    }
}
