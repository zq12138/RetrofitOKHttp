package com.example.admin.retrofitokhttp.wxapi;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.List;

/**
 * Created by master on 2016/12/8.
 */

public class WXAPI {
    private IWXAPI api;
    private static final String APP_ID = "wxfaea8d59847aa3ef";

    /**
     * 创建IWXAPI
     *
     * @param context
     * @return
     */
    public static IWXAPI createIWXAPI(Context context) {
        return WXAPIFactory.createWXAPI(context, APP_ID, false);
    }

    /**
     * 注册微信分享组件，在应用开始部分调用
     *
     * @param context
     */
    public static void registeApi(Context context) {
        WXAPIFactory.createWXAPI(context, APP_ID, true).registerApp(APP_ID);
    }

    Context context;

    public WXAPI(Context context) {
        this.context = context;
        api = createIWXAPI(context);
    }

    /**
     * 微信朋友圈分享
     */
    public void wxSceneTimeline() {

    }



    /**
     * 微信分享工具类，生成唯一ID
     *
     * @param type
     * @return
     */
    private String buildTransaction(final String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
    }

    /**
     * 判断是否安装微信
     *
     * @return
     */
    public boolean isWeixinAvilible() {
        final PackageManager packageManager = context.getPackageManager();// 获取packagemanager
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);// 获取所有已安装程序的包信息
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                if (pn.equals("com.tencent.mm")) {
                    return true;
                }
            }
        }
        return false;
    }
}
