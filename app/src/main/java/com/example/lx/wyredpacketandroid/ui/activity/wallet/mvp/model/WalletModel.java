package com.example.lx.wyredpacketandroid.ui.activity.wallet.mvp.model;

import android.util.Log;

import com.example.lx.wyredpacketandroid.ui.activity.wallet.entity.PutForwardEntity;
import com.example.lx.wyredpacketandroid.ui.activity.wallet.entity.TiXianEntity;
import com.example.lx.wyredpacketandroid.ui.activity.wallet.entity.UserWalletEntity;
import com.example.lx.wyredpacketandroid.ui.activity.wallet.mvp.contract.WalletContract;
import com.example.lx.wyredpacketandroid.utils.LogUtil;
import com.example.lx.wyredpacketandroid.utils.UserInfoUtil;
import com.example.lx.wyredpacketandroid.utils.networkutil.RetrofitUtil;
import com.example.lx.wyredpacketandroid.utils.networkutil.UrlUtil;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class WalletModel implements WalletContract.Model {
    @Override
    public void gainUserWallet(final WalletContract.Presenter presenter) {

        HashMap<String, String> map = new HashMap<>();
        map.put("uid", UserInfoUtil.instance().getId()+"");

        RetrofitUtil.instance().postJson(UrlUtil.USERWALLET, map, UserWalletEntity.class, new Observer() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Object value) {

                UserWalletEntity entity = (UserWalletEntity) value;

                if (entity != null) {
                    presenter.sendUserWallet(entity.getData());
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
    public void gainPutForward(final WalletContract.PFPresenter presenter) {

        HashMap<String, String> map = new HashMap<>();
        map.put("uid", UserInfoUtil.instance().getId()+"");

        RetrofitUtil.instance().postJson(UrlUtil.TIXIANDATA, map, PutForwardEntity.class, new Observer() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Object value) {

                PutForwardEntity entity = (PutForwardEntity) value;
                if (entity != null) {
                    presenter.sendPutForward(entity.getData());
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
    public void gainTiXian(final WalletContract.PFPresenter presenter, Map<String, String> map) {

        RetrofitUtil.instance().postJson(UrlUtil.TIXIAN, map, TiXianEntity.class, new Observer() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Object value) {

                TiXianEntity entity = (TiXianEntity) value;
                LogUtil.e("成功"+entity.getErr_code());
                if (entity != null) {
                    if (entity.getErr_code().equals("200")) {
                        presenter.sendTiXian(entity.getData());
                    }
                }
            }

            @Override
            public void onError(Throwable e) {
                LogUtil.e("失败"+e.toString());
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
