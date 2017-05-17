package com.myfirst.service;

import com.myfirst.dao.IHotelOderDao;
import com.myfirst.entitis.HosHolder;
import com.myfirst.entitis.HotelOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<HotelOrder> findAllPayedHotelOrderByUserId(int userId) {
        return hotelOderDao.selectUserPayedHotelOrderByUserId(userId);
    }
}
