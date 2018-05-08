package com.example.lx.wyredpacketandroid.ui.activity.sendmoney;


import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.lx.wyredpacketandroid.R;
import com.example.lx.wyredpacketandroid.base.BaseActivity;
import com.example.lx.wyredpacketandroid.base.MainApplication;
import com.example.lx.wyredpacketandroid.entity.CreateRedpackEntity;
import com.example.lx.wyredpacketandroid.ui.activity.sendmoney.adapter.AddImgAdapter;
import com.example.lx.wyredpacketandroid.ui.activity.sendmoney.entity.SearchAddressEntity;
import com.example.lx.wyredpacketandroid.ui.activity.sendmoney.entity.TabEntity;
import com.example.lx.wyredpacketandroid.ui.activity.sendmoney.mvp.contract.SendContract;
import com.example.lx.wyredpacketandroid.ui.activity.sendmoney.mvp.presenter.SendPresenter;
import com.example.lx.wyredpacketandroid.utils.CodeUtil;
import com.example.lx.wyredpacketandroid.utils.FileUtils;
import com.example.lx.wyredpacketandroid.utils.LogUtil;
import com.example.lx.wyredpacketandroid.utils.ToastUtil;
import com.example.lx.wyredpacketandroid.utils.UserInfoUtil;
import com.example.lx.wyredpacketandroid.utils.glideutils.GlideRoundTransform;
import com.example.lx.wyredpacketandroid.utils.networkutil.UrlUtil;
import com.example.lx.wyredpacketandroid.utils.recycleviewutil.SpaceItemDecoration;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.google.gson.Gson;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.UpCompletionHandler;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.zhihu.matisse.Matisse;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SendActivity extends BaseActivity implements View.OnClickListener, OnTabSelectListener,SendContract.View, CompoundButton.OnCheckedChangeListener {
    private TextView send_back;
    private TextView send_history;
    private CommonTabLayout send_tab;
    private EditText send_edit_word;
    private RecyclerView send_add_img;
    private LinearLayout send_img_preview;
    private TextView send_edit_oa;
    private TextView send_edit_ob;
    private EditText send_edit_money;
    private TextView send_edit_ma;
    private TextView send_edit_mb;
    private EditText send_edit_much;
    private TextView send_edit_pa;
    private TextView send_range_type;
    private ImageView send_edit_pb;
    private LinearLayout send_search_site_layout;
    private TextView send_range_tv;
    private CheckBox send_check_encryption;
    private ImageView send_checkbox_doubt;
    private TextView send_encryption_modify;
    private TextView send_encryption_tv;
    private TextView send_money_bt;

    private List<Uri> mSelected;
    private AddImgAdapter addImgAdapter;
    private ArrayList<CustomTabEntity> customTabEntities;
    private boolean showFoot = false;
    private Dialog dialog;
    private SendPresenter presenter;
    private HashMap<String, String> map;
    private String type = "3";
    private String point;
    private String point_type = "1";
    private ArrayList<String> imags;

    @Override
    protected void initData() {

        presenter = new SendPresenter(this);

        map = new HashMap<>();

        addTab();

        addImg();

    }

    private void addImg() {

        //add img
        send_add_img.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
//        gridLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        send_add_img.setLayoutManager(gridLayoutManager);
        send_add_img.addItemDecoration(new SpaceItemDecoration(20,CodeUtil.SPAC_ONE));
        mSelected = new ArrayList<>();
        addImgAdapter = new AddImgAdapter(this, mSelected, showFoot);
        send_add_img.setAdapter(addImgAdapter);
    }

    private void addTab() {
        customTabEntities = new ArrayList<>();
        TabEntity tabLeft = new TabEntity("发广播", 0, 0);
        TabEntity tabRight = new TabEntity("发祝福", 0, 0);
        customTabEntities.add(tabLeft);
        customTabEntities.add(tabRight);

        send_tab.setTabData(customTabEntities);

        send_tab.setOnTabSelectListener(this);

    }

    @Override
    protected void initView() {

        send_back = (TextView) findViewById(R.id.send_back);
        send_back.setOnClickListener(this);
        send_history = (TextView) findViewById(R.id.send_history);
        send_history.setOnClickListener(this);
        send_tab = (CommonTabLayout) findViewById(R.id.send_tab);
        send_tab.setOnClickListener(this);
        send_edit_word = (EditText) findViewById(R.id.send_edit_word);
        send_edit_word.setOnClickListener(this);
        send_add_img = (RecyclerView) findViewById(R.id.send_add_img);
        send_add_img.setOnClickListener(this);
        send_img_preview = (LinearLayout) findViewById(R.id.send_img_preview);
        send_img_preview.setOnClickListener(this);
        send_edit_oa = (TextView) findViewById(R.id.send_edit_oa);
        send_edit_oa.setOnClickListener(this);
        send_edit_ob = (TextView) findViewById(R.id.send_edit_ob);
        send_edit_ob.setOnClickListener(this);
        send_edit_money = (EditText) findViewById(R.id.send_edit_money);
        send_edit_money.setOnClickListener(this);
        send_edit_ma = (TextView) findViewById(R.id.send_edit_ma);
        send_edit_ma.setOnClickListener(this);
        send_edit_mb = (TextView) findViewById(R.id.send_edit_mb);
        send_edit_mb.setOnClickListener(this);
        send_edit_much = (EditText) findViewById(R.id.send_edit_much);
        send_edit_much.setOnClickListener(this);
        send_edit_pa = (TextView) findViewById(R.id.send_edit_pa);
        send_edit_pa.setOnClickListener(this);
        send_range_type = (TextView) findViewById(R.id.send_range_type);
        send_range_type.setOnClickListener(this);
        send_edit_pb = (ImageView) findViewById(R.id.send_edit_pb);
        send_edit_pb.setOnClickListener(this);
        send_search_site_layout = (LinearLayout) findViewById(R.id.send_search_site_layout);
        send_search_site_layout.setOnClickListener(this);
        send_range_tv = (TextView) findViewById(R.id.send_range_tv);
        send_range_tv.setOnClickListener(this);
        send_check_encryption = (CheckBox) findViewById(R.id.send_check_encryption);
        send_check_encryption.setOnCheckedChangeListener(this);
        send_checkbox_doubt = (ImageView) findViewById(R.id.send_checkbox_doubt);
        send_checkbox_doubt.setOnClickListener(this);
        send_encryption_modify = (TextView) findViewById(R.id.send_encryption_modify);
        send_encryption_modify.setOnClickListener(this);
        send_encryption_tv = (TextView) findViewById(R.id.send_encryption_tv);
        send_encryption_tv.setOnClickListener(this);
        send_money_bt = (TextView) findViewById(R.id.send_money_bt);
        send_money_bt.setOnClickListener(this);
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_send;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.send_img_preview:

                if (mSelected == null || mSelected.size() <= 0) {
                    ToastUtil.showShortToast("未设置图片");
                    return;
                }

                View dialogView = LayoutInflater.from(this).inflate(R.layout.send_img_preview_dialog, null);
                ImageView preview_dialog_img = dialogView.findViewById(R.id.preview_dialog_img);
                TextView preview_dialog_content = dialogView.findViewById(R.id.preview_dialog_content);
                preview_dialog_content.setText(send_edit_word.getText().toString());
                dialogView.findViewById(R.id.preview_dialog_finish).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                Glide.with(this).load(mSelected.get(0)).transform(new GlideRoundTransform(this, 20)).into(preview_dialog_img);

                dialog = new Dialog(this, R.style.CustomDialog);
                dialog.setContentView(dialogView);
                dialog.setCancelable(false);
                dialog.show();

                break;

            case R.id.send_search_site_layout:

                startActivityForResult(new Intent(this, SearchSiteActivity.class), CodeUtil.INTENT_SEND);

                break;

            case R.id.send_money_bt:

                submit();

                presenter.obtainQiniuToken();

                break;

            case R.id.send_back:

                finish();

                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CodeUtil.REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {

            if (showFoot) {
                mSelected.add(Matisse.obtainResult(data).get(0));
            } else {
                mSelected.addAll(Matisse.obtainResult(data));
            }

            if (mSelected.size() >= 9) {
                showFoot = true;
            }
            addImgAdapter.notify(mSelected, showFoot);
        }

        //搜索地址回调
        if (requestCode == CodeUtil.INTENT_SEND && resultCode == RESULT_OK) {

            String type = data.getStringExtra("type");
            SearchAddressEntity entity = new Gson().fromJson(data.getStringExtra("address"), SearchAddressEntity.class);

            //设置address
            send_range_tv.setText(entity.getAddress());
            //设置range
            if (type.equals(getResources().getString(R.string.search_range_one))) {

                point_type = "1";
            } else if (type.equals(getResources().getString(R.string.search_range_two))){

                point_type = "2";
            }else if (type.equals(getResources().getString(R.string.search_range_three))){

                point_type = "3";
            }
            send_range_type.setText(type);

            //设置投放区域
            point = entity.getPoint().getLongitude() + "," + entity.getPoint().getLatitude();

        }
    }

    @Override
    public void onTabSelect(int position) {

        if (customTabEntities.get(position).getTabTitle().equals(getResources().getString(R.string.send_tab_two))) {
            type = "2";
            send_img_preview.setVisibility(View.VISIBLE);
            send_edit_word.setHint(R.string.send_edit_word_two);
            send_edit_word.setMaxEms(27);
            mSelected.clear();
            showFoot = true;
            addImgAdapter.notify(mSelected, showFoot);
        } else {
            type = "3";
            send_img_preview.setVisibility(View.GONE);
            send_edit_word.setHint(R.string.send_edit_word_one);
            send_edit_word.setMaxEms(300);
            mSelected.clear();
            showFoot = false;
            addImgAdapter.notify(mSelected, showFoot);
        }
    }

    @Override
    public void onTabReselect(int position) {

    }

    private void submit() {
        // validate
        String word = send_edit_word.getText().toString().trim();
        if (TextUtils.isEmpty(word)) {
            Toast.makeText(this, "word不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        String money = send_edit_money.getText().toString().trim();
        if (TextUtils.isEmpty(money)) {
            Toast.makeText(this, "money不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        String much = send_edit_much.getText().toString().trim();
        if (TextUtils.isEmpty(much)) {
            Toast.makeText(this, "much不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something


    }

    @Override
    public void sendPack(final String token) {

        //        data = <File对象、或 文件路径、或 字节数组>
//        String key = <指定七牛服务上的文件名，或 null>;
//        String token = <从服务端SDK获取>;

        imags = new ArrayList<>();

        new Thread(new Runnable() {
            @Override
            public void run() {

                for (final Uri uri : mSelected) {
                    String path = FileUtils.getFileAbsolutePath(SendActivity.this, uri);

                    LogUtil.e("文件路径："+path);
                    MainApplication.uploadManager.put(path, null, token,
                            new UpCompletionHandler() {
                                @Override
                                public void complete(String key, ResponseInfo info, JSONObject res) {
                                    //res包含hash、key等信息，具体字段取决于上传策略的设置
                                    if (info.isOK()) {
                                        LogUtil.e("上传成功");

                                        try {
                                            LogUtil.e("图片地址：" + UrlUtil.URL_QNIUYUN + res.getString("key"));
                                            imags.add(UrlUtil.URL_QNIUYUN + res.getString("key"));
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }

                                        if (uri == mSelected.get(mSelected.size() - 1)) {
                                            goPay();
                                        }
                                    } else {
                                        LogUtil.e("上传失败"+info.error);
                                        //如果失败，这里可以把info信息上报自己的服务器，便于后面分析上传错误原因
                                    }
                                }
                                }, null);
                }
            }
        }).start();

    }

    private void goPay() {

        map.put("uid", UserInfoUtil.instance().getId()+"");
        map.put("type", type);
        map.put("content", send_edit_word.getText().toString());
        map.put("image", new Gson().toJson(imags));
        LogUtil.e("json图片："+new Gson().toJson(imags));
        map.put("money", send_edit_money.getText().toString());
        map.put("num", send_edit_much.getText().toString());
        map.put("point", point);
        map.put("point_type", point_type);

        //红包密码
        if (send_check_encryption.isChecked()) {
            map.put("secret", send_encryption_tv.getText().toString());
        }

        presenter.obtainSendPack(map);

    }

    /**
     *调起微信支付的方法
     **/
    @Override
    public void Success(final CreateRedpackEntity.DataBean data) {

        Runnable payRunnable = new Runnable() {  //这里注意要放在子线程
            @Override
            public void run() {
                PayReq request = new PayReq(); //调起微信APP的对象
                //下面是设置必要的参数，也就是前面说的参数,这几个参数从何而来请看上面说明
                request.appId = data.getAppid();
                request.partnerId = data.getPartnerid();
                request.prepayId = data.getPrepayid();
                request.packageValue = data.getPackageX();
                request.nonceStr = data.getNoncestr();
                request.timeStamp = String.valueOf(data.getTimestamp());
                request.sign = data.getSign();
                MainApplication.mWxApi.sendReq(request);//发送调起微信的请求
            }
        };
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    @Override
    public void onError(String error) {

    }

    //红包加密
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        if (isChecked) {
            send_encryption_modify.setVisibility(View.VISIBLE);
            send_encryption_tv.setVisibility(View.VISIBLE);
        } else {
            send_encryption_modify.setVisibility(View.GONE);
            send_encryption_tv.setVisibility(View.GONE);
        }

    }
}
