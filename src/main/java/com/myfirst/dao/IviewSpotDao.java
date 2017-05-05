package com.myfirst.dao;

import com.myfirst.entitis.ViewSpot;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by yun on 2017/5/3.
 * yun zhi fei
 */
@Mapper
public interface IviewSpotDao {
    String TABLE_NAME = "viewspot";
    String INSERT_FILELD = "viewName,address,phone,website,pictureUrl,description,isDelete";

    @Select({"select * from " + TABLE_NAME + "where isDelete=0"})
    List<ViewSpot> findAllView();

    @Insert({"insert into " + TABLE_NAME + " ( " + INSERT_FILELD + " ) values (" +
            " #{viewName}, " + "#{address}, " + "#{phone}, " + "#{website}, " +
            "#{pictureUrl}, " + "#{description}, " + "#{isDelete} )"})
    int addViewSpot(ViewSpot viewSpot);

    @Update({"update " + TABLE_NAME + "set isDelete=1 " + "where id=#{id}"})
    int deleteViewSpot(@Param("id") int id);

    @Select({"select * from" + TABLE_NAME + " where id=#{ids}"})
    ViewSpot findViewSpotById(@Param("id") int id);
}
