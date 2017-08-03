package com.example.admin.retrofitokhttp.retrofit;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by zq on 2017/8/3.
 */

public class RequestCommand {


    private static ServiceApi getApi() {
        return ServiceCreate.getServiceApi();
    }
    public static void login(Callback callback,String terminalType) {
        Call call = getApi().login(terminalType);
        call.enqueue(callback);
    }

}
