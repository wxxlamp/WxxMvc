package cn.wxxlamp.mvc.core;

import cn.wxxlamp.mvc.annotation.RequestMethod;

import java.util.Objects;

/**
 * @author wxxlamp
 * @date 2020/05/20~18:02
 */
public class RequestMappingInfo {

    private RequestMethod httpMethod;

    private String httpPath;

    public RequestMappingInfo(RequestMethod httpMethod, String httpPath) {
        this.httpMethod = httpMethod;
        this.httpPath = httpPath;
    }

    public RequestMethod getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(RequestMethod httpMethod) {
        this.httpMethod = httpMethod;
    }

    public String getHttpPath() {
        return httpPath;
    }

    public void setHttpPath(String httpPath) {
        this.httpPath = httpPath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RequestMappingInfo requestMappingInfo = (RequestMappingInfo) o;
        return Objects.equals(httpMethod, requestMappingInfo.httpMethod) &&
                Objects.equals(httpPath, requestMappingInfo.httpPath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(httpMethod, httpPath);
    }
}
