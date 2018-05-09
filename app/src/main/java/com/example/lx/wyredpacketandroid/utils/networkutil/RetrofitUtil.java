package com.example.lx.wyredpacketandroid.utils.networkutil;


import android.content.Context;
import android.util.Log;

import com.example.lx.wyredpacketandroid.R;
import com.example.lx.wyredpacketandroid.api.IRetrofitAPI;
import com.example.lx.wyredpacketandroid.base.MainApplication;
import com.example.lx.wyredpacketandroid.entity.CreateRedpackEntity;
import com.example.lx.wyredpacketandroid.entity.UserInfoEntity;
import com.example.lx.wyredpacketandroid.utils.LogUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by Thinkpad on 2017/11/2.
 * 网络请求工具类
 */

public class RetrofitUtil<T> {

    //超时参数
    private static final int MAXTIME = 60;
    private static RetrofitUtil retrofitUtil;
    private final IRetrofitAPI api;
    private T type;
    private static OkHttpClient client;

    //构造函数初始化retrofit
    private RetrofitUtil(String baseurl) {

        client = new OkHttpClient.Builder()
                .sslSocketFactory(getSSLSocketFactory(MainApplication.activity))
                .connectTimeout(MAXTIME, TimeUnit.SECONDS)
                .readTimeout(MAXTIME, TimeUnit.SECONDS)
                .writeTimeout(MAXTIME, TimeUnit.SECONDS)
                .build();

        //向retrofit配置okhttp
        api = new Retrofit.Builder()
                .client(client)
                .baseUrl(baseurl)  //传入公司域名
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(IRetrofitAPI.class);
    }

    //单例retrofitutil
    public static RetrofitUtil instance() {

        if (retrofitUtil == null) {

            synchronized (RetrofitUtil.class) {

                if (retrofitUtil == null) {

                    retrofitUtil = new RetrofitUtil(UrlUtil.URL_BASE);
                }
            }
        }

        return retrofitUtil;
    }

    /**
     * @param url      请求地址
     * @param observer 请求回调
     *                 直接返回结果的String，不做解析
     */
    public void getStr(String url, Observer<Object> observer) {
        Observable<ResponseBody> observable = api.get(url);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<ResponseBody, String>() {
                    @Override
                    public String apply(ResponseBody responseBody) throws Exception {
                        return responseBody.string();
                    }
                })
                .subscribe(observer);
    }

    public void get(String url, Observer<Object> observer) {
        Observable<ResponseBody> observable = api.get(url);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<ResponseBody, T>() {
                    @Override
                    public T apply(ResponseBody responseBody) throws Exception {
                        return new Gson().fromJson(responseBody.string(), new TypeToken<T>() {
                        }.getType());
                    }
                })
                .subscribe(observer);
    }

    //get固定参数
    public void get(String url, final T t, Observer observer) {

        type = t;
        Observable<ResponseBody> observable = api.get(url);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<ResponseBody, T>() {
                    @Override
                    public T apply(ResponseBody responseBody) throws Exception {

                        Gson gson = new Gson();
                        type = (T) gson.fromJson(responseBody.string(), (Class<Object>) t);
                        return type;
                    }
                })
                .subscribe(observer);
    }

    //get固定参数 返回josn
    public void getjson(String url, Observer observer) {

        Observable<ResponseBody> observable = api.get(url);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<ResponseBody, String>() {
                    @Override
                    public String apply(ResponseBody responseBody) throws Exception {

                        return responseBody.string();
                    }
                })
                .subscribe(observer);
    }

    //get自定义参数
    public void get(String serverurl, Map<String, String> params, final T t, Observer observer) {

        type = t;

        Observable<ResponseBody> observable = api.get(Mosaic(serverurl, params));
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<ResponseBody, T>() {
                    @Override
                    public T apply(ResponseBody responseBody) throws Exception {
                        Gson gson = new Gson();
                        type = (T) gson.fromJson(responseBody.string(), (Class<Object>) t);
                        return type;
                    }
                })
                .subscribe(observer);
    }

    public static String Mosaic(String serverurl, Map<String, String> params) {

        StringBuffer sb = null;

        if (params != null) {
            Iterator<String> iterator = params.keySet().iterator();
            while (iterator.hasNext()) {
                String key = iterator.next();
                String value = params.get(key);
                if (sb == null) {
                    sb = new StringBuffer();
                    sb.append("?");
                } else {
                    sb.append("&");
                }
                sb.append(key);
                sb.append("=");
                sb.append(value);
            }

        }


        return serverurl += sb.toString();
    }

    //post自定义参数
    public void post(String serverurl, Map<String, String> map, final T t, Observer observer) {

        type = t;

        LogUtil.e(UrlUtil.URL_BASE + Mosaic(serverurl,map));

        Observable<ResponseBody> observable = api.post(serverurl,map);

        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<ResponseBody, T>() {
                    @Override
                    public T apply(ResponseBody responseBody) throws Exception {

                        Gson gson = new Gson();

                        type = (T) gson.fromJson(responseBody.string(), (Class<Object>) t);

                        return type;
                    }
                })
                .subscribe(observer);
    }

    //post自定义参数 json请求
    public void postJson(String serverurl, Map<String,String> parmes, final T t, Observer observer) {

        type = t;

        Observable<ResponseBody> observable = api.postjson(serverurl,parmes);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<ResponseBody, T>() {
                    @Override
                    public T apply(ResponseBody responseBody) throws Exception {

                        Gson gson = new Gson();
                        type = (T) gson.fromJson(responseBody.string(), (Class<Object>) t);

                        return type;
                    }
                })
                .subscribe(observer);
    }

    public static SSLSocketFactory getSSLSocketFactory(Context context) {
        final String CLIENT_TRUST_PASSWORD = "o90f8l51wv0";//信任证书密码，该证书默认密码是123456
        final String CLIENT_AGREEMENT = "TLS";//使用协议
        final String CLIENT_TRUST_KEYSTORE = "BKS";
        SSLContext sslContext = null;
        try {
            //取得SSL的SSLContext实例
            sslContext = SSLContext.getInstance(CLIENT_AGREEMENT);
            //取得TrustManagerFactory的X509密钥管理器实例
            TrustManagerFactory trustManager = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            //取得BKS密库实例
            KeyStore tks = KeyStore.getInstance(CLIENT_TRUST_KEYSTORE);
            InputStream is = context.getResources().openRawResource(R.raw.wxy);
            try {
                tks.load(is, CLIENT_TRUST_PASSWORD.toCharArray());
            } finally {
                is.close();
            }
            //初始化密钥管理器
            trustManager.init(tks);
            //初始化SSLContext
            sslContext.init(null, trustManager.getTrustManagers(), null);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("SslContextFactory", e.getMessage());
        }
        return sslContext.getSocketFactory();
    }

}
