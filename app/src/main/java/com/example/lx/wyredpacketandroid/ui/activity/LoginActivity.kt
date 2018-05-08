package com.example.lx.wyredpacketandroid.ui.activity

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import android.support.annotation.RequiresApi
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.BackgroundColorSpan
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.text.style.UnderlineSpan
import android.transition.Explode
import android.transition.Fade
import android.transition.Slide
import android.view.View
import android.view.Window
import android.widget.Toast
import com.example.lx.wyredpacketandroid.R
import com.example.lx.wyredpacketandroid.R.id.*
import com.example.lx.wyredpacketandroid.base.BaseActivity
import com.example.lx.wyredpacketandroid.base.MainApplication
import com.example.lx.wyredpacketandroid.utils.ToastUtil
import com.example.lx.wyredpacketandroid.utils.glideutils.GlideImageLoader
import com.tencent.mm.opensdk.modelmsg.SendAuth
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX
import com.tencent.mm.opensdk.modelmsg.WXTextObject
import com.youth.banner.BannerConfig
import com.zhihu.matisse.internal.utils.UIUtils
import kotlinx.android.synthetic.main.activity_login.*
import java.util.ArrayList

class LoginActivity : BaseActivity() {

    override fun initLayout(): Int {
        return R.layout.activity_login
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun initView() {

        if (isAndroid5()) {
            getWindow().setEnterTransition(Slide().setDuration(1000));
            getWindow().setExitTransition(Slide().setDuration(1000));
        }

        initBanner()

        login_clause.text = getClickableSpan()
        //设置超链接可点击
        login_clause.setMovementMethod(LinkMovementMethod.getInstance());

        login_bt.setOnClickListener(View.OnClickListener {

            wxLogin()

            finish()

        })

    }

    override fun initData() {
    }

    fun wxLogin() {
        if (!MainApplication.mWxApi.isWXAppInstalled()) {
            ToastUtil.showShortToast("您还未安装微信客户端")
            return;
        }
        var mas = WXTextObject.TYPE_APPBRAND
        var req =  SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "diandi_wx_login";
        MainApplication.mWxApi.sendReq(req);
    }

    //设置banner图
    private fun initBanner() {

        var images = ArrayList<Int>()

        images.add(R.drawable.loginone)
        images.add(R.drawable.logintwo)
        images.add(R.drawable.loginthree)

        login_banner.setImageLoader(GlideImageLoader())
                .setBannerStyle(BannerConfig.NOT_INDICATOR)
                .setDelayTime(1500)
                .setImages(images)
                .start()
    }

    /**
     * 获取可点击的SpannableString
     * @return
     */
    private fun getClickableSpan():SpannableString {

        val spannableString = SpannableString("登录即表示同意用户协议")

        spannableString.setSpan(object : ClickableSpan() {
            override fun onClick(widget: View) {
                Toast.makeText(this@LoginActivity, "用户协议", Toast.LENGTH_SHORT).show()
            }
        }, 7, 11, Spanned.SPAN_COMPOSING)

        //设置字体前景色
        spannableString.setSpan( ForegroundColorSpan(Color.BLUE), 7, 11, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);  //设置前景色为洋红色

        //设置字体背景色
        spannableString.setSpan( BackgroundColorSpan(Color.TRANSPARENT), 7, 11, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);  //设置背景色为青色

        return spannableString
    }
}
