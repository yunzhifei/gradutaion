package com.myfirst.controllers;

import com.alibaba.fastjson.JSONObject;
import com.myfirst.entitis.*;
import com.myfirst.service.TravelNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/14.
 * yun zhi fei
 */
@RestController
public class TravelNewsController {
    @Autowired
    TravelNewsService travelNewsService;
    @Autowired
    HosHolder hosHolder;


    @RequestMapping(value = "/travelNews/list")
    public String travelNewsList(@RequestParam("callback") String callback, @RequestParam("page") int page, @RequestParam("size") int size) {
        List<TravelNews> travelNews = travelNewsService.findOnePageNews(size, page - 1);
        JSONObject resultJson = new JSONObject();
        Map<String, Object> modelx = new HashMap<String, Object>();
        List<ListViewObject> list = new ArrayList<>();
        for (TravelNews travelNew : travelNews) {
            ListViewObject viewObject = new ListViewObject<GuideInfo>();
            viewObject.setId(travelNew.getId());
            viewObject.setTitle(travelNew.getTitle());
            viewObject.setContent(travelNew.getContent());
            viewObject.setEntity(travelNew);
            viewObject.setImg(travelNew.getPicture());
            list.add(viewObject);
        }
        int count = travelNewsService.findTravelNewsCount();
        modelx.put("data", list);
        modelx.put("count", count);
        resultJson.put("model", modelx);
        resultJson.put("success", true);
        String result = callback + " (' " + resultJson.toJSONString() + " ') ";
        return result;
    }

    @RequestMapping("/travelNews/add")
    public String bookTravelOrder(@Valid TravelNews travelNews, @RequestParam("callback") String callback) {
        String result = "";
        TravelOrder travelOrder = new TravelOrder();
        JSONObject resultJson = new JSONObject();
        if (null == hosHolder.getUser()) {
            resultJson.put("success", false);
            resultJson.put("tip", "请先登录，然后添加旅游资讯！");
            result = callback + " (' " + resultJson.toJSONString() + " ') ";
            return result;
        }
        travelNewsService.addTravelNews(travelNews);
        resultJson.put("success", true);
        resultJson.put("tip", "旅游资讯添加成功！");
        result = callback + " (' " + resultJson.toJSONString() + " ') ";
        return result;
    }

    //订单删除
    @RequestMapping(value = "/travelOrder/delete")
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
        travelNewsService.deleteTravelNewsById(travelOrderId, responseMap);
        if (responseMap.containsKey("error")) {
            resultJson.put("success", false);
            resultJson.put("tip", responseMap.get("error"));
            result = callback + " (' " + resultJson.toJSONString() + " ') ";
            return result;
        }
        resultJson.put("success", true);
        resultJson.put("tip", "旅游资讯删除成功!");
        resultJson.put("model", responseMap);
        result = callback + " (' " + resultJson.toJSONString() + " ') ";
        return result;
    }

}
