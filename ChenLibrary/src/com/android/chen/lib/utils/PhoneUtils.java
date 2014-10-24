package com.android.chen.lib.utils;

import android.content.Context;
import android.telephony.TelephonyManager;

public class PhoneUtils {

    // 获取手机号码(如果本机号码被写入到sim卡中才能读取到，否则返回空字符串)
    public static String getPhoneNumber(Context context) {

	TelephonyManager manager = (TelephonyManager) context
		.getSystemService(Context.TELEPHONY_SERVICE);
	String phoneNumber = manager.getLine1Number();
	return phoneNumber == null ? "" : phoneNumber;
    }

    // 获取手机的IMEI号
    public static String getIMEI(Context context) {

	TelephonyManager manager = (TelephonyManager) context
		.getSystemService(Context.TELEPHONY_SERVICE);
	String imei = manager.getDeviceId();
	return (imei == null || imei.equals("")) ? "" : imei;
    }

}
