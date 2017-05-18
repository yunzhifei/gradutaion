package com.myfirst.controllers;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
import com.myfirst.entitis.*;
import com.myfirst.service.HotelOrderService;
import com.myfirst.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/5.
 * yun zhi fei
 */
@RestController
public class HotelOrderController {
    @Autowired
    HotelOrderService hotelOrderService;
    @Autowired
    HosHolder hosHolder;
    @Autowired
    HotelService hotelService;

    //预定旅馆信息
    @RequestMapping(value = "/hotelOrder/add", method = {RequestMethod.GET, RequestMethod.POST})
    public String bookHotel(@RequestParam("callback") String callback, @RequestParam("id") int hotelId, @RequestParam("personNumber") int personNumber, @RequestParam("bookDate") String bookDate) {
        HotelOrder hotelOrder = new HotelOrder();
        String result = "";
        JSONObject resultJson = new JSONObject();
        if (null == hosHolder.getUser()) {
            resultJson.put("success", false);
            resultJson.put("tip", "请先登录，再操作订单！");
            result = callback + " (' " + resultJson.toJSONString() + " ') ";
            return result;
        }
        resultJson.put("tip", "预定成功!");
        hotelOrder.setHotelId(hotelId);
        hotelOrder.setPersonNumber(personNumber);
        hotelOrder.setUserId(hosHolder.getUser().getId());
        hotelOrderService.bookHotel(hotelOrder);
        result = callback + " (' " + resultJson.toJSONString() + " ') ";
        return result;
    }


    //所有未支付的旅馆订单
    @RequestMapping(value = "/hotelOrderList/unpay")
    public String findAllHotelOrderByUserId(@RequestParam("callback") String callback, @RequestParam("page") int page, @RequestParam("size") int size) {
        JSONObject resultJson = new JSONObject();
        Map<String, Object> modelx = new HashMap<String, Object>();
        String result = "";
        if (null == hosHolder.getUser()) {
            resultJson.put("success", false);
            resultJson.put("tip", "请先登录，再操作订单！");
            result = callback + " (' " + resultJson.toJSONString() + " ') ";
            return result;
        }
        List<HotelOrder> hotelOrders = hotelOrderService.findAllUnPayHotelOrderByUserId(hosHolder.getUser().getId(), size, page);
        List<ListViewObject> list = new ArrayList<>();
        for (HotelOrder hotelOrder : hotelOrders) {
            ListViewObject viewObject = new ListViewObject<HotelOrder>();
            Hotel hotel = hotelService.findHotelById(hotelOrder.getHotelId());
            viewObject.setId(hotelOrder.getId());
            viewObject.setTitle(hotel.getName());
            viewObject.setContent(" 地址 :" + hotel.getAddress() + " 人数:" + hotelOrder.getPersonNumber() + " 价格:" + hotel.getPrice() + "(元/人/晚)");
            viewObject.setImg(hotel.getPictureUrl());
            viewObject.setEntity(hotelOrder);
            list.add(viewObject);
        }
        int count = hotelOrderService.findCountUnpayHotelOrder(hosHolder.getUser().getId());
        modelx.put("data", list);
        modelx.put("count", count);
        resultJson.put("model", modelx);
        resultJson.put("success", true);
        result = callback + " (' " + resultJson.toJSONString() + " ') ";
        return result;
    }

    //所有已经支付过的旅馆订单
    @RequestMapping(value = "/hotelOrderList/payed")
    public String findAllHotelOrderPayedByUserId(@RequestParam("callback") String callback, @RequestParam("page") int page, @RequestParam("size") int size) {
        JSONObject resultJson = new JSONObject();
        Map<String, Object> modelx = new HashMap<String, Object>();
        String result = "";
        if (null == hosHolder.getUser()) {
            resultJson.put("success", false);
            resultJson.put("tip", "请先登录，再操作订单！");
            result = callback + " (' " + resultJson.toJSONString() + " ') ";
            return result;
        }
        List<HotelOrder> hotelOrders = hotelOrderService.findAllPayedHotelOrderByUserId(hosHolder.getUser().getId(), size, page - 1);
        List<ListViewObject> list = new ArrayList<>();
        for (HotelOrder hotelOrder : hotelOrders) {
            ListViewObject viewObject = new ListViewObject<HotelOrder>();
            Hotel hotel = hotelService.findHotelById(hotelOrder.getHotelId());
            viewObject.setId(hotelOrder.getId());
            viewObject.setTitle(hotel.getName());
            viewObject.setContent(" 地址 :" + hotel.getAddress() + " 人数:" + hotelOrder.getPersonNumber() + " 价格:" + hotel.getPrice() + "(元/人/晚)");
            viewObject.setImg(hotel.getPictureUrl());
            viewObject.setEntity(hotelOrder);
            list.add(viewObject);
        }
        int count = hotelOrderService.findCountPayedHotelOrde(hosHolder.getUser().getId());
        modelx.put("data", list);
        modelx.put("count", count);
        resultJson.put("model", modelx);
        resultJson.put("success", true);
        result = callback + " (' " + resultJson.toJSONString() + " ') ";
        return result;
    }

    //订单取消
    @RequestMapping(value = "/hotelOrder/delete")
    public String deleteHotelOrder(@RequestParam("id") int travelOrderId, @RequestParam("callback") String callback) {
        String result = "";
        JSONObject resultJson = new JSONObject();
        Map<String, Object> responseMap = new HashMap<String, Object>();
        if (null == hosHolder.getUser()) {
            resultJson.put("success", false);
            resultJson.put("tip", "请先登录，再管理自己的订单！");
            result = callback + " (' " + resultJson.toJSONString() + " ') ";
            return result;
        }
        hotelOrderService.deleteHoteOrderById(travelOrderId, responseMap);
        if (responseMap.containsKey("error")) {
            resultJson.put("success", false);
            resultJson.put("tip", responseMap.get("error"));
            result = callback + " (' " + resultJson.toJSONString() + " ') ";
            return result;
        }
        resultJson.put("success", true);
        resultJson.put("tip", "订单删除成功!");
        resultJson.put("model", responseMap);
        result = callback + " (' " + resultJson.toJSONString() + " ') ";
        return result;
    }

    //订单支付
    @RequestMapping(value = "/hotelOrder/pay")
    public String payTravelOrder(@RequestParam("id") int travelOrderId, @RequestParam("callback") String callback) {
        String result = "";
        JSONObject resultJson = new JSONObject();
        Map<String, Object> responseMap = new HashMap<String, Object>();
        if (null == hosHolder.getUser()) {
            resultJson.put("success", false);
            resultJson.put("tip", "请先登录，再管理自己的订单！");
            result = callback + " (' " + resultJson.toJSONString() + " ') ";
            return result;
        }
        hotelOrderService.payHotelOrder(travelOrderId, responseMap);
        if (responseMap.containsKey("error")) {
            resultJson.put("success", false);
            resultJson.put("tip", responseMap.get("error"));
            result = callback + " (' " + resultJson.toJSONString() + " ') ";
            return result;
        }
        resultJson.put("success", true);
        resultJson.put("tip", "订单支付成功!");
        resultJson.put("model", responseMap);
        result = callback + " (' " + resultJson.toJSONString() + " ') ";
        return result;
    }

}
