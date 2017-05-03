package com.myfirst.controllers;

import com.alibaba.fastjson.JSON;
import com.myfirst.entitis.User;
import com.myfirst.service.UserService;
import com.sun.deploy.net.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by 58 on 2017/2/8.
 * author yun zhi fei
 */
@Controller
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/login", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String loginMethod(@RequestParam String account, @RequestParam String password, @RequestParam int remember) {
        String reuslt = "";
        Map<String, String> responeMap = new HashMap<String, String>();
        if (account.isEmpty()) {
            responeMap.put("error", "用户名不可以为空！");
        }
        if (password.isEmpty() || password.length() < 8) {
            responeMap.put("error", "账户密码长度不足！至少8位！");
        }

        return JSON.toJSONString(responeMap);
    }

    @RequestMapping(value = "/reg", method = {RequestMethod.POST})
    @ResponseBody
    public String regUser(@Valid User user, BindingResult result, Model model) {
        //分配userId;
        long userid = System.currentTimeMillis() * 10 + user.getUserType();
        user.setUserId(userid);
        StringBuffer salt = new StringBuffer("");
        for (int i = 0; i < 10; i++) {
            salt.append(String.valueOf(48 + new Random().nextInt(10)));
        }
        user.setSalt(salt.toString());
        int useid = 0;
        try {
            useid = userService.addUser(user);
        } catch (Exception e) {

        }
        System.out.println("useid = " + useid);

        return "成功";
    }
}
