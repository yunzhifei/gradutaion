package com.myfirst.dao;

import com.myfirst.entitis.User;
import org.apache.ibatis.annotations.*;


/**
 * Created by 58 on 2017/1/17.
 * author yun zhi fei
 */
@Mapper
public interface IUserDao {
    String TABLE_NAME = " user ";
    String INSERT_FIELD = " username,password,salt,sex,name,address,age,phone,emailAddress,description,userId,userType,isDelete ";

    @Select("select * from " + TABLE_NAME + " where id=#{id}")
    User findUserById(@Param("id") int id);

    @Insert({"insert into " + TABLE_NAME + " ( " + INSERT_FIELD + " ) " + " values ( " + "#{userName}, " + "#{password}, " + "#{salt}, "
            + "#{sex}, " + "#{name}, " + "#{address}, " + "#{age}, " + "#{phone}, " + "#{emailAddress}, " +
            "#{description}, " + "#{userId}, " + "#{userType}, 0)"})
    int addUser(User user);

    @Select({"select * from " + TABLE_NAME + " where userName=#{userName}"})
    User findUserByName(@Param("userName") String userName);

    @Update({"update " + TABLE_NAME + "set userName=#{userName}, " + "name=#{name}, " +
            "sex=#{sex}, " + "age=#{age}, " + "emailAddress=#{emailAddress}, " +
            "phone=#{phone}, " + "description=#{description}" + " where id=#{id}"})
    int updateUserInfo(User user);

    @Update({"update " + TABLE_NAME + "set passWord=#{newPassWord} " + "where id=#{id}"})
    int updateUserPassWord(@Param("id") int id, @Param("newPassWord") String newPassWord);
}
