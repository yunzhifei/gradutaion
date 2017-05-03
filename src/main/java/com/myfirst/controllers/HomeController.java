package com.myfirst.controllers;

import com.myfirst.entitis.ViewSpot;
import com.myfirst.service.ViewSpotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by 58 on 2017/1/17.
 * author yunzhifei
 */
@Controller
public class HomeController {
    @Autowired
    ViewSpotService viewSpotService;

    @RequestMapping(value = {"/", "/index"})
    public String index(Model model) {
        List<ViewSpot> viewSpots = viewSpotService.findAllViewSpot();
        model.addAttribute("viewSpots", viewSpots);
        return "hello";
    }

}
