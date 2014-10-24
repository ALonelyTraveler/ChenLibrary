package com.android.chen.lib.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.text.TextUtils;

import com.android.chen.lib.constants.FileMode;
import com.android.chen.lib.utils.assist.MyFileFilter;
import com.lidroid.xutils.utils.LogUtils;

/**
 * @author Chen
 */
public class FileUtils {
    public static FileMode defaultMode = FileMode.HAVE_STORAGE_HEADER; // 默认由调用者自行添加全路径

    /** 设置文件模式 **/
    public static void setFileMode(FileMode mode) {
	FileUtils.defaultMode = mode;
    }

    /** 获取文件模式 **/
    public static FileMode getFileMode() {
	return FileUtils.defaultMode;
    }

    /** 转换文件 **/
    private static File getFile(File file) {
	if (file == null || TextUtils.isEmpty(file.getAbsolutePath())
		|| file.isDirectory()) {
	    LogUtils.w("Incorrect file path or object is empty!");
	    return null;
	}
	if (defaultMode == FileMode.NONE_STORAGE_HEADER) {
	    file = new File(StorageUtils.getStorageDirectory(),
		    file.getAbsolutePath());
	}
	return file;
    }

    /** 转换目录 **/
    private static File getFolder(File folder) {
	if (folder == null || TextUtils.isEmpty(folder.getAbsolutePath())
		|| folder.isFile()) {
	    LogUtils.w("Incorrect folder path or object is empty!");
	    return null;
	}
	if (defaultMode == FileMode.NONE_STORAGE_HEADER) {
	    folder = new File(StorageUtils.getStorageDirectory(),
		    folder.getAbsolutePath());
	}
	return folder;
    }

    /** 文件是否存在 **/
    public static boolean exist(File file) {
	file = getFile(file);
	if (file == null) {
	    return false;
	}
	return file.exists();
    }

    public static boolean exist(String path) {
	return exist(new File(path));
    }

    /** 创建文件 **/
    public static boolean createFile(File file) {
	file = getFile(file);
	if (file == null) {
	    return false;
	}
	try {
	    file.createNewFile();
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	    return false;
	}
	return true;
    }

    public static boolean createFile(String path) {
	return createFile(new File(path));
    }

    /** 创建文件夹 **/
    public static boolean createFolder(File folder) {
	folder = getFolder(folder);
	if (folder == null) {
	    return false;
	}
	return folder.mkdirs();
    }

    public static boolean createFolder(String path) {
	return createFolder(new File(path));
    }

    /** 删除文件 **/
    public static boolean deleteFile(File file) {
	file = getFile(file);
	if (file == null) {
	    return false;
	}
	return file.delete();
    }

    public static boolean deleteFile(String path) {
	return deleteFile(new File(path));
    }

    /** 删除文件夹 **/
    public static boolean deleteFolder(File folder) {
	folder = getFolder(folder);
	if (folder == null) {
	    return false;
	}
	return deleteFolder(folder, true);
    }

    private static boolean deleteFolder(File folder, boolean remain) {

	if (exist(folder)) {
	    File[] files = folder.listFiles();
	    if (files != null) {
		for (File file : files) {
		    if (file.isDirectory()) {
			deleteFolder(file, remain);
		    } else {
			deleteFile(file);
		    }
		}
	    }
	    return folder.delete();
	} else {
	    return true;
	}
    }

    public static boolean deleteFolder(String path) {
	return deleteFile(new File(path));
    }

    /** 获取以指定文件目录下以指定后缀名的文件 **/
    public static List<File> getAllWithEnd(File folder, String... extensions) {

	folder = getFolder(folder);
	if (folder == null) {
	    return null;
	}
	List<File> files = new ArrayList<File>();
	fileFilter(folder, files, extensions);
	return files;
    }

    public static List<File> getAllWidthEnd(String folder, String... extensions) {
	return getAllWithEnd(new File(folder), extensions);
    }

    /** 过滤文件 **/
    private static void fileFilter(File file, List<File> files,
	    String... extensions) {
	File[] allFiles = file.listFiles();
	File[] allExtensionFiles = file.listFiles(new MyFileFilter(extensions));
	if (allExtensionFiles != null) {
	    for (File single : allExtensionFiles) {
		files.add(single);
	    }
	}
	if (allFiles != null) {
	    for (File single : allFiles) {
		if (single.isDirectory()) {
		    fileFilter(single, files, extensions);
		}
	    }
	}
    }

