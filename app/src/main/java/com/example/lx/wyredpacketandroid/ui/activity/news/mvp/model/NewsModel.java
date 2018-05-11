package com.example.lx.wyredpacketandroid.ui.activity.news.mvp.model;

import com.example.lx.wyredpacketandroid.entity.OpenPackEntity;
import com.example.lx.wyredpacketandroid.ui.activity.news.entity.NewsEntity;
import com.example.lx.wyredpacketandroid.ui.activity.news.mvp.contract.NewsContract;
import com.example.lx.wyredpacketandroid.ui.activity.packdetails.entity.SLReplyEntity;
import com.example.lx.wyredpacketandroid.utils.LogUtil;
import com.example.lx.wyredpacketandroid.utils.networkutil.RetrofitUtil;
import com.example.lx.wyredpacketandroid.utils.networkutil.UrlUtil;

import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class NewsModel implements NewsContract.Model {
    @Override
    public void gainNews(final NewsContract.Presenter presenter, Map<String, String> map) {

        RetrofitUtil.instance().post(UrlUtil.INFORM, map, NewsEntity.class, new Observer() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Object value) {

                NewsEntity entity = (NewsEntity) value;

                if (entity.getData() != null) {
                    presenter.sendNews(entity.getData());
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
    public void gainDetails(final NewsContract.Presenter presenter, Map<String, String> map) {

        RetrofitUtil.instance().post(UrlUtil.MESSAGEDETAIL, map, SLReplyEntity.class, new Observer() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Object value) {

                SLReplyEntity entity = (SLReplyEntity) value;

                if (entity != null) {
                    presenter.sendDetails(entity.getData());
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
