package com.example.lx.wyredpacketandroid.ui.activity.packdetails.mvp.model;

import com.example.lx.wyredpacketandroid.ui.activity.packdetails.entity.AddReplyEntity;
import com.example.lx.wyredpacketandroid.ui.activity.packdetails.entity.MessageListEntity;
import com.example.lx.wyredpacketandroid.ui.activity.packdetails.entity.PraiseEntity;
import com.example.lx.wyredpacketandroid.ui.activity.packdetails.entity.SLReplyEntity;
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

    @Override
    public void gainAddReply(final DetailsContract.Presenter presenter, Map<String, String> map) {

        RetrofitUtil.instance().post(UrlUtil.MESSAGE, map, AddReplyEntity.class, new Observer() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Object value) {

                AddReplyEntity entity = (AddReplyEntity) value;

                if (entity != null) {
                   presenter.sendAddReply(entity.getData().getMsg());
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    } @Override

    public void gainAddReply(final DetailsContract.ReplyPresenter presenter, Map<String, String> map) {

        RetrofitUtil.instance().post(UrlUtil.MESSAGE, map, AddReplyEntity.class, new Observer() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Object value) {

                AddReplyEntity entity = (AddReplyEntity) value;

                if (entity != null) {
                   presenter.sendAddReplyDetail(entity.getData().getMsg());
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
    public void gainPraise(final DetailsContract.Presenter presenter, Map<String, String> map) {

        RetrofitUtil.instance().post(UrlUtil.PRAISE, map, PraiseEntity.class, new Observer() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Object value) {

                PraiseEntity entity = (PraiseEntity) value;

                if (entity != null) {
                    presenter.sendPraise(entity.getData().getPraiseNum());
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
    public void gainReplyDetail(final DetailsContract.ReplyPresenter presenter, Map<String, String> map) {

        RetrofitUtil.instance().post(UrlUtil.WITHDRAWLOG, map, SLReplyEntity.class, new Observer() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Object value) {

                SLReplyEntity entity = (SLReplyEntity) value;

                if (entity != null) {
                    presenter.sendReplyDetail(entity.getData());
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
