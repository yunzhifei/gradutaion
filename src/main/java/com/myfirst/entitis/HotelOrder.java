package com.myfirst.entitis;

import org.springframework.stereotype.Component;

/**
 * Created by yun on 2017/5/3.
 * yun zhi fei
 */
@Component
public class HotelOrder {
    private int id;
    private int hotelId;
    private int personNumber;
    private int userId;
    private byte isDelete;
    private byte payState;
    private byte orderState;

    public void setId(int id) {
        this.id = id;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public void setPersonNumber(int personNumber) {
        this.personNumber = personNumber;
    }


    public void setIsDelete(byte isDelete) {
        this.isDelete = isDelete;
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

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setPayState(byte payState) {
        this.payState = payState;
    }

    public void setOrderState(byte orderState) {
        this.orderState = orderState;
    }

    public int getUserId() {

        return userId;
    }

    public byte getPayState() {
        return payState;
    }

    public byte getOrderState() {
        return orderState;
    }

    public byte getIsDelete() {
        return isDelete;
    }


}
