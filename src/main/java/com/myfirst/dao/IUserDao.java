package com.myfirst.dao;

import com.myfirst.entitis.User;
import org.apache.ibatis.annotations.*;


/**
 * Created by 58 on 2017/1/17.
 * author yun zhi fei
 */
@Mapper
public interface IUserDao {
    @Select("select * from user where id=#{id}")
    User findUserById(@Param("id") int id);

    @Insert({"insert into loginticket(userId,status,expired,ticket) values(#{userId},#{status},#{expired},#{ticket})"})
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int login(@Param("userId") String userId, @Param("status") int status, @Param("ticket") String ticket, @Param("expired") Boolean expired);

}
