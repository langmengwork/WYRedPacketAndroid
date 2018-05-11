package com.example.lx.wyredpacketandroid.ui.activity.personal_center.mvp.model;

import com.example.lx.wyredpacketandroid.entity.OpenPackEntity;
import com.example.lx.wyredpacketandroid.ui.activity.personal_center.entity.CollectMoneyEntity;
import com.example.lx.wyredpacketandroid.ui.activity.personal_center.entity.SendMoneyEntity;
import com.example.lx.wyredpacketandroid.ui.activity.personal_center.mvp.contract.PepContract;
import com.example.lx.wyredpacketandroid.utils.LogUtil;
import com.example.lx.wyredpacketandroid.utils.networkutil.RetrofitUtil;
import com.example.lx.wyredpacketandroid.utils.networkutil.UrlUtil;

import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class PepModel implements PepContract.Model {

    @Override
    public void gainCollect(final PepContract.Presenter presenter, Map<String, String> map) {

        RetrofitUtil.instance().post(UrlUtil.RECEIVELIST, map, CollectMoneyEntity.class, new Observer() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Object value) {

                CollectMoneyEntity entity = (CollectMoneyEntity) value;

                if (entity != null) {
                    presenter.sendCollect(entity.getData());
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void gainSend(final PepContract.Presenter presenter, Map<String, String> map) {

        RetrofitUtil.instance().post(UrlUtil.SENDLIST, map, SendMoneyEntity.class, new Observer() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Object value) {

                SendMoneyEntity entity = (SendMoneyEntity) value;

                if (entity != null) {
                    LogUtil.e("成功"+entity.getData().getList().size());
                    presenter.sendSend(entity.getData());
                }
            }

            @Override
            public void onError(Throwable e) {

                LogUtil.e("send失败"+e.toString());
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void gainDetails(final PepContract.Presenter presenter, Map<String, String> map) {

        RetrofitUtil.instance().post(UrlUtil.PACKDETAIL, map, OpenPackEntity.class, new Observer() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Object value) {

                OpenPackEntity entity = (OpenPackEntity) value;

                presenter.sendDetails(entity.getData());
            }

            @Override
            public void onError(Throwable e) {
                LogUtil.e("shibai"+e.toString());
            }

            @Override
            public void onComplete() {

            }
        });
    }

}
