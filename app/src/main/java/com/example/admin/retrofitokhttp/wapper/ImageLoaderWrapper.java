package com.example.admin.retrofitokhttp.wapper;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;

import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;


/**
 * Created by master on 2017/6/27 0027.
 */

public class ImageLoaderWrapper {

    public static void init(Context context){
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                .diskCacheExtraOptions(480, 800, null)
                .denyCacheImageMultipleSizesInMemory()
                .memoryCache(new LruMemoryCache(2 * 1024 * 1024))
                .diskCacheSize(50 * 1024 * 1024)
                .diskCacheFileCount(100)
                .writeDebugLogs()
                .build();
        ImageLoader.getInstance().init(config);
        com.nostra13.universalimageloader.utils.L.disableLogging();//关闭logcat中的日志
        ImageLoader.getInstance().init(config);
        com.nostra13.universalimageloader.utils.L.disableLogging();//关闭logcat中的日志
    }
    public static void display(String url, ImageView imageView) {
        DisplayImageOptions imageOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();
        ImageLoader.getInstance().displayImage(url, imageView,imageOptions);
    }

    public static void display(String url, ImageView imageView, ImageLoaderOptions options) {
        DisplayImageOptions imageOptions = new DisplayImageOptions.Builder()
                .showImageOnFail(options.failedId)
                .showImageOnLoading(options.LoadingId)
                .showImageForEmptyUri(options.emptyUriId)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();
        ImageLoader.getInstance().displayImage(url, imageView, imageOptions);
    }

    public static void loadImage(String url, final ImageLoadingListener loadingListener) {
        ImageLoader.getInstance().loadImage(url, new SimpleImageLoadingListener() {
            @Override
            public void onLoadingStarted(String s, View view) {
                loadingListener.onLoadingStarted(s, view);
            }

            @Override
            public void onLoadingFailed(String s, View view, FailReason failReason) {
                loadingListener.onLoadingFailed(s, view, failReason.toString());
            }

            @Override
            public void onLoadingComplete(String s, View view, Bitmap bitmap) {
                loadingListener.onLoadingComplete(s, view, bitmap);
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
