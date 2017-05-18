package com.myfirst.service;

import com.myfirst.dao.ITravelOrderDao;
import com.myfirst.entitis.HosHolder;
import com.myfirst.entitis.TravelOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/4.
 * yun zhi fei
 */
@Service
public class TravelOrderService {
    @Autowired
    ITravelOrderDao travelOrderDao;
    @Autowired
    HosHolder hosHolder;

    //预订交通方式
    public int bookTravel(TravelOrder travelOrder) {
        int userId = hosHolder.getUser().getId();
        travelOrder.setUserId(userId);
        return travelOrderDao.addTravelOrder(travelOrder);
    }

    //通过id找到订单
    public TravelOrder findTravelOrderById(int id) {
        return travelOrderDao.findTravelOrderById(id);
    }

    //支付交通费用
    public int payTravel(int id, Map<String, Object> responseMap) {
        TravelOrder travelOrder = findTravelOrderById(id);
        if (travelOrder.getPayState() == 1) {
            responseMap.put("error", "订单已经支付过请不要重新支付");
            return -1;
        }
        return travelOrderDao.updateTravelOrderPayStateById(id);
    }

    //展示该用户下的所有的预订过的方式
    public List<TravelOrder> findAllTravelOrderByUserId(int userId) {
        return travelOrderDao.findAllTravelOrderByUserId(userId);
    }

    //删除用户的订单
    public int deleteTravelOrderById(int travelOrderId, Map<String, Object> responseMap) {
        if (null == findTravelOrderById(travelOrderId)) {
            responseMap.put("error", "订单不存在或者已经删除过，请不要重复删除！");
            return -1;
        }
        travelOrderDao.updateTravelOrderIsDelete(travelOrderId);
        return 0;
    }

    //查询该个用户的所有未支付的出行订单
    public List<TravelOrder> findAllUserUnPayTravelOrderByUserId(int userId, int size, int page) {
        return travelOrderDao.selectUserUnPayTravelOrderByUserId(userId, (page - 1) * size, size);
    }

    //查询所有已经支付过的出行订单
    public List<TravelOrder> findAllUserPayedTravelOrderByUserId(int userId, int size, int page) {
        return travelOrderDao.selectUserPayedTravelOrderByUserId(userId, (page ) * size, size);
    }

    //未支付的出行订单总数
    public int findCountUserUnpayTravelOrderByUserId(int userId) {
        return travelOrderDao.selectCountUserUnpayTravelOrderByUserId(userId);
    }

    //已经支付的出行订单总数
    public int findCountUserPayedTravelOrderByUserId(int userId) {
        return travelOrderDao.selectCountUserPayedTravelOrderByUserId(userId);
    }

}
