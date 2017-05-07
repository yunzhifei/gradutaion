package com.myfirst.controllers;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONPObject;
import com.myfirst.entitis.HotelOrder;
import com.myfirst.service.HotelOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Administrator on 2017/5/5.
 * yun zhi fei
 */
@RestController
public class HotelOrderController {
    @Autowired
    HotelOrderService hotelOrderService;

    //预定旅馆信息
    @RequestMapping(value = "/hotelOrder/add", method = {RequestMethod.GET, RequestMethod.POST})
    public String bookHotel(@Valid HotelOrder hotelOrder) {
        hotelOrderService.bookHotel(hotelOrder);
        return "success";
    }

    //支付旅馆费用
    @RequestMapping(value = "/hotelOrder/pay")
    public String payTravelMoney(@RequestParam("id") int id) {
        hotelOrderService.payHotelOrder(id);
        return "success";
    }


    //展示一个人所有的预定旅馆订单
    @RequestMapping(value = "/hotelOrderList")
    public String showHotelOrderList(HttpRequest httpRequest, @RequestParam("callBack") String callBack) {
        int userId = 0;
        List<HotelOrder> hotelOrderList = hotelOrderService.findAllHotelOrderByUserId(userId);
        String result = callBack + "(" + JSON.toJSONString(hotelOrderList) + ")";
        return result;
    }

}
