package net.iwantbuyer.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import net.iwantbuyer.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketTimeoutException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.ForwardingSink;
import okio.ForwardingSource;
import okio.Okio;
import okio.Sink;
import okio.Source;

/**
 * Author: YangShuYu on 2016/9/15 23:24
 */
public class HttpUtils {
    private static HttpUtils httpUtils;
    private static OkHttpClient okHttpClient;

    private HttpUtils() {
    }

    public static synchronized HttpUtils getInstance() {
        if (httpUtils == null) {
            httpUtils = new HttpUtils();
            okHttpClient = new OkHttpClient.Builder()
                    .readTimeout(10, TimeUnit.SECONDS)
                    .connectTimeout(5, TimeUnit.SECONDS)
                    .writeTimeout(60, TimeUnit.SECONDS)
                    .build();
        }
        return httpUtils;
    }

    /**
     * 只是回调请求码的原因（200  返回success  其余返回error   一些另外错误（url错误） 会在Log中打印）
     */
    public interface OnRequestListener {
        public void success(String response, String link);

        public void error(int requestCode, String message);

        public void failure(Exception exception);
    }

    public OnRequestListener onRequestListener;

    public void setOnRequestListener(OnRequestListener onRequestListener) {
        this.onRequestListener = onRequestListener;
    }

    /**
     * 上传进度回调接口
     */
    public interface OnUploadPregressListener {
        public void progress(int progress);
    }

    public OnUploadPregressListener OnUploadPregressListener;

    public void OnUploadPregressListener(OnUploadPregressListener onUploadPregressListener) {
        this.OnUploadPregressListener = onUploadPregressListener;
    }

    /**
     * 下载进度回调接口
     */
    public interface OnDownProgressListener {
        public void progress(int progress);
    }

    public OnDownProgressListener onDownProgressListener;

    public void OnDownPregressListener(OnDownProgressListener onDownPregrossListener) {
        this.onDownProgressListener = onDownProgressListener;
    }


