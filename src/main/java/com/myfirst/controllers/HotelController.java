package com.myfirst.controllers;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.myfirst.entitis.GuideInfo;
import com.myfirst.entitis.Hotel;
import com.myfirst.entitis.ListViewObject;
import com.myfirst.service.HotelService;
import org.omg.PortableInterceptor.HOLDING;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yun zhi fei on 2017/5/3.
 * yun zhi fei
 */
@RestController
public class HotelController {
    @Autowired
    HotelService hotelService;

    @RequestMapping("/hotel/list")
    public String hotelList(@RequestParam("callback") String callback, @RequestParam("page") int page, @RequestParam("size") int size) {
        List<Hotel> hotels = hotelService.findOnePageHotel(size, page - 1);
        JSONObject resultJson = new JSONObject();
        Map<String, Object> modelx = new HashMap<String, Object>();
        List<ListViewObject> list = new ArrayList<>();
        for (Hotel hotel : hotels) {
            ListViewObject viewObject = new ListViewObject<Hotel>();
            viewObject.setId(hotel.getId());
            viewObject.setTitle(hotel.getName());
            viewObject.setContent(hotel.getDescription().substring(0, 10));
            viewObject.setEntity(hotel);
            viewObject.setImg(hotel.getPictureUrl());
            list.add(viewObject);
        }
        int count = hotelService.findHotelCount();
        modelx.put("data", list);
        modelx.put("count", count);
        resultJson.put("model", modelx);
        resultJson.put("success", true);
        String result = callback + " (' " + resultJson.toJSONString() + " ') ";
        return result;
    }


    @RequestMapping("/hotel/{hotelId}")
    public String hotelDetail(@PathVariable("hotelId") int hotelId) {
        Hotel hotel = hotelService.findHotelById(hotelId);
        return JSON.toJSONString(hotel);
    }

    @RequestMapping("/hotel/add")
    public String addHotel(@Valid Hotel hotel) {
        int hotelId = (int) System.currentTimeMillis() / 10;
        hotel.setHotelId(hotelId);
        hotelService.addHotel(hotel);
        return "success";
    }

    @RequestMapping("/hotel/delete/{hotelId}")
    public String deleteHotel(@PathVariable("hotelId") int hotelId) {
        hotelService.deleteHotel(hotelId);
        return "success";
    }
}
