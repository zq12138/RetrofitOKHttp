package com.example.admin.retrofitokhttp.wxapi;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.example.admin.retrofitokhttp.BaseActivity;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;

/**
 * Created by zq on 2017/8/9.
 */

public class WXEntryActivity extends BaseActivity implements IWXAPIEventHandler {
    private IWXAPI api;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        api = WXAPI.createIWXAPI(this);
        api.handleIntent(getIntent(), this);
    }

    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp baseResp) {
        String result = ""+baseResp.errCode;
        switch (baseResp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
                setTitle("分享成功");
                result = "分享成功";//成功
                Toast.makeText(this, result, Toast.LENGTH_SHORT).show();

                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                result = "取消";//取消
                Toast.makeText(this, result, Toast.LENGTH_SHORT).show();

                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                result = "微信拒绝";//被拒绝
                Toast.makeText(this, result, Toast.LENGTH_SHORT).show();

                break;
            default:
//                result = "返回";//返回
                Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
                break;

        }

    }
}
