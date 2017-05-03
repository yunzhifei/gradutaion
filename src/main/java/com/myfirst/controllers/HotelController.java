package com.myfirst.controllers;

import com.myfirst.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yunzh on 2017/5/3.
 */
@RestController
public class HotelController {
    @Autowired
    HotelService hotelService;
}
