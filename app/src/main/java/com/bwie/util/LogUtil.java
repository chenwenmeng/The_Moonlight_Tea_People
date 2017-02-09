package com.bwie.util;

import android.util.Log;

/**
 * 类描述:Log工具类
 * 作者：陈文梦
 * 时间:2016/12/28 18:42
 * 邮箱:18310832074@163.com
 */

public class LogUtil {

    public static  final  boolean DEBUG = true;
    public static final String TAG = "TAG";
    private static LogUtil sLogUtil;

    private LogUtil() {
    }

    public static LogUtil getInstance() {
        if (sLogUtil == null) {
            synchronized (LogUtil.class) {
                if (sLogUtil == null) {
                    sLogUtil = new LogUtil();
                }
            }
        }
        return sLogUtil;
    }

    public void debug(String msg){
        if(DEBUG){
            Log.d(TAG,msg);
        }
    }

    public void info(String msg){
        if(DEBUG){
            Log.i(TAG,msg);
        }
    }

    public void error(String msg){
        if(DEBUG){
            Log.e(TAG,msg);
        }
    }

    public void warn(String msg){
        if(DEBUG){
            Log.w(TAG,msg);
        }
    }


}
