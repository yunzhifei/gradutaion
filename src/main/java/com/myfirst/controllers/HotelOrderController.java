package com.myfirst.controllers;

import com.myfirst.entitis.HotelOrder;
import com.myfirst.service.HotelOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

/**
 * Created by Administrator on 2017/5/5.
 * yun zhi fei
 */
@Controller
public class HotelOrderController {
    @Autowired
    HotelOrderService hotelOrderService;

    //预定旅馆信息
    @RequestMapping(value = "/hotelOrder/add", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
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


}
