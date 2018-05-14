package com.example.lx.wyredpacketandroid.ui.activity.record;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.lx.wyredpacketandroid.R;
import com.example.lx.wyredpacketandroid.base.BaseActivity;
import com.example.lx.wyredpacketandroid.ui.activity.record.adapter.RecordAdapter;
import com.example.lx.wyredpacketandroid.ui.activity.record.adapter.RecordPopAdapter;
import com.example.lx.wyredpacketandroid.ui.activity.record.entity.RecordFiveEntity;
import com.example.lx.wyredpacketandroid.ui.activity.record.entity.RecordFourEntity;
import com.example.lx.wyredpacketandroid.ui.activity.record.entity.RecordOneEntity;
import com.example.lx.wyredpacketandroid.ui.activity.record.entity.RecordPopEntity;
import com.example.lx.wyredpacketandroid.ui.activity.record.entity.RecordThreeEntity;
import com.example.lx.wyredpacketandroid.ui.activity.record.entity.RecordTwoEntity;
import com.example.lx.wyredpacketandroid.ui.activity.record.mvp.contract.RecordContract;
import com.example.lx.wyredpacketandroid.ui.activity.record.mvp.presenter.RecordPresenter;
import com.example.lx.wyredpacketandroid.utils.CodeUtil;
import com.example.lx.wyredpacketandroid.utils.LogUtil;
import com.example.lx.wyredpacketandroid.utils.UserInfoUtil;
import com.example.lx.wyredpacketandroid.utils.recycleviewutil.SpaceItemDecoration;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RecordActivity extends BaseActivity implements View.OnClickListener, RecordContract.View, CompoundButton.OnCheckedChangeListener, RecordPopAdapter.TitleListner, OnLoadMoreListener {

    private ImageView record_back;
    private CheckBox record_type_check;
    private TextView record_screen;
    private TextView record_type_num;
    private TextView record_type_tv;
    private CardView dynamic_card;
    private RecyclerView record_recycler;
    private SmartRefreshLayout record_refresh;
    private int type = 1;
    private int m_type = 0;
    private int page = 0;
    private boolean state = true;
    private RecordPresenter presenter;
    private List<RecordOneEntity.DataBean.ListBean> olist = new ArrayList<>();
    private List<RecordTwoEntity.DataBean.ListBean> tlist = new ArrayList<>();
    private List<RecordThreeEntity.DataBean.ListBean> thlist = new ArrayList<>();
    private List<RecordFourEntity.DataBean.ListBean> flist = new ArrayList<>();
    private List<RecordFiveEntity.DataBean.ListBean> fvlist = new ArrayList<>();

    private RecordAdapter recordAdapter;
    private Toolbar record_toolbar;
    private RecordPopAdapter recordPopAdapter;
    private ArrayList<RecordPopEntity> popList;
    private PopupWindow popupWindow;
    private HashMap<String, String> map;
    private Dialog screenDialog;
    private RadioButton record_screen_1;
    private RadioButton record_screen_2;
    private RadioButton record_screen_0;

    @Override
    protected void initData() {

        presenter = new RecordPresenter(this);

        if (getIntent().getIntExtra("type", 0) != 0) {

            type = getIntent().getIntExtra("type", 0);
            if (type == 5) {
                record_type_check.setText("提现记录");
                record_type_tv.setText("提现记录");
            }
        }

        initRecycler();

        initPopUpWindow();

        initDialog();

        refreshData();

    }

    private void initDialog() {

        screenDialog = new Dialog(this, R.style.CustomDialog);

        View view = LayoutInflater.from(this).inflate(R.layout.record_screen_dialog, null);

        record_screen_0 = view.findViewById(R.id.record_screen_0);
        record_screen_0.setOnClickListener(this);
        record_screen_1 = view.findViewById(R.id.record_screen_1);
        record_screen_1.setOnClickListener(this);
        record_screen_2 = view.findViewById(R.id.record_screen_2);
        record_screen_2.setOnClickListener(this);

        screenDialog.setContentView(view);

        Window dialogWindow = screenDialog.getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        lp.x = 0; // 新位置X坐标
        lp.y = -20; // 新位置Y坐标
        lp.width = (int) getResources().getDisplayMetrics().widthPixels; // 宽度
        dialogWindow.setAttributes(lp);

    }

    private void initPopUpWindow() {

        popList = new ArrayList<>();

        popList.add(new RecordPopEntity("红包股价值", false, 4));
        popList.add(new RecordPopEntity("红包股数", false, 2));
        popList.add(new RecordPopEntity("红包股价", false, 3));
        popList.add(new RecordPopEntity("发红包", false, 1));
        popList.add(new RecordPopEntity("提现记录", false, 5));

        for (RecordPopEntity recordPopEntity : popList) {
            if (recordPopEntity.getType() == type) {
                recordPopEntity.setState(true);
            }
        }

        View view = LayoutInflater.from(this).inflate(R.layout.record_popup, null);

        RecyclerView record_pop_recycler = view.findViewById(R.id.record_pop_recycler);

        record_pop_recycler.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        record_pop_recycler.setLayoutManager(gridLayoutManager);
        record_pop_recycler.addItemDecoration(new SpaceItemDecoration(20, CodeUtil.SPAC_THREE));

        recordPopAdapter = new RecordPopAdapter(this, popList);

        recordPopAdapter.setListner(this);

        record_pop_recycler.setAdapter(recordPopAdapter);

        popupWindow = new PopupWindow(this);

        popupWindow.setContentView(view);

        popupWindow.setWidth(RecyclerView.LayoutParams.MATCH_PARENT);

        popupWindow.setHeight(RecyclerView.LayoutParams.WRAP_CONTENT);

        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.WHITE));

        popupWindow.setOutsideTouchable(true);

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {

                record_type_check.setChecked(false);
            }
        });

    }

    private void refreshData() {

        page++;
        map = new HashMap<>();
        map.put("uid", UserInfoUtil.instance().getId() + "");
        map.put("type", type + "");
        map.put("page", page + "");
        if (record_screen.getVisibility() == View.VISIBLE) {
            map.put("m_type", m_type + "");
        }
        presenter.obtainRecord(map);

    }

    private void initRecycler() {

        record_recycler.setHasFixedSize(true);

        record_recycler.setLayoutManager(new LinearLayoutManager(this));

        //添加Android自带的分割线
        record_recycler.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        recordAdapter = new RecordAdapter(this, olist, tlist, thlist, flist, fvlist);

        record_recycler.setAdapter(recordAdapter);
    }

    @Override
    protected void initView() {

        record_back = (ImageView) findViewById(R.id.record_back);
        record_back.setOnClickListener(this);
        record_type_check = (CheckBox) findViewById(R.id.record_type_check);
        record_type_check.setOnCheckedChangeListener(this);
        record_screen = (TextView) findViewById(R.id.record_screen);
        record_screen.setOnClickListener(this);
        record_type_num = (TextView) findViewById(R.id.record_type_num);
        record_type_num.setOnClickListener(this);
        record_type_tv = (TextView) findViewById(R.id.record_type_tv);
        record_type_tv.setOnClickListener(this);
        dynamic_card = (CardView) findViewById(R.id.dynamic_card);
        dynamic_card.setOnClickListener(this);
        record_recycler = (RecyclerView) findViewById(R.id.record_recycler);
        record_recycler.setOnClickListener(this);
        record_refresh = (SmartRefreshLayout) findViewById(R.id.record_refresh);
        record_refresh.setOnLoadMoreListener(this);
        record_refresh.setEnableRefresh(false);
        record_toolbar = findViewById(R.id.record_toolbar);
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_record;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.record_back:

                finish();

                break;

            case R.id.record_screen:

                screenDialog.show();

                break;
            case R.id.record_screen_0:

                m_type = 0;

                screenDialog.dismiss();

                init();

                break;

            case R.id.record_screen_1:

                m_type = 1;

                screenDialog.dismiss();

                init();
                break;

            case R.id.record_screen_2:

                m_type = 2;

                screenDialog.dismiss();

                init();
                break;
        }
    }

    @Override
    public void showView(RecordOneEntity.DataBean oEntity, RecordTwoEntity.DataBean tEntity, RecordThreeEntity.DataBean thEntity, RecordFourEntity.DataBean fEntity, RecordFiveEntity.DataBean fvEntity) {

        record_refresh.finishLoadMore();

        if (oEntity != null) {
            initOEntity(oEntity);
        } else if (tEntity != null) {
            initTEntity(tEntity);
        }
        if (thEntity != null) {
            initTHEntity(thEntity);
        }
        if (fEntity != null) {
            initFEntity(fEntity);
        }
        if (fvEntity != null) {
            initFVEntity(fvEntity);
        }
    }

    private void initFVEntity(RecordFiveEntity.DataBean fvEntity) {

        if (state) {
            state = false;
            record_type_num.setText(fvEntity.getTotalMoney() + "");
        }

        if (fvEntity.getList() == null || fvEntity.getList().size() <= 0) {
            record_refresh.setNoMoreData(true);
        }

        fvlist.addAll(fvEntity.getList());

        recordAdapter.notify(olist, tlist, thlist, flist, fvlist);

    }

    private void initFEntity(RecordFourEntity.DataBean fEntity) {

        if (state) {
            state = false;
            LogUtil.e("股价"+fEntity.getTotalStockValue());
            record_type_num.setText(fEntity.getTotalStockValue() + "");
        }

        if (fEntity.getList() == null || fEntity.getList().size() <= 0) {
            record_refresh.setNoMoreData(true);
        }

        flist.addAll(fEntity.getList());

        recordAdapter.notify(olist, tlist, thlist, flist, fvlist);

    }

    private void initTHEntity(RecordThreeEntity.DataBean thEntity) {

        if (state) {
            state = false;
            record_type_num.setText(thEntity.getCurrentStock() + "");
        }

        if (thEntity.getList() == null || thEntity.getList().size() <= 0) {
            record_refresh.setNoMoreData(true);
        }

        thlist.addAll(thEntity.getList());

        recordAdapter.notify(olist, tlist, thlist, flist, fvlist);

    }

    private void initTEntity(RecordTwoEntity.DataBean tEntity) {

        LogUtil.e("请求回传2" + tEntity.getTotalStockNum());
        if (state) {
            state = false;
            record_type_num.setText(tEntity.getTotalStockNum() + "");
        }

        if (tEntity.getList() == null || tEntity.getList().size() <= 0) {
            record_refresh.setNoMoreData(true);
        }

        tlist.addAll(tEntity.getList());
        LogUtil.e("添加" + tEntity.getList().size());
        recordAdapter.notify(olist, tlist, thlist, flist, fvlist);

    }

    private void initOEntity(RecordOneEntity.DataBean oEntity) {

        LogUtil.e("请求回传");
        if (state) {
            state = false;
            record_type_num.setText(oEntity.getTotalMoney() + "");
        }

        if (oEntity.getList() == null || oEntity.getList().size() <= 0) {
            record_refresh.setNoMoreData(true);
        }

        olist.addAll(oEntity.getList());

        recordAdapter.notify(olist, tlist, thlist, flist, fvlist);
    }

    @Override
    public void onError(String error) {

    }

    @Override
    public void onCheckedChanged(final CompoundButton buttonView, boolean isChecked) {

        if (isChecked) {

            popupWindow.showAsDropDown(record_toolbar);

        } else {

            popupWindow.dismiss();
        }
    }

    @Override
    public void onCheck(int position) {

        for (RecordPopEntity recordPopEntity : popList) {
            recordPopEntity.setState(false);
        }

        popList.get(position).setState(true);

        type = popList.get(position).getType();

        recordPopAdapter.notifyDataSetChanged();

        record_type_check.setText(popList.get(position).getTitle());

        record_type_tv.setText(popList.get(position).getTitle());

        popupWindow.dismiss();

        init();

    }

    private void init() {

        state = true;

        record_refresh.setNoMoreData(false);

        page = 0;

        olist.clear();

        tlist.clear();

        thlist.clear();

        flist.clear();

        fvlist.clear();

        if (type == 4) {
            record_screen.setVisibility(View.VISIBLE);
        } else {
            record_screen.setVisibility(View.GONE);
        }

        refreshData();

    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {

        refreshData();

    }

    public static void StartRecordActivity(Context context,int type) {

        context.startActivity(new Intent(context,RecordActivity.class).putExtra("type",type));
    }
}
