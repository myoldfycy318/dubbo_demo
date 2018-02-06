package com.annotation;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import java.lang.annotation.*;


/**
 *
 */
@Inherited
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ApiMethod {

    /**
     * 映射的方法
     *
     * @return
     */
    String method() default "";

    /**
     * 每分钟访问限制 默认
     */
    int limitCount() default 999999999;

    /**
     *
     * 时间段，单位为毫秒，默认值一分钟
     */
    long time() default 60000;
}
