package com.example.admin.retrofitokhttp.retrofit;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

/**
 * Created by zq on 2017/8/3.
 */

public class CookieJars implements CookieJar {

    public List<Cookie> list = new ArrayList<>();

    @Override
    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
        list = cookies;
    }

    @Override
    public List<Cookie> loadForRequest(HttpUrl url) {
        return list;
    }
}
