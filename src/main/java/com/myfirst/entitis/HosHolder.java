package com.myfirst.entitis;

import org.springframework.stereotype.Component;

import java.util.Hashtable;

/**
 * Created by yun on 2017/5/3.
 */
@Component
public class HosHolder {
    private static Hashtable<String, User> users = new Hashtable<String, User>();

    public int setUser(User user) {
        users.put("user", user);
        return 0;
    }

    public int clear() {
        users.clear();
        return 0;
    }

    public void remover() {
        if (users.contains("user")) {
            users.remove("user");
        }
    }

    public User getUser() {
        return users.get("user");
    }
}
