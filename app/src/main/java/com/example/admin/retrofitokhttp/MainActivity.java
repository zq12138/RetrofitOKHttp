package com.example.admin.retrofitokhttp;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.admin.retrofitokhttp.model.ImageCodeBean;
import com.example.admin.retrofitokhttp.retrofit.RequestCommand;
import com.example.admin.retrofitokhttp.retrofit.callback.RequesCallBack;

import java.util.List;

import butterknife.ButterKnife;
import cn.jpush.android.api.JPushInterface;
import retrofit2.Response;

public class MainActivity extends BaseActivity {
    ImageView imageView;
    Button btn,stopPush,resumePush;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        imageView = (ImageView) findViewById(R.id.imageView);
        btn=(Button)findViewById(R.id.button);
        stopPush=(Button)findViewById(R.id.stopPush);
        resumePush=(Button)findViewById(R.id.resumePush);
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


}
