package cn.wxxlamp.mvc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 把返回值变为json <br/>
 * <b>这个注解加不加都无所谓，系统默认也只能把返回值转为json</b>
 * @author wxxlamp
 * @date 2020/05/19~22:20
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ResponseBody {
}
