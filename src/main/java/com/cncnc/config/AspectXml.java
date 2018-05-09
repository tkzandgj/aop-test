package com.cncnc.config;

public class AspectXml {

    public void before(){
        System.out.println("---------AspectXml 前置通知-----------");
    }


    public void afterReturn(Object returnValue){
        System.out.println("---------AspectXml 后置通知------------" + returnValue);
    }

    public void afterThrowing(Throwable throwable){
        System.out.println("---------AspectXml 异常通知------------" + throwable.getMessage());
    }


    public void after(){
        System.out.println("---------AspectXml 最终通知------------");
    }
}
