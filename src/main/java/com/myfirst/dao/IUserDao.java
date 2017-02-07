package com.myfirst.dao;

import com.myfirst.entitis.User;
import org.apache.ibatis.annotations.Select;
import org.mybatis.spring.annotation.MapperScan;

/**
 * Created by 58 on 2017/1/17.
 * author yun zhi fei
 */
@MapperScan
public interface IUserDao {
    @Select("select * from user where id=#{id}")
    User findUserById(Integer id);
}
