package com.myfirst;

import com.myfirst.dao.*;
import com.myfirst.entitis.GuideInfo;
import com.myfirst.entitis.LoginTicket;
import com.myfirst.entitis.Travel;
import com.myfirst.entitis.User;
import com.myfirst.service.QiNiuService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ZfApplication.class)
@WebAppConfiguration
public class ZfApplicationTests {
    @Autowired
    IviewSpotDao iviewSpotDao;

    @Autowired
    IUserDao iUserDao;
    @Autowired
    QiNiuService qiNiuService;
    @Autowired
    ILoginTicketDao iticketDao;
    @Autowired
    ItravelDao itravelDao;
    @Autowired
    IGuideInfoDao guideInfoDao;

    @Test
    public void testData() throws IOException {
//        User user = new User();
//        for (int i = 0; i < 10; i++) {
//            Random random = new Random();
//            user.setSalt("salt" + random.nextInt(40));
//            user.setName("yun zhi fei" + random.nextInt(40));
//            user.setDescription("活泼开朗！");
//            user.setAge(10 + random.nextInt(40));
//            user.setAddress("河南省开封市");
//            user.setPhone("188290406" + random.nextInt(40));
//            user.setEmailAddress("yun_zhi_fei_" + random.nextInt(40) + "@139.com");
//            user.setIsDelete((short) 0);
//            user.setPassword("nowcodre");
//            user.setSex("男");
//            user.setUserName("yun" + random.nextInt(100));
//            user.setUserType((short) 0);
//            try {
//                System.out.println("sout" + iUserDao.addUser(user));
//            } catch (Exception e) {
//                e.printStackTrace();
//            }

//        }
//        System.out.println(qiNiuService.getUpToken());
//        LoginTicket loginTicket;
//        loginTicket = new LoginTicket();
//        loginTicket.setExpired((short) 0);
//        loginTicket.setUserId(40);
//        loginTicket.setStatus(0);
//        String ticket = UUID.randomUUID().toString().replaceAll("-", "");
//        loginTicket.setTicket(ticket);
//        int l = iticketDao.addLoginTicket(loginTicket);
        File file = new File("city.txt");
        FileInputStream fileInputStream = new FileInputStream(file);
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String line = null;
        List<String> cityList = new LinkedList<String>();
        while ((line = bufferedReader.readLine()) != null) {
            String[] citys = line.split("\t");
            for (String city : citys) {
                cityList.add(city);
            }
        }
        String[] x = {"赵", "钱", "孙", "李", "周"};
        String[] m = {"学友", "德华", "成龙", "新宇", "建国", "建军", "晨曦", "静静"};
        GuideInfo guideInfo = new GuideInfo();
        Random random = new Random();

        String s = "1234567890qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM";
        for (int i = 0; i < 100; i++) {
            int Randomx = random.nextInt(x.length);
            int radomm = random.nextInt(m.length);
            guideInfo.setSex("男");
            guideInfo.setName(x[Randomx] + m[radomm]);
            guideInfo.setDescription("我是最好的导游哈哈啊！");
            String cartId = "";
            for (int j = 0; j < 6; j++) {
                int k = random.nextInt(s.length());
                cartId += s.charAt(k);
            }
            int start = new Random().nextInt(cityList.size());
            guideInfo.setCardId(cartId);
            int price = (random.nextInt(500) % 200 + 200);
            guideInfo.setPriceOfDay(price + " 元/天");
            guideInfo.setServerCity(cityList.get(start));
            guideInfo.setWorkYear("1" + "年");
            guideInfo.setImg("http://opkb3ene9.bkt.clouddn.com/dao"+ (i%10+1)+ ".jpg");
            guideInfoDao.addGuideInfo(guideInfo);
        }
    }
//        File file = new File("city.txt");
//        FileInputStream fileInputStream = new FileInputStream(file);
//        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
//        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
//        String line = null;
//        List<String> cityList = new LinkedList<String>();
//        while ((line = bufferedReader.readLine()) != null) {
//            String[] citys = line.split("\t");
//            for (String city : citys) {
//                cityList.add(city);
//            }
//        }
//        int price = 0;
//        String[] strings = {"飞机", "轮船", "火车", "高铁", "动车"};
//        for (int i = 0; i < 100; i++) {
//            int start = new Random().nextInt(cityList.size());
//            int end = new Random().nextInt(cityList.size());
//            price = new Random().nextInt(3000) % 2500 + 500;
//
//            Travel travel = new Travel();
//            travel.setStartAddress(cityList.get(start));
//            travel.setEndAddress(cityList.get(end));
//            travel.setPrice(price);
//            travel.setTravelType(strings[i % 5]);
//            itravelDao.addTravel(travel);
//        }
//    }


}
