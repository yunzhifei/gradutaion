package com.myfirst;

import com.myfirst.dao.IUserDao;
import com.myfirst.dao.IviewSpotDao;
import com.myfirst.entitis.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Random;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ZfApplication.class)
@WebAppConfiguration
public class ZfApplicationTests {
    @Autowired
    IviewSpotDao iviewSpotDao;

    @Autowired
    IUserDao iUserDao;

    @Test
    public void testData() {
        User user = new User();
        for (int i = 0; i < 10; i++) {
            Random random = new Random();
            user.setSalt("salt" + random.nextInt(40));
            user.setName("yun zhi fei" + random.nextInt(40));
            user.setDescription("活泼开朗！");
            user.setAge(10 + random.nextInt(40));
            user.setAddress("河南省开封市");
            user.setPhone("188290406" + random.nextInt(40));
            user.setEmailAddress("yun_zhi_fei_" + random.nextInt(40) + "@139.com");
            user.setIsDelete((short) 0);
            user.setPassword("nowcodre");
            user.setSex((byte) 0);
            user.setUserName("yun" + random.nextInt(100));
            user.setUserType((short) 0);
            user.setUserId(random.nextLong());
            try {
                System.out.println("sout" + iUserDao.addUser(user));
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

}
