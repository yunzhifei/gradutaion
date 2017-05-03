package com.myfirst.entitis;

import org.springframework.stereotype.Component;

/**
 * Created by yunzh on 2017/5/3.
 */
@Component
public class TravelOrder {
    private int id;
    private String bookDate;
    private int personNumber;
    private byte paystate;
    private byte orderstate;
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

    public void setPaystate(byte paystate) {
        this.paystate = paystate;
    }

    public void setOrderstate(byte orderstate) {
        this.orderstate = orderstate;
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

    public byte getPaystate() {
        return paystate;
    }

    public byte getOrderstate() {
        return orderstate;
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
