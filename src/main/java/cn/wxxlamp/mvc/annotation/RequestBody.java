package cn.wxxlamp.mvc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 当请求体为json格式时，该注解会把json映射到方法参数上
 * 映射的参数可以是实体类（此时注解需要标识在参数上），也可以是多个String类型的key（此时注解需要标识到方法上）
 * @author wxxlamp
 * @date 2021/04/30~14:42
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestBody {
}
