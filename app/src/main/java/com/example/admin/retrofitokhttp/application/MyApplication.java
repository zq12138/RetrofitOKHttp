package com.example.admin.retrofitokhttp.application;

import android.app.Application;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by zq on 2017/8/9.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
    }
}
