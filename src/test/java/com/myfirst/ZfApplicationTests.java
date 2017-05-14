package com.myfirst;

import com.myfirst.dao.*;
import com.myfirst.entitis.*;
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

//        String[] x = {"赵", "钱", "孙", "李", "周"};
//        String[] m = {"学友", "德华", "成龙", "新宇", "建国", "建军", "晨曦", "静静"};
//        GuideInfo guideInfo = new GuideInfo();
//        Random random = new Random();
//
//        String s = "1234567890qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM";
//        for (int i = 0; i < 100; i++) {
//            int Randomx = random.nextInt(x.length);
//            int radomm = random.nextInt(m.length);
//            guideInfo.setSex("男");
//            guideInfo.setName(x[Randomx] + m[radomm]);
//            guideInfo.setDescription("我是最好的导游哈哈啊！");
//            String cartId = "";
//            for (int j = 0; j < 6; j++) {
//                int k = random.nextInt(s.length());
//                cartId += s.charAt(k);
//            }
//            int start = new Random().nextInt(cityList.size());
//            guideInfo.setCardId(cartId);
//            int price = (random.nextInt(500) % 200 + 200);
//            guideInfo.setPriceOfDay(price + " 元/天");
//            guideInfo.setServerCity(cityList.get(start));
//            guideInfo.setWorkYear("1" + "年");
//            guideInfo.setImg("http://opkb3ene9.bkt.clouddn.com/dao"+ (i%10+1)+ ".jpg");
//            guideInfoDao.addGuideInfo(guideInfo);
//        }
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
    @Autowired
    IHotelDao hotelDao;

    @Test
    public void testDate1() throws IOException {
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

        File file1 = new File("hotel.txt");
        FileInputStream fileInputStream1 = new FileInputStream(file1);
        InputStreamReader inputStreamReader1 = new InputStreamReader(fileInputStream1, "UTF-8");
        BufferedReader bufferedReader1 = new BufferedReader(inputStreamReader1);
        String line1 = null;
        List<String> cityList1 = new LinkedList<String>();
        while ((line1 = bufferedReader1.readLine()) != null) {
            String[] citys1 = line1.split("\t");
            for (String city : citys1) {
                cityList1.add(city);
            }
        }
        Hotel hotel = new Hotel();

        for (int i = 0; i < 100; i++) {
            int price = new Random().nextInt(500) % 200 + 300;
            int hotel1 = new Random().nextInt(cityList1.size());
            int randomCity = new Random().nextInt(cityList.size());
            hotel.setAddress(cityList.get(randomCity));
            hotel.setDescription("世界第一豪华旅馆");
            hotel.setName(cityList1.get(hotel1));
            hotel.setPictureUrl("http://opkb3ene9.bkt.clouddn.com/hotel" + (i % 10 + 1) + ".jpg");
            System.out.println("hotel.getPictureUrl() = " + hotel.getPictureUrl());
            hotel.setPrice(price);
            hotelDao.addHotel(hotel);
        }
    }
}
