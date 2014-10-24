
package com.android.chen.lib.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class IntentUtils
{

	/**
	 * 启动界面
	 * 
	 * @param context
	 * @param toClass
	 * @param extras
	 * @param requestCode
	 * @param flag
	 */
	public static void launcher(Context context, Class<?> toClass, Bundle extras, int requestCode, int flag)
	{

		Intent intent = new Intent(context, toClass);
		intent.setFlags(flag);
		if (extras != null)
		{
			intent.putExtras(extras);
		}
		if (requestCode != -1)
		{
			((Activity) context).startActivityForResult(intent, requestCode);
		} else
		{
			context.startActivity(intent);
		}
	}

	/**
	 * @param context
	 * @param toClass
	 */
	public static void launcher(Context context, Class<?> toClass)
	{

		launcher(context, toClass, null, -1, Intent.FLAG_ACTIVITY_CLEAR_TOP);
	}

	/**
	 * @param context
	 * @param toClass
	 * @param extras
	 */
	public static void launcher(Context context, Class<?> toClass, Bundle extras)
	{

		launcher(context, toClass, extras, -1, Intent.FLAG_ACTIVITY_CLEAR_TOP);
	}

	/**
	 * @param context
	 * @param toClass
	 * @param extras
	 * @param requestCode
	 */
	public static void launcher(Context context, Class<?> toClass, Bundle extras, int requestCode)
	{

		launcher(context, toClass, extras, requestCode, Intent.FLAG_ACTIVITY_CLEAR_TOP);
	}

	/**
	 * @param context
	 * @param toClass
	 * @param flag
	 */
	public static void launcher(Context context, Class<?> toClass, int flag)
	{

		launcher(context, toClass, null, -1, flag);
	}

	/**
	 * @param context
	 * @param toClass
	 * @param flag
	 * @param extras
	 */
	public static void launcher(Context context, Class<?> toClass, int flag, Bundle extras)
	{

		launcher(context, toClass, extras, -1, flag);
	}

	/**
	 * @param context
	 * @param toClass
	 * @param requestCode
	 * @param flag
	 */
	public static void launcher(Context context, Class<?> toClass, int requestCode, int flag)
	{

		launcher(context, toClass, null, requestCode, flag);
	}
}
