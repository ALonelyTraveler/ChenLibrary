package com.android.chen.lib.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;

/*
 * To get package info.
 */
/**
 * @author WinChen
 * @date 2014-4-4 下午4:23:26
 * @version 1.01
 */
public class PackageUtils {

    /**
     * To get name of Application.
     * 
     * @return
     */
    public static String getApplicationName(Context context) {

	PackageManager packageManager = null;
	ApplicationInfo applicationInfo = null;
	try {
	    packageManager = context.getPackageManager();
	    applicationInfo = packageManager.getApplicationInfo(
		    context.getPackageName(), 0);
	} catch (PackageManager.NameNotFoundException e) {
	    applicationInfo = null;
	}
	String applicationName = (String) packageManager
		.getApplicationLabel(applicationInfo);
	return applicationName;
    }

    /**
     * 获取软件版本号
     * 
     * @param context
     * @return
     */
    public static int getVersionCode(Context context) {

	int versionCode = 0;
	try {
	    versionCode = context.getPackageManager().getPackageInfo(
		    context.getPackageName(), 0).versionCode;
	} catch (NameNotFoundException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}
	return versionCode;
    }

    /**
     * 获取软件版本名称
     * 
     * @param context
     * @return
     */
    public static String getVersionName(Context context) {

	String versionCode = "";
	try {
	    versionCode = context.getPackageManager().getPackageInfo(
		    context.getPackageName(), 0).versionName;
	} catch (NameNotFoundException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	    versionCode = "1.0";
	}
	return versionCode;
    }
}
