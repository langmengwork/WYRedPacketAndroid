package com.example.lx.wyredpacketandroid.ui.activity.wallet.mvp.presenter;

import com.example.lx.wyredpacketandroid.ui.activity.wallet.entity.PutForwardEntity;
import com.example.lx.wyredpacketandroid.ui.activity.wallet.mvp.contract.WalletContract;
import com.example.lx.wyredpacketandroid.ui.activity.wallet.mvp.model.WalletModel;

import java.util.Map;

public class PutForwardPresenter extends WalletContract.PFPresenter {

    public PutForwardPresenter(WalletContract.PFView view) {

        mView = view;
        mModel = new WalletModel();
    }

    @Override
    public void obtainPutForward() {

        mModel.gainPutForward(this);

    }

    @Override
    public void sendPutForward(PutForwardEntity.DataBean data) {

        mView.showView(data);
    }

    @Override
    public void obtainTiXian(Map<String, String> map) {

        mModel.gainTiXian(this, map);
    }

    @Override
    public void sendTiXian(String state) {

        mView.PfState(state);
    }
}
