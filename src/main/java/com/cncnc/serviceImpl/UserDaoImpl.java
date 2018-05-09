package com.cncnc.serviceImpl;

import com.cncnc.service.UserDao;
import org.springframework.stereotype.Repository;

/**
 * @author tukangzheng
 */
@Repository
public class UserDaoImpl implements UserDao {

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
