package com.myfirst.entitis;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by 58 on 2017/1/17.
 * author yunzhifei
 * author yun zhi fei
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int id;
    private long userId;
    private String userName;
    private String password;
    private String salt;
    private byte sex;
    private String name;
    private String address;
    private short userType;
    private int age;
    private String phone;
    private String email;
    private String description;
    private short isDelete;

}
