package com.example.lx.wyredpacketandroid.ui.activity.packdetails;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.lx.wyredpacketandroid.R;
import com.example.lx.wyredpacketandroid.base.BaseActivity;
import com.example.lx.wyredpacketandroid.ui.activity.StartActivity;
import com.example.lx.wyredpacketandroid.ui.activity.packdetails.adapter.SLReplyAdapter;
import com.example.lx.wyredpacketandroid.ui.activity.packdetails.entity.SLReplyEntity;
import com.example.lx.wyredpacketandroid.ui.activity.packdetails.mvp.contract.DetailsContract;
import com.example.lx.wyredpacketandroid.ui.activity.packdetails.mvp.presenter.ReplyDetailPresenter;
import com.example.lx.wyredpacketandroid.utils.ToastUtil;
import com.example.lx.wyredpacketandroid.utils.UserInfoUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ReplyDetailActivity extends BaseActivity implements View.OnClickListener, DetailsContract.ReplyView, OnLoadMoreListener {

    private ImageView reply_detail_back;
    private TextView reply_detail_title;
    private ImageView reply_icon_item;
    private TextView reply_name_item;
    private TextView reply_content_item;
    private TextView reply_time_item;
    private RecyclerView reply_detail_recycler;
    private ReplyDetailPresenter presenter;
    private SmartRefreshLayout reply_detail_refresh;
    private String pid;
    private int page = 0;
    private SLReplyAdapter slReplyAdapter;
    private List<SLReplyEntity.DataBean.ListBean> list = new ArrayList<>();
    private boolean state = true;
    private EditText reply_detail_editer;
    private TextView reply_detail_send;
    private String pack_id;

    @Override
    protected void initData() {

        //提取数据
        presenter = new ReplyDetailPresenter(this);

        pid = getIntent().getStringExtra("pid");

        pack_id = getIntent().getStringExtra("pack_id");

        reply_detail_refresh.autoLoadMore();

    }

    @Override
    protected void initView() {

        reply_detail_back = (ImageView) findViewById(R.id.reply_detail_back);
        reply_detail_back.setOnClickListener(this);
        reply_detail_title = (TextView) findViewById(R.id.reply_detail_title);
        reply_detail_title.setOnClickListener(this);
        reply_icon_item = (ImageView) findViewById(R.id.reply_icon_item);
        reply_icon_item.setOnClickListener(this);
        reply_name_item = (TextView) findViewById(R.id.reply_name_item);
        reply_name_item.setOnClickListener(this);
        reply_content_item = (TextView) findViewById(R.id.reply_content_item);
        reply_content_item.setOnClickListener(this);
        reply_time_item = (TextView) findViewById(R.id.reply_time_item);
        reply_time_item.setOnClickListener(this);
        reply_detail_recycler = (RecyclerView) findViewById(R.id.reply_detail_recycler);
        reply_detail_recycler.setOnClickListener(this);
        reply_detail_refresh = findViewById(R.id.reply_detail_refresh);
        reply_detail_refresh.setOnLoadMoreListener(this);
        reply_detail_editer = (EditText) findViewById(R.id.reply_detail_editer);
        reply_detail_send = (TextView) findViewById(R.id.reply_detail_send);
        reply_detail_send.setOnClickListener(this);
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_reply_detail;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.reply_detail_send:

                submit();

                HashMap<String, String> map = new HashMap<>();
                map.put("uid", UserInfoUtil.instance().getId()+"");
                map.put("pack_id", pack_id);
                map.put("pid", pid);
                map.put("content", reply_detail_editer.getText().toString());
                presenter.obtainAddReplyDetail(map);

                reply_detail_editer.setText("");

                View view = getWindow().peekDecorView();
                if (view != null) {
                    InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }

                break;
            case R.id.reply_detail_back:

                finish();

                break;
        }
    }

    @Override
    public void shwoView(SLReplyEntity.DataBean data) {

        reply_detail_refresh.finishLoadMore();

        if (state) {
            state = false;
            //设置标题
            reply_detail_title.setText(data.getReturnCount() + "条回复");
            initInfo(data.getInfo());
        }

        if (data.getList().size() <= 0 || data == null) {

            reply_detail_refresh.setNoMoreData(true);

            return;
        }

        list.addAll(data.getList());

        if (slReplyAdapter == null) {

            initReply();
        } else {

            slReplyAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void showAddReplyDetail(String msg) {

        if (msg.equals("成功")) {
            page = 0;
            list.clear();
            state = true;
            reply_detail_refresh.setNoMoreData(false);
            reply_detail_refresh.autoLoadMore();
        } else {
            ToastUtil.showShortToast(msg);
        }
    }

    private void initInfo(SLReplyEntity.DataBean.InfoBean info) {

        Glide.with(this).load(info.getHeadimgurl()).into(reply_icon_item);
        reply_name_item.setText(info.getName());
        reply_content_item.setText(info.getContent());
        reply_time_item.setText(info.getCreated_at());

    }

    private void initReply() {

        reply_detail_recycler.setHasFixedSize(true);

        reply_detail_recycler.setLayoutManager(new LinearLayoutManager(this));

        //添加Android自带的分割线
        reply_detail_recycler.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        slReplyAdapter = new SLReplyAdapter(this, list);

        reply_detail_recycler.setAdapter(slReplyAdapter);

    }

    @Override
    public void onError(String error) {

    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {

        page++;
        HashMap<String, String> map = new HashMap<>();
        map.put("pid", pid);
        map.put("page", page + "");
        presenter.obtainReplyDetail(map);
    }

    private void submit() {
        // validate
        String editer = reply_detail_editer.getText().toString().trim();
        if (TextUtils.isEmpty(editer)) {
            Toast.makeText(this, "editer不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something


    }

    public static void StartReplyDetailActivity(Context context,String pid,String pack_id) {

        context.startActivity(new Intent(context, ReplyDetailActivity.class)
                .putExtra("pid", pid)
                .putExtra("pack_id", pack_id));
    }
}
