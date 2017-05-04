package com.myfirst.dao;

import com.myfirst.entitis.Travel;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created by Administrator on 2017/5/4.
 */
@Mapper
public interface ItravelDao {
    String TABLE_NAMEE = "travel";
    String INSERT_FIELD = "travleid,startAddress,endAddress,price,travelType";

    @Insert({"insert into " + TABLE_NAMEE + " ( " + INSERT_FIELD + " ) values (" + "#{travelid}, "
            + "#{startAddress}, " + "#{endAddress}, " + "#{price}, " + "#{travelType} )"})
    int addTravel(Travel travel);

}
