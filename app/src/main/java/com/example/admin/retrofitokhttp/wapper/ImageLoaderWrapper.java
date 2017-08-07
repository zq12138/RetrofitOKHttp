package com.example.admin.retrofitokhttp.wapper;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;


/**
 * Created by master on 2017/6/27 0027.
 */

public class ImageLoaderWrapper {

    public static void init(Context context) {
//        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
//                .diskCacheExtraOptions(480, 800, null)
//                .denyCacheImageMultipleSizesInMemory()
//                .memoryCache(new LruMemoryCache(2 * 1024 * 1024))
//                .diskCacheSize(50 * 1024 * 1024)
//                .diskCacheFileCount(100)
//                .writeDebugLogs()
//                .build();
//        ImageLoader.getInstance().init(config);
//        com.nostra13.universalimageloader.utils.L.disableLogging();//关闭logcat中的日志
//        ImageLoader.getInstance().init(config);
//        com.nostra13.universalimageloader.utils.L.disableLogging();//关闭logcat中的日志
    }

    public static void display(Context context, String url, ImageView imageView) {
//        DisplayImageOptions imageOptions = new DisplayImageOptions.Builder()
//                .cacheInMemory(true)
//                .cacheOnDisk(true)
//                .build();
//        ImageLoader.getInstance().displayImage(url, imageView,imageOptions);
        Glide.with(context).load(url).into(imageView);
    }

    public static void display(Context context, String url, ImageView imageView, ImageLoaderOptions options) {
        Glide.with(context)
                .load(url)
                .error(options.failedId)//失败
                .placeholder(options.LoadingId)//加载中
                .into(imageView);

//        DisplayImageOptions imageOptions = new DisplayImageOptions.Builder()
//                .showImageOnFail(options.failedId)
//                .showImageOnLoading(options.LoadingId)
//                .showImageForEmptyUri(options.emptyUriId)
//                .cacheInMemory(true)
//                .cacheOnDisk(true)
//                .build();
//        ImageLoader.getInstance().displayImage(url, imageView, imageOptions);
    }

    public static void loadImage(Context context, String url,ImageView imageView, final ImageLoadingListener loadingListener) {

        Glide.with(context).load(url).into(new GlideDrawableImageViewTarget(imageView) {
            @Override
            public void onLoadStarted(Drawable placeholder) {
                // 开始加载图片
            }

            @Override
            public void onLoadFailed(Exception e, Drawable errorDrawable) {
              //  图片加载失败

            }

            @Override
            public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                super.onResourceReady(resource, glideAnimation);
                // 图片加载完成
            }
        });
    }

    public interface ImageLoadingListener {
        void onLoadingStarted(String s, View view);

        void onLoadingFailed(String s, View view, String failedStr);

        void onLoadingComplete(String s, View view, Bitmap bitmap);
    }

    public static class EmptyImageLoadingListener implements ImageLoadingListener {

        @Override
        public void onLoadingStarted(String s, View view) {

        }

        @Override
        public void onLoadingFailed(String s, View view, String failedStr) {

        }

        @Override
        public void onLoadingComplete(String s, View view, Bitmap bitmap) {

        }

    }


}
