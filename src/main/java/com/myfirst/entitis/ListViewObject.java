package com.myfirst.entitis;

/**
 * Created by Administrator on 2017/5/13.
 */
public class ListViewObject<T> {
    int id;
    String title;
    String content;
    String img;

    public void setImg(String img) {
        this.img = img;
    }

    public String getImg() {

        return img;
    }

    T entity;

    public void setEntity(T entity) {
        this.entity = entity;
    }

    public T getEntity() {

        return entity;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getId() {

        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
