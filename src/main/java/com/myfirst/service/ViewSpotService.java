package com.myfirst.service;

import com.myfirst.dao.IviewSpotDao;
import com.myfirst.entitis.ViewSpot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.View;
import java.util.List;
import java.util.Map;

/**
 * Created by yun on 2017/5/3.
 */
@Service
public class ViewSpotService {
    @Autowired
    IviewSpotDao iviewSpotDao;

    //浏览所有景点信息
    public List<ViewSpot> findAllViewSpot() {
        return iviewSpotDao.findAllView();
    }

    //添加景点信息
    public int addViewSpot(ViewSpot viewSpot) {
        return iviewSpotDao.addViewSpot(viewSpot);
    }


    //浏览单个景点信息
    public ViewSpot findViewSpotById(int id) {
        return iviewSpotDao.findViewSpotById(id);
    }

    //返回一页景点数据
    public List<ViewSpot> findOnePageViewSpot(int page, int size) {
        return iviewSpotDao.findOnePageViewSpot((page - 1) * size, size);
    }

    //返回景点的数目
    public int findViewSpotCount() {
        return iviewSpotDao.findViewSpotCount();
    }

    //删除景点根据ID
    public int deleteViewSpotById(int viewSpotId, Map<String, Object> responseMap) {
        if (null == findViewSpotById(viewSpotId)) {
            responseMap.put("error", "该景点已经被删除过，请不要重复删除！");
            return -1;
        }
        return iviewSpotDao.deleteViewSpotById(viewSpotId);
    }
}
