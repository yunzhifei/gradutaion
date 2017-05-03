package com.myfirst.entitis;

import org.springframework.stereotype.Component;

/**
 * Created by yunzh on 2017/5/3.
 */
@Component
public class Hotel {
    int id;
    int hotleId;
    String name;
    String address;
    int price;
    String pictureUrl;
    String description;
    byte isDelete;

    public void setId(int id) {
        this.id = id;
    }

    public void setHotleId(int hotleId) {
        this.hotleId = hotleId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setIsDelete(byte isDelete) {
        this.isDelete = isDelete;
    }

    public int getId() {

        return id;
    }

    public int getHotleId() {
        return hotleId;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public int getPrice() {
        return price;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public String getDescription() {
        return description;
    }

    public byte getIsDelete() {
        return isDelete;
    }
}
