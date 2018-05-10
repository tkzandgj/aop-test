package com.cncnc.annotation;


import java.lang.annotation.*;

@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ServiceOperationLog {

    /**
     * Service层日志操作处理
     * @return
     */
    String description() default "";
}
