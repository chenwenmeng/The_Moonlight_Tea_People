package com.bwie.tea_people;

import android.app.Application;


import android.content.Context;



import com.bwie.util.ImageLoaderUtils;

/**
 * 类描述:
 * 作者：陈文梦
 * 时间:2017/2/9 18:54
 * 邮箱:18310832074@163.com
 */

public class MyApp extends Application {

    //点击首页左侧数据记录位置
    public static int num=0;
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context=this;
        ImageLoaderUtils.initConfiguration(context);
    }
}
