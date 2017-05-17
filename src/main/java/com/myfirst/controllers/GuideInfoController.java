package com.myfirst.controllers;

import com.alibaba.fastjson.JSONObject;
import com.myfirst.entitis.GuideInfo;
import com.myfirst.entitis.HosHolder;
import com.myfirst.entitis.ListViewObject;
import com.myfirst.service.GuideService;
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
public class GuideInfoController {
    @Autowired
    GuideService guideService;
    @Autowired
    HosHolder hosHolder;

    @RequestMapping(value = "/guideInfo/list")
    public String guideList(@RequestParam("callback") String callback, @RequestParam("page") int page, @RequestParam("size") int size) {
        List<GuideInfo> guideInfos = guideService.showGuideInfo(size, page - 1);
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

    @RequestMapping(value = "/guideInfo/add")
    public String addGuideInfo(@Valid GuideInfo guideInfo, @RequestParam("callback") String callback) {
        String result = "";
        JSONObject resultJson = new JSONObject();
        if (null == hosHolder.getUser()) {
            resultJson.put("success", false);
            resultJson.put("tip", "请先登录，然后操作导游信息！");
            result = callback + " (' " + resultJson.toJSONString() + " ') ";
            return result;
        }
        resultJson.put("tip", "添加成功!");
        resultJson.put("success", true);
        guideService.addGuideInfo(guideInfo);
        result = callback + " (' " + resultJson.toJSONString() + " ') ";
        return result;
    }

    @RequestMapping(value = "/guideInfo/delete")
    public String deleteGuideInfo(@RequestParam("id") int guideInfoId, @RequestParam("callback") String callback) {
        String result = "";
        Map<String, Object> responseMap = new HashMap<String, Object>();
        JSONObject resultJson = new JSONObject();
        if (null == hosHolder.getUser()) {
            resultJson.put("success", false);
            resultJson.put("tip", "请先登录，然后操作导游信息！");
            result = callback + " (' " + resultJson.toJSONString() + " ') ";
            return result;
        }
        guideService.deleteGuideInfo(guideInfoId, resultJson);
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
