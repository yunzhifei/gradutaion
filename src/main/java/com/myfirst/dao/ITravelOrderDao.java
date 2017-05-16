package com.myfirst.dao;

import com.myfirst.entitis.TravelOrder;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by Administrator on 2017/5/4.
 * yun zhi fei
 */
@Mapper
public interface ITravelOrderDao {
    String TABLE_NAME = " travelOrder ";
    String INSERT_FIELD = " bookDate,personNumber,payState,orderState,isDelete,userId,travelId ";
    String SELECT_FIELD = "id, " + INSERT_FIELD;

    @Insert({"insert into " + TABLE_NAME + " ( " + INSERT_FIELD + " ) values (" +
            "#{bookDate}, " + "#{personNumber}, " + "#{payState}, " + "#{orderState}, "
            + "#{isDelete}, " + "#{userId}, " + "#{travelId} )"})
    int addTravelOrder(TravelOrder travelOrder);

    @Select({"select " + SELECT_FIELD + " from " + TABLE_NAME + "where userId=#{userId}"})
    List<TravelOrder> findAllTravelOrderByUserId(@Param("userId") int userId);

    @Select({"update " + TABLE_NAME + " set payState=1" + " where id=#{id}"})
    int updateTravelOrderPayStateById(@Param("id") int id);

    @Update({"update " + TABLE_NAME + "set isDelete=1 " + "where id=#{travelOrderId}"})
    int updateTravelOrderIsDelete(@Param("travelOrderId") int travelOrderId);

    @Select({"select " + INSERT_FIELD + " from " + TABLE_NAME + " where id=#{id}"})
    TravelOrder findTravelOrderById(@Param("id") int id);
}
