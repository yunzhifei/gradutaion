package com.myfirst.controllers;

import com.myfirst.entitis.User;
import com.myfirst.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
