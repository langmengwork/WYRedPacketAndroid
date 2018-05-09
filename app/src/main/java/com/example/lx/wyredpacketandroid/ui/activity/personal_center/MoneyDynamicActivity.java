package com.example.lx.wyredpacketandroid.ui.activity.personal_center;

import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.lx.wyredpacketandroid.R;
import com.example.lx.wyredpacketandroid.base.BaseActivity;
import com.example.lx.wyredpacketandroid.ui.activity.packdetails.adapter.ReceiveDetailAdapter;
import com.example.lx.wyredpacketandroid.ui.activity.personal_center.adapter.CollectMoneyAdapter;
import com.example.lx.wyredpacketandroid.ui.activity.personal_center.adapter.SendMoneyAdapter;
import com.example.lx.wyredpacketandroid.ui.activity.personal_center.entity.CollectMoneyEntity;
import com.example.lx.wyredpacketandroid.ui.activity.personal_center.entity.SendMoneyEntity;
import com.example.lx.wyredpacketandroid.ui.activity.personal_center.mvp.contract.PepContract;
import com.example.lx.wyredpacketandroid.ui.activity.personal_center.mvp.presenter.PepPresenter;
import com.example.lx.wyredpacketandroid.ui.activity.sendmoney.entity.TabEntity;
import com.example.lx.wyredpacketandroid.utils.UserInfoUtil;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MoneyDynamicActivity extends BaseActivity implements View.OnClickListener, OnLoadMoreListener, OnRefreshListener, OnTabSelectListener,PepContract.View {


    private CommonTabLayout dynamic_tab;
    private TextView card_left_num;
    private TextView card_left_tip;
    private TextView card_rghit_num;
    private TextView card_rghit_tip;
    private RecyclerView dynamic_recycler;
    private SmartRefreshLayout dynamic_refresh;
    private ArrayList<CustomTabEntity>  customTabEntities;
    private int page = 0;
    private PepPresenter presenter;
    private List<CollectMoneyEntity.DataBean.ListBean> collectList = new ArrayList<>();
    private List<SendMoneyEntity.DataBean.ListBean> sendList = new ArrayList<>();
    private CollectMoneyAdapter collectMoneyAdapter;
    private SendMoneyAdapter sendMoneyAdapter;
    private int TABTYPE = 0;
    private static final int COLLECT = 0;
    private static final int SEND = 1;
    private boolean state = true;


    @Override
    protected void initData() {

        presenter = new PepPresenter(this);

        addTab();

        initRecycler();

        refreshData();

    }

    private void initRecycler() {

        dynamic_recycler.setHasFixedSize(true);

        dynamic_recycler.setLayoutManager(new LinearLayoutManager(this));

        //添加Android自带的分割线
        dynamic_recycler.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

    }

    private void refreshData() {

        page++;
        HashMap<String, String> map = new HashMap<>();
        map.put("uid", UserInfoUtil.instance().getId()+"");
        map.put("page", page+"");

        if (TABTYPE == COLLECT) {
            presenter.obtainCollect(map);
        } else {
            presenter.obtainSend(map);
        }
    }

    private void addTab() {

        customTabEntities = new ArrayList<>();
        TabEntity tabLeft = new TabEntity(getResources().getString(R.string.dynamic_tab_collect), 0, 0);
        TabEntity tabRight = new TabEntity(getResources().getString(R.string.dynamic_tab_send), 0, 0);
        customTabEntities.add(tabLeft);
        customTabEntities.add(tabRight);

        dynamic_tab.setTabData(customTabEntities);

        dynamic_tab.setOnTabSelectListener(this);

    }

    @Override
    protected void initView() {

        dynamic_tab = (CommonTabLayout) findViewById(R.id.dynamic_tab);
        dynamic_tab.setOnClickListener(this);
        card_left_num = (TextView) findViewById(R.id.card_left_num);
        card_left_num.setOnClickListener(this);
        card_left_tip = (TextView) findViewById(R.id.card_left_tip);
        card_left_tip.setOnClickListener(this);
        card_rghit_num = (TextView) findViewById(R.id.card_rghit_num);
        card_rghit_num.setOnClickListener(this);
        card_rghit_tip = (TextView) findViewById(R.id.card_rghit_tip);
        card_rghit_tip.setOnClickListener(this);
        dynamic_recycler = (RecyclerView) findViewById(R.id.dynamic_recycler);
        dynamic_refresh = (SmartRefreshLayout) findViewById(R.id.dynamic_refresh);
        dynamic_refresh.setOnLoadMoreListener(this);
        dynamic_refresh.setOnRefreshListener(this);
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_money_dynamic;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {

        refreshData();
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {

        page = 0;
        refreshData();
    }

    @Override
    public void onTabSelect(int position) {

        page = 0;
        collectList.clear();
        sendList.clear();
        dynamic_refresh.setNoMoreData(false);
        state = true;

        if (customTabEntities.get(position).getTabTitle().equals(getResources().getString(R.string.dynamic_tab_collect))) {

            TABTYPE = COLLECT;
            refreshData();

        } else {

            TABTYPE = SEND;
            refreshData();

        }
    }

    @Override
    public void onTabReselect(int position) {


    }

    @Override
    public void shwoCollect(CollectMoneyEntity.DataBean data) {

        dynamic_refresh.finishLoadMore();
        dynamic_refresh.finishRefresh();

        if (state) {
            state = false;
            card_left_num.setText(data.getReceiveMoney() + "");
            card_rghit_num.setText(data.getTotalMoney() + "");
            card_left_tip.setText(getResources().getString(R.string.dynamic_card_one));
            card_rghit_tip.setText(getResources().getString(R.string.dynamic_card_two));
        }

        if (data.getList() == null || data.getList().size() <= 0) {

            dynamic_refresh.finishLoadMoreWithNoMoreData();
            return;
        }

        collectList.addAll(data.getList());

        if (collectMoneyAdapter == null) {
            collectMoneyAdapter = new CollectMoneyAdapter(this, collectList);
            dynamic_recycler.setAdapter(collectMoneyAdapter);
        } else {

            collectMoneyAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void shwoSend(SendMoneyEntity.DataBean data) {

        dynamic_refresh.finishLoadMore();
        dynamic_refresh.finishRefresh();

        if (state) {
            state = false;
            card_left_num.setText(data.getTotalMoney() + "");
            card_rghit_num.setText(data.getTotalPeople() + "");
            card_left_tip.setText(getResources().getString(R.string.dynamic_card_three));
            card_rghit_tip.setText(getResources().getString(R.string.dynamic_card_four));
        }

        if (data.getList() == null || data.getList().size() <= 0) {

            dynamic_refresh.finishLoadMoreWithNoMoreData();
            return;
        }

        sendList = data.getList();

        if (sendMoneyAdapter == null) {

            sendMoneyAdapter = new SendMoneyAdapter(this, sendList);
            dynamic_recycler.setAdapter(sendMoneyAdapter);

        } else {

            sendMoneyAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void onError(String error) {

    }
}
