package com.myfirst.entitis;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by 58 on 2017/1/17.
 * author yunzhifei
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int id;
    private Long userId;
    private String userName;
    private String account;
}
