package com.lxy.stock.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by liuxinyu on 2016/6/26.
 */
public class Tools {

    public static String formartDate(Date now, Date compare) {
        long l = now.getTime() - compare.getTime();

        long day = l / (24 * 60 * 60 * 1000);
        long hour = (l / (60 * 60 * 1000) - day * 24);
        long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
        // long s = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min *
        // 60);// 秒
        StringBuilder sb = new StringBuilder();
        if (day > 0) {
//            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
//                    "MM-dd HH:mm", Locale.CHINA);
//            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
//                    "yyyy-MM-dd", Locale.CHINA);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
                    "MM-dd", Locale.CHINA);
            return simpleDateFormat.format(compare.getTime());
        }

        if (hour > 0) {
            sb.append(hour + "小时前");

            if (min == 0) {
                //sb.append("前");
            }
        }


        if (hour == 0 && min > 0) {
            sb.append(min + "分钟前");
        }

        if (hour == 0 && min == 0) {
            sb.append("刚刚");
        }
        return sb.toString();
    }

    //格式化  保留两位
    public static String formateChange(String rateStr){
        if(rateStr.indexOf(".") != -1){
            //获取小数点的位置
            int num = 0;
            num = rateStr.indexOf(".");

            //获取小数点后面的数字 是否有两位 不足两位补足两位
            String dianAfter = rateStr.substring(0,num+1);
            String afterData = rateStr.replace(dianAfter, "");
            if(afterData.length() < 2){
                afterData = afterData + "0" ;
            }else{
                afterData = afterData;
            }
            return rateStr.substring(0,num) + "." + afterData.substring(0,2);
        }else{
            if(rateStr == "1"){
                return "100";
            }else{
                return rateStr;
            }
        }
    }
}
