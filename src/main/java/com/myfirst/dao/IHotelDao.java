package com.myfirst.dao;

import com.myfirst.entitis.Hotel;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by yunzh on 2017/5/3.
 */
@Mapper
public interface IHotelDao {
    String TABLE_NAME = "hotel";
    String INSERT_FILED = "id,hotelId,name,address,price,pictureUrl,Description,isDelete";

    @Select("select * from " + TABLE_NAME)
    List<Hotel> findAllHotel();

    @Insert("insert into " + TABLE_NAME + " ( " + INSERT_FILED + " ) values ( null," +
            "#{hotelId}, " + "#{name}, " + "#{address}, " + "#{price}, " +
            "#{price}, " + "#{pictureUrl}, " + "#{description}, " + "#{isDelete} )")
    int addHotel(@Param("hotel") Hotel hotel);

    @Select("select * from " + TABLE_NAME + " where id=#{id}")
    Hotel findHotelById(@Param("id") int id);
}
