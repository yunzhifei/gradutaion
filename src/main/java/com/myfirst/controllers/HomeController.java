package com.myfirst.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @RequestMapping(value = "/hello")
    public String hello(Model model) {
        model.addAttribute("hello",6);
        return "hello";
    }

}
