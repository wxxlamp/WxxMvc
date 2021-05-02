package cn.wxxlamp.mvc.core;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wxxlamp
 * @date 2021/05/01~20:32
 */
public class ArgumentResolverComposite implements ArgumentResolver {

    private final List<ArgumentResolver> argumentResolvers = new ArrayList<>();

    private final Map<Parameter, ArgumentResolver> cache = new ConcurrentHashMap<>();

    @Override
    public boolean supportsParameter(Parameter parameter) {
        return getArgumentResolver(parameter) != null;
    }

    @Override
    public Object resolveArgument(Parameter parameter, HttpServletRequest request) {
        return getArgumentResolver(parameter).resolveArgument(parameter, request);
    }

    public ArgumentResolverComposite addResolver(ArgumentResolver argumentResolver) {
        argumentResolvers.add(argumentResolver);
        return this;
    }

    private ArgumentResolver getArgumentResolver(Parameter parameter) {
        ArgumentResolver result = cache.get(parameter);
        if (result == null) {
            for (ArgumentResolver argumentResolver : argumentResolvers) {
                if (argumentResolver.supportsParameter(parameter)) {
                    result = argumentResolver;
                    cache.put(parameter, result);
                    break;
                }
            }
        }
        return result;
    }
}
