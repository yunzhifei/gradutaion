package com.myfirst.controllers;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
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
    public String bookTravelOrder(@RequestParam("callback") String callback, @RequestParam("travelId") int travelId, @RequestParam("personNumber") int personNumber, @RequestParam("bookDate") String bookDate) {
        String result = "";
        TravelOrder travelOrder = new TravelOrder();
        JSONObject resultJson = new JSONObject();
        if (null == hosHolder.getUser()) {
            resultJson.put("success", false);
            resultJson.put("tip", "请先登录，再操作订单！");
            result = callback + " (' " + resultJson.toJSONString() + " ') ";
            return result;
        }
        result = callback + " (' " + resultJson.toJSONString() + " ') ";
        travelOrder.setBookDate(bookDate);
        travelOrder.setUserId(hosHolder.getUser().getId());
        int s = travelOrderService.bookTravel(travelOrder);
        return result;
    }


}
