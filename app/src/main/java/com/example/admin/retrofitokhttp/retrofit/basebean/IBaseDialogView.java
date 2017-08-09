package com.example.admin.retrofitokhttp.retrofit.basebean;

import android.app.Dialog;
import android.support.annotation.StyleRes;

import com.example.admin.retrofitokhttp.BaseActivity;

/**
 * Created by zq on 2017/8/7.
 */

public interface IBaseDialogView {

    Dialog createDialog(@StyleRes int themeResId);

    void showError(String message);

    BaseActivity getBaseActivity();

    boolean isAlive();
}
