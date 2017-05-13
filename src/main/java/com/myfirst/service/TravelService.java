package com.myfirst.service;

import com.myfirst.dao.ItravelDao;
import com.myfirst.entitis.Travel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/5/4.
 * yun zhi fei
 */
@Service
public class TravelService {
    @Autowired
    ItravelDao travelDao;

    //展示所有的出行方式信息
    public List<Travel> findAllTravel(int size, int page) {
        return travelDao.findOnePageTravel((page - 1) * size, size);
    }

    //添加出行方式信息
    public int addTravel(Travel travel) {
        return travelDao.addTravel(travel);
    }

    //选出指定的某条出行方式信息
    public Travel findTravelById(int id) {
        return travelDao.findTravelById(id);
    }

    public int findTravelCount() {
        return travelDao.findCount();
    }
}
