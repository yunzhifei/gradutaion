package com.myfirst.service;

import com.myfirst.dao.ItravelDao;
import com.myfirst.entitis.Travel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/4.
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

    //删除出行方式信息
    public int deleteTravelById(int travelId, Map<String, Object> responseMap) {
        if (null == findTravelById(travelId)) {
            responseMap.put("error", "出行方式已经删除，或者不存在，请不要重复删除！");
            return -1;
        }
        travelDao.updateTravelIsDeleteById(travelId);
        return 0;
    }
}
