package com.myfirst.service;

import com.myfirst.dao.ILoginTicketDao;
import com.myfirst.dao.IUserDao;
import com.myfirst.entitis.LoginTicket;
import com.myfirst.entitis.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

/**
 * Created by 58 on 2017/1/17.
 * author yun zhi fei
 */
@Service
public class UserService {
    @Autowired
    IUserDao iUserDao;
    @Autowired
    ILoginTicketDao iticketDao;

    public User findUserById(int userId) {
        return iUserDao.findUserById(userId);
    }

    public int addUser(User user) {
        return iUserDao.addUser(user);
    }

    public User findUserByName(String name) {
        return iUserDao.findUserByName(name);
    }

    //登陆
    public Map<String, String> login(String account, String password, Map<String, String> responeMap) {
        User user = findUserByName(account);
        if (null == user) {
            responeMap.put("error", "用户不存在！");
        }
        if (!user.getPassword().equals(password)) {
            responeMap.put("error", "用户名和密码不匹配！");
        } else {
            LoginTicket loginTicket;
            loginTicket = new LoginTicket();
            loginTicket.setExpired((short) 0);
            loginTicket.setUserId(user.getId());
            loginTicket.setStatus(0);
            String ticket = UUID.randomUUID().toString().replaceAll("-", "");
            loginTicket.setTicket(ticket);
            iticketDao.addLoginTicket(loginTicket);
            responeMap.put("ticket", loginTicket.getTicket());
        }
        return responeMap;
    }

    //注销
    public int logout(String ticket) {
        return iticketDao.deleteTicket(ticket);
    }

    //修改用户信息
    public int updateUserInfo(User user) {
        return iUserDao.updateUserInfo(user);
    }

    //修改密码
    public int updatePassword(int id, String oldPassWord, String newPassWord) {
        //先判断用户的老密码正确与否
        User user = iUserDao.findUserById(id);
        if (null == user || !user.getPassword().equals(oldPassWord)) {
            return -1;
        }
        return iUserDao.updateUserPassWord(id, newPassWord);
    }
}
