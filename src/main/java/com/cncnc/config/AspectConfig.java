package com.cncnc.config;


import org.aspectj.lang.annotation.*;

/**
 * @author tukangzheng
 */
@Aspect
public class AspectConfig {

    //@Before("execution(* com.cncnc.service.UserDao.addUser(..))")

    //@Before("within(com.cncnc.service.UserDao+)")

    //@Before("within(com.cncnc.service.UserDao..*)")
    //@Before("within(com.cncnc.service.UserDao+)")

    /**
     * 匹配com.cncnc.serviceImpl这个包下的所有类中的所有方法   @Before("within(com.cncnc.serviceImpl..*)")
     * 匹配UserDaoImpl类中所有方法   @Before("within(com.cncnc.serviceImpl.UserDaoImpl)")
     * 匹配UserDaoImpl类及其子类中所有方法   @Before("within(com.cncnc.serviceImpl.UserDaoImpl+)")
     * 匹配所有实现UserDao接口的类的所有方法  @Before("within(com.cncnc.service.UserDao+)")
     */
    @Before("within(com.cncnc.service.UserService+)")
    public void before(){
        System.out.println("-----------前置通知-----------");
    }



    @AfterReturning(value = "execution(* com.cncnc.service.UserService.addUser(..))", returning = "returnValue")
    public void afterReturning(Object returnValue){
        System.out.println("----------后置通知---------" + returnValue);
    }



    @AfterThrowing(value = "execution(* com.cncnc.service.UserService.addUser(..))", throwing = "e")
    public void afterThrowable(Throwable e){
        System.out.println("---------出现异常--------" + e.getMessage());
    }



    @After("execution(* com.cncnc.service.UserService.addUser(..))")
    public void after(){
        System.out.println("-----------最终通知----------");
    }


    @Pointcut("execution(* com.cncnc.service.UserService.addUser(..))")
    private void myPonitCut(){}


    @After(value = "myPonitCut()")
    public void afterDemo(){
        System.out.println("-----------最终通知的Pointcut写法----------");
    }
}
