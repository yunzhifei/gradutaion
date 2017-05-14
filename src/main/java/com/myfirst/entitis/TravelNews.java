package com.myfirst.entitis;

/**
 * Created by Administrator on 2017/5/14.
 */
public class TravelNews {
    private int id;
    private String title;
    private String createDate;
    private String content;
    private String picture;

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public int getId() {

        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getCreateDate() {
        return createDate;
    }

    public String getContent() {
        return content;
    }

    public String getPicture() {
        return picture;
    }
}
