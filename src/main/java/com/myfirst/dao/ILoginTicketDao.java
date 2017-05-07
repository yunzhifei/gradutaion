package com.myfirst.dao;

import com.myfirst.entitis.LoginTicket;
import org.apache.ibatis.annotations.*;

/**
 * Created by Administrator on 2017/5/4.
 * yun zhi fei
 */
@Mapper
public interface ILoginTicketDao {
    String TABLE_NAME = " loginticket ";
    String INSERT_FIELD = " expired,status,ticket,userId ";
    String SELECT_FIELD = " id," + INSERT_FIELD;

    @Insert({"insert into " + TABLE_NAME + " ( " + INSERT_FIELD + " ) values (" +
            "#{expired}, " + "#{status}, " + "#{ticket}, " + "#{userId} )"})
    int addLoginTicket(LoginTicket loginTicket);

    @Update({"update " + TABLE_NAME + "set expired=1 " + "where ticket=#{ticket}"})
    int deleteTicket(@Param("ticket") String ticket);

    @Select("select " + SELECT_FIELD + " from " + TABLE_NAME + "where userId=#{userId} and expired=0")
    LoginTicket findLoginTicketByUserId(@Param("userId") int userId);
}
