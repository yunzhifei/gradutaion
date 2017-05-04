package com.myfirst.service;

import com.myfirst.dao.ILoginTicketDao;
import com.myfirst.dao.IUserDao;
import com.myfirst.entitis.LoginTicket;
import com.myfirst.entitis.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

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
            loginTicket.setUserId(user.getUserId());
            loginTicket.setStatus(0);
            // TODO: 2017/5/3 ticket产生算法
            String ticket = "";
            loginTicket.setTicket(ticket);
            iticketDao.addLoginTicket(loginTicket);
            responeMap.put("ticket", loginTicket.getTicket());
        }
        return responeMap;
    }
}
