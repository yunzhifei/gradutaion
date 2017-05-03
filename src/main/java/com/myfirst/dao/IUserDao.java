package com.myfirst.dao;

import com.myfirst.entitis.User;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * Created by 58 on 2017/1/17.
 * author yun zhi fei
 */
@Mapper
public interface IUserDao {
    String TABLE_NAME = "user";
    String INSERT_FIELD = "id,username,password,salt,sex,name,address,age,phone,emailAddress,description,userid,userType,isDelete";

    @Select("select * from " + TABLE_NAME + " where id=#{id}")
    User findUserById(@Param("id") int id);

    @Insert("insert into " + TABLE_NAME + " ( " + INSERT_FIELD + " ) " + " values ( null, " + "#{user.userName}, " + "#{user.password}, " + "#{user.salt}, "
            + "#{user.sex}, " + "#{user.name}, " + "#{user.address}, " + "#{user.age}, " + "#{user.phone}, " + "#{user.emailAddress}, " +
            "#{user.description}, " + "#{user.userId}, " + "#{user.userType}, 0)")
    int addUser(@Param("user") User user);

    @Select("select * from " + TABLE_NAME + "where name=#{name}")
    User findUserByName(@Param("name") String name);

}
