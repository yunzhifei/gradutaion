package com.myfirst.entitis;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

/**
 * Created by yunzh on 2017/5/3.
 */
@Component
public class ViewSpot {
    int id;
    String viewName;
    String address;
    String phone;
    String website;

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getPictureUrl() {

        return pictureUrl;
    }

    String pictureUrl;
    String description;
    short isDelete;

    public void setId(int id) {
        this.id = id;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setWebsite(String website) {
        this.website = website;
    }


    public void setDescription(String description) {
        this.description = description;
    }

    public void setIsDelete(short isDelete) {
        this.isDelete = isDelete;
    }

    public int getId() {

        return id;
    }

    public String getViewName() {
        return viewName;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getWebsite() {
        return website;
    }


    public String getDescription() {
        return description;
    }

    public short getIsDelete() {
        return isDelete;
    }
}
