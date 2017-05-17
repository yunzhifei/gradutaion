package com.myfirst.service;

import com.myfirst.dao.IHotelOderDao;
import com.myfirst.entitis.HosHolder;
import com.myfirst.entitis.HotelOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/4.
 * yun zhi fei
 */
@Service
public class HotelOrderService {
    @Autowired
    IHotelOderDao hotelOderDao;

    //通过用户id查找所有的旅馆订单信息
    public List<HotelOrder> findAllHotelOrderByUserId(int userId) {
        return hotelOderDao.findAllHotelOrderByUserId(userId);
    }

    //预订旅馆
    public int bookHotel(HotelOrder hotelOrder) {
        return hotelOderDao.addHotelOrder(hotelOrder);
    }

    //支付旅馆费用
    public int payHotelOrder(int id) {
        return hotelOderDao.updateHotelOrderPayStateById(id);
    }

    //找到一个用户所有未支付的旅馆订单
    public List<HotelOrder> findAllUnPayHotelOrderByUserId(int userId) {
        return hotelOderDao.selectUserUnPayHotelOrderByUserId(userId);
    }

    //未支付总数
    public int findCountUnpayHotelOrder(int userId) {
        return hotelOderDao.selectCountUserUnpayHotelOrderByUserId(userId);
    }

    //找到一个用户所有已支付的旅馆订单
    public List<HotelOrder> findAllPayedHotelOrderByUserId(int userId) {
        return hotelOderDao.selectUserPayedHotelOrderByUserId(userId);
    }

    //已支付总数
    public int findCountPayedHotelOrde(int userId) {
        return hotelOderDao.selectCountUserPayedHotelOrderByUserId(userId);
    }

    //通过订单id找到旅馆的订单
    public HotelOrder findHotelOrderById(int id) {
        return hotelOderDao.findHotelOrderById(id);
    }

    public int deleteHoteOrderById(int hoteOrderlId, Map<String, Object> responseMap) {
        if (null == findHotelOrderById(hoteOrderlId)) {
            responseMap.put("error", "订单已经成功删除，或者订单不存在！请不要重复删除");
        }
        return hotelOderDao.updateHotelOrderIsDeleteById(hoteOrderlId);
    }
}
