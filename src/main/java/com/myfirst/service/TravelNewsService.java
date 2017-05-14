package com.myfirst.service;

import com.myfirst.dao.ITravelNewDao;
import com.myfirst.entitis.TravelNews;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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


}
