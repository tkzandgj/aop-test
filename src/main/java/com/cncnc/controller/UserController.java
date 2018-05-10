package com.cncnc.controller;

import com.cncnc.annotation.ControllerOperationLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);



    public String testAopTime(){
        try {
            Thread.sleep(10000);
            return "this is test spring aop";
        } catch (InterruptedException e){
            logger.error("exception is :{}", e);
        }

        return "test spring aop end!";
    }


    @ControllerOperationLog(description = "拦截controller的Log信息")
    public String testAopOperationLog(){
        return "test aop operation Log";
    }
}
