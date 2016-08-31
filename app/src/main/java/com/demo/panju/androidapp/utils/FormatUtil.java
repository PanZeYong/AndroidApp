package com.demo.panju.androidapp.utils;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Author : PZY
 * Date : 2016.8.31
 */
public class FormatUtil {

    public static String formatFileSize(long size){
        DecimalFormat df = new DecimalFormat("#.##");

        if (size / 1024 < 1024) {
            return Double.parseDouble(df.format((double)size / 1024)) + "KB";
        } else if (size / 1024 / 1024 < 1024) {
            return Double.parseDouble(df.format((double)size / 1024 / 1024)) + "MB";
        } else {
            return Double.parseDouble(df.format((double)size / 1024 / 1024 / 1024)) + "G";
        }
    }

    public static String formatFileTime(long time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CANADA);
        Date date = new Date(time);
        return sdf.format(date);
    }
}
