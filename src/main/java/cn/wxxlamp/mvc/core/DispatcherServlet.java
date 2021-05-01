package cn.wxxlamp.mvc.core;

import cn.wxxlamp.mvc.annotation.RequestMethod;

import javax.servlet.ServletException;
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

    private RequestMappingHandler requestMappingHandler;

    @Override
    public void init() throws ServletException {
        requestMappingHandler = new RequestMappingHandler();
        super.init();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        String method = req.getMethod();
        String path = req.getPathInfo();
        String end = "/";
        if (path.endsWith(end)) {
            path = path.substring(0, path.length() - 1);
        }

        HandlerMethod handlerMethod = requestMappingHandler.getControllerInfo(RequestMethod.valueOf(method), path);

        if (handlerMethod == null) {
            noHandlerFound(resp);
            return;
        }
        try {
            Object result = invokeController(req, resp, handlerMethod);
            ResultRender resultRender = new ResultRender();
            resultRender.resultResolver(handlerMethod, result, req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void noHandlerFound(HttpServletResponse resp) throws IOException {
        resp.sendError(HttpServletResponse.SC_NOT_FOUND);
    }

    /**
     * 反射调用Controller中的方法
     * @param request {@link HttpServletRequest}
     * @param response {@link HttpServletResponse}
     * @param handlerMethod {@link HandlerMethod}
     */
    public Object invokeController(HttpServletRequest request, HttpServletResponse response, HandlerMethod handlerMethod)
            throws InvocationTargetException, IllegalAccessException, InstantiationException {

        Object controller = handlerMethod.getControllerClass().newInstance();
        Method method = handlerMethod.getInvokeMethod();
        method.setAccessible(true);

        Object result;
        result = method.invoke(controller);

        return result;
    }
}
