package com.example.admin.retrofitokhttp.retrofit.callback;

import android.app.Dialog;
import android.widget.Toast;

import com.example.admin.retrofitokhttp.R;
import com.example.admin.retrofitokhttp.retrofit.basebean.IBaseDialogView;

import java.lang.ref.WeakReference;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by zq on 2017/8/7.
 */

public abstract class RequesCallBack<E, T extends IBaseDialogView> implements Callback<E> {

    public RequesCallBack(T requesr) {
        attachTargetWeakReference = new WeakReference<>(requesr);
    }

    WeakReference<T> attachTargetWeakReference;
    Dialog dialog;

    protected T getAttachTarget() {
        return attachTargetWeakReference.get();
    }

    public void onPreRequest() {
        dialog = getAttachTarget().createDialog(R.style.customDialogTheme);
        dialog.setContentView(R.layout.wait_progress_dialog);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    @Override
    public void onResponse(Call<E> call, Response<E> response) {
        dissmisDialog();
        if (response.body() == null) {
            Toast.makeText(getAttachTarget().getBaseActivity(), "网络不给力", Toast.LENGTH_LONG).show();



            return;
        }
        if (response.isSuccessful()) {
            onResponseSuccess(response);
        } else {
            Toast.makeText(getAttachTarget().getBaseActivity(), response.message(), Toast.LENGTH_LONG).show();
        }
    }

    private void dissmisDialog() {
        if (dialog == null) return;
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    public abstract void onResponseSuccess(Response<E> response);

    @Override
    public void onFailure(Call<E> call, Throwable t) {
        dissmisDialog();
        Toast.makeText(getAttachTarget().getBaseActivity(), "网络不给力", Toast.LENGTH_LONG).show();
    }
}
