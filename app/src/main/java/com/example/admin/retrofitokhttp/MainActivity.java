package com.example.admin.retrofitokhttp;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;

import com.example.admin.retrofitokhttp.model.ImageCodeBean;
import com.example.admin.retrofitokhttp.retrofit.RequestCommand;

import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        imageView = (ImageView) findViewById(R.id.imageView);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequestCommand.login(new Callback() {
                    @Override
                    public void onResponse(Call call, Response response) {
                        ImageCodeBean imageCodeBean = (ImageCodeBean) response.body();
                        byte[] image = Base64.decode(imageCodeBean.getBody().getIndentify(), Base64.DEFAULT);
                        imageView.setImageBitmap(BitmapFactory.decodeByteArray(image, 0, image.length));
                    }

                    @Override
                    public void onFailure(Call call, Throwable t) {
                    }
                }, "3");
            }
        });
    }


}
