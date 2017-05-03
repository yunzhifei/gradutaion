package com.myfirst.controllers;

import com.alibaba.fastjson.JSON;
import com.myfirst.entitis.Hotel;
import com.myfirst.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by yun zhi fei on 2017/5/3.
 * yun zhi fei
 */
@RestController
public class HotelController {
    @Autowired
    HotelService hotelService;

    @RequestMapping("/hotel/list")
    public String hotelList() {
        List<Hotel> list = hotelService.findAllHotel();
        return JSON.toJSONString(list);
    }

    @RequestMapping("/hotel/{hotelId}")
    public String hotelDetail(@PathVariable("hotelId") int hotelId) {
        Hotel hotel = hotelService.findHotlById(hotelId);
        return JSON.toJSONString(hotel);
    }

    @RequestMapping("/hotel/add")
    public String addHotel(@Valid Hotel hotel) {
        int hotelId = (int) System.currentTimeMillis() / 10;
        hotel.setHotelId(hotelId);
        hotelService.addHotel(hotel);
        return "";
    }

    @RequestMapping("/hotel/delete/{hotelId}")
    public String deleteHotel(@PathVariable("hotelId") int hotelId) {
        hotelService.deleteHotel(hotelId);
        return "success";
    }
}
