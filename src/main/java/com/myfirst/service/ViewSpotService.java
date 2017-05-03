package com.myfirst.service;

import com.myfirst.dao.IviewSpotDao;
import com.myfirst.entitis.ViewSpot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yunzh on 2017/5/3.
 */
@Service
public class ViewSpotService {
    @Autowired
    IviewSpotDao iviewSpotDao;

    public List<ViewSpot> findAllViewSpot() {
        return iviewSpotDao.findAllView();
    }
}
