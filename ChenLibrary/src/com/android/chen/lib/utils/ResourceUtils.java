package com.android.chen.lib.utils;

import java.lang.reflect.Field;

public class ResourceUtils {
    // 通过字符串找到资源的id
    public static int getResouceIdByName(Class<?> myClass, String name) {

	int id = 0;
	try {
	    Field field = myClass.getField(name);
	    id = field.getInt(myClass.newInstance());
	} catch (NoSuchFieldException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (IllegalArgumentException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (IllegalAccessException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (InstantiationException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	return id;
    }
}
