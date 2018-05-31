package com.share.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DatetimeUtil {
    public static String currentDate(String dateFormat) {
        SimpleDateFormat dateFormat1 = new SimpleDateFormat(dateFormat);
        String date = dateFormat1.format(new Date());
        return date;
    }
}
