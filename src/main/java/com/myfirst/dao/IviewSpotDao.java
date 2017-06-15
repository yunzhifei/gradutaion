package com.myfirst.dao;

import com.myfirst.entitis.ViewSpot;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by yun on 2017/5/3.
 */
@Mapper
public interface IviewSpotDao {
    String TABLE_NAME = " viewSpot ";
    String INSERT_FILED = " viewName,address,phone,website,pictureUrl,description,isDelete ";
    String SELECT_FIELD = "id, " + INSERT_FILED;

    @Select({"select * from " + TABLE_NAME + " where isDelete=0"})
    List<ViewSpot> findAllView();

    @Insert({"insert into " + TABLE_NAME + " ( " + INSERT_FILED + " ) values (" +
            " #{viewName}, " + "#{address}, " + "#{phone}, " + "#{website}, " +
            "#{pictureUrl}, " + "#{description}, " + "#{isDelete} )"})
    int addViewSpot(ViewSpot viewSpot);


    @Select({"select * from" + TABLE_NAME + " where id=#{id}"})
    ViewSpot findViewSpotById(@Param("id") int id);

    @Select({"select " + SELECT_FIELD + " from " + TABLE_NAME + " where isDelete=0 limit #{offset},#{rows}"})
    List<ViewSpot> findOnePageViewSpot(@Param("offset") int offset, @Param("rows") int rows);

    @Select({"select count(1) " + " from " + TABLE_NAME + " where isDelete=0"})
    int findViewSpotCount();

    @Update({"update " + TABLE_NAME + " set isDelete=1 " + "where id=#{id}"})
    int deleteViewSpotById(@Param("id") int id);
}
