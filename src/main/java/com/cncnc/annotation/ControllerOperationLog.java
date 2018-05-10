package com.cncnc.annotation;


import java.lang.annotation.*;


@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ControllerOperationLog {

    /**
     * Controller层日志操作处理
     * @return
     */
    String description() default "";
}