    /** 复制assets目录下的文件到指定目录 **/
    public boolean assetsCopyData(Context context, String strAssetsFilePath,
	    String strDesFilePath) {

	boolean bIsSuc = true;
	InputStream inputStream = null;
	OutputStream outputStream = null;

	File file = getFile(new File(strDesFilePath));
	if (!exist(file)) {
	    try {
		file.createNewFile();
		Runtime.getRuntime().exec("chmod 766 " + file);
	    } catch (IOException e) {
		bIsSuc = false;
	    }

	} else {// 存在
	    return true;
	}

	try {
	    inputStream = context.getAssets().open(strAssetsFilePath);
	    outputStream = new FileOutputStream(file);

	    int nLen = 0;

	    byte[] buff = new byte[1024 * 1];
	    while ((nLen = inputStream.read(buff)) > 0) {
		outputStream.write(buff, 0, nLen);
	    }

	    // 完成
	} catch (IOException e) {
	    bIsSuc = false;
	} finally {
	    try {
		if (outputStream != null) {
		    outputStream.close();
		}

		if (inputStream != null) {
		    inputStream.close();
		}
	    } catch (IOException e) {
		bIsSuc = false;
	    }

	}
	return bIsSuc;
    }

    /**
     * 复制文件
     * 
     * @throws IOException
     **/
    public static boolean copyFile(File src, File dst) throws IOException {

	src = getFile(src);
	dst = getFile(dst);
	if (src == null || dst == null) {
	    return false;
	}
	if (!dst.exists()) {
	    FileUtils.createFile(dst);
	}
	FileInputStream inputStream = null;
	inputStream = new FileInputStream(src);
	return copyFile(inputStream, dst);
    }

    private static boolean copyFile(InputStream inputStream, File dst)
	    throws IOException {
	FileOutputStream outputStream = null;
	outputStream = new FileOutputStream(dst);
	int readLen = 0;
	byte[] buf = new byte[1024];
	while ((readLen = inputStream.read(buf)) != -1) {
	    outputStream.write(buf, 0, readLen);
	}
	outputStream.flush();
	inputStream.close();
	outputStream.close();
	return true;
    }

    public static boolean copyFile(String src, String dst) throws IOException {
	return copyFile(new File(src), new File(dst));
    }

    /** 复制目录 **/
    public static boolean copyFolder(File srcDir, File destDir)
	    throws IOException {

	if ((!srcDir.exists())) {
	    return false;
	}
	if (srcDir.isFile() || destDir.isFile())
	    return false;// 判断是否是目录
	if (!destDir.exists()) {
	    destDir.mkdirs();
	}
	File[] srcFiles = srcDir.listFiles();
	int len = srcFiles.length;
	for (int i = 0; i < len; i++) {
	    if (srcFiles[i].isFile()) {
		// 获得目标文件
		File destFile = new File(destDir.getPath() + "//"
			+ srcFiles[i].getName());
		copyFile(srcFiles[i], destFile);
	    } else if (srcFiles[i].isDirectory()) {
		File theDestDir = new File(destDir.getPath() + "//"
			+ srcFiles[i].getName());
		copyFolder(srcFiles[i], theDestDir);
	    }
	}
	return true;
    }

    public static boolean copyFolder(String srcDir, String desDir)
	    throws IOException {

	return copyFolder(new File(srcDir), new File(desDir));
    }

    /** 移动文件或目录 **/
    public static boolean move(File src, File dst) {
	src = getFile(src);
	dst = getFile(dst);
	if (src == null || dst == null) {
	    return false;
	}
	dst.mkdirs();
	return src.renameTo(dst);
    }

    public static boolean move(String src, String dst) {
	return move(new File(src), new File(dst));
    }

    /** 获取应用程序么有目录 **/
    public static File getPrivateDir(Context context) {
	return context.getFilesDir();
    }

    /** 获取文件大小 **/
    public static long getFileSize(File file) {
	file = getFile(file);
	if (file == null) {
	    throw new NullPointerException("File path error!");
	}
	return file.length();
    }

    /** 获取文件夹大小 **/
    public static long getFolderSize(File folder) {
	folder = getFolder(folder);
	if (folder == null) {
	    throw new NullPointerException("Folder path error!");
	}
	if (!folder.exists()) {
	    return 0;
	}
	return getFolderSize(folder, true);
    }

    /**
     * @param folder
     * @param remain  保留字段（没用处）
     * @return
     */
    private static long getFolderSize(File folder, boolean remain) {
	long size = 0;
	File[] files = folder.listFiles();
	if (files != null) {
	    for (File file : files) {
		if (file.isDirectory()) {
		    size += getFolderSize(folder, remain);
		} else {
		    size += getFileSize(file);
		}
	    }
	}
	return size;
    }

}
