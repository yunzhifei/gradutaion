package com.myfirst.controllers;

import com.alibaba.fastjson.JSONObject;
import com.myfirst.entitis.HosHolder;
import com.myfirst.entitis.Hotel;
import com.myfirst.entitis.ListViewObject;
import com.myfirst.service.HotelService;
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
    @Autowired
    HosHolder hosHolder;

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
            viewObject.setContent(hotel.getDescription().substring(0, 5));
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


    @RequestMapping("/hotel/add")
    public String addHotel(@Valid Hotel hotel, @RequestParam("callback") String callback) {
        String result = "";
        JSONObject resultJson = new JSONObject();
        if (null == hosHolder.getUser()) {
            resultJson.put("success", false);
            resultJson.put("tip", "请先登录，然后操作导游信息！");
            result = callback + " (' " + resultJson.toJSONString() + " ') ";
            return result;
        }
        hotelService.addHotel(hotel);
        resultJson.put("tip", "添加成功!");
        resultJson.put("success", true);
        result = callback + " (' " + resultJson.toJSONString() + " ') ";
        return result;
    }

    @RequestMapping("/hotel/delete")
    public String deleteHotel(@RequestParam("id") int hotelId, @RequestParam("callback") String callback) {
        String result = "";
        Map<String, Object> responseMap = new HashMap<String, Object>();
        JSONObject resultJson = new JSONObject();
        if (null == hosHolder.getUser()) {
            resultJson.put("success", false);
            resultJson.put("tip", "请先登录，然后操作导游信息！");
            result = callback + " (' " + resultJson.toJSONString() + " ') ";
            return result;
        }
        hotelService.deleteHotel(hotelId,responseMap);
        if (responseMap.containsKey("error")) {
            resultJson.put("success", false);
            resultJson.put("tip", responseMap.get("error"));
            result = callback + " (' " + resultJson.toJSONString() + " ') ";
            return result;
        }
        resultJson.put("tip", "删除成功!");
        resultJson.put("success", true);
        result = callback + " (' " + resultJson.toJSONString() + " ') ";
        return result;
    }

}
