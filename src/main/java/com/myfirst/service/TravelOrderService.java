package com.myfirst.service;

import com.myfirst.dao.ITravelOrderDao;
import com.myfirst.entitis.TravelOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/5/4.
 * yun zhi fei
 */
@Service
public class TravelOrderService {
    @Autowired
    ITravelOrderDao travelOrderDao;

    //预订交通方式
    public int bookTravel(TravelOrder travelOrder) {
        return travelOrderDao.addTravelOrder(travelOrder);
    }

    //支付交通费用
    public int payTravel(int id) {
        return travelOrderDao.updateTravelOrderPayStateById(id);
    }

    //展示该用户下的所有的预订过的方式
    public List<TravelOrder> findAllTravelOrderByUserId(int userId) {
        return travelOrderDao.findAllTravelOrderByUserId(userId);
    }
}
