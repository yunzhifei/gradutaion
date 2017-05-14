package com.myfirst.controllers;

import com.alibaba.fastjson.JSONObject;
import com.myfirst.entitis.GuideInfo;
import com.myfirst.entitis.ListViewObject;
import com.myfirst.entitis.TravelNews;
import com.myfirst.service.TravelNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

}
