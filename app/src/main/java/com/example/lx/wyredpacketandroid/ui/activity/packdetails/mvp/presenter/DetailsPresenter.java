package com.example.lx.wyredpacketandroid.ui.activity.packdetails.mvp.presenter;

import com.example.lx.wyredpacketandroid.ui.activity.packdetails.entity.MessageListEntity;
import com.example.lx.wyredpacketandroid.ui.activity.packdetails.mvp.contract.DetailsContract;
import com.example.lx.wyredpacketandroid.ui.activity.packdetails.mvp.model.DetailsModel;

import java.util.List;
import java.util.Map;

public class DetailsPresenter extends DetailsContract.Presenter {

    public DetailsPresenter(DetailsContract.View view) {

        mView = view;
        mModel = new DetailsModel();
    }

    @Override
    public void obtainLoadMore(Map<String, String> map) {

        mModel.gainLoadMore(this, map);
    }

    @Override
    public void sendLoadMore(List<MessageListEntity.DataBean> data) {

        mView.showLoadMore(data);
    }

    @Override
    public void obtainAddReply(Map<String, String> map) {

        mModel.gainAddReply(this, map);
    }

    @Override
    public void sendAddReply(String msg) {

        mView.showAddReply(msg);
    }

    @Override
    public void obtainPraise(Map<String, String> map) {

        mModel.gainPraise(this, map);
    }

    @Override
    public void sendPraise(int praiseNum) {

        mView.showPraise(praiseNum);
    }
}
