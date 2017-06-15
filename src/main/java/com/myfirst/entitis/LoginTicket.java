package com.myfirst.entitis;

import org.springframework.stereotype.Component;

/**
 * Created by 58 on 2017/2/9.
 */
@Component
public class LoginTicket {
    int id;

    public void setId(int id) {
        this.id = id;
    }

    public void setExpired(short expired) {
        this.expired = expired;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public short getExpired() {
        return expired;
    }

    public int getStatus() {
        return status;
    }

    public String getTicket() {
        return ticket;
    }

    public long getUserId() {
        return userId;
    }

    short expired;
    int status;
    String ticket;
    long userId;
}
