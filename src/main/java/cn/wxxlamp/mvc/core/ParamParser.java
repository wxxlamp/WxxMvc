package cn.wxxlamp.mvc.core;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author wxxlamp
 * @date 2021/04/30~14:50
 */
public class ParamParser {

    /**
     * 获取请求参数
     * TODO 获取body，这个可以通过@RequestBody这个注解后再增加
     * @param request {@link HttpServletRequest}
     * @return name & value /  get.post
     */
    public Map<String, String> getRequestParams(HttpServletRequest request) {
        Map<String, String> params = new HashMap<>();
        request.getParameterMap().forEach((name, value) -> {
            params.put(name, value[0]);
        });
        return params;
    }

    /**
     * 将Controller中某一方法的参数实例化并赋值
     * @param methodParams name & type
     * @param requestParams name & value
     * @return 类型为type，值为value，名字为name的实例
     */
    public List<Object> instantiateMethodArgs(Map<String, Class<?>> methodParams, Map<String, String> requestParams) {
        return methodParams.keySet().stream().map(paramName -> {
            Class<?> type = methodParams.get(paramName);
            String value = requestParams.get(paramName);
            Object instance = null;
            // TODO
            return instance;
        }).collect(Collectors.toList());
    }
}
