package com.example.lx.wyredpacketandroid.ui.activity.wallet.mvp.presenter;

import com.example.lx.wyredpacketandroid.ui.activity.wallet.entity.PutForwardEntity;
import com.example.lx.wyredpacketandroid.ui.activity.wallet.entity.UserWalletEntity;
import com.example.lx.wyredpacketandroid.ui.activity.wallet.mvp.contract.WalletContract;
import com.example.lx.wyredpacketandroid.ui.activity.wallet.mvp.model.WalletModel;

public class WalletPresenter extends WalletContract.Presenter {

    public WalletPresenter(WalletContract.View view) {

        mView = view;
        mModel = new WalletModel();
    }

    @Override
    public void obtainUserWallet() {

        mModel.gainUserWallet(this);
    }

    @Override
    public void sendUserWallet(UserWalletEntity.DataBean data) {

        mView.showView(data);
    }
}
