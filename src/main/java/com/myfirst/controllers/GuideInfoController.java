package com.myfirst.controllers;

import com.alibaba.fastjson.JSONObject;
import com.myfirst.entitis.GuideInfo;
import com.myfirst.entitis.ListViewObject;
import com.myfirst.entitis.Travel;
import com.myfirst.service.GuideService;
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
public class GuideInfoController {
    @Autowired
    GuideService guideService;

    @RequestMapping(value = "/guideInfo/list")
    public String guideList(@RequestParam("callback") String callback, @RequestParam("page") int page, @RequestParam("size") int size) {
        List<GuideInfo> guideInfos = guideService.showGuideInfo(size,page-1);
        JSONObject resultJson = new JSONObject();
        Map<String, Object> modelx = new HashMap<String, Object>();
        List<ListViewObject> list = new ArrayList<>();
        for (GuideInfo guideInfo : guideInfos) {
            ListViewObject viewObject = new ListViewObject<GuideInfo>();
            viewObject.setId(guideInfo.getId());
            viewObject.setTitle(guideInfo.getName());
            viewObject.setContent(guideInfo.getDescription().substring(0, 10));
            viewObject.setEntity(guideInfo);
            viewObject.setImg(guideInfo.getImg());
            list.add(viewObject);
        }
        int count = guideService.findGuideInfoCount();
        modelx.put("data", list);
        modelx.put("count", count);
        resultJson.put("model", modelx);
        resultJson.put("success", true);
        String result = callback + " (' " + resultJson.toJSONString() + " ') ";
        return result;
    }
}
