package com.android.chen.lib.utils;

import java.io.File;

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;

/*
 * This class is used to manager storage.
 */
/**
 * @author WinChen
 * @date 2014-4-2 上午9:06:03
 * @version 1.01
 */
public class StorageUtils {

    /**
     * 判断储存设备是否挂载
     * 
     * @return
     */
    public static boolean isMount() {

	return Environment.getExternalStorageState().equals(
		Environment.MEDIA_MOUNTED);
    }

    /**
     * 获取储存设备路径
     * 
     * @return
     */
    public static String getStorageDirectory() {

	File file = getStorageFile();
	if (file == null) {
	    return null;
	}
	return file.getAbsolutePath();
    }

    /**
     * 获取储存设备路径
     * 
     * @return
     */
    public static File getStorageFile() {
	return Environment.getExternalStorageDirectory();
    }

    /**
     * 获取储存设备的总大小
     * 
     * @param format
     * @param multiple
     * @return
     */
    @SuppressWarnings("deprecation")
    public static long getStorageVolume() {

	File file = getStorageFile();
	StatFs sFs = new StatFs(file.getPath());
	long blockSize = sFs.getBlockSize();
	int total = sFs.getBlockCount();
	long size = total * blockSize;
	return size;
    }

    /** 获取储存器总大小并格式化输出 **/
    public static String getStorageVolumeFormat(Context context) {
	long size = getStorageVolume();
	return StringUtils.formatSize(context, size);
    }

    /**
     * 获取储存器的可用空间大小
     * 
     * @param format
     * @param multiple
     * @return
     */
    @SuppressWarnings("deprecation")
    public static long getUsableVolumn() {
	File file = getStorageFile();
	StatFs sFs = new StatFs(file.getPath());
	long blockSize = sFs.getBlockSize();
	int avialable_blocks = sFs.getAvailableBlocks();
	long avialable = avialable_blocks * blockSize;
	return avialable;
    }

    /** 获取储存器可用空间并格式化输出 **/
    public static String getUsableVolumnFormat(Context context) {
	long size = getUsableVolumn();
	return StringUtils.formatSize(context, size);
    }
}
