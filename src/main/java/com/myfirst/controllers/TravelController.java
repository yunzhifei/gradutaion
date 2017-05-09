package com.myfirst.controllers;

import com.alibaba.fastjson.JSONObject;
import com.myfirst.entitis.Travel;
import com.myfirst.service.TravelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Administrator on 2017/5/9.
 * yun zhi fei
 */
@RestController
public class TravelController {
    @Autowired
    TravelService travelService;

    @RequestMapping(value = "/travel/list")
    public String findAllTravel(@RequestParam("callback") String callback) {
        List<Travel> travels = travelService.findAllTravel();
        JSONObject resultJson = new JSONObject();
        resultJson.put("model", travels);
        resultJson.put("success", true);
        resultJson.put("apiName", "travels");
        String result = callback + " (' " + resultJson.toJSONString() + " ') ";
        return result;
    }

    @RequestMapping(value = "/travel/add")
    public String addTravel(@Valid Travel travel, @RequestParam("callback") String callback) {
        int result = travelService.addTravel(travel);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("model", result);
        jsonObject.put("success", true);
        jsonObject.put("apiName", "travelAdd");
        jsonObject.put("tip", "添加成功！");
        String resultString = callback + " ('" + jsonObject.toJSONString() + "')";
        return resultString;
    }

    @RequestMapping(value = "/travel/{travelId}")
    public String findTravelById(@PathVariable("travelId") int travelId, @RequestParam("callback") String callback) {
        Travel travel = travelService.findTravelById(travelId);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("model", travel);
        jsonObject.put("success", true);
        jsonObject.put("apiName", "theTravel");
        String result = callback + " ('" + jsonObject.toJSONString() + " ')";
        return result;
    }

}
