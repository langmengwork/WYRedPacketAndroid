package com.example.lx.wyredpacketandroid.ui.activity.packdetails;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lx.wyredpacketandroid.R;
import com.example.lx.wyredpacketandroid.base.BaseActivity;
import com.example.lx.wyredpacketandroid.ui.activity.packdetails.adapter.ReceiveDetailAdapter;
import com.example.lx.wyredpacketandroid.ui.activity.packdetails.adapter.SLReplyAdapter;
import com.example.lx.wyredpacketandroid.ui.activity.packdetails.entity.ReceiveDetailEntity;
import com.example.lx.wyredpacketandroid.ui.activity.packdetails.mvp.contract.DetailsContract;
import com.example.lx.wyredpacketandroid.ui.activity.packdetails.mvp.presenter.ReceiveDetailPresenter;
import com.example.lx.wyredpacketandroid.utils.UserInfoUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ReceiveDetailsActivity extends BaseActivity implements View.OnClickListener, OnLoadMoreListener, OnRefreshListener ,DetailsContract.ReceiveView{

    private ImageView receive_detail_back;
    private TextView receive_detail_title;
    private TextView receive_detail_tv;
    private RecyclerView receive_detail_recycler;
    private SmartRefreshLayout receive_detail_refresh;
    private String pack_id;
    private int page = 0;
    private List<ReceiveDetailEntity.DataBean.UserListBean> userList = new ArrayList<>();
    private ReceiveDetailAdapter receiveDetailAdapter;
    private ReceiveDetailPresenter presenter;
    private boolean state = true;

    @Override
    protected void initData() {

        presenter = new ReceiveDetailPresenter(this);

        pack_id = getIntent().getStringExtra("pack_id");

    }

    @Override
    protected void initView() {

        receive_detail_back = (ImageView) findViewById(R.id.receive_detail_back);
        receive_detail_back.setOnClickListener(this);
        receive_detail_title = (TextView) findViewById(R.id.receive_detail_title);
        receive_detail_title.setOnClickListener(this);
        receive_detail_tv = (TextView) findViewById(R.id.receive_detail_tv);
        receive_detail_tv.setOnClickListener(this);
        receive_detail_recycler = (RecyclerView) findViewById(R.id.receive_detail_recycler);
        receive_detail_refresh = (SmartRefreshLayout) findViewById(R.id.receive_detail_refresh);
        receive_detail_refresh.setOnLoadMoreListener(this);
        receive_detail_refresh.setOnRefreshListener(this);
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_receive_details;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {

        page++;

        getData();
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {

        page++;
        userList.clear();
        receive_detail_refresh.setNoMoreData(false);
        getData();

    }

    private void getData() {

        HashMap<String, String> map = new HashMap<>();
        map.put("uid", UserInfoUtil.instance().getId()+"");
        map.put("pack_id", pack_id);
        map.put("page",page+"");

        presenter.obtainReceiveDetail(map);

    }

    public static void startReceiveDetailsActivity(Context context,String pack_id) {

        context.startActivity(new Intent(context, ReceiveDetailsActivity.class)
                .putExtra("pack_id", pack_id)
        );
    }

    @Override
    public void shwoView(ReceiveDetailEntity.DataBean data) {

        if (state) {
            state = false;

            //标题数量
            receive_detail_title.setText(data.getCurrentNum() + "人领取");

            //利率详情
            receive_detail_tv.setText("已领取" + data.getCurrentNum() + "/" + data.getTotalNum() + "," + "一共" + data.getTotalMoney() + "元(服务费)" + data.getService_rate());
        }

        receive_detail_refresh.finishRefresh();
        receive_detail_refresh.finishLoadMore();

        if (data.getUserList() == null || data.getUserList().size() <= 0) {

            receive_detail_refresh.finishLoadMoreWithNoMoreData();
        }

        userList.addAll(data.getUserList());

        if (receiveDetailAdapter == null) {
            initReply();
        } else {
            receiveDetailAdapter.notifyDataSetChanged();
        }
    }

    private void initReply() {

        receive_detail_recycler.setHasFixedSize(true);

        receive_detail_recycler.setLayoutManager(new LinearLayoutManager(this));

        //添加Android自带的分割线
        receive_detail_recycler.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        receiveDetailAdapter = new ReceiveDetailAdapter(this, userList);

        receive_detail_recycler.setAdapter(receiveDetailAdapter);

    }

    @Override
    public void onError(String error) {

    }
}
