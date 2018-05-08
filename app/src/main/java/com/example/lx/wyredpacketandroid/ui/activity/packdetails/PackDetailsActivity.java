package com.example.lx.wyredpacketandroid.ui.activity.packdetails;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.lx.wyredpacketandroid.R;
import com.example.lx.wyredpacketandroid.base.BaseActivity;
import com.example.lx.wyredpacketandroid.entity.OpenPackEntity;
import com.example.lx.wyredpacketandroid.ui.activity.packdetails.adapter.DetailsImgsAdapter;
import com.example.lx.wyredpacketandroid.ui.activity.packdetails.adapter.ReplyAdapter;
import com.example.lx.wyredpacketandroid.ui.activity.packdetails.entity.MessageListEntity;
import com.example.lx.wyredpacketandroid.ui.activity.packdetails.mvp.contract.DetailsContract;
import com.example.lx.wyredpacketandroid.ui.activity.packdetails.mvp.presenter.DetailsPresenter;
import com.example.lx.wyredpacketandroid.utils.CodeUtil;
import com.example.lx.wyredpacketandroid.utils.recycleviewutil.SpaceItemDecoration;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;

import java.util.HashMap;
import java.util.List;

public class PackDetailsActivity extends BaseActivity implements View.OnClickListener, OnLoadMoreListener,DetailsContract.View {


    private TextView details_title;
    private ImageView details_icon;
    private TextView details_name, details_shares, details_region;
    private TextView details_money;
    private TextView details_yuan;
    private TextView details_wallet;
    private RecyclerView details_imgs_recycle, reply_recycler;
    private TextView details_receive_num;
    private OpenPackEntity.DataBean detailsEntity;
    private EditText reply_editer;
    private CheckBox reply_like;
    private RelativeLayout reply_bottom_bar;
    private TextView reply_num;
    private RelativeLayout reply_bar;
    private SmartRefreshLayout reply_refresh;
    private DetailsPresenter presenter;
    private List<MessageListEntity.DataBean> replyList;
    private ReplyAdapter replyAdapter = null;

    @Override
    protected void initData() {

        presenter = new DetailsPresenter(this);

        //获取详情数据
        detailsEntity = new Gson().fromJson(getIntent().getStringExtra("data"),
                OpenPackEntity.DataBean.class);

        //设置红包icon
        Glide.with(this).load(detailsEntity.getPackImg()).into(details_icon);

        //设置标题
        details_name.setText(detailsEntity.getPackName());

        //设置股数
        details_shares.setText("+" + detailsEntity.getGushu() + "红包股");

        //设置范围
        details_region.setText(detailsEntity.getPackArea());

        //设置金额
        details_money.setText(detailsEntity.getPackMoney() + "");

        //设置领取人数
        details_receive_num.setText("…" + detailsEntity.getTotalUserNum() + "+人领取");

        initImags();

        reply_refresh.autoLoadMore();
    }

    private void initReply() {

        details_imgs_recycle.setHasFixedSize(true);

        details_imgs_recycle.setLayoutManager(new LinearLayoutManager(this));

        replyAdapter = new ReplyAdapter(this, replyList);

        details_imgs_recycle.setAdapter(replyAdapter);

        //留言总数
        reply_num.setText(detailsEntity.getMessageCount() + "");

    }

    private void initImags() {

        details_imgs_recycle.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        details_imgs_recycle.setLayoutManager(linearLayoutManager);
        details_imgs_recycle.addItemDecoration(new SpaceItemDecoration(-20, CodeUtil.SPAC_TWO));

        DetailsImgsAdapter detailsImgsAdapter = new DetailsImgsAdapter(this, detailsEntity.getImage());

        details_imgs_recycle.setAdapter(detailsImgsAdapter);

    }

    @Override
    protected void initView() {

        details_title = (TextView) findViewById(R.id.details_title);
        details_title.setOnClickListener(this);
        details_icon = (ImageView) findViewById(R.id.details_icon);
        details_icon.setOnClickListener(this);
        details_name = (TextView) findViewById(R.id.details_name);
        details_name.setOnClickListener(this);
        details_money = (TextView) findViewById(R.id.details_money);
        details_money.setOnClickListener(this);
        details_yuan = (TextView) findViewById(R.id.details_yuan);
        details_yuan.setOnClickListener(this);
        details_wallet = (TextView) findViewById(R.id.details_wallet);
        details_wallet.setOnClickListener(this);
        details_imgs_recycle = (RecyclerView) findViewById(R.id.details_imgs_recycle);
        reply_recycler = (RecyclerView) findViewById(R.id.reply_recycler);
        details_receive_num = (TextView) findViewById(R.id.details_receive_num);
        details_receive_num.setOnClickListener(this);
        details_shares = findViewById(R.id.details_shares);
        details_region = findViewById(R.id.details_region);
        reply_editer = (EditText) findViewById(R.id.reply_editer);
        reply_editer.setOnClickListener(this);
        reply_like = (CheckBox) findViewById(R.id.reply_like);
        reply_like.setOnClickListener(this);
        reply_bottom_bar = (RelativeLayout) findViewById(R.id.reply_bottom_bar);
        reply_bottom_bar.setOnClickListener(this);
        reply_num = (TextView) findViewById(R.id.reply_num);
        reply_num.setOnClickListener(this);
        reply_bar = (RelativeLayout) findViewById(R.id.reply_bar);
        reply_bar.setOnClickListener(this);
        reply_refresh = findViewById(R.id.reply_refresh);
        reply_refresh.setOnLoadMoreListener(this);
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_pack_details;
    }

    @Override
    public void onClick(View v) {

    }

    private void submit() {
        // validate
        String editer = reply_editer.getText().toString().trim();
        if (TextUtils.isEmpty(editer)) {
            Toast.makeText(this, "editer不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something


    }


    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {

        HashMap<String, String> map = new HashMap<>();
        map.put("pack_id", detailsEntity.getPack_id() + "");
        presenter.obtainLoadMore(map);

    }

    @Override
    public void showLoadMore(List<MessageListEntity.DataBean> data) {

        if (data.size() <= 0) {

        }
        replyList.addAll(data);

        if (replyAdapter == null) {

            initReply();
        } else {

            replyAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void onError(String error) {

    }
}
