package com.britecloud.marketingcloud.console.configuration;

import java.lang.annotation.*;

/**
 * 自定义日志注解类
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OperationLogAnn {
    String value() default "";
}
