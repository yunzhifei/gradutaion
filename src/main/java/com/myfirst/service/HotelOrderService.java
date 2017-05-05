package com.myfirst.service;

import com.myfirst.dao.IHotelOderDao;
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
}
