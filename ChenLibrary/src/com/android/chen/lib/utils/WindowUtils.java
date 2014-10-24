package com.android.chen.lib.utils;

import android.content.Context;
import android.view.WindowManager;

/*
 * To get width and height of windows.
 */
/**
 * @author WinChen
 * @date 2014-4-3 下午1:27:27
 * @version 1.01
 */
public class WindowUtils {

    private static int window_width;

    private static int window_height;

    private static boolean isCalc = false;

    @SuppressWarnings("deprecation")
    public static void calc(Context context) {
	if (!isCalc) {
	    WindowManager windowManager = (WindowManager) context
		    .getSystemService(Context.WINDOW_SERVICE);
	    window_width = windowManager.getDefaultDisplay().getWidth();
	    window_height = windowManager.getDefaultDisplay().getHeight();
	}
    }

    public static int getWidth() {

	return window_width;
    }

    public static int getHeight() {

	return window_height;
    }
}
