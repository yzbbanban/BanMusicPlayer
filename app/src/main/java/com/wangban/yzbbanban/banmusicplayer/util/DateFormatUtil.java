package com.wangban.yzbbanban.banmusicplayer.util;

import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * Created by YZBbanban on 16/7/2.
 */
public class DateFormatUtil {
    private static int millseconds;
    private static SimpleDateFormat sdf=new SimpleDateFormat("mm:ss");
    private static Date date;
    public DateFormatUtil(int millseconds) {
        this.millseconds = millseconds;
    }

    public static String getDate(int millseconds){
        date=new Date(millseconds);
        String time = sdf.format(date);
        return time;
    }
}
