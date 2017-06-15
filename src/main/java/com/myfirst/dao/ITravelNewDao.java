package com.myfirst.dao;

import com.myfirst.entitis.TravelNews;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by Administrator on 2017/5/14
 */
@Mapper
public interface ITravelNewDao {
    String TABLE_NAME = "  travelNews ";
    String INSERT_FILED = " title,createDate,content,picture ";

    @Select({" select * from " + TABLE_NAME + " where isDelete=0 "+ " limit #{offset},#{rows}"})
    List<TravelNews> findOnePageNews(@Param("offset") int offset, @Param("rows") int rows);

    @Insert({"insert into " + TABLE_NAME + " ( " + INSERT_FILED + " ) values ( " +
            "#{title}, " + "#{createDate}, " + "#{content}, " + "#{picture} )"})
    int addTravelNew(TravelNews travelNews);

    @Select({"select count(1) from " + TABLE_NAME})
    int findNewsCount();

    @Update({"update " + TABLE_NAME + " set isDelete=1 " + " where id=#{id}"})
    int updateTravelNewsIsDelete(@Param("id") int id);

    @Select({"select " + INSERT_FILED + " from " + TABLE_NAME + " where id=#{travelNewsId} and isDelete=0"})
    TravelNews findTravelNewsById(@Param("travelNewsId") int travelNewsId);
}
