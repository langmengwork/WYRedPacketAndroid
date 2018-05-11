package com.example.lx.wyredpacketandroid.ui.activity.news.mvp.presenter;

import com.example.lx.wyredpacketandroid.entity.OpenPackEntity;
import com.example.lx.wyredpacketandroid.ui.activity.news.entity.NewsEntity;
import com.example.lx.wyredpacketandroid.ui.activity.news.mvp.contract.NewsContract;
import com.example.lx.wyredpacketandroid.ui.activity.news.mvp.model.NewsModel;
import com.example.lx.wyredpacketandroid.ui.activity.packdetails.entity.SLReplyEntity;

import java.util.List;
import java.util.Map;

public class NewsPresenter extends NewsContract.Presenter {

    public NewsPresenter(NewsContract.View view) {

        mView = view;
        mModel = new NewsModel();
    }

    @Override
    public void obtainNews(Map<String, String> map) {

        mModel.gainNews(this, map);
    }

    @Override
    public void sendNews(List<NewsEntity.DataBean> data) {

        mView.showView(data);
    }

    @Override
    public void obtainDetails(Map<String, String> map) {

        mModel.gainDetails(this, map);
    }

    @Override
    public void sendDetails(SLReplyEntity.DataBean data) {

//        mView.showDetails(data);
    }
}
