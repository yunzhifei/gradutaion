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
    private short userType;
    private short isDelete;
}
