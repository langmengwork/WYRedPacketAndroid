package com.example.lx.wyredpacketandroid.api;


import com.example.lx.wyredpacketandroid.entity.CreateRedpackEntity;

import java.util.Map;

import io.reactivex.Observable;

import okhttp3.ResponseBody;

import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Url;

public interface IRetrofitAPI {

    @GET("{url}")
    Observable<ResponseBody> get(@Path("url")String url);

    @FormUrlEncoded
    @POST()
    Observable <ResponseBody> post(@Url String url, @FieldMap Map<String,String> parmes);

    @POST()
    Observable<ResponseBody> postjson(@Url() String url,@Body()Map<String,String> parmes);

}
