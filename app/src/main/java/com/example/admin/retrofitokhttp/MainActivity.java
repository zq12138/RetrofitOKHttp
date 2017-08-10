package com.example.admin.retrofitokhttp;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.admin.retrofitokhttp.model.ImageCodeBean;
import com.example.admin.retrofitokhttp.retrofit.RequestCommand;
import com.example.admin.retrofitokhttp.retrofit.callback.RequesCallBack;
import com.example.admin.retrofitokhttp.wxapi.WXAPI;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;

import java.util.List;

import butterknife.ButterKnife;
import cn.jpush.android.api.JPushInterface;
import retrofit2.Response;

public class MainActivity extends BaseActivity {
    ImageView imageView;
    Button btn, stopPush, resumePush, wx_share;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        imageView = (ImageView) findViewById(R.id.imageView);
        btn = (Button) findViewById(R.id.button);
        stopPush = (Button) findViewById(R.id.stopPush);
        resumePush = (Button) findViewById(R.id.resumePush);
        wx_share = (Button) findViewById(R.id.wx_share);
        wx_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WXWebpageObject webpage = new WXWebpageObject();
                webpage.webpageUrl = "www.baidu.com";
                WXMediaMessage msg = new WXMediaMessage(webpage);
                msg.title = "asfds";
                msg.description = "fsfdsgfd";

                SendMessageToWX.Req req = new SendMessageToWX.Req();
                req.transaction = buildTransaction("webpage");
                req.message = msg;
                req.scene = SendMessageToWX.Req.WXSceneTimeline;
                WXAPI.createIWXAPI(MainActivity.this).sendReq(req);
            }
        });
        stopPush.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JPushInterface.stopPush(MainActivity.this);
            }
        });
        resumePush.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JPushInterface.resumePush(MainActivity.this);
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isWeixinAvilible(MainActivity.this)) return;
                Intent intent = new Intent();
                ComponentName cmp = new ComponentName("com.stateunion.p2p.etongdai", "com.stateunion.p2p.etongdai.activity.login.LoginActivity");
                intent.setAction(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_LAUNCHER);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setComponent(cmp);
                startActivity(intent);
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequestCommand.login(new RequesCallBack(MainActivity.this) {
                    @Override
                    public void onResponseSuccess(Response response) {
                        ImageCodeBean imageCodeBean = (ImageCodeBean) response.body();
                        byte[] image = Base64.decode(imageCodeBean.getBody().getIndentify(), Base64.DEFAULT);
                        imageView.setImageBitmap(BitmapFactory.decodeByteArray(image, 0, image.length));
                    }
                }, "3");
            }
        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public static boolean isWeixinAvilible(Context context) {
        final PackageManager packageManager = context.getPackageManager();
        // 获取packagemanager
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);
        // 获取所有已安装程序的包信息
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

    public void shareTxtWX() {
        WXAPI wxapi=new WXAPI(this);
        wxapi.wxSceneTimeline();
    }

    private String buildTransaction(final String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
