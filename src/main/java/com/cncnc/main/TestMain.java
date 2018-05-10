package com.cncnc.main;


import com.cncnc.controller.UserController;
import com.cncnc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author tukangzheng
 */
public class TestMain {


    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"application.xml"});

        /*UserDao userDao = (UserDao) context.getBean("userDao");

        userDao.addUser();

        userDao.deleteUser();

        userDao.findUser();

        userDao.updateUser();*/

        UserController userController = (UserController)context.getBean("userController");

        userController.testAopOperationLog();


        UserService userService = (UserService) context.getBean("userService");

        userService.addUser();
    }
}
