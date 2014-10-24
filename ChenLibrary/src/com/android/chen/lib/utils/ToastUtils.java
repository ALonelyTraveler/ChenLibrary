package com.android.chen.lib.utils;


import android.content.Context;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.Toast;


/*
 * Define kind of Toast. In order to display and operation.
 */
/**
 * @author WinChen
 * @date 2014-4-1 下午9:02:14
 * @version 1.02
 * 
 */
public class ToastUtils {
	/**
	 * show toast by inputing msg of string.
	 * 
	 * @param msg
	 */
	public static void show(Context context,String msg)
	{
		show(context,msg, Toast.LENGTH_SHORT);
	}

	/**
	 * show toast by inputing msg of integer.
	 * 
	 * @param msgId
	 */
	public static void show(Context context,int msgId)
	{
		show(context,msgId, Toast.LENGTH_SHORT);
	}

	/**
	 * @param msgId
	 * @param duration
	 *            Toast.LENGTH_SHORT or Toast.LENGTH_LONG
	 */
	public static void show(Context context,int msgId, int duration)
	{
		show(context,context.getResources().getString(msgId),duration);
	}

	/**
	 * @param msg
	 * @param duration
	 *            Toast.LENGTH_SHORT or Toast.LENGTH_LONG
	 */
	public static void show(Context context,String msg, int duration)
	{
		Toast toast = Toast.makeText(context, msg, duration);
		if(duration==Toast.LENGTH_SHORT||duration==Toast.LENGTH_LONG)
		{
			toast.setDuration(duration);
		}
		else {
			toast.setDuration(Toast.LENGTH_LONG);
			Handler handler = new Handler();
			handler.postDelayed(new CancelRunnable(toast), duration);
		}
		toast.show();
	}
	static class CancelRunnable implements Runnable{
		private Toast toast;
		public CancelRunnable(Toast toast)
		{
			this.toast = toast;
		}
		@Override
		public void run()
		{
			toast.cancel();
			// TODO Auto-generated method stub
			
		}
	}

	/**
	 * show custom toast.
	 * 
	 * @param defStyle
	 */
	public static Toast showCustomToast(Context context,int defStyle, int gravity, int xOffset,
			int yOffset, int duration)
	{
		final Toast toast = new Toast(context);
		toast.setGravity(gravity, xOffset, yOffset);
		toast.setView(LayoutInflater.from(context).inflate(
				defStyle, null));
		if(duration==Toast.LENGTH_SHORT||duration==Toast.LENGTH_LONG)
		{
			toast.setDuration(duration);
		}
		else {
			toast.setDuration(Toast.LENGTH_LONG);
			Handler handler = new Handler();
			handler.postDelayed(new CancelRunnable(toast), duration);
		}
		toast.show();
		return toast;
	}

	/**
	 * show custom toast.
	 * @param defStyle
	 * @param gravity
	 * @param duration
	 */
	public static Toast showCustomToast(Context context,int defStyle, int gravity, int duration)
	{
		return showCustomToast(context,defStyle, gravity, 0, 0, duration);
		
	}

	/**
	 * show custom toast.
	 * @param defStyle
	 * @param gravity
	 */
	public static Toast showCustomToast(Context context,int defStyle, int gravity)
	{
		return showCustomToast(context,defStyle, gravity, Toast.LENGTH_SHORT);
	}

	/**
	 * show custom toast.
	 * @param defStyle
	 */
	public static Toast showCustomToast(Context context,int defStyle)
	{
		return showCustomToast(context,defStyle, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL);
	}

}
