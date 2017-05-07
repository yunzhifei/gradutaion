package com.myfirst.controllers;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.myfirst.entitis.User;
import com.myfirst.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
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
    public String loginMethod(@RequestParam String account, @RequestParam String password, @RequestParam String callback, HttpServletResponse httpServletResponse) {
        Map<String, Object> responeMap = new HashMap<String, Object>();
        if (account.isEmpty()) {
            responeMap.put("error", "用户名不可以为空！");
        }
        if (password.isEmpty() || password.length() < 3) {
            responeMap.put("error", "账户密码长度不足！至少3位！");
        }
        userService.login(account, password, responeMap);
        //登陆成功
        if (responeMap.containsKey("ticket")) {
            if (responeMap.containsKey("ticket")) {
                responeMap.put("ticket", responeMap.get("ticket"));
            }
            responeMap.put("logined", true);
        } else {
            responeMap.put("logined", false);
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("model", JSON.toJSON(responeMap));
        jsonObject.put("tip", "成功");
        jsonObject.put("success", "true");
        jsonObject.put("apiName", "account");
        String result = callback + " (' " + jsonObject.toJSONString() + " ') ";
        return result;
    }

    @RequestMapping(value = "/reg", method = {RequestMethod.POST})
    @ResponseBody
    public String regUser(@Valid User user, BindingResult result, Model model) {
        StringBuffer salt = new StringBuffer("");
        for (int i = 0; i < 10; i++) {
            salt.append(String.valueOf(48 + new Random().nextInt(10)));
        }
        user.setSalt(salt.toString());
        int useId = 0;
        try {
            useId = userService.addUser(user);
        } catch (Exception e) {

        }
        System.out.println("useid = " + useId);

        return "success";
    }

    @RequestMapping(value = "/logout", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String logout(@RequestParam String callback, @RequestParam String ticket, HttpServletResponse httpServletResponse) {
        Map<String, Object> responseMap = new HashMap<String, Object>();
        JSONObject resultObject = new JSONObject();
        userService.logout(ticket);
        responseMap.put("logined", false);
        Cookie cookie = new Cookie("ticket", ticket);
        cookie.setMaxAge(0);
        httpServletResponse.addCookie(cookie);
        resultObject.put("tip", "注销成功!");
        resultObject.put("model", responseMap);
        resultObject.put("success", true);
//        resultObject.put("redirect", "http://127.0.0.1:8080/");
        resultObject.put("apiName", "account");
        String result = callback + " (' " + resultObject.toJSONString() + " ') ";
        return result;
    }
}