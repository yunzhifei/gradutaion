package com.myfirst.dao;

import com.myfirst.entitis.User;
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
    User findUserById(@Param("id") Integer id);
}
