package com.myfirst.dao;

import com.myfirst.entitis.ViewSpot;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by yunzh on 2017/5/3.
 */
@Mapper
public interface IviewSpotDao {
    String TABLE_NAME = "viewspot";
    String INSERT_FILELD = "id,viewName,address,phone,website,pictureUrl,description,isDelete";

    @Select({"select * from " + TABLE_NAME})
    List<ViewSpot> findAllView();
}
