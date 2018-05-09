package com.example.lx.wyredpacketandroid.ui.activity.packdetails.mvp.presenter;

import com.example.lx.wyredpacketandroid.ui.activity.packdetails.entity.ReceiveDetailEntity;
import com.example.lx.wyredpacketandroid.ui.activity.packdetails.mvp.contract.DetailsContract;
import com.example.lx.wyredpacketandroid.ui.activity.packdetails.mvp.model.DetailsModel;

import java.util.Map;

public class ReceiveDetailPresenter extends DetailsContract.ReceivePresenter {


    public ReceiveDetailPresenter(DetailsContract.ReceiveView view) {

        mView = view;
        mModel = new DetailsModel();
    }

    @Override
    public void obtainReceiveDetail(Map<String, String> map) {

        mModel.gainReceiveDetail(this, map);
    }

    @Override
    public void sendReceiveDetail(ReceiveDetailEntity.DataBean data) {

        mView.shwoView(data);
    }
}
