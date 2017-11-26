package net.iwantbuyer.api;

import android.util.Log;

import net.iwantbuyer.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.Headers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by yangshuyu on 2017/5/4.
 */
public class RetrofitUtils {
    private static final String NEWS_HOST = "https://api-my.luckybuyer.net";
    public static RetrofitUtils retrofitUtils;
    public static Retrofit retrofit;
    public static LogInterceptor logInterceptor;
    public Api api;
    public static Headers headers;

    public static synchronized RetrofitUtils getInstance() {
        if (retrofitUtils == null) {
            retrofitUtils = new RetrofitUtils();

            OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
//          builder.addNetworkInterceptor(new NetworkInterceptor());
            builder.connectTimeout(10, TimeUnit.SECONDS);
//            if (BuildConfig.DEBUG) {
            //日志拦截器
//                builder.addNetworkInterceptor(new OkhttpInterceptor().interceptor());
            logInterceptor = new LogInterceptor();
            builder.addInterceptor(logInterceptor);
//            }
            headers = logInterceptor.headers;
            OkHttpClient client = builder.build();

            retrofit = new Retrofit.Builder()
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .baseUrl(NEWS_HOST)
                    .build();

        }
        return retrofitUtils;
    }

    public Api getApi(){
        return api = retrofit.create(Api.class);
    }

    public Headers getHeaders(){
        return logInterceptor.headers;
    }
}
