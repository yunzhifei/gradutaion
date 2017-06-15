package com.myfirst.entitis;

import org.springframework.stereotype.Component;

/**
 * Created by 58 on 2017/1/17
 */
@Component
public class User {
    private int id;
    private String userName;
    private String password;
    private String salt;
    private String name;
    private String address;
    private short userType;
    private int age;
    private String phone;
    private String emailAddress;
    private String description;
    private short isDelete;
    String sex;

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSex() {

        return sex;
    }

    public void setId(int id) {
        this.id = id;
    }


    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setUserType(short userType) {
        this.userType = userType;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
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


    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getSalt() {
        return salt;
    }


    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public short getUserType() {
        return userType;
    }

    public int getAge() {
        return age;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getDescription() {
        return description;
    }

    public short getIsDelete() {
        return isDelete;
    }


}
