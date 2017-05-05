package com.myfirst.service;

import com.myfirst.dao.IviewSpotDao;
import com.myfirst.entitis.ViewSpot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.View;
import java.util.List;

/**
 * Created by yunzh on 2017/5/3.
 * yun zhi fei
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

    //删除景点信息
    public int deleteVIewSpot(int id) {
        return iviewSpotDao.deleteViewSpot(id);
    }

    //浏览单个景点信息
    public ViewSpot findViewSpotById(int id) {
        return iviewSpotDao.findViewSpotById(id);
    }
}
