package com.cncnc.serviceImpl;

import com.cncnc.annotation.ServiceOperationLog;
import com.cncnc.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author tukangzheng
 */
@Service
public class UserServiceImpl implements UserService {


    @ServiceOperationLog(description = "service add user Log.....")
    @Override
    public int addUser() {
        System.out.println("==========add user=========");
        return 0;
    }

    @Override
    public void updateUser() {
        System.out.println("==========update user=========");
    }

    @Override
    public void deleteUser() {
        System.out.println("==========delete user=========");
    }

    @Override
    public void findUser() {
        System.out.println("==========find user=========");
    }
}
