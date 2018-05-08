package com.example.lx.wyredpacketandroid.ui.activity.packdetails.mvp.contract;

import com.example.lx.wyredpacketandroid.base.BaseModel;
import com.example.lx.wyredpacketandroid.base.BasePresenter;
import com.example.lx.wyredpacketandroid.base.BaseView;
import com.example.lx.wyredpacketandroid.ui.activity.packdetails.entity.MessageListEntity;

import java.util.List;
import java.util.Map;

public interface DetailsContract {

    interface View extends BaseView {

        void showLoadMore(List<MessageListEntity.DataBean> data);

    }

    interface Model extends BaseModel {

        void gainLoadMore(Presenter presenter, Map<String, String> map);
    }

    abstract class Presenter extends BasePresenter<View, Model> {

        public abstract void obtainLoadMore(Map<String, String> map);

        public abstract void sendLoadMore(List<MessageListEntity.DataBean> data);

    }
}
