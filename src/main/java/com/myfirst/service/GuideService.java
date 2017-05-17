package com.myfirst.service;

import com.myfirst.dao.IGuideInfoDao;
import com.myfirst.entitis.GuideInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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
    public List<GuideInfo> showGuideInfo(int size, int page) {
        return guideInfoDao.selectAllGuideInfo(page * size, size);
    }

    //展示具体每一个导游信息
    public GuideInfo showGuideInfoByid(int travelid) {
        return guideInfoDao.selectGuideInfoById(travelid);
    }

    //删除导游信息
    public int deleteGuideInfo(int travelid, Map<String, Object> responseMap) {
        if (null == showGuideInfoByid(travelid)) {
            responseMap.put("error", "导游信息不存在或者已经删除,请不要重复删除！");
            return -1;
        }
        return guideInfoDao.deleteGuideInfoById(travelid);
    }

    //获取导游总数
    public int findGuideInfoCount() {
        return guideInfoDao.findGuideInfoCount();
    }
}
