package org.wxxlamp.mvc.core;

import org.wxxlamp.mvc.annotation.ResponseBody;
import org.wxxlamp.mvc.bean.ControllerInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 渲染结果
 * @author wxxlamp
 * @date 2020/05/21~10:06
 */
public class ResultRender {

    /**
     * 解析返回值，渲染response
     * @param controllerInfo {@link ControllerInfo}
     * @param result 对应方法的结果
     * @param request {@link HttpServletRequest}
     * @param response {@link HttpServletResponse}
     */
    public void resultResolver(ControllerInfo controllerInfo, Object result,
                               HttpServletRequest request, HttpServletResponse response) {
        if (result == null) {
             return;
        }
        boolean isJson = controllerInfo.getInvokeMethod().isAnnotationPresent(ResponseBody.class);
        if (isJson) {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            try (PrintWriter printWriter = response.getWriter()){
                // TODO 转为json
                printWriter.write(000);
                printWriter.flush();
            } catch (IOException e) {
                response.setStatus(404);
            }
        } else {
            // throw error
        }
    }
}
