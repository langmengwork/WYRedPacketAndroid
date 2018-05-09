package com.example.lx.wyredpacketandroid.ui.activity.packdetails.mvp.presenter;

import com.example.lx.wyredpacketandroid.ui.activity.packdetails.entity.SLReplyEntity;
import com.example.lx.wyredpacketandroid.ui.activity.packdetails.mvp.contract.DetailsContract;
import com.example.lx.wyredpacketandroid.ui.activity.packdetails.mvp.model.DetailsModel;

import java.util.Map;

public class ReplyDetailPresenter extends DetailsContract.ReplyPresenter {

    public ReplyDetailPresenter(DetailsContract.ReplyView view) {
        mView = view;
        mModel = new DetailsModel();
    }

    @Override
    public void obtainReplyDetail(Map<String, String> map) {

        mModel.gainReplyDetail(this,map);
    }

    @Override
    public void sendReplyDetail(SLReplyEntity.DataBean data) {

        mView.shwoView(data);
    }

    @Override
    public void obtainAddReplyDetail(Map<String, String> map) {

        mModel.gainAddReply(this,map);
    }

    @Override
    public void sendAddReplyDetail(String msg) {

        mView.showAddReplyDetail(msg);
    }
}
