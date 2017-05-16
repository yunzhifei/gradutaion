package com.myfirst.controllers;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.myfirst.entitis.HosHolder;
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

    @Autowired
    HosHolder hosHolder;

    @RequestMapping(value = "/login", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String loginMethod(@RequestParam("account") String account, @RequestParam("password") String password, @RequestParam("callback") String callback, HttpServletResponse httpServletResponse) {
        Map<String, Object> responeMap = new HashMap<String, Object>();
        JSONObject jsonObject = new JSONObject();
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
            jsonObject.put("tip", "登陆成功");
        } else {
            responeMap.put("logined", false);
            jsonObject.put("tip", responeMap.get("error"));
        }

        jsonObject.put("model", JSON.toJSON(responeMap));

        jsonObject.put("success", "true");
        jsonObject.put("apiName", "account");
        String result = callback + " (' " + jsonObject.toJSONString() + " ') ";
        return result;
    }

    @RequestMapping(value = "/reg", method = {RequestMethod.POST})
    @ResponseBody
    public String regUser(@Valid User user, BindingResult result, Model model, @RequestParam("callback") String callback) {
        String resultString = "";
        StringBuffer salt = new StringBuffer("");
        for (int i = 0; i < 10; i++) {
            salt.append(String.valueOf(48 + new Random().nextInt(10)));
        }
        JSONObject resultObject = new JSONObject();

        resultObject.put("apiName", "account");
        user.setSalt(salt.toString());
        int useId = 0;
        try {
            userService.addUser(user);
        } catch (Exception e) {
            resultObject.put("success", false);
        }
        resultObject.put("success", true);
        System.out.println("useid = " + useId);
        resultString = callback + " (' " + resultObject.toJSONString() + " ') ";
        return resultString;
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
        resultObject.put("apiName", "account");
        hosHolder.clear();
        String result = callback + " (' " + resultObject.toJSONString() + " ') ";
        return result;
    }

    @RequestMapping(value = "/changePassWord")
    public String changPassWord(@RequestParam("oldPassWord") String oldPassWord, @RequestParam("newPassWord") String newPassWord, @RequestParam("callback") String callback) {
        Map<String, Object> responseMap = new HashMap<String, Object>();
        JSONObject resultObject = new JSONObject();

        if (null == hosHolder.getUser()) {
            responseMap.put("error", "用户未登录！");
        }
        userService.updatePassword(hosHolder.getUser().getId(), oldPassWord, newPassWord, responseMap);
        if (responseMap.containsKey("error")) {
            resultObject.put("tip", responseMap.get("error"));
            resultObject.put("success", false);
            String result = callback + " (' " + resultObject.toJSONString() + " ') ";
            return result;
        }
        resultObject.put("tip", "修改成功!");
        resultObject.put("success", true);
        String result = callback + " (' " + resultObject.toJSONString() + " ') ";
        return result;

    }
}