package com.myfirst.dao;

import com.myfirst.entitis.User;
import org.apache.ibatis.annotations.*;

import java.util.List;


/**
 * Created by 58 on 2017/1/17.
 * author yun zhi fei
 */
@Mapper
public interface IUserDao {
    String TABLE_NAME = " user ";
    String INSERT_FIELD = " username,password,salt,sex,name,address,age,phone,emailAddress,description,userType,isDelete ";
    String SELECT_FIELD = "id, " + INSERT_FIELD;

    @Select("select * from " + TABLE_NAME + " where id=#{id}")
    User findUserById(@Param("id") int id);

    @Insert({"insert into " + TABLE_NAME + " ( " + INSERT_FIELD + " ) " + " values ( " + "#{userName}, " + "#{password}, " + "#{salt}, "
            + "#{sex}, " + "#{name}, " + "#{address}, " + "#{age}, " + "#{phone}, " + "#{emailAddress}, " +
            "#{description}, " + "#{userType}, 0)"})
    int addUser(User user);

    @Select({"select * from " + TABLE_NAME + " where userName=#{userName}"})
    User findUserByName(@Param("userName") String userName);

    @Update({"update " + TABLE_NAME + "set userName=#{userName}, " + "name=#{name}, " +
            "sex=#{sex}, " + "age=#{age}, " + "emailAddress=#{emailAddress}, " +
            "phone=#{phone}, " + "description=#{description}" + " where id=#{id}"})
    int updateUserInfo(User user);

    @Update({"update " + TABLE_NAME + "set passWord=#{newPassWord} " + "where id=#{id}"})
    int updateUserPassWord(@Param("id") int id, @Param("newPassWord") String newPassWord);

    @Update({"update " + TABLE_NAME + "set isDelete=1 " + "where id=#{userId}"})
    int updateUserIsDeleteById(@Param("userId") int userId);

    @Select({"select " + SELECT_FIELD + " from " + TABLE_NAME + " where isDelete=0 limit #{offset},#{rows}"})
    List<User> findUserUndelete(@Param("offset") int offset, @Param("rows") int rows);

    @Select({" select count(1) " + " from " + TABLE_NAME  + " where isDelete=0"})
    int findUserCount();
}
