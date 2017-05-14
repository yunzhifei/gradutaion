package com.myfirst.service;

import com.myfirst.dao.IHotelDao;
import com.myfirst.entitis.Hotel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yun zhi fei on 2017/5/3.
 * yun zhi fei
 */
@Service
public class HotelService {
    @Autowired
    IHotelDao hotelDao;

    public Hotel findHotelById(int id) {
        return hotelDao.findHotelById(id);
    }

    //展示所有的旅馆信息
    public List<Hotel> findAllHotel() {
        return hotelDao.findAllHotel();
    }

    //添加旅馆信息
    public int addHotel(Hotel hotel) {
        return hotelDao.addHotel(hotel);
    }

    //删除旅馆信息
    public int deleteHotel(int hotelId) {
        return hotelDao.updateHotel(hotelId);
    }


    //分页展示旅馆信息
    public List<Hotel> findOnePageHotel(int size, int page) {
        return hotelDao.findOnePageHotel(page * size, size);
    }
    //获取旅馆总数

    public int findHotelCount() {
        return hotelDao.findHotelCount();
    }

}
