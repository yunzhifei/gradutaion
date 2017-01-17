package com.myfirst.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by 58 on 2017/1/17.
 * author yunzhifei
 */
@Controller
public class HomeController {
    @RequestMapping(value = {"/", "/index"})
    @ResponseBody
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/hello/{userId}")
    public String hello(Model model, HttpServletRequest request, @PathVariable("userId") String userId) {
        System.out.println(request.getRequestURL());
        System.out.println(userId);
        model.addAttribute("hello", 6);
        return "hello";
    }

}
