package com.example.lx.wyredpacketandroid.ui.activity.wallet.mvp.contract;

import com.example.lx.wyredpacketandroid.base.BaseModel;
import com.example.lx.wyredpacketandroid.base.BasePresenter;
import com.example.lx.wyredpacketandroid.base.BaseView;
import com.example.lx.wyredpacketandroid.ui.activity.wallet.entity.PutForwardEntity;
import com.example.lx.wyredpacketandroid.ui.activity.wallet.entity.UserWalletEntity;

import java.util.Map;


public interface WalletContract {

    interface View extends BaseView {

        void showView(UserWalletEntity.DataBean data);

    }

    interface PFView extends BaseView {

        void showView(PutForwardEntity.DataBean data);

        void PfState(String state);

    }

    interface Model extends BaseModel {

        void gainUserWallet(Presenter presenter);

        void gainPutForward(PFPresenter presenter);

        void gainTiXian(PFPresenter presenter, Map<String, String> map);

    }


    abstract class Presenter extends BasePresenter<View, Model> {

        public abstract void obtainUserWallet();

        public abstract void sendUserWallet(UserWalletEntity.DataBean data);

    }

    abstract class PFPresenter extends BasePresenter<PFView, Model> {

        public abstract void obtainPutForward();

        public abstract void sendPutForward(PutForwardEntity.DataBean data);

        public abstract void obtainTiXian(Map<String, String> map);

        public abstract void sendTiXian(String state);

    }
}
