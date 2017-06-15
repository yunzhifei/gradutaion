package com.myfirst.entitis;

import org.springframework.stereotype.Component;

/**
 * Created by yun on 2017/5/3
 */
@Component
public class TravelOrder {
    private int id;
    private String bookDate;
    private int personNumber;
    private byte payState;
    private byte orderState;
    private byte isDelete;
    private long userId;
    private int travelId;

    public void setId(int id) {
        this.id = id;
    }

    public void setBookDate(String bookDate) {
        this.bookDate = bookDate;
    }

    public void setPersonNumber(int personNumber) {
        this.personNumber = personNumber;
    }


    public void setIsDelete(byte isDelete) {
        this.isDelete = isDelete;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setTravelId(int travelId) {
        this.travelId = travelId;
    }

    public int getId() {

        return id;
    }

    public String getBookDate() {
        return bookDate;
    }

    public int getPersonNumber() {
        return personNumber;
    }

    public void setPayState(byte payState) {
        this.payState = payState;
    }

    public void setOrderState(byte orderState) {
        this.orderState = orderState;
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

    public long getUserId() {
        return userId;
    }

    public int getTravelId() {
        return travelId;
    }
}
