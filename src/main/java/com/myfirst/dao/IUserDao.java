package com.myfirst.dao;

import com.myfirst.entitis.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


/**
 * Created by 58 on 2017/1/17.
 * author yun zhi fei
 */
@Mapper
public interface IUserDao {
    @Select("select * from user where id=#{id}")
    User findUserById(@Param("id") int id);

    @Insert({"insert into loginticket(userId,status,expired,ticket) values(#{userId},#{status},#{expired},#{ticket})"})
    int login(@Param("userId")String userId,@Param("status")int status,@Param("ticket")String ticket,@Param("expired")Boolean expired);

}
