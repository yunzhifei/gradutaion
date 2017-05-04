package com.myfirst.dao;

import com.myfirst.entitis.LoginTicket;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * Created by Administrator on 2017/5/4.
 * yun zhi fei
 */
@Mapper
public interface ILoginTicketDao {
    String TABLE_NAME = "loginticket";
    String INSERT_FIELD = "expired,status,ticket,userid";
    String SELECT_FIELD = "id," + INSERT_FIELD;

    @Select({"insert into " + TABLE_NAME + " ( " + INSERT_FIELD + " ) values (" +
            "#{expired}, " + "#{ticket}, " + "#{status}, " + "#{userid} )"})
    int addLoginTicket(LoginTicket loginTicket);

    @Update({"update " + TABLE_NAME + "set expired=1" + "where ticket=#{ticket}"})
    int deleteTicket(@Param("ticket") String ticket);
}
