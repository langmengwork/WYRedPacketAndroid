package com.example.lx.wyredpacketandroid.ui.activity.sendmoney.mvp.presenter;

import com.example.lx.wyredpacketandroid.entity.CreateRedpackEntity;
import com.example.lx.wyredpacketandroid.ui.activity.sendmoney.mvp.contract.SendContract;
import com.example.lx.wyredpacketandroid.ui.activity.sendmoney.mvp.model.SendModel;

import java.util.Map;

public class SendPresenter extends SendContract.Presenter {

    public SendPresenter(SendContract.View view) {
        mView = view;
        mModel = new SendModel();
    }

    @Override
    public void obtainSendPack(Map<String, String> map) {

        mModel.gainSendPack(this,map);
    }

    @Override
    public void obtainQiniuToken() {

        mModel.gainQiniuToken(this);
    }

    @Override
    public void sendSendPack(CreateRedpackEntity.DataBean data) {

        mView.Success(data);
    }

    @Override
    public void sendQiniuToken(String token) {

        mView.sendPack(token);
    }
}
