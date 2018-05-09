package com.example.lx.wyredpacketandroid.wxapi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.lx.wyredpacketandroid.base.MainApplication;
import com.example.lx.wyredpacketandroid.mvp.contract.MainContract;
import com.example.lx.wyredpacketandroid.mvp.presenter.MainPresenter;
import com.example.lx.wyredpacketandroid.ui.activity.MainActivity;
import com.example.lx.wyredpacketandroid.utils.LogUtil;
import com.example.lx.wyredpacketandroid.utils.ToastUtil;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;

public class WXEntryActivity extends AppCompatActivity implements IWXAPIEventHandler ,MainContract.View{

    private static final int RETURN_MSG_TYPE_LOGIN = 1;
    private static final int RETURN_MSG_TYPE_SHARE = 2;
    private MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //如果没回调onResp，八成是这句没有写
        MainApplication.mWxApi.handleIntent(getIntent(), this);

        presenter = new MainPresenter(this);

    }

    // 微信发送请求到第三方应用时，会回调到该方法
    @Override
    public void onReq(BaseReq req) {

    }

    // 第三方应用发送到微信的请求处理后的响应结果，会回调到该方法
    //app发送消息给微信，处理返回消息的回调
    @Override
    public void onResp(BaseResp resp) {

        switch (resp.errCode) {

            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                ToastUtil.showShortToast("拒绝授权");
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:

                if(resp.getType()== ConstantsAPI.COMMAND_PAY_BY_WX) presenter.obtainOrder();
                if (RETURN_MSG_TYPE_SHARE == resp.getType()) ToastUtil.showShortToast("分享失败");
                else ToastUtil.showShortToast("登录失败");

                finish();
                break;
            case BaseResp.ErrCode.ERR_OK:

                switch (resp.getType()) {

                    case RETURN_MSG_TYPE_LOGIN:
                        //拿到了微信返回的code,立马再去请求access_token
                        String code = ((SendAuth.Resp) resp).code;

                        presenter.obtainLogin(code);

                        LogUtil.e("拿到code"+code);

                        break;
                    case RETURN_MSG_TYPE_SHARE:
                        ToastUtil.showShortToast("微信分享成功");
                        finish();
                        break;

                    case ConstantsAPI.COMMAND_PAY_BY_WX:

//                        Toast.makeText(this, "支付成功", Toast.LENGTH_LONG).show();
//
//                        finish();

                        presenter.obtainOrder();

                        break;
                }
                break;
        }
    }

    @Override
    public void showView() {

        startActivity(new Intent(this, MainActivity.class));

        finish();
    }

    @Override
    public void Success(int state) {

        if (state == 1) {
            Toast.makeText(this, "支付成功", Toast.LENGTH_LONG).show();
            finish();
        } else {
            Toast.makeText(this, "支付失败", Toast.LENGTH_LONG).show();
            finish();
        }
    }

    @Override
    public void onError(String error) {

    }
}