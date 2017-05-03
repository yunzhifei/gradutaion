package com.myfirst.entitis;

import org.springframework.stereotype.Component;

/**
 * Created by yunzh on 2017/5/3.
 */
@Component
public class HotelOrder {
    private int id;
    private int hotelId;
    private int personNumber;
    private int userid;
    private byte isDelete;
    private byte paystate;
    private byte orderstate;

    public void setId(int id) {
        this.id = id;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public void setPersonNumber(int personNumber) {
        this.personNumber = personNumber;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public void setIsDelete(byte isDelete) {
        this.isDelete = isDelete;
    }

    public void setPaystate(byte paystate) {
        this.paystate = paystate;
    }

    public void setOrderstate(byte orderstate) {
        this.orderstate = orderstate;
    }

    public int getId() {

        return id;
    }

    public int getHotelId() {
        return hotelId;
    }

    public int getPersonNumber() {
        return personNumber;
    }

    public int getUserid() {
        return userid;
    }

    public byte getIsDelete() {
        return isDelete;
    }

    public byte getPaystate() {
        return paystate;
    }

    public byte getOrderstate() {
        return orderstate;
    }
}
