package com.myfirst.controllers;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONPObject;
import com.myfirst.entitis.HosHolder;
import com.myfirst.entitis.TravelOrder;
import com.myfirst.entitis.User;
import com.myfirst.service.TravelOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Administrator on 2017/5/7.
 * yun zhi fei
 */
@RestController
public class TravelOrderController {

    @Autowired
    TravelOrderService travelOrderService;
    @Autowired
    HosHolder hosHolder;

    @RequestMapping(value = "/travelOrderList")
    public String findAllTravelOrderByUserId() {
        User user = hosHolder.getUser();
        List<TravelOrder> list = travelOrderService.findAllTravelOrderByUserId(user.getId());
        return JSON.toJSONString(list);
    }

    @RequestMapping("/travelOrder/add")
    public String bookTravelOrder(@Valid TravelOrder travelOrder) {
        travelOrder.setUserId(hosHolder.getUser().getId());
        int result = travelOrderService.bookTravel(travelOrder);
        return "success";
    }



}
