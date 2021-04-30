package org.wxxlamp.mvc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author wxxlamp
 * @date 2020/05/21~08:49
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestParam {
    /**
     * value表示前端传来的key
     * @return key from front
     */
    String value() default "";

    boolean required() default true;
}
