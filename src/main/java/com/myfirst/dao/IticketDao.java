package com.myfirst.dao;

import com.myfirst.entitis.LoginTicket;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * Created by yunzh on 2017/5/3.
 */
@Mapper
public interface IticketDao {
    String TABLE_NAME = "loginticket";
    String INSERT_FILED = "userid,status,ticket,expire";

    @Insert({"insert into " + TABLE_NAME + " ( " + INSERT_FILED + " ) " + "values ( 0" +
            "#{userId}" + "#{status}"
            + "#{ticket}" + "#{expire}"})
    int addTicket(LoginTicket loginTicket);

    @Update({"update " + TABLE_NAME + "expire=1 where ticket=#{ticket}"})
    int deleteTicket(@Param("ticket") String ticket);
}
