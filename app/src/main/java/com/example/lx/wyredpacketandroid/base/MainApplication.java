package com.example.lx.wyredpacketandroid.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.example.lx.wyredpacketandroid.R;
import com.example.lx.wyredpacketandroid.utils.CodeUtil;

import com.qiniu.android.storage.Configuration;
import com.qiniu.android.storage.UploadManager;
import com.scwang.smartrefresh.header.FunGameHitBlockHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.ArrayList;

public class MainApplication extends Application {

    //定位当前activity
    public static Activity activity;
    public static IWXAPI mWxApi;
    public static UploadManager uploadManager;
    public static ArrayList<Activity> activityList;

    @Override
    public void onCreate() {
        super.onCreate();

        //初始化微信
        registToWX();

        //初始化七牛云
        initQiniuyun();

        activityList = new ArrayList<>();

    }

    private void initQiniuyun() {

        Configuration config = new Configuration.Builder()
                .chunkSize(512 * 1024)        // 分片上传时，每片的大小。 默认256K
                .putThreshhold(1024 * 1024)   // 启用分片上传阀值。默认512K
                .connectTimeout(10)           // 链接超时。默认10秒
//                .useHttps(true)               // 是否使用https上传域名
                .responseTimeout(60)          // 服务器响应超时。默认60秒
//                .zone(FixedZone.zone0)        // 设置区域，指定不同区域的上传域名、备用域名、备用IP。
                .build();
// 重用uploadManager。一般地，只需要创建一个uploadManager对象
        uploadManager = new UploadManager(config);
    }

    private void registToWX() {
        //AppConst.WEIXIN.APP_ID是指你应用在微信开放平台上的AppID，记得替换。
        mWxApi = WXAPIFactory.createWXAPI(this, CodeUtil.APP_ID, false);
        // 将该app注册到微信
        mWxApi.registerApp(CodeUtil.APP_ID);
    }

    public static void removeAll(){

        for (Activity activity1 : activityList) {
            activity1.finish();
        }
    }

    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);//全局设置主题颜色
                return new FunGameHitBlockHeader(context);//打砖块
            }
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                //指定为经典Footer，默认是 BallPulseFooter
                return new ClassicsFooter(context).setDrawableSize(20);
            }
        });
    }
}
