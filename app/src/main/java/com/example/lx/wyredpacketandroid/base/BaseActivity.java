package com.example.lx.wyredpacketandroid.base;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.zhy.autolayout.AutoLayoutActivity;

public abstract class BaseActivity extends AutoLayoutActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainApplication.activity = this;

        setContentView(initLayout());

        initView();

        initData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        MainApplication.activity = this;

        initResume();
    }

    protected void initResume() {

    }

    @Override
    protected void onStart() {
        super.onStart();
        initStart();
    }


    @Override
    protected void onStop() {
        super.onStop();
        initStop();
    }

    public void initStop() {

    }

    public void initStart() {

    }


    //初始化数据
    protected abstract void initData();

    //初始化view
    protected abstract void initView();

    //初始化布局
    protected abstract int initLayout();

    /**
     * 判断android SDK 版本是否大于等于5.0
     * @return
     */
    public static boolean isAndroid5() {

        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }
}
