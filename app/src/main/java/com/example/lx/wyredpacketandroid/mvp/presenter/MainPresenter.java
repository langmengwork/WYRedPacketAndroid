package com.example.lx.wyredpacketandroid.mvp.presenter;

import com.example.lx.wyredpacketandroid.mvp.contract.MainContract;
import com.example.lx.wyredpacketandroid.mvp.model.MainModel;

public class MainPresenter extends MainContract.Presenter {

    public MainPresenter(MainContract.View view) {
        mView = view;
        mModel = new MainModel();
    }

    @Override
    public void obtainLogin(String code) {

        mModel.gainLogin(this,code);
    }

    @Override
    public void sendLogin() {

        mView.showView();
    }

    @Override
    public void obtainToken() {

        mModel.gainToken(this);
    }

    @Override
    public void sendTokenSuccess() {

        mView.showView();
    }

    @Override
    public void sendTokenError() {

        mView.onError("");
    }

    @Override
    public void sendError(String error) {

        mView.onError(error);
    }

    @Override
    public void obtainOrder() {

        mModel.gainOrder(this);
    }

    @Override
    public void sendOrder(int status) {

    }

}
