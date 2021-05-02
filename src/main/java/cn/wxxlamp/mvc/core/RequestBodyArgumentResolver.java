package cn.wxxlamp.mvc.core;

import cn.wxxlamp.mvc.annotation.RequestBody;
import cn.wxxlamp.mvc.error.EnumException;
import cn.wxxlamp.mvc.error.WxxException;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.Parameter;

/**
 * @author wxxlamp
 * @date 2021/05/01~20:33
 */
public class RequestBodyArgumentResolver implements ArgumentResolver {

    @Override
    public boolean supportsParameter(Parameter parameter) {
        return parameter.isAnnotationPresent(RequestBody.class);
    }

    @Override
    public Object resolveArgument(Parameter parameter, HttpServletRequest request) {
        if (!"application/json".equals(request.getHeader("Content-Type"))) {
            throw new WxxException(EnumException.HTTP_BODY_IS_NOT_YSE);
        }
        ServletInputStream inputStream;
        try {
            inputStream = request.getInputStream();
            StringBuilder content = new StringBuilder();
            byte[] b = new byte[1024];
            int lens;
            while ((lens = inputStream.read(b)) > 0) {
                content.append(new String(b, 0, lens));
            }
            System.out.println(content.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
