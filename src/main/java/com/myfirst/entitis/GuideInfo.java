package com.myfirst.entitis;

import org.springframework.stereotype.Component;

/**
 * Created by yunzh on 2017/5/3.
 */
@Component
public class GuideInfo {
    private int id;
    private String name;
    private byte sex;
    private String serverCity;
    private String cardId;
    private String workYear;
    private String priceOfDay;
    private String description;
    private byte isDelete;
    private int guideId;

    public void setName(String name) {
        this.name = name;
    }

    public void setSex(byte sex) {
        this.sex = sex;
    }

    public void setServerCity(String serverCity) {
        this.serverCity = serverCity;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public void setWorkYear(String workYear) {
        this.workYear = workYear;
    }


    public void setDescription(String description) {
        this.description = description;
    }

    public void setIsDelete(byte isDelete) {
        this.isDelete = isDelete;
    }

    public void setGuideId(int guideId) {
        this.guideId = guideId;
    }

    public int getId() {

        return id;
    }

    public String getName() {
        return name;
    }

    public byte getSex() {
        return sex;
    }

    public String getServerCity() {
        return serverCity;
    }

    public String getCardId() {
        return cardId;
    }

    public String getWorkYear() {
        return workYear;
    }

    public void setPriceOfDay(String priceOfDay) {
        this.priceOfDay = priceOfDay;
    }

    public String getPriceOfDay() {

        return priceOfDay;
    }

    public String getDescription() {
        return description;
    }

    public byte getIsDelete() {
        return isDelete;
    }

    public int getGuideId() {
        return guideId;
    }

    public void setId(int id) {

        this.id = id;
    }
}
