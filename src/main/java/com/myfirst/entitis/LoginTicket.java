package com.myfirst.entitis;

import lombok.Data;

/**
 * Created by 58 on 2017/2/9.
 */
@Data
public class LoginTicket {
    int id;
    Boolean expired;
    int status;
    String ticket;
    String userId;
}