    /**
     * get请求
     *
     * @param url
     * @param onRequestListener
     */
    public void getRequest(String url, Map<String, String> map, final OnRequestListener onRequestListener) {
//        Request request = new Request.Builder()
//                .url(url)
//                .build();

        Request.Builder builder = new Request.Builder();
        builder.url(url);
        if (map != null) {
            Set<String> key = map.keySet();
            for (Iterator it = key.iterator(); it.hasNext(); ) {
                String s = (String) it.next();
                builder.addHeader(s, map.get(s));
            }
        }

        Request request = builder.build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

                onRequestListener.failure(e);
                Log.e("TAG_Okhttp", e.toString());
                Log.e("TAG..", e.getClass() +"");
                Log.e("TAG..", e.getLocalizedMessage() +"");
                Log.e("TAG..", e.getCause() +"");
                if (e.getClass().equals(SocketTimeoutException.class)) {
                    //网络连接超时  指定界面
                    Log.e("TAG", "连接超时");
                }

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (onRequestListener != null) {
                    if (response.code() == 200) {
                        onRequestListener.success(response.body().string(),response.header("link"));

//                        for (int i =0;i < response.headers().toString().split("\n").length;i++){
//                            response.header
//                        }
                    } else {
                        onRequestListener.error(response.code(), response.body().string());
                    }
                }
            }
        });
    }

    public void postRequest(String url, Map<String, String> map, final OnRequestListener onRequestListener) {


        FormBody.Builder builder = new FormBody.Builder();

        Request.Builder builde = new Request.Builder();

        if(map != null) {
            Set<String> key = map.keySet();
            for (Iterator it = key.iterator(); it.hasNext(); ) {
                String s = (String) it.next();
                builde.addHeader(s, map.get(s));
            }
        }
        RequestBody requestBody = builder.build();
        Request request = builde
                .url(url)
                .post(requestBody)
                .build();


        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("TAG_Okhttp", e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (onRequestListener != null) {
                    if (response.code() == 200) {
                        onRequestListener.success(response.body().string(),response.header("link"));
                    } else {
                        onRequestListener.error(response.code(), response.body().string());
                    }
                }
            }
        });
    }

    //delete
    public void deleteResponse(String url, Map<String, String> header, final OnRequestListener onRequestListener) {

        Set<String> key = header.keySet();
        Request.Builder builder = new Request.Builder();
        builder.url(url).delete();

        if (header != null) {
            for (Iterator it = key.iterator(); it.hasNext(); ) {
                String s = (String) it.next();
                builder.addHeader(s, header.get(s));
            }
        }

        Request request = builder.build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("TAG_Okhttp", e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (onRequestListener != null) {
                    if (response.code() == 200) {
                        onRequestListener.success(response.body().string(),response.header("link"));
                    } else {
                        onRequestListener.error(response.code(), response.body().string());

                    }
                }
            }
        });
    }

    //Post JSON
    public void postJson(String url, String json, Map<String, String> header, final OnRequestListener onRequestListener) {

        Set<String> key = header.keySet();
        Request.Builder builder = new Request.Builder();
        builder.url(url)
                .post(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json));

        if (header != null) {
            for (Iterator it = key.iterator(); it.hasNext(); ) {
                String s = (String) it.next();
                builder.addHeader(s, header.get(s));
            }
        }

        Request request = builder.build();


        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("TAG_Okhttp", e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (onRequestListener != null) {
                    if (response.code() == 200) {
                        onRequestListener.success(response.body().string(),response.header("link"));
                    } else {
                        onRequestListener.error(response.code(), response.body().string());

                    }
                }
            }
        });
    }
    //PUT JSON
    public void putJson(String url, String json, Map<String, String> header, final OnRequestListener onRequestListener) {

        Set<String> key = header.keySet();
        Request.Builder builder = new Request.Builder();
        builder.url(url)
                .put(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json));

        if (header != null) {
            for (Iterator it = key.iterator(); it.hasNext(); ) {
                String s = (String) it.next();
                builder.addHeader(s, header.get(s));
            }
        }

        Request request = builder.build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("TAG_Okhttp", e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (onRequestListener != null) {
                    if (response.code() == 200) {
                        onRequestListener.success(response.body().string(),response.header("link"));
                    } else {
                        onRequestListener.error(response.code(), response.body().string());

                    }
                }
            }
        });
    }

    //PUT JSON
    public void patchJson(String url, String json, Map<String, String> header, final OnRequestListener onRequestListener) {

        Set<String> key = header.keySet();
        Request.Builder builder = new Request.Builder();
        builder.url(url)
                .patch(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json));

        if (header != null) {
            for (Iterator it = key.iterator(); it.hasNext(); ) {
                String s = (String) it.next();
                builder.addHeader(s, header.get(s));
            }
        }

        Request request = builder.build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("TAG_Okhttp", e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (onRequestListener != null) {
                    if (response.code() == 200) {
                        onRequestListener.success(response.body().string(),response.header("link"));
                    } else {
                        onRequestListener.error(response.code(), response.body().string());

                    }
                }
            }
        });
    }

    public void UpLoadRequest(String url, File file, String type, final OnRequestListener onRequestListener, final OnUploadPregressListener onUploadPregressListener) {
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", type, RequestBody.create(MediaType.parse("application/pdf; charset=utf-8"), file))
                .build();
        ProgressRequestBody progressRequestBody = new ProgressRequestBody(requestBody, new ProgressListener() {
            @Override
            public void update(long bytesRead, long contentLength, boolean done) {
                if (onUploadPregressListener != null) {
                    int progress = (int) (100.0 * bytesRead / contentLength);
                    onUploadPregressListener.progress(progress);
                }
            }
        });
        Request request = new Request.Builder()
                .url(url)
                .post(progressRequestBody)
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("TAG", e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (onRequestListener != null) {
                    if (response.code() == 200) {
                        onRequestListener.success(response.body().string(),response.header("link"));
                    } else {
                        onRequestListener.error(response.code(), response.body().string());
                    }
                }
            }
        });
    }

    public void downRequest(String url, final File file, final OnRequestListener onRequestListener, final OnDownProgressListener onDownProgressListener) {
        okHttpClient = new OkHttpClient.Builder().addNetworkInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Response originalResponse = chain.proceed(chain.request());
                return originalResponse.newBuilder()
                        .body(new ProgressResponseBody(originalResponse.body(), new ProgressResponseListener() {
                            @Override
                            public void update(long bytesRead, long contentLength, boolean done) {
                                if (onDownProgressListener != null) {
                                    int progress = (int) (100.0 * bytesRead / contentLength);
                                    onDownProgressListener.progress(progress);
                                }

                            }
                        }))
                        .build();
            }
        })
                .connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(300, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build();
        Request request = new Request.Builder()
                .url(url)
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("TAG", e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (onRequestListener != null) {
                    if (response.code() == 200) {
                        Log.e("TAG_okhttp", "下载成功");
                        onRequestListener.success(response.body().string(),response.header("link"));
                        if (response != null) {
                            //下载完成，保存数据到文件
                            InputStream is = response.body().byteStream();
                            FileOutputStream fos = new FileOutputStream(file);
                            byte[] buf = new byte[1024];
                            int hasRead = 0;
                            while ((hasRead = is.read(buf)) > 0) {
                                fos.write(buf, 0, hasRead);
                            }
                            fos.close();
                            is.close();
                        }
                    } else {
                        onRequestListener.error(response.code(), response.body().string());
                    }
                }

            }
        });
    }

    //自定义的RequestBody，能够显示进度
    public class ProgressRequestBody extends RequestBody {
        //实际的待包装请求体
        private final RequestBody requestBody;
        //进度回调接口
        private final ProgressListener progressListener;
        //包装完成的BufferedSink
        private BufferedSink bufferedSink;

        /**
         * 构造函数，赋值
         *
         * @param requestBody      待包装的请求体
         * @param progressListener 回调接口
         */
        public ProgressRequestBody(RequestBody requestBody, ProgressListener progressListener) {
            this.requestBody = requestBody;
            this.progressListener = progressListener;
        }

        /**
         * 重写调用实际的响应体的contentType
         *
         * @return MediaType
         */
        @Override
        public MediaType contentType() {
            return requestBody.contentType();
        }

        /**
         * 重写调用实际的响应体的contentLength
         *
         * @return contentLength
         * @throws IOException 异常
         */
        @Override
        public long contentLength() throws IOException {
            return requestBody.contentLength();
        }

        /**
         * 重写进行写入
         *
         * @param sink BufferedSink
         * @throws IOException 异常
         */
        @Override
        public void writeTo(BufferedSink sink) throws IOException {
            if (bufferedSink == null) {
                //包装
                bufferedSink = Okio.buffer(sink(sink));
            }
            //写入
            requestBody.writeTo(bufferedSink);
            //必须调用flush，否则最后一部分数据可能不会被写入
            bufferedSink.flush();

        }

        /**
         * 写入，回调进度接口
         *
         * @param sink Sink
         * @return Sink
         */
        private Sink sink(Sink sink) {
            return new ForwardingSink(sink) {
                //当前写入字节数
                long bytesWritten = 0L;
                //总字节长度，避免多次调用contentLength()方法
                long contentLength = 0L;

                @Override
                public void write(Buffer source, long byteCount) throws IOException {
                    super.write(source, byteCount);
                    if (contentLength == 0) {
                        //获得contentLength的值，后续不再调用
                        contentLength = contentLength();
                    }
                    //增加当前写入的字节数
                    bytesWritten += byteCount;
                    //回调
                    progressListener.update(bytesWritten, contentLength, bytesWritten == contentLength);
                }
            };
        }
    }

    //进度回调接口
    interface ProgressListener {
        void update(long bytesRead, long contentLength, boolean done);
    }

    /**
     * 自定义的ResponseBody，在其中处理下载进度
     */
    private static class ProgressResponseBody extends ResponseBody {

        private final ResponseBody responseBody;
        private final ProgressResponseListener progressResponseListener;
        private BufferedSource bufferedSource;

        public ProgressResponseBody(ResponseBody responseBody, ProgressResponseListener progressResponseListener) {
            this.responseBody = responseBody;
            this.progressResponseListener = progressResponseListener;
        }

        @Override
        public MediaType contentType() {
            return responseBody.contentType();
        }

        @Override
        public long contentLength() {
            return responseBody.contentLength();
        }

        @Override
        public BufferedSource source() {
            if (bufferedSource == null) {
                bufferedSource = Okio.buffer(source(responseBody.source()));
            }
            return bufferedSource;
        }

        private Source source(Source source) {
            return new ForwardingSource(source) {
                long totalBytesRead = 0L;

                @Override
                public long read(Buffer sink, long byteCount) throws IOException {
                    long bytesRead = super.read(sink, byteCount);
                    // read() returns the number of bytes read, or -1 if this source is exhausted.
                    totalBytesRead += bytesRead != -1 ? bytesRead : 0;
                    progressResponseListener.update(totalBytesRead, responseBody.contentLength(), bytesRead == -1);
                    return bytesRead;
                }
            };
        }
    }

    //进度回调接口
    interface ProgressResponseListener {
        void update(long bytesRead, long contentLength, boolean done);
    }

    /**
     * 联网请求 等待 alertdialog
     */

    static AlertDialog show;

    public void startNetworkWaiting(Context context) {
        //得到屏幕的 尺寸 动态设置
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int screenWidth = wm.getDefaultDisplay().getWidth();
        int screenHeight = wm.getDefaultDisplay().getHeight();

        View inflate = View.inflate(context, R.layout.network_waiting_alertdialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(inflate);
        if(context instanceof Activity && ((Activity)context).isDestroyed()) {
            return;
        }
        show = builder.show();
        show.setCanceledOnTouchOutside(false);   //点击外部不消失
        show.setCancelable(false);               //点击外部和返回按钮都不消失
//        show.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        Window window = show.getWindow();
//        window.setGravity(Gravity.BOTTOM);
        show.getWindow().setLayout(3 * screenWidth / 8, 3 * screenHeight / 20);
        show.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    public void stopNetWorkWaiting() {
        if (show != null&&show.isShowing()) {
            show.dismiss();
        }
    }
}
