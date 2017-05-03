package com.myfirst.entitis;

import org.springframework.stereotype.Component;

/**
 * Created by yunzh on 2017/5/3.
 */
@Component
public class HosHolder {
    private static ThreadLocal<User> users = new ThreadLocal<User>();

    public User getUser() {
        return users.get();
    }

    public void setUser(User user) {
        users.set(user);
    }

    public void clear() {
        users.remove();
    }
}
