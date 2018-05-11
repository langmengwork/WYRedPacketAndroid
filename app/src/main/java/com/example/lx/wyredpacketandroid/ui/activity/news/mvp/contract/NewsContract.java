package com.example.lx.wyredpacketandroid.ui.activity.news.mvp.contract;

import com.example.lx.wyredpacketandroid.base.BaseModel;
import com.example.lx.wyredpacketandroid.base.BasePresenter;
import com.example.lx.wyredpacketandroid.base.BaseView;
import com.example.lx.wyredpacketandroid.entity.OpenPackEntity;
import com.example.lx.wyredpacketandroid.ui.activity.news.entity.NewsEntity;
import com.example.lx.wyredpacketandroid.ui.activity.packdetails.entity.SLReplyEntity;

import java.util.List;
import java.util.Map;

public interface NewsContract {

    interface View extends BaseView {

        void showView(List<NewsEntity.DataBean> data);

//        void showDetails(SLReplyEntity.DataBean data);

    }


    interface Model extends BaseModel {

        void gainNews(Presenter presenter, Map<String, String> map);

        void gainDetails(Presenter presenter, Map<String, String> map);

    }

    abstract class Presenter extends BasePresenter<View, Model> {

        public abstract void obtainNews(Map<String, String> map);

        public abstract void sendNews(List<NewsEntity.DataBean> data);

        public abstract void obtainDetails(Map<String, String> map);

        public abstract void sendDetails(SLReplyEntity.DataBean data);

    }
}
