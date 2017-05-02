package com.myfirst.controllers;

import com.myfirst.entitis.User;
import com.myfirst.service.UserService;
import com.sun.deploy.net.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by 58 on 2017/2/8.
 * author yun zhi fei
 */
@Controller
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/user", method = {RequestMethod.GET})
    public String showUserInfo() {
        return "user";
    }

    @RequestMapping("/longin")
    public String loginMethod(Model model, HttpResponse httpResponse,
                              @RequestParam("userName") String userName,
                              @RequestParam("passWord") String passWord,
                              @RequestParam(value = "remember", defaultValue = "0") int remember) {

        return "";
    }

    @RequestMapping(value = "/user", method = {RequestMethod.POST})
    public String regUser(@Valid User user, BindingResult result,Model model) {
        System.out.println("adfsdfasd");
        userService.findUserById(1);
        return "user";
    }
}
