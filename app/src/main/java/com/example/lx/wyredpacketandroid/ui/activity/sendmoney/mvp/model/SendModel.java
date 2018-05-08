package com.example.lx.wyredpacketandroid.ui.activity.sendmoney.mvp.model;

import com.example.lx.wyredpacketandroid.entity.CreateRedpackEntity;
import com.example.lx.wyredpacketandroid.ui.activity.sendmoney.entity.QiniuTokenEntity;
import com.example.lx.wyredpacketandroid.ui.activity.sendmoney.mvp.contract.SendContract;
import com.example.lx.wyredpacketandroid.utils.LogUtil;
import com.example.lx.wyredpacketandroid.utils.networkutil.RetrofitUtil;
import com.example.lx.wyredpacketandroid.utils.networkutil.UrlUtil;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class SendModel implements SendContract.Model {

    @Override
    public void gainSendPack(final SendContract.Presenter presenter, Map<String, String> map) {

        RetrofitUtil.instance().postJson(UrlUtil.CREATEREDPACK, map, CreateRedpackEntity.class, new Observer() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Object value) {

                CreateRedpackEntity entity = (CreateRedpackEntity) value;

                presenter.sendSendPack(entity.getData());

            }

            @Override
            public void onError(Throwable e) {
                LogUtil.e("失败失败" + e.toString());
            }

            @Override
            public void onComplete() {

            }});

    }

    @Override
    public void gainQiniuToken(final SendContract.Presenter presenter) {

        RetrofitUtil.instance().get(UrlUtil.QINIUTOKEN, QiniuTokenEntity.class, new Observer() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Object value) {

                QiniuTokenEntity entity = (QiniuTokenEntity) value;

                presenter.sendQiniuToken(entity.getData().getToken());

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }


}
