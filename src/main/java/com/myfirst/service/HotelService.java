package com.myfirst.service;

import com.myfirst.dao.IHotelDao;
import com.myfirst.entitis.Hotel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yunzh on 2017/5/3.
 */
@Service
public class HotelService {
    @Autowired
    IHotelDao hotelDao;

    public Hotel findHotelById(int id) {
        return hotelDao.findHotelById(id);
    }

    public List<Hotel> findAllHotel() {
        return hotelDao.findAllHotel();
    }

    public int addHotel(Hotel hotel) {
        return hotelDao.addHotel(hotel);
    }

    public int deleteHotel(int hotelId) {
        return hotelDao.updateHotel(hotelId);
    }
}
