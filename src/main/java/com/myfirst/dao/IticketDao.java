package com.myfirst.dao;

import com.myfirst.entitis.LoginTicket;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created by yunzh on 2017/5/3.
 */
@Mapper
public interface IticketDao {
    String TABLE_NAME = "loginticket";
    String INSERT_FILED = "id,userid,status,ticket,expire";

    @Insert("insert into " + TABLE_NAME + " ( " + INSERT_FILED + " ) " + "values ( 0" +
            "#{loginTicket.userId}" + "#{loginTicket.status}"
            + "#{loginTicket.ticket}" + "#{loginTicket.expire}")
    int addTicket(@Param("loginTicket") LoginTicket loginTicket);
}
