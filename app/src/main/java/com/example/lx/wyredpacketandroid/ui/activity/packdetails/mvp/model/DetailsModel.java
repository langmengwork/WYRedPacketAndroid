package com.example.lx.wyredpacketandroid.ui.activity.packdetails.mvp.model;

import com.example.lx.wyredpacketandroid.ui.activity.packdetails.entity.MessageListEntity;
import com.example.lx.wyredpacketandroid.ui.activity.packdetails.mvp.contract.DetailsContract;
import com.example.lx.wyredpacketandroid.utils.networkutil.RetrofitUtil;
import com.example.lx.wyredpacketandroid.utils.networkutil.UrlUtil;

import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class DetailsModel implements DetailsContract.Model {
    @Override
    public void gainLoadMore(final DetailsContract.Presenter presenter, Map<String, String> map) {

        RetrofitUtil.instance().post(UrlUtil.MESSAGELIST, map, MessageListEntity.class, new Observer() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Object value) {

                MessageListEntity entity = (MessageListEntity) value;

                if (entity != null) {
                    presenter.sendLoadMore(entity.getData());
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
}
