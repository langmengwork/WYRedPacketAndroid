package com.example.lx.wyredpacketandroid.ui.activity.sendmoney.mvp.contract;

import com.example.lx.wyredpacketandroid.base.BaseModel;
import com.example.lx.wyredpacketandroid.base.BasePresenter;
import com.example.lx.wyredpacketandroid.base.BaseView;
import com.example.lx.wyredpacketandroid.entity.CreateRedpackEntity;

import java.util.Map;

public interface SendContract {

    interface View extends BaseView {

        void sendPack(String token);

        void Success(CreateRedpackEntity.DataBean data);

    }

    interface Model extends BaseModel {

        void gainSendPack(Presenter presenter,Map<String,String> map);

        void gainQiniuToken(Presenter presenter);

    }

    abstract class Presenter extends BasePresenter<View, Model> {

        public abstract void obtainSendPack(Map<String,String> map);

        public abstract void obtainQiniuToken();

        public abstract void sendSendPack(CreateRedpackEntity.DataBean data);

        public abstract void sendQiniuToken(String token);
    }
}
