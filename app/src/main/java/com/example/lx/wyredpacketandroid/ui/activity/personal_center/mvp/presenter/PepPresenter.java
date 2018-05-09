package com.example.lx.wyredpacketandroid.ui.activity.personal_center.mvp.presenter;

import com.example.lx.wyredpacketandroid.ui.activity.personal_center.entity.CollectMoneyEntity;
import com.example.lx.wyredpacketandroid.ui.activity.personal_center.entity.SendMoneyEntity;
import com.example.lx.wyredpacketandroid.ui.activity.personal_center.mvp.contract.PepContract;
import com.example.lx.wyredpacketandroid.ui.activity.personal_center.mvp.model.PepModel;

import java.util.Map;

public class PepPresenter extends PepContract.Presenter {

    public PepPresenter(PepContract.View view) {

        mView = view;
        mModel = new PepModel();

    }

    @Override
    public void obtainCollect(Map<String, String> map) {

        mModel.gainCollect(this, map);
    }

    @Override
    public void sendCollect(CollectMoneyEntity.DataBean data) {

        mView.shwoCollect(data);
    }

    @Override
    public void obtainSend(Map<String, String> map) {

        mModel.gainSend(this, map);
    }

    @Override
    public void sendSend(SendMoneyEntity.DataBean data) {

        mView.shwoSend(data);
    }

}
