package annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 功能描述： 路由参数配置注解
 * 
 * @date 2012-5-21 上午11:35:54
 * sample:
 * @Transactional(paramIndex = 1)
    @RouteParam(clazz = String.class, field = DbInstanceId.DB_INSTANCE_ID)
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RouteParam {
    /** 拦截分库参数的类型 */
    Class<?> clazz() default String.class;

    /** 分库参数名称 */
    String field();
    
    /** 分库参数是否数组，如果数组，取index()位置做为分库参数 */
    boolean isArray() default false;

    /** 数组时，取第index位置的序号 */
    int index() default 0;

}
