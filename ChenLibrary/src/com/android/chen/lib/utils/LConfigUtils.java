package com.android.chen.lib.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * @author WinChen
 * @date 2014-4-2 上午9:22:09
 * @version 1.02
 * 本地文件配置
 */
public class LConfigUtils {

    private final static int PREFERENCES_MODE = Context.MODE_PRIVATE;

    private final static int INT_PREFERENCES_DEFAULT_VALUE = 0;

    private final static long LONG_PREFERENCES_DEFAULT_VALUE = 0;

    private final static float FLOAT_PREFERENCES_DEFAULT_VALUE = 0f;

    private final static boolean BOOLEAN_PREFERENCES_DEFAULT_VALUE = false;

    private final static String STRING_PREFERENCES_DEFAULT_VALUE = "";

    public final static String SPLIT_CHAR = "\\."; // 分隔SharedPreferences的配置文件名与字段

    /**
     * 设置整型的SharedPreferences
     * @param context    上下文
     * @param name_key   配置文件_关键词
     * @param value		值
     * @param mode		模式
     */
    public static void setInt(Context context, String name_key, int value,
	    int mode) {
	String[] splits = name_key.split(SPLIT_CHAR);
	if (splits.length != 2) {
	    throw new RuntimeException(
		    "Configuration file name or an incorrect key!");
	}
	SharedPreferences preferences = context.getSharedPreferences(splits[0],
		mode);
	preferences.edit().putInt(splits[1], value).commit();
    }

    public static void setInt(Context context, String name_key, int value) {

	setInt(context, name_key, value, PREFERENCES_MODE);
    }
    /**
     * 从配置文件中获取指定字段名的整型值
     * @param context		上下文
     * @param name_key   配置文件名_关键词
     * @param defaultValue		默认值
     * @param mode		模式
     * @return
     */
    public static int getInt(Context context, String name_key,
	    int defaultValue, int mode) {
	String[] splits = name_key.split(SPLIT_CHAR);
	if (splits.length != 2) {
	    throw new RuntimeException(
		    "Configuration file name or an incorrect key!");
	}
	SharedPreferences preferences = context.getSharedPreferences(splits[0],
		mode);
	return preferences.getInt(splits[1], defaultValue);
    }

    public static int getInt(Context context, String name_key, int defaultValue) {

	return getInt(context, name_key, defaultValue, PREFERENCES_MODE);
    }

    public static int getInt(Context context, String name_key) {

	return getInt(context, name_key, INT_PREFERENCES_DEFAULT_VALUE);
    }

    
    /**
     * 设置长整型的SharedPreferences
     * @param context    上下文
     * @param name_key   配置文件_关键词
     * @param value		值
     * @param mode		模式
     */
    public static void setLong(Context context, String name_key, long value,
	    int mode) {
	String[] splits = name_key.split(SPLIT_CHAR);
	if (splits.length != 2) {
	    throw new RuntimeException(
		    "Configuration file name or an incorrect key!");
	}
	SharedPreferences preferences = context.getSharedPreferences(splits[0],
		mode);
	preferences.edit().putLong(splits[1], value).commit();
    }

    public static void setLong(Context context, String name_key, long value) {
	setLong(context, name_key, value, PREFERENCES_MODE);
    }
    /**
     * 从配置文件中获取指定字段名的长整型值
     * @param context		上下文
     * @param name_key   配置文件名_关键词
     * @param defaultValue		默认值
     * @param mode		模式
     * @return
     */
    public static Long getLong(Context context, String name_key,
	    long defaultValue, int mode) {
	String[] splits = name_key.split(SPLIT_CHAR);
	if (splits.length != 2) {
	    throw new RuntimeException(
		    "Configuration file name or an incorrect key!");
	}
	SharedPreferences preferences = context.getSharedPreferences(splits[0],
		mode);
	return preferences.getLong(splits[1], defaultValue);
    }

    public static long getLong(Context context, String name_key,
	    long defaultValue) {

	return getLong(context, name_key, defaultValue, PREFERENCES_MODE);
    }

    public static long getLong(Context context, String name_key) {

	return getLong(context, name_key, LONG_PREFERENCES_DEFAULT_VALUE);
    }

    
    /**
     * 设置布尔型的SharedPreferences
     * @param context    上下文
     * @param name_key   配置文件_关键词
     * @param value		值
     * @param mode		模式
     */
    public static void setBoolean(Context context, String name_key,
	    boolean value, int mode) {
	String[] splits = name_key.split(SPLIT_CHAR);
	if (splits.length != 2) {
	    throw new RuntimeException(
		    "Configuration file name or an incorrect key!");
	}
	SharedPreferences preferences = context.getSharedPreferences(splits[0],
		mode);
	preferences.edit().putBoolean(splits[1], value).commit();
    }

    public static void setBoolean(Context context, String name_key,
	    boolean value) {

	setBoolean(context, name_key, value, PREFERENCES_MODE);
    }

