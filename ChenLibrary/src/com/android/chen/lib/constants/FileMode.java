package com.android.chen.lib.constants;


/**
 * @author Chen 文件的模式
 */
public enum FileMode {
    /**
     * HAVE_STORAGE_HEADER 由调用者自动加上Environment.getExternalStorageDirectory()
     * NONE_STORAGE_HEADER 由程序自动加上Environment.getExternalStorageDirectory()
     */
    HAVE_STORAGE_HEADER, NONE_STORAGE_HEADER
}
