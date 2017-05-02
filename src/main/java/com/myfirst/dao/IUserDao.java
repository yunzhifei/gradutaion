package com.myfirst.dao;

import com.myfirst.entitis.User;
import org.apache.ibatis.annotations.*;


/**
 * Created by 58 on 2017/1/17.
 * author yun zhi fei
 */
@Mapper
public interface IUserDao {
    String TABLE_NAME = "user";
    @Select("select * from "+TABLE_NAME+" where id=#{id}")
    User findUserById(@Param("id") int id);

   @Insert("insert into "+ TABLE_NAME + "values(" +"null, " + "#{user.userid}, "+"#{user.userName}, "+"#{user.password}, "
   +"#{user.salt}, " +"#{user.Type}, 0" )
    int addUser(@Param("user") User user);



}
