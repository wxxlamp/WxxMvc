package cn.wxxlamp.mvc.core;

import cn.wxxlamp.mvc.annotation.RequestBody;
import cn.wxxlamp.mvc.error.EnumException;
import cn.wxxlamp.mvc.error.WxxException;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wxxlamp
 * @date 2021/05/01~20:33
 */
public class RequestBodyArgumentResolver implements ArgumentResolver {

    private static final String CONTENT_TYPE = "Content-Type";
    private static final String JSON = "application/json";
    private Map<String, Object> cacheBody;

    @Override
    public boolean supportsParameter(Parameter parameter) {
        return parameter.isAnnotationPresent(RequestBody.class);
    }

    @Override
    public Object resolveArgument(Parameter parameter, HttpServletRequest request) {
        if (!JSON.equals(request.getHeader(CONTENT_TYPE))) {
            throw new WxxException(EnumException.HTTP_BODY_IS_NOT_YSE);
        }
        if (cacheBody == null) {
            getBody(request);
        }
        return cacheBody.get(parameter.getName());
    }

    private void getBody(HttpServletRequest request) {
        try {
            ServletInputStream inputStream = request.getInputStream();
            StringBuilder content = new StringBuilder();
            byte[] b = new byte[1024];
            int lens;
            while ((lens = inputStream.read(b)) > 0) {
                content.append(new String(b, 0, lens));
            }
            cacheBody = JSONObject.parseObject(String.valueOf(content));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
