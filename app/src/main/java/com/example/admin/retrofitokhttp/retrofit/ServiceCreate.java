package com.example.admin.retrofitokhttp.retrofit;

import android.util.Log;

import com.example.admin.retrofitokhttp.BuildConfig;
import com.example.admin.retrofitokhttp.commons.Config;
import com.ihsanbal.logging.Level;
import com.ihsanbal.logging.LoggingInterceptor;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by zq on 2017/8/3.
 */

public class ServiceCreate {
    private static ServiceApi serviceApi = createApi();

    public ServiceCreate() {
    }

    public static ServiceApi getServiceApi() {
        return serviceApi;
    }

    private static ServiceApi createApi() {
        Retrofit mRetrofit = new Retrofit.Builder()
                .baseUrl(Config.HTTPS_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(createOKHttpClient())
                .build();
        return mRetrofit.create(ServiceApi.class);
    }

    private static OkHttpClient createOKHttpClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(new ParamInterceptor())
                .addInterceptor(getLogInterceptor())
                .hostnameVerifier(new NotHostnameVerifier())
                .sslSocketFactory(createSSLSocket())
                .cookieJar(new CookieJars())
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();
    }

    private static SSLSocketFactory createSSLSocket() {
        try {
            SSLContext ssl = SSLContext.getInstance("SSL");
            X509TrustManager xtm = new X509TrustManager() {
                @Override
                public void checkClientTrusted(X509Certificate[] chain, String authType) {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] chain, String authType) {
                }

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    X509Certificate[] x509Certificates = new X509Certificate[0];
                    return x509Certificates;
                }
            };
            ssl.init(null, new TrustManager[]{xtm}, new SecureRandom());
            return ssl.getSocketFactory();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Interceptor getLogInterceptor() {
        return new LoggingInterceptor.Builder()
                .loggable(BuildConfig.DEBUG)
                .setLevel(Level.BASIC)
                .log(Log.WARN)
                .request("Request")
                .response("Response")
                .addHeader("version", BuildConfig.VERSION_NAME)
                .build();
    }
}
