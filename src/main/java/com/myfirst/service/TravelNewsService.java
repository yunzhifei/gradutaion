package com.myfirst.service;

import com.myfirst.dao.ITravelNewDao;
import com.myfirst.entitis.TravelNews;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/14.
 * yun zhi fei
 */
@Service
public class TravelNewsService {
    @Autowired
    ITravelNewDao travelNewDao;

    public List<TravelNews> findOnePageNews(int size, int page) {
        return travelNewDao.findOnePageNews(page * size, size);
    }

    public int findTravelNewsCount() {
        return travelNewDao.findNewsCount();
    }

    public int addTravelNews(TravelNews travelNews) {
        return travelNewDao.addTravelNew(travelNews);
    }

    public TravelNews findTravelNewsById(int travelNewsId) {
        return travelNewDao.findTravelNewsById(travelNewsId);
    }

    public int deleteTravelNewsById(int travelNewsId, Map<String, Object> responseMap) {
        if (null == findTravelNewsById(travelNewsId)) {
            responseMap.put("error", "资讯不存在或者已经被删除请不要重复删除!");
            return -1;
        }
        return travelNewDao.updateTravelNewsIsDelete(travelNewsId);
    }

}
