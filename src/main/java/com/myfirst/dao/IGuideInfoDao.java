package com.myfirst.dao;

import com.myfirst.entitis.GuideInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by yunzh on 2017/5/3.
 */
@Mapper
public interface IGuideInfoDao {
    String TABLE_NAME = "guideinfo";
    String INSERT_FIELD = "name,sex,serverCity,cardId,workYear,priceOfDay,description,isDelete,guideId";
    String SELECT_FIELD = "id," + INSERT_FIELD;

    @Insert("insert into " + TABLE_NAME + " ( " + INSERT_FIELD + " )  values (" +
            "#{name}, " + "#{sex}, " + "#{cardId}, " + "#{workYear}, " + "#{priceOfDay}, "
            + "#{description}, " + "#{isDelete}, " + "#{guideid}")
    int addGuideInfo(@Param("guideInfo") GuideInfo guideInfo);

    @Select("select * form " + TABLE_NAME)
    List<GuideInfo> selectAllGuideInfo();



}
