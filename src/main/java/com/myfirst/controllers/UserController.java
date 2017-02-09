package com.myfirst.controllers;

import com.myfirst.entitis.User;
import com.myfirst.service.UserService;
import com.sun.deploy.net.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by 58 on 2017/2/8.
 * author yun zhi fei
 */
@Controller
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/user/{userId}")
    public String showUserInfo(@PathVariable("userId") int userId, Model model) {
        User user = userService.findUserById(userId);
        model.addAttribute("user", user);
        return "user";
    }

    @RequestMapping("/longin")
    public String loginMethod(Model model, HttpResponse httpResponse,
                              @RequestParam("userName") String userName,
                              @RequestParam("passWord") String passWord,
                              @RequestParam(value = "remember", defaultValue = "0") int remember) {

        return "";
    }
}
