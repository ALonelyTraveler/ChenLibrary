package com.android.chen.lib.utils;

import android.content.Context;
import android.text.format.Formatter;

public class StringUtils {

    /** 将size格式化成储存格式输出，即M/G/kb等 **/
    public static String formatSize(Context context, long size) {
	return Formatter.formatFileSize(context, size);
    }

}
