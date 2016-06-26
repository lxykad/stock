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
}
