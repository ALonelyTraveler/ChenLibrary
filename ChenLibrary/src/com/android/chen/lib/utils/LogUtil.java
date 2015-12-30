package com.hoperun.utils;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.TimeZone;

import android.os.Environment;
import android.util.Log;

import com.project.common.Constants;

/**
 * 
 * 日志工具类
 * 
 * @author liu_yong
 * @version [版本号, 2012-3-7]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class LogUtil extends Thread
{
    
    /**
     * 日志名
     */
    private static final String TAG = "SDINFO-Log";
    
    /**
     * 打印所有日志包括数据交互
     */
    public static int LOG_LEVEL_ALL = 0;
    
    /**
     * 打印调试信息
     */
    public static final int LOG_LEVEL_DEBUG = 1;
    
    /**
     * 只打印必要信息
     */
    public static final int LOG_LEVEL_RELEASE = 2;
    
    /**
     * 日志等级
     */
    public static int log_level = LOG_LEVEL_ALL;
    
    /**
     * 写入的文件名
     */
    private final static String FILENAME = ".txt";
    
    /**
     * 写文件任务的列表
     */
    private static Queue<String> lstStorageTask = new LinkedList<String>();
    
    /**
     * 写文件线程是否正在运行
     */
    private static boolean isRunnig = false;
    
    /**
     * 需要随机写入的文件流对象
     */
    private static RandomAccessFile file = null;
    
    /**
     * 日志保存路径（SD卡）
     */
    public static String logpath = "/sdcard/hdl/";
    
    /**
     * 当没有写入任何数据的时候返回0
     */
    public final static int NONE = 0;
    
    /**
     * 当出错的时候返回的字节数为-1
     */
    public final static int ERROR = -1;
    
    /**
     * 控制开关是否关闭DEBUG日志
     */
    public static boolean isClose = false;
    
    /**
     * 检查sdcard是否正常状态
     */
    public static boolean sdcardState = true;
    
    /**
     * 构造函数
     */
    public LogUtil()
    {
        openFile();
    }
    
    /**
     * 构造文件对象
     */
    public static void openFile()
    {
        
        logpath = Environment.getExternalStorageDirectory() + Constants.LOG_DIR;
        File dir = new File(logpath);
        if (!dir.exists())
        {
            dir.mkdirs();
        }
        try
        {
            if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
            {
                sdcardState = false;
                return;
            }
            
            Log.i(TAG, "initial LogX RandomAccessFile...");
            file = new RandomAccessFile(createFile(), "rw");
            if (file == null)
            {
                Log.i(TAG, "contruct file error!!!");
            }
            
            deleteUnusedFile();
            // 删除过期的日志文件
            
        }
        catch (IOException e)
        {
            closeFile();
            Log.i(TAG, "" + e.getMessage());
        }
        
        isRunnig = true;
    }
    
    /**
     * 删除过期的日志文件
     * 
     * @see [类、类#方法、类#成员]
     */
    private static void deleteUnusedFile()
    {
        File fileDir = new File(logpath);
        if (fileDir.exists())
        {
            File[] listFiles = fileDir.listFiles();
            if (listFiles != null && listFiles.length > 0)
            {
                
                for (int i = 0; i < listFiles.length; i++)
                {
                    File innerFile = listFiles[i];
                    String fileName = innerFile.getName();
                    fileName = fileName.substring(0, fileName.lastIndexOf("."));
                    if (innerFile.getName().compareTo(getBeforeTime()) < 0)
                    {
                        innerFile.delete();
                    }
                    
                }
            }
            
        }
    }
    
    /**
     * 创建日志文件
     * 
     * @return file对象
     */
    private static File createFile()
    {
        
        File dir = new File(logpath);
        if (!dir.exists())
        {
            try
            {
                dir.mkdirs();
            }
            catch (Exception e)
            {
                LogUtil.logExceptionStackTrace(TAG, e);
            }
            
        }
        
        File file = new File(logpath + getCurrentDate() + FILENAME);
        if (!file.exists())
        {
            try
            {
                file.createNewFile();
            }
            catch (IOException e)
            {
                Log.i(TAG, "" + e.getMessage());
            }
        }
        
        return file;
    }
    
    /**
     * 记录操作日志
     * 
     * @param log 日志信息
     */
    public static void writeOperLog(String tag, String message)
    {
        if (message == null)
        {
            message = "";
        }
        // 方便在控制台输出,暂时开启
        Log.i(tag, message);
        
        if (!sdcardState)
        {
            Log.e(TAG, "SDCARD not normal");
            return;
        }
        
        if (isRunnig)
        {
            synchronized (lstStorageTask)
            {
                lstStorageTask.add("\r\n" + getCurrentTime() + "   operate   " + tag + "    " + message);
                lstStorageTask.notify();
            }
        }
    }
    
    /**
     * 记录运行日志
     * 
     * @param log 日志信息
     */
    public static void writeRunLog(String tag, String message)
    {
        
        if (message == null)
        {
            message = "";
        }
        // 方便在控制台输出,暂时开启
        Log.i(tag, message);
        
        if (!sdcardState)
        {
            Log.e(TAG, "SDCARD not normal");
            return;
        }
        
        if (isRunnig)
        {
            synchronized (lstStorageTask)
            {
                
                lstStorageTask.add("\r\n" + getCurrentTime() + "   run   " + tag + "    " + message);
                lstStorageTask.notify();
            }
        }
    }
    
    /**
     * 记录接口日志
     * 
     * @param log 日志信息
     */
    public static void writeNetLog(String tag, String message)
    {
        if (log_level > LOG_LEVEL_ALL)
        {
            return;
        }
        if (message == null)
        {
            message = "";
        }
        // 方便在控制台输出,暂时开启
        Log.i(tag, message);
        
        if (!sdcardState)
        {
            Log.e(TAG, "SDCARD not normal");
            return;
        }
        
        if (isRunnig)
        {
            synchronized (lstStorageTask)
            {
                lstStorageTask.add("\r\n" + getCurrentTime() + "   net   " + tag + "    " + message);
                lstStorageTask.notify();
            }
        }
    }
    
    /**
     * 记录调试日志
     * 
     * @param log 日志信息
     */
    public static void writeDebugLog(String tag, String message)
    {
        if (log_level > LOG_LEVEL_DEBUG)
        {
            return;
        }
        if (message == null)
        {
            message = "";
        }
        
        // 方便在控制台输出,暂时开启
        Log.i(tag, message);
        
        if (!sdcardState)
        {
            Log.e(TAG, "SDCARD not normal");
            return;
        }
        
        if (isClose)
        {
            return;
        }
        
        if (isRunnig)
        {
            synchronized (lstStorageTask)
            {
                lstStorageTask.add("\r\n" + getCurrentTime() + "   debug   " + tag + "    " + message);
                lstStorageTask.notify();
            }
        }
    }
    
    /**
     * 记录安全日志
     * 
     * @param log 日志信息
     */
    public static void writeSecurityLog(String tag, String message)
    {
        
        if (message == null)
        {
            message = "";
        }
        // 方便在控制台输出,暂时开启
        Log.e(tag, message);
        
        if (!sdcardState)
        {
            Log.e(TAG, "SDCARD not normal");
            return;
        }
        
        if (isRunnig)
        {
            synchronized (lstStorageTask)
            {
                lstStorageTask.add("\r\n" + getCurrentTime() + "   security   " + tag + "    " + message);
                lstStorageTask.notify();
            }
        }
    }
    
    /**
     * <p>
     * log异常栈。
     * </p>
     * 
     * @param tag TAG
     * @param e 异常
     */
    public static void logExceptionStackTrace(String tag, Exception e)
    {
        
        if (tag == null || e == null)
        {
            return;
        }
        
        try
        {
            
            writeSecurityLog(tag, "FATAL EXCEPTION: main");
            String message = e.getMessage();
            
            writeSecurityLog(tag, e.getClass().getName() + ": " + (message == null ? "" : message));
            
            StackTraceElement[] stackTrace = e.getStackTrace();
            for (StackTraceElement element : stackTrace)
            {
                
                if (element != null)
                {
                    writeSecurityLog(tag, "    at " + element.getClassName() + "." + element.getMethodName() + "("
                        + element.getFileName() + ":" + element.getLineNumber() + ")");
                }
                
            }
            
        }
        catch (Exception e1)
        {
        }
        
    }
    
    /**
     * 线程执行体
     */
    public void run()
    {
        while (isRunnig)
        {
            try
            {
                if (lstStorageTask == null)
                {
                    Log.i(TAG, "In storage thread the lstStorageTask is null.");
                    break;
                }
                
                String dataBlock = null;
                synchronized (lstStorageTask)
                {
                    // 如果当前没有要写的数据，等待...
                    if (lstStorageTask.isEmpty())
                    {
                        lstStorageTask.wait();
                    }
                    
                    // 如果有数据，取出要写的内容
                    if (!lstStorageTask.isEmpty())
                    {
                        dataBlock = (String)lstStorageTask.poll();
                    }
                }
                
                // 执行写文件的操作
                if (dataBlock != null)
                {
                    try
                    {
                        int ret = writeFile(file, dataBlock.getBytes());
                        if (ret == ERROR)
                        {
                            // 向SD卡中存储文件时出错处理
                            writeLogError();
                            break;
                        }
                    }
                    catch (Exception e)
                    {
                        // 向SD卡中存储文件时出错处理
                        writeLogError();
                        Log.i(TAG, "" + e.getMessage());
                        break;
                    }
                }
            }
            catch (InterruptedException e)
            {
                // 终止写文件线程
                Log.i(TAG, "The write file thread is closed.");
                break;
            }
        }
        
        isRunnig = false;
    }
    
    /**
     * 向SD卡中存储文件时出错处理
     */
    private static void writeLogError()
    {
        // 置位写文件线程运行标志
        
        // 关闭文件流
        closeFile();
        
        // 清空写文件任务
        clearLogTaskList();
    }
    
    /**
     * 关闭文件对象
     */
    public static void closeFile()
    {
        isRunnig = false;
        
        try
        {
            if (file != null)
            {
                file.close();
            }
        }
        catch (IOException e)
        {
            Log.i(TAG, "file.close() Exception!!!");
        }
        finally
        {
            file = null;
        }
    }
    
    /**
     * 清空写文件任务
     */
    private static void clearLogTaskList()
    {
        synchronized (lstStorageTask)
        {
            // 先清空所有的任务对象
            Iterator<String> tasks = lstStorageTask.iterator();
            while (tasks.hasNext())
            {
                String task = tasks.next();
                if (task != null)
                {
                    // 清空内存
                    task = null;
                }
            }
            // 清空任务列表
            lstStorageTask.clear();
        }
    }
    
    // /**
    // * 删除文件
    // */
    // public static void deleteFile() {
    // File file = new File(LOG_PATH_SD + FILENAME);
    // if (file.exists()) {
    // file.delete();
    // }
    // }
    
    /**
     * 将从网络获取来的数据流写入文件中
     * 
     * @param ops 从网络获取来的io流
     * @param fileName 需要存储的文件的名称
     * @return 总共存储成功的字节数
     * @throws SDNotEnouchSpaceException sd卡空间不足时抛出的异常
     * @throws SDUnavailableException sd卡不可用时抛出的异常
     */
    public static int writeFile(RandomAccessFile file, byte[] io)
    {
        int length = NONE;
        
        if (io != null)
        {
            if (file != null)
            {
                try
                {
                    file.seek(file.length());
                    file.write(io);
                }
                catch (IOException e)
                {
                    LogUtil.writeDebugLog(e.getMessage(), " fail");
                    // checkSD(io);
                    // 如果出现异常，返回的字节数为-1，表示出现了异常，没有写入成功
                    return ERROR;
                }
                length = io.length;
            }
            
        }
        
        return length;
    }
    
    /**
     * 获取当前日期
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    private static String getCurrentDate()
    {
        String dateString = "";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        GregorianCalendar mCalendar = new GregorianCalendar();
        mCalendar.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        dateString = dateFormat.format(mCalendar.getTime());
        return dateString;
    }
    
    /**
     * 获得当前时间
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    private static String getCurrentTime()
    {
        
        String year = null;
        String month = null;
        String day = null;
        String hour = null;
        String min = null;
        String second = null;
        GregorianCalendar mCalendar = new GregorianCalendar();
        mCalendar.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        year = mCalendar.get(Calendar.YEAR) + "";
        month =
            (mCalendar.get(Calendar.MONTH) + 1) < 10 ? "0" + (mCalendar.get(Calendar.MONTH) + 1) : ""
                + (mCalendar.get(Calendar.MONTH) + 1);
        day =
            (mCalendar.get(Calendar.DAY_OF_MONTH)) < 10 ? "0" + (mCalendar.get(Calendar.DAY_OF_MONTH)) : ""
                + (mCalendar.get(Calendar.DAY_OF_MONTH));
        hour =
            mCalendar.get(Calendar.HOUR_OF_DAY) < 10 ? "0" + mCalendar.get(Calendar.HOUR_OF_DAY) : ""
                + mCalendar.get(Calendar.HOUR_OF_DAY);
        min =
            mCalendar.get(Calendar.MINUTE) < 10 ? "0" + mCalendar.get(Calendar.MINUTE) : ""
                + mCalendar.get(Calendar.MINUTE);
        second =
            mCalendar.get(Calendar.SECOND) < 10 ? "0" + mCalendar.get(Calendar.SECOND) : ""
                + mCalendar.get(Calendar.SECOND);
        return year + "-" + month + "-" + day + "  " + hour + ":" + min + ":" + second;
    }
    
    /**
     * 获得三天前时间
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    private static String getBeforeTime()
    {
        
        String dateString = "";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        GregorianCalendar mCalendar = new GregorianCalendar();
        mCalendar.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        mCalendar.add(Calendar.DAY_OF_MONTH, -3);
        dateString = dateFormat.format(mCalendar.getTime());
        return dateString;
        
    }
}
