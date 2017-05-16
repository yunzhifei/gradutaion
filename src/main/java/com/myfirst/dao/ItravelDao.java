package com.myfirst.dao;

import com.myfirst.entitis.Travel;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by Administrator on 2017/5/4.
 * yun zhi fei
 */
@Mapper
public interface ItravelDao {
    String TABLE_NAME = " travel ";
    String INSERT_FIELD = " startAddress,endAddress,price,travelType ";
    String SELECT_FIELD = " id, " + INSERT_FIELD;

    @Insert({"insert into " + TABLE_NAME + " ( " + INSERT_FIELD + " ) values (" +
            "#{startAddress}, " + "#{endAddress}, " + "#{price}, " + "#{travelType} )"})
    int addTravel(Travel travel);


    @Select({"select " + SELECT_FIELD + " from " + TABLE_NAME + " limit #{offset},#{rows}"})
    List<Travel> findOnePageTravel(@Param("offset") int offset, @Param("rows") int rows);

    @Select({"select " + SELECT_FIELD + " from " + TABLE_NAME + "where id=#{id} and isDelete=0"})
    Travel findTravelById(@Param("id") int id);

    @Select({"select count(1) from " + TABLE_NAME})
    int findCount();

    @Update({" update " + TABLE_NAME + " set isDelete=1 " + " where id=#{travelId}"})
    int updateTravelIsDeleteById(@Param("travelId") int id);

}
