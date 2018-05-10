package com.example.lx.wyredpacketandroid.ui.activity.record.mvp.model;

import com.example.lx.wyredpacketandroid.ui.activity.record.entity.RecordFiveEntity;
import com.example.lx.wyredpacketandroid.ui.activity.record.entity.RecordFourEntity;
import com.example.lx.wyredpacketandroid.ui.activity.record.entity.RecordOneEntity;
import com.example.lx.wyredpacketandroid.ui.activity.record.entity.RecordThreeEntity;
import com.example.lx.wyredpacketandroid.ui.activity.record.entity.RecordTwoEntity;
import com.example.lx.wyredpacketandroid.ui.activity.record.mvp.contract.RecordContract;
import com.example.lx.wyredpacketandroid.utils.LogUtil;
import com.example.lx.wyredpacketandroid.utils.networkutil.RetrofitUtil;
import com.example.lx.wyredpacketandroid.utils.networkutil.UrlUtil;

import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class RecordModel implements RecordContract.Model{

    @Override
    public void gainRecordOne(final RecordContract.Presenter presenter, Map<String, String> map) {

        RetrofitUtil.instance().post(UrlUtil.LOG, map, RecordOneEntity.class, new Observer() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Object value) {
                LogUtil.e("成功");
                RecordOneEntity entity = (RecordOneEntity) value;
                if (entity != null) {

                    presenter.sendLogin(entity.getData(),null,null,null,null);
                }
            }

            @Override
            public void onError(Throwable e) {
                LogUtil.e("失败1"+e.toString());
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void gainRecordTwo(final RecordContract.Presenter presenter, Map<String, String> map) {

        RetrofitUtil.instance().post(UrlUtil.LOG, map, RecordTwoEntity.class, new Observer() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Object value) {

                RecordTwoEntity entity = (RecordTwoEntity) value;
                if (entity != null) {

                    presenter.sendLogin(null,entity.getData(),null,null,null);
                }
            }

            @Override
            public void onError(Throwable e) {
                LogUtil.e("失败2" + e.toString());
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void gainRecordThree(final RecordContract.Presenter presenter, Map<String, String> map) {

        RetrofitUtil.instance().post(UrlUtil.LOG, map, RecordThreeEntity.class, new Observer() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Object value) {

                RecordThreeEntity entity = (RecordThreeEntity) value;
                if (entity != null) {

                    presenter.sendLogin(null,null,entity.getData(),null,null);
                }
            }

            @Override
            public void onError(Throwable e) {
                LogUtil.e("失败3" + e.toString());
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void gainRecordFour(final RecordContract.Presenter presenter, Map<String, String> map) {

        RetrofitUtil.instance().post(UrlUtil.LOG, map, RecordFourEntity.class, new Observer() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Object value) {

                RecordFourEntity entity = (RecordFourEntity) value;
                if (entity != null) {

                    presenter.sendLogin(null,null,null,entity.getData(),null);
                }
            }

            @Override
            public void onError(Throwable e) {
                LogUtil.e("失败4" + e.toString());
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void gainRecordFive(final RecordContract.Presenter presenter, Map<String, String> map) {

        RetrofitUtil.instance().post(UrlUtil.LOG, map, RecordFiveEntity.class, new Observer() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Object value) {

                RecordFiveEntity entity = (RecordFiveEntity) value;
                if (entity != null) {

                    presenter.sendLogin(null,null,null,null,entity.getData());
                }
            }

            @Override
            public void onError(Throwable e) {
                LogUtil.e("失败5" + e.toString());
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
