package com.myfirst.service;

import com.myfirst.dao.IGuideInfoDao;
import com.myfirst.entitis.GuideInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/5/4.
 * yun zhi fei
 */
@Service
public class GuideService {
    @Autowired
    IGuideInfoDao guideInfoDao;

    //添加导游信息
    public int addGuideInfo(GuideInfo guideInfo) {
        return guideInfoDao.addGuideInfo(guideInfo);
    }

    //展示导游信息
    public List<GuideInfo> showGuideInfo() {
        return guideInfoDao.selectAllGuideInfo();
    }

    //展示具体每一天导游信息
    public GuideInfo showGuideInfoByid(int travelid) {
        return guideInfoDao.selectGuideInfoById(travelid);
    }

    //删除导游信息
    public int deleteGuideInfo(int travelid) {
        return guideInfoDao.deleteGuideInfoById(travelid);
    }
}
