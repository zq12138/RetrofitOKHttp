package com.example.admin.retrofitokhttp;

import android.app.Application;

import com.example.admin.retrofitokhttp.wapper.ImageLoaderWrapper;

/**
 * Created by zq on 2017/8/3.
 */

public class ROApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        ImageLoaderWrapper.init(this);

    }
}
