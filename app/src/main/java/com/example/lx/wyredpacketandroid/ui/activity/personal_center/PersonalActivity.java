package com.example.lx.wyredpacketandroid.ui.activity.personal_center;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lx.wyredpacketandroid.R;
import com.example.lx.wyredpacketandroid.base.BaseActivity;
import com.example.lx.wyredpacketandroid.base.MainApplication;
import com.example.lx.wyredpacketandroid.ui.activity.LoginActivity;
import com.example.lx.wyredpacketandroid.utils.GreendaoUtil;
import com.example.lx.wyredpacketandroid.utils.UserInfoUtil;
import com.example.lx.wyredpacketandroid.utils.glideutils.GlideRoundTransform;

public class PersonalActivity extends BaseActivity implements View.OnClickListener {

    private ImageView pep_back;
    private ImageView pep_icon;
    private TextView pep_name;
    private TextView pep_id;
    private ImageView per_line_one;
    private RelativeLayout pep_money_dynamic_layout;
    private ImageView per_line_two;
    private ImageView per_line_two_in;
    private RelativeLayout pep_service_layout;
    private ImageView per_line_three;
    private RelativeLayout pep_feedback_layout;
    private TextView pep_exit_account;

    @Override
    protected void initData() {

        //设置用户头像
        Glide.with(this).load(UserInfoUtil.instance().getHeadimgurl()).transform(new GlideRoundTransform(this, 10)).into(pep_icon);

        //设置用户名称
        pep_name.setText(UserInfoUtil.instance().getNickname());

        //设置用户ID
        pep_id.setText("ID:" + UserInfoUtil.instance().getId());

    }

    @Override
    protected void initView() {

        pep_back = (ImageView) findViewById(R.id.pep_back);
        pep_back.setOnClickListener(this);
        pep_icon = (ImageView) findViewById(R.id.pep_icon);
        pep_icon.setOnClickListener(this);
        pep_name = (TextView) findViewById(R.id.pep_name);
        pep_name.setOnClickListener(this);
        pep_id = (TextView) findViewById(R.id.pep_id);
        pep_id.setOnClickListener(this);
        per_line_one = (ImageView) findViewById(R.id.per_line_one);
        per_line_one.setOnClickListener(this);
        pep_money_dynamic_layout = (RelativeLayout) findViewById(R.id.pep_money_dynamic_layout);
        pep_money_dynamic_layout.setOnClickListener(this);
        per_line_two = (ImageView) findViewById(R.id.per_line_two);
        per_line_two.setOnClickListener(this);
        per_line_two_in = (ImageView) findViewById(R.id.per_line_two_in);
        per_line_two_in.setOnClickListener(this);
        pep_service_layout = (RelativeLayout) findViewById(R.id.pep_service_layout);
        pep_service_layout.setOnClickListener(this);
        per_line_three = (ImageView) findViewById(R.id.per_line_three);
        per_line_three.setOnClickListener(this);
        pep_feedback_layout = (RelativeLayout) findViewById(R.id.pep_feedback_layout);
        pep_feedback_layout.setOnClickListener(this);
        pep_exit_account = (TextView) findViewById(R.id.pep_exit_account);
        pep_exit_account.setOnClickListener(this);
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_personal;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.pep_back:

                finish();

                break;

            case R.id.pep_money_dynamic_layout:

                startActivity(new Intent(this,MoneyDynamicActivity.class));

                break;

            case R.id.pep_service_layout:

                break;

            case R.id.pep_feedback_layout:

                break;

            case R.id.pep_exit_account:

                GreendaoUtil.instance().exitLogin();

                MainApplication.removeAll();

                startActivity(new Intent(this, LoginActivity.class));

                break;
        }
    }
}
