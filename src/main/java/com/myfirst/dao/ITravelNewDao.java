package com.myfirst.dao;

import com.myfirst.entitis.TravelNews;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by Administrator on 2017/5/14.
 * yun zhi fei
 */
@Mapper
public interface ITravelNewDao {
    String TABLE_NAME = "  travelNews ";
    String INSERT_FILED = " title,createDate,content,picture ";

    @Select({" select * from " + TABLE_NAME + " limit #{offset},#{rows}"})
    List<TravelNews> findOnePageNews(@Param("offset") int offset, @Param("rows") int rows);

    @Insert({"insert into " + TABLE_NAME + " ( " + INSERT_FILED + " ) values ( " +
            "#{title}, " + "#{createDate}, " + "#{content}, " + "#{picture} )"})
    int addTravelNew(TravelNews travelNews);

    @Select({"select count(1) from " + TABLE_NAME})
    int findNewsCount();
}
