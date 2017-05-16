package com.myfirst.dao;

import com.myfirst.entitis.Hotel;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by yunzh on 2017/5/3.
 */
@Mapper
public interface IHotelDao {
    String TABLE_NAME = " hotel ";
    String INSERT_FILED = " name,address,price,pictureUrl,Description,isDelete ";

    @Select({"select * from " + TABLE_NAME})
    List<Hotel> findAllHotel();

    @Select({" select * from " + TABLE_NAME + " limit #{offset},#{rows}"})
    List<Hotel> findOnePageHotel(@Param("offset") int offset, @Param("rows") int rows);

    @Insert({"insert into " + TABLE_NAME + " ( " + INSERT_FILED + " ) values ( " +
            "#{name}, " + "#{address}, " + "#{price}, " +
            "#{pictureUrl}, " + "#{description}, " + "#{isDelete} )"})
    int addHotel(Hotel hotel);

    @Select({"select * from " + TABLE_NAME + " where id=#{id} and isDelete=0"})
    Hotel findHotelById(@Param("id") int id);

    @Update({"update " + TABLE_NAME + "isDelete=1 where hotelId=#{hotelId}"})
    int updateHotel(@Param("hotelId") int hotelId);


    @Select({"select count(1) from " + TABLE_NAME})
    int findHotelCount();
}
