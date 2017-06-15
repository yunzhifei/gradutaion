package com.myfirst.controllers;

import com.alibaba.fastjson.JSONObject;
import com.myfirst.entitis.HosHolder;
import com.myfirst.entitis.ListViewObject;
import com.myfirst.entitis.Travel;
import com.myfirst.service.TravelService;
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
 * Created by Administrator on 2017/5/9.
 */
@RestController
public class TravelController {
    @Autowired
    TravelService travelService;
    @Autowired
    HosHolder hosHolder;

    @RequestMapping(value = "/travel/list")
    public String findAllTravel(@RequestParam("callback") String callback, @RequestParam("page") int page, @RequestParam("size") int size) {
        List<Travel> travels = travelService.findAllTravel(size, page);
        JSONObject resultJson = new JSONObject();
        Map<String, Object> modelx = new HashMap<String, Object>();
        List<ListViewObject> list = new ArrayList<>();
        for (Travel travel : travels) {
            ListViewObject viewObject = new ListViewObject<Travel>();
            viewObject.setId(travel.getId());
            viewObject.setTitle(travel.getTravelType());
            viewObject.setContent(travel.getStartAddress() + " 到 " + travel.getEndAddress());
            viewObject.setEntity(travel);
            list.add(viewObject);
        }
        int count = travelService.findTravelCount();
        modelx.put("data", list);
        modelx.put("count", count);
        resultJson.put("model", modelx);
        resultJson.put("success", true);
        String result = callback + " (' " + resultJson.toJSONString() + " ') ";
        return result;
    }

    @RequestMapping(value = "/travel/add")
    public String addTravel(@Valid Travel travel) {
        int result = travelService.addTravel(travel);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("model", result);
        jsonObject.put("success", true);
        jsonObject.put("apiName", "travelAdd");
        jsonObject.put("tip", "添加成功！");
        String resultString =  jsonObject.toJSONString() ;
        return resultString;
    }


    //订单删除
    @RequestMapping(value = "/travel/delete")
    public String addViewSpot(@RequestParam("id") int travelOrderId, @RequestParam("callback") String callback) {
        String result = "";
        JSONObject resultJson = new JSONObject();
        Map<String, Object> responseMap = new HashMap<String, Object>();
        if (null == hosHolder.getUser()) {
            resultJson.put("success", false);
            resultJson.put("tip", "请先登录，再管理资讯信息！");
            result = callback + " (' " + resultJson.toJSONString() + " ') ";
            return result;
        }
        travelService.deleteTravelById(travelOrderId, responseMap);
        if (responseMap.containsKey("error")) {
            resultJson.put("success", false);
            resultJson.put("tip", responseMap.get("error"));
            result = callback + " (' " + resultJson.toJSONString() + " ') ";
            return result;
        }
        resultJson.put("success", true);
        resultJson.put("tip", "交通信息删除成功!");
        resultJson.put("model", responseMap);
        result = callback + " (' " + resultJson.toJSONString() + " ') ";
        return result;
    }

}
