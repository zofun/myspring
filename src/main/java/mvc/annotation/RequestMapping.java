package mvc.annotation;

import bean.factory.DefaultBeanFactory;

import java.lang.annotation.*;

/**
 * @ClassName : RequestMapping
 * @Author : TCW
 * @Date: 2020-02-18 11:02
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestMapping {

    String name() default "";

    String[] value() default "";

    String[] path() default {};

    RequestMethod[] method() default {};

    String[] params() default {};

    String[] headers() default {};

    String[] consumes() default {};

    String[] produces() default {};


}
