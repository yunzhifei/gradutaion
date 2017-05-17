package com.myfirst.controllers;

import com.alibaba.fastjson.JSONObject;
import com.myfirst.entitis.GuideInfo;
import com.myfirst.entitis.HosHolder;
import com.myfirst.entitis.ListViewObject;
import com.myfirst.entitis.ViewSpot;
import com.myfirst.service.ViewSpotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yunzh on 2017/5/16.
 * yun zhi fei
 */
@RestController
public class ViewSpotController {
    @Autowired
    HosHolder hosHolder;
    @Autowired
    ViewSpotService viewSpotService;

    @RequestMapping(value = "/viewSpot/list")
    public String viewSpotList(@RequestParam("callback") String callback, @RequestParam("page") int page, @RequestParam("size") int size) {
        List<ViewSpot> viewSpots = viewSpotService.findOnePageViewSpot(page, size);
        JSONObject resultJson = new JSONObject();
        Map<String, Object> modelx = new HashMap<String, Object>();
        List<ListViewObject> list = new ArrayList<>();
        for (ViewSpot viewSpot : viewSpots) {
            ListViewObject viewObject = new ListViewObject<GuideInfo>();
            viewObject.setId(viewSpot.getId());
            viewObject.setTitle(viewSpot.getViewName());
            viewObject.setContent(viewSpot.getDescription());
            viewObject.setEntity(viewSpot);
            viewObject.setImg(viewSpot.getPictureUrl());
            list.add(viewObject);
        }
        int count = viewSpotService.findViewSpotCount();
        modelx.put("data", list);
        modelx.put("count", count);
        resultJson.put("model", modelx);
        resultJson.put("success", true);
        String result = callback + " (' " + resultJson.toJSONString() + " ') ";
        return result;
    }

    //景点管理模块，删除景点
    @RequestMapping(value = "/viewSpot/delete")
    @ResponseBody
    public String deleteUserById(@RequestParam("id") int userId, @RequestParam("callback") String callback) {
        Map<String, Object> responseMap = new HashMap<String, Object>();
        JSONObject resultObject = new JSONObject();
        String result = "";
        //未登录
        if (null == hosHolder.getUser()) {
            resultObject.put("tip", "请先登录然后再操作！");
            resultObject.put("success", false);
            result = callback + " (' " + resultObject.toJSONString() + " ') ";
            return result;
        }
        viewSpotService.deleteViewSpotById(userId, responseMap);
        //景点不存在
        if (responseMap.containsKey("error")) {
            resultObject.put("tip", responseMap.get("error"));
            resultObject.put("success", false);
            result = callback + " (' " + resultObject.toJSONString() + " ') ";
            return result;
        }
        resultObject.put("tip", "删除成功!");
        resultObject.put("model", responseMap);
        resultObject.put("success", true);

        result = callback + " (' " + resultObject.toJSONString() + " ') ";
        return result;
    }

    //后台管理添加景点信息
    @RequestMapping(value = "/viewSpot/add")
    public String addViewSpot(@Valid ViewSpot viewSpot, @RequestParam("callback") String callback) {
        String result = "";
        JSONObject resultJson = new JSONObject();
        if (null == hosHolder.getUser()) {
            resultJson.put("success", false);
            resultJson.put("tip", "请先登录，再添加景点！");
            result = callback + " (' " + resultJson.toJSONString() + " ') ";
            return result;
        }
        resultJson.put("success", true);
        resultJson.put("tip", "添加景点信息成功!");
        resultJson.put("model", "");
        viewSpotService.addViewSpot(viewSpot);
        result = callback + " (' " + resultJson.toJSONString() + " ') ";
        return result;
    }
}
