package com.example.lx.wyredpacketandroid.ui.activity.personal_center.mvp.contract;

import com.example.lx.wyredpacketandroid.base.BaseModel;
import com.example.lx.wyredpacketandroid.base.BasePresenter;
import com.example.lx.wyredpacketandroid.base.BaseView;
import com.example.lx.wyredpacketandroid.ui.activity.personal_center.entity.CollectMoneyEntity;
import com.example.lx.wyredpacketandroid.ui.activity.personal_center.entity.SendMoneyEntity;

import java.util.Map;

public interface PepContract {

    interface View extends BaseView {

        void shwoCollect(CollectMoneyEntity.DataBean data);

        void shwoSend(SendMoneyEntity.DataBean data);

    }

    interface Model extends BaseModel {

        void gainCollect(Presenter presenter, Map<String, String> map);

        void gainSend(Presenter presenter, Map<String, String> map);

    }

    abstract class Presenter extends BasePresenter<View, Model> {

        public abstract void obtainCollect(Map<String, String> map);

        public abstract void sendCollect(CollectMoneyEntity.DataBean data);

        public abstract void obtainSend(Map<String, String> map);

        public abstract void sendSend(SendMoneyEntity.DataBean data);

    }

}
