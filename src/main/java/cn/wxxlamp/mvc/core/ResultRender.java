package cn.wxxlamp.mvc.core;

import com.alibaba.fastjson.JSON;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 渲染结果
 * @author wxxlamp
 * @date 2020/05/21~10:06
 */
public class ResultRender {

    /**
     * 解析返回值，渲染response
     * @param handlerMethod {@link HandlerMethod}
     * @param result 对应方法的结果
     * @param request {@link HttpServletRequest}
     * @param response {@link HttpServletResponse}
     */
    public void resultResolver(HandlerMethod handlerMethod, Object result,
                               HttpServletRequest request, HttpServletResponse response) {
        if (result == null) {
             return;
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try (PrintWriter printWriter = response.getWriter()){
            printWriter.write(JSON.toJSONString(result));
            printWriter.flush();
        } catch (IOException e) {
            response.setStatus(404);
        }

    }
}
