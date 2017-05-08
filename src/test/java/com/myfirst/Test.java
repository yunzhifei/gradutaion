package com.myfirst;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/5/8.
 */
public class Test {
    @org.junit.Test
    public  void test(){
        System.out.println("isEmail(\"yunzhifei_chd@139.com\") = " + isEmail("yunzhifei_chd@139.com"));
    }
    public static boolean isEmail(String strEmail) {
        String strPattern = "^[a-zA-Z][\\w\\.-]*[a-zA-Z0-9]@[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]$";

        Pattern p = Pattern.compile(strPattern);
        Matcher m = p.matcher(strEmail);
        return m.matches();
    }
}
