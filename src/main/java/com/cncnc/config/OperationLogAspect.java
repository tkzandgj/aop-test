package com.cncnc.config;


import com.cncnc.annotation.ControllerOperationLog;
import com.cncnc.annotation.ServiceOperationLog;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author tukangzheng
 */
@Aspect
@Component
public class OperationLogAspect {

    private static final Logger logger = LoggerFactory.getLogger(OperationLogAspect.class);


    @Pointcut("@annotation(com.cncnc.annotation.ControllerOperationLog)")
    public void controllerAspect(){}


    @Pointcut("@annotation(com.cncnc.annotation.ServiceOperationLog)")
    public void serviceAspect(){}


    /**
     * 前置通知，用于拦截Controller层记录用户的操作
     */
    @Before("controllerAspect()")
    public void before(JoinPoint joinPoint) throws ClassNotFoundException{
        String controllerDescription = getControllerMethodDescription(joinPoint);
        System.out.println("------------controller层的方法描述信息为------------" + controllerDescription);
        System.out.println("------------controller层的前置通知-----------");
    }


    /**
     * 异常通知，用于拦截Service层记录日志操作
     * @param joinPoint
     * @param e
     */
    @AfterThrowing(pointcut = "serviceAspect()", throwing = "e")
    public void afterThrowing(JoinPoint joinPoint, Throwable e) throws ClassNotFoundException{
        String serviceDescription = getServiceMethodDescription(joinPoint);
        System.out.println("------------service层的异常描述信息为------------" + serviceDescription);
        System.out.println("------------service层的异常通知--------------");
    }


    /**
     * service层的最终通知
     * @param joinPoint
     */
    @After("serviceAspect()")
    public void after(JoinPoint joinPoint) throws ClassNotFoundException {
        String serviceDescription = getServiceMethodDescription(joinPoint);
        System.out.println("------------service层的最终通知信息为------------" + serviceDescription);
        System.out.println("------------service层的最终通知--------------");
    }


    /**
     * 获取注解中对方法的描述信息，用于Service注解
     * @param joinPoint
     * @return
     * @throws ClassNotFoundException
     */
    public static String getServiceMethodDescription(JoinPoint joinPoint) throws ClassNotFoundException{
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();

        String description = "";
        for (Method method : methods){
            if (method.getName().equals(methodName)){
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length){
                    description = method.getAnnotation(ServiceOperationLog.class).description();
                    break;
                }
            }
        }
        return description;
    }


    /**
     * 获取注解中对方法的描述信息，用于Controller注解
     * @param joinPoint
     * @return
     * @throws ClassNotFoundException
     */
    public static String getControllerMethodDescription(JoinPoint joinPoint) throws ClassNotFoundException{
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();

        String description = "";
        for (Method method : methods){
            if (method.getName().equals(methodName)){
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length){
                    description = method.getAnnotation(ControllerOperationLog.class).description();
                    break;
                }
            }
        }
        return description;
    }
}
