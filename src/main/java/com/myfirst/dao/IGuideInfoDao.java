package com.myfirst.dao;

import com.myfirst.entitis.GuideInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by yunzh on 2017/5/3.
 */
@Mapper
public interface IGuideInfoDao {
    String TABLE_NAME = "guideInfo";
    String INSERT_FIELD = "name,sex,serverCity,cardId,workYear,priceOfDay,description,isDelete,guideId";
    String SELECT_FIELD = "id," + INSERT_FIELD;

    @Insert({"insert into " + TABLE_NAME + " ( " + INSERT_FIELD + " )  values (" +
            "#{name}, " + "#{sex}, " + "#{cardId}, " + "#{workYear}, " + "#{priceOfDay}, "
            + "#{description}, " + "#{isDelete}, " + "#{guideid}"})
    int addGuideInfo(GuideInfo guideInfo);

    @Select({"select " + SELECT_FIELD + " form " + TABLE_NAME})
    List<GuideInfo> selectAllGuideInfo();

    @Select({"select " + SELECT_FIELD + " from " + TABLE_NAME + "where travelid=#{travelid}"})
    GuideInfo selectGuideInfoById(@Param("travelid") int travelid);

    @Update({"update " + TABLE_NAME + " set isDelete=1" + "where travelid=#{travelid}"})
    int deleteGuideInfoById(@Param("travelid") int travelid);
}
