package com.myfirst.controllers;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.myfirst.entitis.GuideInfo;
import com.myfirst.entitis.HosHolder;
import com.myfirst.entitis.ListViewObject;
import com.myfirst.entitis.User;
import com.myfirst.service.UserService;
import com.myfirst.utl.GraduationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.*;

/**
 * Created by 58 on 2017/2/8.
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
    public String regUser(@Valid User user, BindingResult result, Model model) {
        String resultString = "";
        StringBuffer salt = new StringBuffer("");
        //密码盐
        for (int i = 0; i < 10; i++) {
            salt.append(String.valueOf(48 + new Random().nextInt(10)));
        }
        //md5加密
        user.setPassword(GraduationUtil.MD5(user.getPassword() + salt));
        JSONObject resultObject = new JSONObject();

        resultObject.put("apiName", "account");
        user.setSalt(salt.toString());
        int useId = 0;
        try {
            userService.addUser(user);
        } catch (Exception e) {
            resultObject.put("success", false);
        }
        resultObject.put("tip", "注册成功！");
        resultObject.put("success", true);
        System.out.println("useid = " + useId);

        return resultObject.toJSONString();
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
    @ResponseBody
    public String changPassWord(@RequestParam("oldPassWord") String oldPassWord, @RequestParam("newPassWord") String newPassWord, @RequestParam("callback") String callback, @RequestParam("newPassWord1") String newPassWord1) {

        Map<String, Object> responseMap = new HashMap<String, Object>();
        JSONObject resultObject = new JSONObject();
        if (!newPassWord.equals(newPassWord1)) {
            resultObject.put("tip", "两次输入的新密码不一致！");
            resultObject.put("success", false);
            String result = callback + " (' " + resultObject.toJSONString() + " ') ";
            return result;
        }
        if (null == hosHolder.getUser()) {
            resultObject.put("success", false);
            responseMap.put("error", "用户未登录！");
            String result = callback + " (' " + resultObject.toJSONString() + " ') ";
            return result;
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

    //用户管理模块,删除用户
    @RequestMapping(value = "/user/delete")
    @ResponseBody
    public String deleteUserById(@RequestParam("id") int userId, @RequestParam("callback") String callback) {
        Map<String, Object> responseMap = new HashMap<String, Object>();
        JSONObject resultObject = new JSONObject();
        String result = "";
        userService.deleteUserById(userId, responseMap);
        if (responseMap.containsKey("error")) {
            resultObject.put("tip", "删除失败用户不存在！");
            resultObject.put("success", false);
            result = callback + " (' " + resultObject.toJSONString() + " ') ";
            return result;
        }
        resultObject.put("tip", "删除成功!");
        resultObject.put("model", responseMap);
        resultObject.put("success", true);
        hosHolder.clear();
        result = callback + " (' " + resultObject.toJSONString() + " ') ";
        return result;
    }

    //用户管理显示所有的用户
    @RequestMapping(value = "/user/list")
    @ResponseBody
    public String userList(@RequestParam("callback") String callback, @RequestParam("page") int page, @RequestParam("size") int size) {
        List<User> users = userService.findAllUndeleteUser(size, page - 1);
        JSONObject resultJson = new JSONObject();
        Map<String, Object> modelx = new HashMap<String, Object>();
        List<ListViewObject> list = new ArrayList<>();
        for (User user : users) {
            ListViewObject viewObject = new ListViewObject<GuideInfo>();
            viewObject.setId(user.getId());
            viewObject.setContent("用户名： " + user.getUserName() + "用户住址：" + user.getAddress() + " 用户性别 ： " + user.getSex() + " 用户邮箱： " + user.getEmailAddress());
            viewObject.setEntity(user);
            list.add(viewObject);
        }
        int count = userService.findUserCountUndelete();
        modelx.put("data", list);
        modelx.put("count", count);
        resultJson.put("model", modelx);
        resultJson.put("success", true);
        String result = callback + " (' " + resultJson.toJSONString() + " ') ";
        return result;
    }
}