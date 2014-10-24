package com.android.chen.lib.utils;

import java.util.UUID;

public class UUIDUtils {

    /*
     *生成随机的UUID
     */
	public static String getUUID() {
		return UUID.randomUUID().toString();
	}
}
