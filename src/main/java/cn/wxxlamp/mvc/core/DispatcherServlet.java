package cn.wxxlamp.mvc.core;

import cn.wxxlamp.mvc.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author wxxlamp
 * @date 2020/05/19~22:22
 */
public class DispatcherServlet extends HttpServlet {

    private RequestMappingHandler requestMappingHandler;

    private HandlerAdapter handlerAdapter;

    private ResultRender resultRender;

    @Override
    public void init() throws ServletException {
        super.init();
        initHandler();
    }

    private void initHandler() {
        requestMappingHandler = new RequestMappingHandler();
        handlerAdapter = new HandlerAdapter();
        resultRender = new ResultRender();
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

        HandlerMethod handlerMethod = requestMappingHandler.getHandler(
                RequestMethod.valueOf(method), path);

        if (handlerMethod == null) {
            noHandlerFound(resp);
            return;
        }

        Object result = handlerAdapter.handle(req, resp, handlerMethod);
        resultRender.resultResolver(req, resp, result);

    }

    private void noHandlerFound(HttpServletResponse resp) throws IOException {
        resp.sendError(HttpServletResponse.SC_NOT_FOUND);
    }

}
