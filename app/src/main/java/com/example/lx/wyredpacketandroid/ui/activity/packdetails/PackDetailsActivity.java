package com.example.lx.wyredpacketandroid.ui.activity.packdetails;

import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewStub;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.lx.wyredpacketandroid.R;
import com.example.lx.wyredpacketandroid.base.BaseActivity;
import com.example.lx.wyredpacketandroid.entity.OpenPackEntity;
import com.example.lx.wyredpacketandroid.ui.activity.packdetails.adapter.DetailsImgsAdapter;
import com.example.lx.wyredpacketandroid.ui.activity.packdetails.adapter.DetailsRbShowAdapter;
import com.example.lx.wyredpacketandroid.ui.activity.packdetails.adapter.ReplyAdapter;
import com.example.lx.wyredpacketandroid.ui.activity.packdetails.entity.MessageListEntity;
import com.example.lx.wyredpacketandroid.ui.activity.packdetails.mvp.contract.DetailsContract;
import com.example.lx.wyredpacketandroid.ui.activity.packdetails.mvp.presenter.DetailsPresenter;
import com.example.lx.wyredpacketandroid.ui.activity.sendmoney.adapter.AddImgAdapter;
import com.example.lx.wyredpacketandroid.utils.CodeUtil;
import com.example.lx.wyredpacketandroid.utils.LogUtil;
import com.example.lx.wyredpacketandroid.utils.ToastUtil;
import com.example.lx.wyredpacketandroid.utils.UserInfoUtil;
import com.example.lx.wyredpacketandroid.utils.recycleviewutil.SpaceItemDecoration;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PackDetailsActivity extends BaseActivity implements View.OnClickListener, OnLoadMoreListener, DetailsContract.View, TextView.OnEditorActionListener, OnRefreshListener {


    private TextView details_title;
    private ImageView details_icon;
    private TextView details_name, details_shares, details_region;
    private TextView details_money;
    private TextView details_wallet;
    private RecyclerView details_imgs_recycle, reply_recycler;
    private TextView details_receive_num;
    private OpenPackEntity.DataBean detailsEntity;
    private EditText reply_editer;
    private ImageView reply_like;
    private RelativeLayout reply_bottom_bar;
    private TextView reply_num;
    private RelativeLayout reply_bar;
    private SmartRefreshLayout reply_refresh;
    private DetailsPresenter presenter;
    private ArrayList<MessageListEntity.DataBean> replyList = new ArrayList<>();
    private ReplyAdapter replyAdapter = null;
    private int page = 0;
    private String type = "2";
    private boolean moneystate = true;
    private TextView bl_show_content;
    private ImageView bl_show_img;
    private TextView rb_show_content;
    private RecyclerView rb_show_imags;
    private TextView rb_show_like_num;
    private LinearLayout rb_show_layout;
    private RelativeLayout bl_show_layout;

    @Override
    protected void initData() {

        presenter = new DetailsPresenter(this);

        //获取详情数据
        detailsEntity = new Gson().fromJson(getIntent().getStringExtra("data"),
                OpenPackEntity.DataBean.class);

        if (getIntent().getStringExtra("state") != null) {
            moneystate = false;
        }

        //设置红包icon
        Glide.with(this).load(detailsEntity.getPackImg()).into(details_icon);

        //设置标题
        details_name.setText(detailsEntity.getPackName());

        //设置范围
        details_region.setText(detailsEntity.getPackArea());

        //设置金额

        if (moneystate) {

            details_money.setVisibility(View.VISIBLE);
            details_shares.setVisibility(View.VISIBLE);
            details_wallet.setVisibility(View.VISIBLE);

            SpannableString spanString = new SpannableString(detailsEntity.getPackMoney());
            if (detailsEntity.getPackMoney().equals(getResources().getString(R.string.hand_slow))) {
                AbsoluteSizeSpan span = new AbsoluteSizeSpan(60);
                spanString.setSpan(span, 0, detailsEntity.getPackMoney().length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            } else {
                AbsoluteSizeSpan span = new AbsoluteSizeSpan(30);
                spanString.setSpan(span, detailsEntity.getPackMoney().length() - 1, detailsEntity.getPackMoney().length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                //设置股数
                details_shares.setText("+" + detailsEntity.getGushu() + "红包股");
            }

            details_money.setText(spanString);
        }


        //设置领取人数
        details_receive_num.setText("…" + detailsEntity.getTotalUserNum() + "+人领取");

        initImags();

        if (detailsEntity.getType().equals("1") || detailsEntity.getType().equals("4")) {

        } else if (detailsEntity.getType().equals("2")) {

//            //祝福show
            bl_show_layout.setVisibility(View.GONE);
            initBlView();

        } else if (detailsEntity.getType().equals("3")) {


            rb_show_layout.setVisibility(View.VISIBLE);
            initRbView();
        }

        reply_refresh.autoLoadMore();
    }

    private void initRbView() {

        if (detailsEntity.getContent() != null) {
            rb_show_content.setText(detailsEntity.getContent());
        }

        rb_show_like_num.setText(detailsEntity.getPraiseNum()+"");

        addImg();

    }

    private void initBlView() {

        if (detailsEntity.getContent() != null) {
            bl_show_content.setText(detailsEntity.getContent());
        }

        if (detailsEntity.getImage() != null && detailsEntity.getImage().size() > 0) {
            Glide.with(this).load(detailsEntity.getImage().get(0)).into(bl_show_img);
        }
    }

    private void initReply() {

        reply_recycler.setHasFixedSize(true);

        reply_recycler.setLayoutManager(new LinearLayoutManager(this));

        //添加Android自带的分割线
        reply_recycler.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        replyAdapter = new ReplyAdapter(this, replyList, detailsEntity.getPack_id());

        reply_recycler.setAdapter(replyAdapter);

        //留言总数
        reply_num.setText(detailsEntity.getMessageCount() + "");

    }

    private void addImg() {

        //add img
        rb_show_imags.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        rb_show_imags.setLayoutManager(gridLayoutManager);
        rb_show_imags.addItemDecoration(new SpaceItemDecoration(20,CodeUtil.SPAC_ONE));

        DetailsRbShowAdapter detailsRbShowAdapter = new DetailsRbShowAdapter(this,detailsEntity.getImage());

        rb_show_imags.setAdapter(detailsRbShowAdapter);
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

        bl_show_content = findViewById(R.id.bl_show_content);
        bl_show_img = findViewById(R.id.bl_show_img);
        rb_show_content = findViewById(R.id.rb_show_content);
        rb_show_like_num = findViewById(R.id.rb_show_like_num);
        rb_show_imags = findViewById(R.id.rb_show_imags);
        details_title = (TextView) findViewById(R.id.details_title);
        details_title.setOnClickListener(this);
        details_icon = (ImageView) findViewById(R.id.details_icon);
        details_icon.setOnClickListener(this);
        details_name = (TextView) findViewById(R.id.details_name);
        details_name.setOnClickListener(this);
        details_money = (TextView) findViewById(R.id.details_money);
        details_money.setOnClickListener(this);
        details_wallet = (TextView) findViewById(R.id.details_wallet);
        details_wallet.setOnClickListener(this);
        details_imgs_recycle = (RecyclerView) findViewById(R.id.details_imgs_recycle);
        details_imgs_recycle.setOnClickListener(this);
        reply_recycler = (RecyclerView) findViewById(R.id.reply_recycler);
        details_receive_num = (TextView) findViewById(R.id.details_receive_num);
        details_receive_num.setOnClickListener(this);
        details_shares = findViewById(R.id.details_shares);
        details_region = findViewById(R.id.details_region);
        reply_editer = (EditText) findViewById(R.id.reply_editer);
        reply_editer.setOnEditorActionListener(this);
        reply_like = (ImageView) findViewById(R.id.reply_like);
        reply_like.setOnClickListener(this);
        reply_bottom_bar = (RelativeLayout) findViewById(R.id.reply_bottom_bar);
        reply_bottom_bar.setOnClickListener(this);
        reply_num = (TextView) findViewById(R.id.reply_num);
        reply_num.setOnClickListener(this);
        reply_bar = (RelativeLayout) findViewById(R.id.reply_bar);
        reply_bar.setOnClickListener(this);
        reply_refresh = findViewById(R.id.reply_refresh);
        reply_refresh.setOnLoadMoreListener(this);
        reply_refresh.setOnRefreshListener(this);
        rb_show_layout = findViewById(R.id.rb_show_layout);
        bl_show_layout = findViewById(R.id.bl_show_layout);

    }

    @Override
    protected void initResume() {
        super.initResume();

        reply_refresh.autoRefresh();
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_pack_details;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.reply_like:

                HashMap<String, String> map = new HashMap<>();
                map.put("uid", UserInfoUtil.instance().getId() + "");
                map.put("pack_id", detailsEntity.getPack_id());

                if (type.equals("2")) {
                    type = "1";
                    map.put("type", type);
                } else {
                    type = "2";
                    map.put("type", type);
                }

                presenter.obtainPraise(map);

                break;
            case R.id.details_imgs_recycle:

                ReceiveDetailsActivity.startReceiveDetailsActivity(this,detailsEntity.getPack_id());

                break;
        }
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


        page++;
        LogUtil.e("page" + page);
        HashMap<String, String> map = new HashMap<>();
        map.put("pack_id", detailsEntity.getPack_id() + "");
        map.put("page", page + "");
        presenter.obtainLoadMore(map);

    }

    @Override
    public void showLoadMore(List<MessageListEntity.DataBean> data) {

        reply_refresh.finishLoadMore();
        reply_refresh.finishRefresh();

        if (data.size() <= 0 || data == null) {

            reply_refresh.setNoMoreData(true);

            return;
        }

        replyList.addAll(data);

        if (replyAdapter == null) {

            initReply();
        } else {

            replyAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void showAddReply(String msg) {

        if (msg.equals("成功")) {
            page = 0;
            replyList.clear();
            reply_refresh.setNoMoreData(false);
            reply_refresh.autoLoadMore();
        } else {
            ToastUtil.showShortToast(msg);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void showPraise(int praiseNum) {

        if (type.equals("1")) {
            Glide.with(this).load(R.drawable.like_click).into(reply_like);
        } else {
            Glide.with(this).load(R.drawable.like_noclick).into(reply_like);
        }
    }

    @Override
    public void onError(String error) {

    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

        if (actionId == EditorInfo.IME_ACTION_SEND) {

            submit();

            HashMap<String, String> map = new HashMap<>();
            map.put("uid", UserInfoUtil.instance().getId() + "");
            map.put("pack_id", detailsEntity.getPack_id());
//            map.put("pid", );
            map.put("content", v.getText().toString());
            presenter.obtainAddReply(map);

            v.setText("");
        }
        return false;
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {

        page = 1;
        replyList.clear();
        HashMap<String, String> map = new HashMap<>();
        map.put("pack_id", detailsEntity.getPack_id() + "");
        map.put("page", page + "");
        presenter.obtainLoadMore(map);
    }

}
