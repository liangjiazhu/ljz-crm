package com.lljz.crm.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class StringUtils {


    public static String getDote(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        Date date = new Date();
        return  df.format(date);
    }
}
