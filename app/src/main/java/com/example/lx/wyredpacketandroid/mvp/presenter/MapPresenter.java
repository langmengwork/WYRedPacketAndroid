package com.example.lx.wyredpacketandroid.mvp.presenter;

import com.example.lx.wyredpacketandroid.entity.GetPackEntity;
import com.example.lx.wyredpacketandroid.entity.GetpageEntity;
import com.example.lx.wyredpacketandroid.mvp.contract.MainContract;
import com.example.lx.wyredpacketandroid.mvp.model.MainModel;

import java.util.List;
import java.util.Map;

public class MapPresenter extends MainContract.MapPresenter {

    public MapPresenter(MainContract.mapView view) {
        mModel = new MainModel();
        mView = view;
    }

    @Override
    public void obtainUserInfo() {

        mModel.gainUserInfo(this);
    }

    @Override
    public void obtainGetPack(Map<String, String> map) {

        mModel.gainGetPack(this,map);
    }

    @Override
    public void sendGetPack(GetPackEntity.DataBean data) {
        mView.showView(data);
    }

}