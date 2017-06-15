package com.myfirst.service;

import com.myfirst.dao.ILoginTicketDao;
import com.myfirst.dao.IUserDao;
import com.myfirst.entitis.HosHolder;
import com.myfirst.entitis.LoginTicket;
import com.myfirst.entitis.User;
import com.myfirst.utl.GraduationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by 58 on 2017/1/17.
 */
@Service
public class UserService {
    @Autowired
    IUserDao iUserDao;
    @Autowired
    ILoginTicketDao iticketDao;
    @Autowired
    HosHolder hosHolder;

    public User findUserById(int userId) {
        return iUserDao.findUserById(userId);
    }

    public int addUser(User user) {
        //用户的密码
        return iUserDao.addUser(user);
    }

    public User findUserByName(String name) {
        return iUserDao.findUserByName(name);
    }

    //登陆
    public Map<String, Object> login(String account, String password, Map<String, Object> responeMap) {
        User user = findUserByName(account);
        if (null == user) {
            responeMap.put("error", "用户不存在！");
            return responeMap;
        }
        if (!user.getPassword().equals(GraduationUtil.MD5(password + user.getSalt()))) {
            responeMap.put("error", "用户名和密码不匹配！");
            return responeMap;
        } else {
            hosHolder.setUser(user);
            LoginTicket userLoginTicket = iticketDao.findLoginTicketByUserId(user.getId());
            if (null == userLoginTicket) {
                LoginTicket loginTicket;
                loginTicket = new LoginTicket();
                loginTicket.setExpired((short) 0);
                loginTicket.setUserId(user.getId());
                loginTicket.setStatus(0);
                String ticket = UUID.randomUUID().toString().replaceAll("-", "");
                loginTicket.setTicket(ticket);
                iticketDao.addLoginTicket(loginTicket);
                responeMap.put("ticket", loginTicket.getTicket());
            } else {
                responeMap.put("ticket", userLoginTicket.getTicket());
            }

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
    public int updatePassword(int id, String oldPassWord, String newPassWord, Map<String, Object> responseMap) {
        //先判断用户的老密码正确与否
        User user = iUserDao.findUserById(id);
        if (null == user || !user.getPassword().equals(oldPassWord)) {
            responseMap.put("error", "旧密码不正确！");
        }
        return iUserDao.updateUserPassWord(id, newPassWord);
    }

    //删除用户
    public int deleteUserById(int userId, Map<String, Object> responseMap) {
        User user = iUserDao.findUserById(userId);
        if (null == user) {
            responseMap.put("error", "用户不存在！");
            return -1;
        }
        int result = iUserDao.updateUserIsDeleteById(userId);
        return result;
    }

    //查找所有未被删除的用户
    public List<User> findAllUndeleteUser(int size, int page) {
        return iUserDao.findUserUndelete(page * size, size);
    }

    //计算所有未被删除的用户数目
    public int findUserCountUndelete() {
        return iUserDao.findUserCount();
    }
}




