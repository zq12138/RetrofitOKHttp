package com.example.admin.retrofitokhttp.retrofit;

import com.example.admin.retrofitokhttp.retrofit.callback.RequesCallBack;

import retrofit2.Call;

/**
 * Created by zq on 2017/8/3.
 */

public class RequestCommand {


    private static ServiceApi getApi() {
        return ServiceCreate.getServiceApi();
    }

    public static void login(RequesCallBack callBack, String terminalType) {
        Call call = getApi().login(terminalType);
        send(callBack, call);
    }


    private static void send(RequesCallBack callBack, Call call) {
        callBack.onPreRequest();
        call.enqueue(callBack);
    }

}
