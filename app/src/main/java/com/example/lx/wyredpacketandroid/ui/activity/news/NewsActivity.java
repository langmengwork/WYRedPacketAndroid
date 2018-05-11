package com.example.lx.wyredpacketandroid.ui.activity.news;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.lx.wyredpacketandroid.R;
import com.example.lx.wyredpacketandroid.base.BaseActivity;
import com.example.lx.wyredpacketandroid.entity.OpenPackEntity;
import com.example.lx.wyredpacketandroid.ui.activity.news.adapter.NewsAdapter;
import com.example.lx.wyredpacketandroid.ui.activity.news.entity.NewsEntity;
import com.example.lx.wyredpacketandroid.ui.activity.news.mvp.contract.NewsContract;
import com.example.lx.wyredpacketandroid.ui.activity.news.mvp.presenter.NewsPresenter;
import com.example.lx.wyredpacketandroid.ui.activity.packdetails.PackDetailsActivity;
import com.example.lx.wyredpacketandroid.ui.activity.packdetails.ReplyDetailActivity;
import com.example.lx.wyredpacketandroid.ui.activity.packdetails.adapter.ReplyAdapter;
import com.example.lx.wyredpacketandroid.ui.activity.packdetails.entity.SLReplyEntity;
import com.example.lx.wyredpacketandroid.utils.UserInfoUtil;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NewsActivity extends BaseActivity implements View.OnClickListener ,NewsContract.View, OnLoadMoreListener, NewsAdapter.onListner {


    private ImageView news_back;
    private RecyclerView news_recycler;
    private int page = 0;
    private SmartRefreshLayout news_refresh;
    private NewsPresenter presenter;
    private List<NewsEntity.DataBean> newsList = new ArrayList<>();
    private NewsAdapter newsAdapter;

    @Override
    protected void initData() {

        presenter = new NewsPresenter(this);

        news_recycler.setHasFixedSize(true);

        news_recycler.setLayoutManager(new LinearLayoutManager(this));

        //添加Android自带的分割线
        news_recycler.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        newsAdapter = new NewsAdapter(this,newsList);

        newsAdapter.setOnListner(this);

        news_recycler.setAdapter(newsAdapter);

        news_refresh.autoLoadMore();
    }

    @Override
    protected void initView() {

        news_back = (ImageView) findViewById(R.id.news_back);
        news_back.setOnClickListener(this);
        news_recycler = (RecyclerView) findViewById(R.id.news_recycler);
        news_refresh = findViewById(R.id.news_refresh);
        news_refresh.setEnableRefresh(false);
        news_refresh.setOnLoadMoreListener(this);
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_news;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.news_back:

                finish();

                break;
        }
    }

    @Override
    public void showView(List<NewsEntity.DataBean> data) {

        news_refresh.finishLoadMore();

        if (data == null || data.size() <= 0) {
            news_refresh.setNoMoreData(true);
            return;
        }

        newsList.addAll(data);

        newsAdapter.notifyDataSetChanged();

    }

    @Override
    public void onError(String error) {

    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {

        page++;
        HashMap<String, String> map = new HashMap<>();
        map.put("uid", UserInfoUtil.instance().getId()+"");
        map.put("page", page+"");

        presenter.obtainNews(map);

    }

    @Override
    public void adapterClick(int position) {

        ReplyDetailActivity.StartReplyDetailActivity(this,newsList.get(position).getParam_id()+"",
                newsList.get(position).getPack_id()+"");

    }
}
