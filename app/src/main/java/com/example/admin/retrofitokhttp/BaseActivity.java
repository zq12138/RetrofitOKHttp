package com.example.admin.retrofitokhttp;

import android.app.Dialog;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.StyleRes;
import android.support.v7.app.AppCompatActivity;

import com.example.admin.retrofitokhttp.retrofit.basebean.IBaseDialogView;

/**
 * Created by zq on 2017/8/7.
 */

public class BaseActivity extends AppCompatActivity implements IBaseDialogView {

    private boolean isAlive = false;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        isAlive = true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        isAlive = false;
    }

    @Override
    public Dialog createDialog(@StyleRes int themeResId) {
        Dialog dialog = new Dialog(this, themeResId);
        return dialog;
    }

    @Override
    public void showError(String message) {

    }

    @Override
    public BaseActivity getBaseActivity() {
        return BaseActivity.this;
    }

    @Override
    public boolean isAlive() {
        return isAlive;
    }
}
