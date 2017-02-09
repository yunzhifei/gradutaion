package com.myfirst;

import com.myfirst.dao.IUserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ZfApplication.class)
@WebAppConfiguration
public class ZfApplicationTests {
    @Autowired
    IUserDao userDao;

    @Test
    public void contextLoads() {
       userDao.login("14444",2,"asdfasdf",false);
    }

}
