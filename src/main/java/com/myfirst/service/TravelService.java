package com.myfirst.service;

import com.myfirst.dao.ItravelDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/5/4.
 * yun zhi fei
 */
@Service
public class TravelService {
    @Autowired
    ItravelDao travelDao;


}
