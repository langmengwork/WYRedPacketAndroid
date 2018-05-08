package com.example.lx.wyredpacketandroid.mvp.presenter;

import com.example.lx.wyredpacketandroid.entity.OpenPackEntity;
import com.example.lx.wyredpacketandroid.mvp.contract.MainContract;
import com.example.lx.wyredpacketandroid.mvp.model.MainModel;

import java.util.Map;

public class OpenPresenter extends MainContract.OpenPresenter {

    public OpenPresenter(MainContract.openView view) {
        mView = view;
        mModel = new MainModel();
    }

    @Override
    public void obtainOpen(Map<String, String> map) {

        mModel.gainOpenPack(this, map);
    }

    @Override
    public void sendOpen(OpenPackEntity.DataBean data) {

        mView.showView(data);
    }

    @Override
    public void sendError(String error) {

        mView.onError(error);
    }
}
