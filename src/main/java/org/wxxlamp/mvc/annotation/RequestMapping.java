package org.wxxlamp.mvc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author wxxlamp
 * @date 2020/05/19~22:16
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestMapping {
    /**
     * 请求路径
     */
    String value() default "";

    /**
     * 请求方法
     */
    RequestMethod method() default RequestMethod.GET;
}
