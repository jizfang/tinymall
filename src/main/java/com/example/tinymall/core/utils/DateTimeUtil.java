package com.example.tinymall.core.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @ClassName DateTimeUtil
 * @Description
 * @Author jzf
 * @Date 2020-4-9 16:27
 */
public class DateTimeUtil {
    /**
     * 格式 yyyy年MM月dd日 HH:mm:ss
     *
     * @param dateTime
     * @return
     */
    public static String getDateTimeDisplayString(LocalDateTime dateTime) {
        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss");
        String strDate2 = dtf2.format(dateTime);

        return strDate2;
    }
}
