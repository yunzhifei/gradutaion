package com.myfirst.service;

import com.myfirst.dao.IUserDao;
import com.myfirst.entitis.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 58 on 2017/1/17.
 * author yunzhifei
 */
@Service
public class UserService {
    @Autowired
    private IUserDao userDao;

    public User findUserById(int userId) {
        return userDao.findUserById(userId);
    }
}