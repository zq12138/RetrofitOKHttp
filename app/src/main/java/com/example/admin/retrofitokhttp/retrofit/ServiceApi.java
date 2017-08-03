package com.example.admin.retrofitokhttp.retrofit;

import com.example.admin.retrofitokhttp.model.ImageCodeBean;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by zq on 2017/8/3.
 */

public interface ServiceApi {

    @POST("api/system/identify")
    @FormUrlEncoded
    Call<ImageCodeBean> login(@Field("terminalType")String terminalType);


}
