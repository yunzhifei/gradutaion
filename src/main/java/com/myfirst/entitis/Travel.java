package com.myfirst.entitis;

import org.springframework.stereotype.Component;

/**
 * Created by yun on 2017/5/3.
 * yun zhi fei
 */
@Component
public class Travel {
    private int id;
    private String startAddress;
    private String endAddress;
    private int price;
    private String travelType;

    public void setId(int id) {
        this.id = id;
    }


    public void setStartAddress(String startAddress) {
        this.startAddress = startAddress;
    }

    public void setEndAddress(String endAddress) {
        this.endAddress = endAddress;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setTravelType(String travelType) {
        this.travelType = travelType;
    }

    public int getId() {
        return id;
    }

    public String getStartAddress() {
        return startAddress;
    }

    public String getEndAddress() {
        return endAddress;
    }

    public int getPrice() {
        return price;
    }

    public String getTravelType() {
        return travelType;
    }

}
