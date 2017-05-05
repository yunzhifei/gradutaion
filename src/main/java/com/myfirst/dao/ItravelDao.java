package com.myfirst.dao;

import com.myfirst.entitis.Travel;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by Administrator on 2017/5/4.
 * yun zhi fei
 */
@Mapper
public interface ItravelDao {
    String TABLE_NAMEE = "travel";
    String INSERT_FIELD = "travelId,startAddress,endAddress,price,travelType";
    String SELECT_FIELD = "id," + INSERT_FIELD;

    @Insert({"insert into " + TABLE_NAMEE + " ( " + INSERT_FIELD + " ) values (" + "#{travelid}, "
            + "#{startAddress}, " + "#{endAddress}, " + "#{price}, " + "#{travelType} )"})
    int addTravel(Travel travel);


    @Select({"select " + SELECT_FIELD + " from " + TABLE_NAMEE})
    List<Travel> findAllTravel();

    @Select({"select " + SELECT_FIELD + " from " + TABLE_NAMEE + "where id=#{id}"})
    Travel findTravelById(@Param("id") int id);
}