    /**
     * 从配置文件中获取指定字段名的布尔值
     * @param context		上下文
     * @param name_key   配置文件名_关键词
     * @param defaultValue		默认值
     * @param mode		模式
     * @return
     */
    public static boolean getBoolean(Context context, String name_key,
	    boolean defaultValue, int mode) {
	String[] splits = name_key.split(SPLIT_CHAR);
	if (splits.length != 2) {
	    throw new RuntimeException(
		    "Configuration file name or an incorrect key!");
	}
	SharedPreferences preferences = context.getSharedPreferences(splits[0],
		mode);
	return preferences.getBoolean(splits[1], defaultValue);
    }

    public static boolean getBoolean(Context context, String name_key,
	    boolean defaultValue) {

	return getBoolean(context, name_key, defaultValue, PREFERENCES_MODE);
    }

    public static boolean getBoolean(Context context, String name_key) {

	return getBoolean(context, name_key, BOOLEAN_PREFERENCES_DEFAULT_VALUE);
    }

    
    /**
     * 设置浮点型的SharedPreferences
     * @param context    上下文
     * @param name_key   配置文件_关键词
     * @param value		值
     * @param mode		模式
     */
    public static void setFloat(Context context, String name_key, float value,
	    int mode) {
	String[] splits = name_key.split(SPLIT_CHAR);
	if (splits.length != 2) {
	    throw new RuntimeException(
		    "Configuration file name or an incorrect key!");
	}
	SharedPreferences preferences = context.getSharedPreferences(splits[0],
		mode);
	preferences.edit().putFloat(splits[1], value).commit();
    }

    public static void setFloat(Context context, String name_key, float value) {

	setFloat(context, name_key, value, PREFERENCES_MODE);
    }
    /**
     * 从配置文件中获取指定字段名的浮点型值
     * @param context		上下文
     * @param name_key   配置文件名_关键词
     * @param defaultValue		默认值
     * @param mode		模式
     * @return
     */
    public static float getFloat(Context context, String name_key,
	    float defaultValue, int mode) {
	String[] splits = name_key.split(SPLIT_CHAR);
	if (splits.length != 2) {
	    throw new RuntimeException(
		    "Configuration file name or an incorrect key!");
	}
	SharedPreferences preferences = context.getSharedPreferences(splits[0],
		mode);
	return preferences.getFloat(splits[1], defaultValue);
    }

    public static float getFloat(Context context, String name_key,
	    float defaultValue) {

	return getFloat(context, name_key, defaultValue, PREFERENCES_MODE);
    }

    public static float getFloat(Context context, String name_key) {

	return getFloat(context, name_key, FLOAT_PREFERENCES_DEFAULT_VALUE);
    }

    
    /**
     * 设置字符串型的SharedPreferences
     * @param context    上下文
     * @param name_key   配置文件_关键词
     * @param value		值
     * @param mode		模式
     */
    public static void setString(Context context, String name_key,
	    String value, int mode) {
	String[] splits = name_key.split(SPLIT_CHAR);
	if (splits.length != 2) {
	    throw new RuntimeException(
		    "Configuration file name or an incorrect key!");
	}
	SharedPreferences preferences = context.getSharedPreferences(splits[0],
		mode);
	preferences.edit().putString(splits[1], value).commit();
    }

    public static void setString(Context context, String name_key, String value) {

	setString(context, name_key, value, PREFERENCES_MODE);
    }

    /**
     * 从配置文件中获取指定字段名的字符串值
     * @param context		上下文
     * @param name_key   配置文件名_关键词
     * @param defaultValue		默认值
     * @param mode		模式
     * @return
     */
    public static String getString(Context context, String name_key,
	    String defaultValue, int mode) {
	String[] splits = name_key.split(SPLIT_CHAR);
	if (splits.length != 2) {
	    throw new RuntimeException(
		    "Configuration file name or an incorrect key!");
	}
	SharedPreferences preferences = context.getSharedPreferences(splits[0],
		mode);
	return preferences.getString(splits[1], defaultValue);
    }

    public static String getString(Context context, String name_key,
	    String defaultValue) {

	return getString(context, name_key, defaultValue, PREFERENCES_MODE);
    }

    public static String getString(Context context, String name_key) {

	return getString(context, name_key, STRING_PREFERENCES_DEFAULT_VALUE);
    }

    /**
     * 清空配置文件中的所有值
     * @param context
     * @param preName   配置文件名称
     * @param mode
     */
    public static void clearPreferences(Context context, String preName,
	    int mode) {

	SharedPreferences preferences = context.getSharedPreferences(preName,
		mode);
	Editor editor = preferences.edit();
	editor.clear();
	editor.commit();
    }

    public static void clearPreferences(Context context, String preName) {

	clearPreferences(context, preName, PREFERENCES_MODE);
    }

}
