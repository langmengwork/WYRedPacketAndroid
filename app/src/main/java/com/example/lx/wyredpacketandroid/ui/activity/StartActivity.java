package com.example.lx.wyredpacketandroid.ui.activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.CountDownTimer;

import com.example.lx.wyredpacketandroid.R;
import com.example.lx.wyredpacketandroid.base.BaseActivity;
import com.example.lx.wyredpacketandroid.mvp.contract.MainContract;
import com.example.lx.wyredpacketandroid.mvp.presenter.MainPresenter;
import com.example.lx.wyredpacketandroid.utils.LogUtil;

public class StartActivity extends BaseActivity implements MainContract.View{

    private CountDownTimer timer;

    @Override
    protected void initData() {

        timer = new CountDownTimer(3000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                //计时间隔
            }

            @Override
            public void onFinish() {

                MainPresenter presenter = new MainPresenter(StartActivity.this);

                presenter.obtainToken();


//                startActivity(new Intent(StartActivity.this, MainActivity.class));
//
//                finish();

                timer.cancel();

            }
        }.start();

    }

    @Override
    protected void initView() {

    }

    @Override
    protected int initLayout() {
        return R.layout.activity_start;
    }

    @Override
    public void showView() {

        startActivity(new Intent(StartActivity.this, MainActivity.class));

        finish();
    }

    @Override
    public void Success(int state) {

    }

    @Override
    public void onError(String error) {

        startactivity();
    }

    private void startactivity() {

        if (isAndroid5()) {
            startActivity(new Intent(StartActivity.this, LoginActivity.class),
                    ActivityOptions.makeSceneTransitionAnimation(StartActivity.this).toBundle());
        } else {
            startActivity(new Intent(StartActivity.this, LoginActivity.class));
        }

        finish();

    }
}
