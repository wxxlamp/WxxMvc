package org.wxxlamp.mvc.core;

import org.wxxlamp.mvc.annotation.RequestMethod;
import org.wxxlamp.mvc.bean.ControllerInfo;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * @author wxxlamp
 * @date 2020/05/19~22:22
 */
public class DispatcherServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        String method = req.getMethod();
        String path = req.getPathInfo();
        String end = "/";
        if (path.endsWith(end)) {
            path = path.substring(0, path.length() - 1);
        }

        ControllerHandler controllerHandler = new ControllerHandler();
        ControllerInfo controllerInfo = controllerHandler.getControllerInfo(RequestMethod.valueOf(method), path);

        try {
            invokeController(req, resp, controllerInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 反射调用Controller中的方法
     * @param request {@link HttpServletRequest}
     * @param response {@link HttpServletResponse}
     * @param controllerInfo {@link ControllerInfo}
     */
    public void invokeController(HttpServletRequest request, HttpServletResponse response, ControllerInfo controllerInfo)
            throws InvocationTargetException, IllegalAccessException, InstantiationException {
        ParamParser parser = new ParamParser();
        Map<String, String> requestParams = parser.getRequestParams(request);
        List<Object> instances = parser.instantiateMethodArgs(controllerInfo.getMethodParameter(), requestParams);

        Object controller = controllerInfo.getControllerClass().newInstance();
        Method method = controllerInfo.getInvokeMethod();
        method.setAccessible(true);

        Object result;
        if (instances.size() == 0) {
            result = method.invoke(controller);
        } else {
            result = method.invoke(controller, instances);
        }

        ResultRender resultRender = new ResultRender();
        resultRender.resultResolver(controllerInfo, result, request, response);
    }
}
