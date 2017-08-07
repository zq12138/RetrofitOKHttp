package com.example.admin.retrofitokhttp.retrofit.callback;

import com.example.admin.retrofitokhttp.retrofit.basebean.IBaseBean;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by zq on 2017/8/4.
 */

public abstract class RequestCallBack<E extends IBaseBean> implements Callback<E> {

    protected abstract void onResponseSuccess(E e, Call<E> call);

    @Override
    public void onResponse(Call<E> call, Response<E> response) {
        if (response.body() == null) {
            onFailure(call);
            return;
        }
        if (response.body().isSuccess()) {
            onResponseSuccess(response.body(), call);
        } else {
            onResponseFailure(response.body(), call);
        }
    }

    // 会话超时
    public static final String ERROR_CODE_SESSION_TIMEOUT = "800000";

    // 用户被挤掉
    protected static final String ERROR_CODE_LOGIN_CROWD = "800001";
    // 账户冻结
    protected static final String ERROR_CODE_ACCOUNT_FREEZE = "103";

    protected abstract void onResponseSessionTimeOut(E e, Call<E> call);

    protected abstract void onResponseLoginCrowd(E e, Call<E> call);

    protected abstract void onResponseAccountFreeze(E e, Call<E> call);

    protected abstract void onResponseFeilureMessage(E e, Call<E> call);

    public abstract void onPreRequst(Call<E> call);

    private void onResponseFailure(E body, Call<E> call) {
        if (("ERROR_CODE_SESSION_TIMEOUT").equals(body.getCode())) {
            onResponseSessionTimeOut(body, call);
        } else if (("ERROR_CODE_LOGIN_CROWD").equals(body.getCode())) {
            onResponseLoginCrowd(body, call);
        } else if (("ERROR_CODE_ACCOUNT_FREEZE").equals(body.getCode())) {
            onResponseAccountFreeze(body, call);
        } else {
            onResponseFeilureMessage(body, call);
        }
    }

    @Override
    public void onFailure(Call<E> call, Throwable t) {
        onFailure(call);
    }

    protected abstract void onFailure(Call<E> call);
}
