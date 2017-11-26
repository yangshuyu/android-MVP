package net.iwantbuyer.api;

import android.util.Log;

import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by yangshuyu on 2017/5/4.
 */
public class OkhttpInterceptor {
    public HttpLoggingInterceptor  interceptor(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.e("TAG312", message);
            }
        });
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return interceptor;
    }

}
