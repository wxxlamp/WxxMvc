package cn.wxxlamp.mvc.core;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Parameter;

/**
 * @author wxxlamp
 * @date 2021/05/01~10:00
 */
public interface ArgumentResolver {

    /**
     * 是否支持处理该参数
     * @param parameter 参数
     * @return true/false
     */
    boolean supportsParameter(Parameter parameter);

    /**
     * 处理该参数
     * @param parameter 参数
     * @param request 请求
     * @return 参数集
     */
    Object resolveArgument(Parameter parameter, HttpServletRequest request);
}
