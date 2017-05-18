package com.myfirst.controllers;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
import com.myfirst.entitis.*;
import com.myfirst.service.TravelOrderService;
import com.myfirst.service.TravelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Autowired
    TravelService travelService;

    @RequestMapping(value = "/travelOrderList/unpay")
    public String findAllTravelOrderByUserId(@RequestParam("callback") String callback, @RequestParam("page") int page, @RequestParam("size") int size) {
        JSONObject resultJson = new JSONObject();
        Map<String, Object> modelx = new HashMap<String, Object>();
        String result = "";
        if (null == hosHolder.getUser()) {
            resultJson.put("success", false);
            resultJson.put("tip", "请先登录，再操作订单！");
            result = callback + " (' " + resultJson.toJSONString() + " ') ";
            return result;
        }
        List<TravelOrder> travelOrders = travelOrderService.findAllUserUnPayTravelOrderByUserId(hosHolder.getUser().getId(), size, page - 1);
        List<ListViewObject> list = new ArrayList<>();
        for (TravelOrder travelOrder : travelOrders) {
            ListViewObject viewObject = new ListViewObject<GuideInfo>();
            Travel travel = travelService.findTravelById(travelOrder.getTravelId());
            viewObject.setId(travelOrder.getId());
            viewObject.setTitle(travel.getTravelType());
            viewObject.setContent("起点:  " + travel.getStartAddress() + " 终点：" + travel.getEndAddress() + " 人数： " + travelOrder.getPersonNumber() + " 单价：" + travel.getPrice() + " (元/人)");
            viewObject.setEntity(travelOrder);
            list.add(viewObject);
        }
        int count = travelOrderService.findCountUserUnpayTravelOrderByUserId(hosHolder.getUser().getId());
        modelx.put("data", list);
        modelx.put("count", count);
        resultJson.put("model", modelx);
        resultJson.put("success", true);
        result = callback + " (' " + resultJson.toJSONString() + " ') ";
        return result;
    }

    @RequestMapping(value = "/travelOrderList/payed")
    public String findTravelOrderPayedByUserId(@RequestParam("callback") String callback, @RequestParam("page") int page, @RequestParam("size") int size) {
        JSONObject resultJson = new JSONObject();
        Map<String, Object> modelx = new HashMap<String, Object>();
        String result = "";
        if (null == hosHolder.getUser()) {
            resultJson.put("success", false);
            resultJson.put("tip", "请先登录，再操作订单！");
            result = callback + " (' " + resultJson.toJSONString() + " ') ";
            return result;
        }
        List<TravelOrder> travelOrders = travelOrderService.findAllUserPayedTravelOrderByUserId(hosHolder.getUser().getId(), size, page - 1);
        List<ListViewObject> list = new ArrayList<>();
        for (TravelOrder travelOrder : travelOrders) {
            ListViewObject viewObject = new ListViewObject<GuideInfo>();
            Travel travel = travelService.findTravelById(travelOrder.getTravelId());
            viewObject.setId(travelOrder.getId());
            viewObject.setTitle(travel.getTravelType());
            viewObject.setContent("起点:  " + travel.getStartAddress() + " 终点：" + travel.getEndAddress() + " 人数： " + travelOrder.getPersonNumber() + " 单价：" + travel.getPrice() + " (元/人)");
            viewObject.setEntity(travelOrder);
            list.add(viewObject);
        }
        int count = travelOrderService.findCountUserPayedTravelOrderByUserId(hosHolder.getUser().getId());
        modelx.put("data", list);
        modelx.put("count", count);
        resultJson.put("model", modelx);
        resultJson.put("success", true);
        result = callback + " (' " + resultJson.toJSONString() + " ') ";
        return result;
    }


    @RequestMapping("/travelOrder/add")
    public String bookTravelOrder(@RequestParam("callback") String callback, @RequestParam("id") int travelId, @RequestParam("personNumber") int personNumber, @RequestParam("bookDate") String bookDate) {
        String result = "";
        TravelOrder travelOrder = new TravelOrder();
        JSONObject resultJson = new JSONObject();
        if (null == hosHolder.getUser()) {
            resultJson.put("success", false);
            resultJson.put("tip", "请先登录，再操作订单！");
            result = callback + " (' " + resultJson.toJSONString() + " ') ";
            return result;
        }
        resultJson.put("tip", "订单提交成功!");
        travelOrder.setBookDate(bookDate);
        travelOrder.setPersonNumber(personNumber);
        travelOrder.setTravelId(travelId);
        travelOrder.setUserId(hosHolder.getUser().getId());
        int s = travelOrderService.bookTravel(travelOrder);
        result = callback + " (' " + resultJson.toJSONString() + " ') ";
        return result;
    }

    //订单删除
    @RequestMapping(value = "/travelOrder/delete")
    public String deleteTravelOrder(@RequestParam("id") int travelOrderId, @RequestParam("callback") String callback) {
        String result = "";
        JSONObject resultJson = new JSONObject();
        Map<String, Object> responseMap = new HashMap<String, Object>();
        if (null == hosHolder.getUser()) {
            resultJson.put("success", false);
            resultJson.put("tip", "请先登录，再管理自己的订单！");
            result = callback + " (' " + resultJson.toJSONString() + " ') ";
            return result;
        }
        travelOrderService.deleteTravelOrderById(travelOrderId, responseMap);
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
    @RequestMapping(value = "/travelOrder/pay")
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
        travelOrderService.payTravel(travelOrderId, responseMap);
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
