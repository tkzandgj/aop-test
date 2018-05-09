package com.cncnc.config;


import com.cncnc.bean.MonitorTime;
import com.cncnc.utils.DateUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.util.Date;

@Aspect
public class TimerAspect {

    @Pointcut("execution(* com.cncnc.controller.UserController.testAopTime(..))")
    void timer(){}

    @Around("timer()")
    public Object logTimer(ProceedingJoinPoint thisJoinPoint) throws Throwable{
        MonitorTime monitorTime = new MonitorTime();

        String clazzName = thisJoinPoint.getTarget().getClass().getName();
        String methodName = thisJoinPoint.getSignature().getName();

        monitorTime.setClassName(clazzName);
        monitorTime.setMethodName(methodName);
        monitorTime.setLongTime(DateUtil.formatDate(new Date()));

        long startTime = System.currentTimeMillis();
        Object result = thisJoinPoint.proceed();
        long endTime = System.currentTimeMillis() - startTime;

        monitorTime.setConsumerTime(endTime);

        System.out.println(monitorTime);
        return result;
    }
}
