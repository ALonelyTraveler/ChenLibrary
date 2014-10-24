package com.android.chen.lib.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.text.TextUtils;

/*
 * This class is used to format Date or dateString.
 */
/**
 * @author WinChen
 * @date 2014-4-1 下午4:40:46
 * @version 1.01
 */
public class DateUtils {
    private final static String DATE_TO_STRING_FORMAT = "yyyy-mm-dd HH:MM";
    private final static String STRING_TO_DATE_FORMAT = "yyyy-mm-dd";

    /**
     * Date Object convert to String.
     * 
     * @param date
     * @return
     */
    public static String dateToString(Date date) {
	return dateToString(date, DATE_TO_STRING_FORMAT);
    }

    /**
     * According to format Date Object convert to String.
     * 
     * @param date
     * @param format
     * @return
     */
    public static String dateToString(Date date, String format) {
	if (date == null) {
	    return null;
	}
	SimpleDateFormat date_format = new SimpleDateFormat(format,
		Locale.CHINA);
	return date_format.format(date);
    }

    /**
     * String Object convert to Date.
     * 
     * @param str
     * @return
     */
    public static Date stringToDate(String str) {
	return stringToDate(str, STRING_TO_DATE_FORMAT);
    }

    /**
     * According to format String Object convert to Date.
     * 
     * @param str
     * @param format
     * @return
     */
    public static Date stringToDate(String str, String format) {
	if (TextUtils.isEmpty(str) || TextUtils.isEmpty(format)) {
	    return null;
	}
	SimpleDateFormat date_format = new SimpleDateFormat(format,
		Locale.CHINA);
	Date date = null;
	try {
	    date = date_format.parse(str);
	} catch (ParseException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	return date;
    }
}
