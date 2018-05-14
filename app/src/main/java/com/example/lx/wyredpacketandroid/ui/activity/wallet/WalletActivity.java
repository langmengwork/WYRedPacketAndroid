package com.example.lx.wyredpacketandroid.ui.activity.wallet;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lx.wyredpacketandroid.R;
import com.example.lx.wyredpacketandroid.base.BaseActivity;
import com.example.lx.wyredpacketandroid.ui.activity.MainActivity;
import com.example.lx.wyredpacketandroid.ui.activity.sendmoney.SendActivity;
import com.example.lx.wyredpacketandroid.ui.activity.wallet.entity.UserWalletEntity;
import com.example.lx.wyredpacketandroid.ui.activity.wallet.mvp.contract.WalletContract;
import com.example.lx.wyredpacketandroid.ui.activity.wallet.mvp.presenter.WalletPresenter;
import com.example.lx.wyredpacketandroid.utils.customview.ChartMarkerView;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class WalletActivity extends BaseActivity implements View.OnClickListener ,WalletContract.View{

    private TextView wallet_money;
    private TextView wallet_extract;
    private TextView wallet_tip_one;
    private TextView wallet_card_left_num;
    private TextView wallet_card_rghit_num;
    private LineChart wallet_chart;
    private WalletPresenter presenter;
    private CheckBox wallet_collect_check;
    private CheckBox wallet_wallet_check;
    private LinearLayout wallet_send_money;

    @Override
    protected void initData() {

        presenter = new WalletPresenter(this);

        presenter.obtainUserWallet();
    }

    @Override
    protected void initView() {

        wallet_money = (TextView) findViewById(R.id.wallet_money);
        wallet_money.setOnClickListener(this);
        wallet_extract = (TextView) findViewById(R.id.wallet_extract);
        wallet_extract.setOnClickListener(this);
        wallet_tip_one = (TextView) findViewById(R.id.wallet_tip_one);
        wallet_tip_one.setOnClickListener(this);
        wallet_card_left_num = (TextView) findViewById(R.id.wallet_card_left_num);
        wallet_card_left_num.setOnClickListener(this);
        wallet_card_rghit_num = (TextView) findViewById(R.id.wallet_card_rghit_num);
        wallet_card_rghit_num.setOnClickListener(this);
        wallet_chart = (LineChart) findViewById(R.id.wallet_chart);
        wallet_chart.setOnClickListener(this);
        wallet_collect_check = findViewById(R.id.wallet_collect_check);
        wallet_collect_check.setOnClickListener(this);
        wallet_wallet_check = findViewById(R.id.wallet_wallet_check);
        wallet_wallet_check.setOnClickListener(this);
        wallet_send_money = findViewById(R.id.wallet_send_money);
        wallet_send_money.setOnClickListener(this);

    }

    @Override
    protected int initLayout() {
        return R.layout.activity_wallet;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.wallet_extract:

                startActivity(new Intent(this,PutForwardActivity.class));

                break;
            case R.id.wallet_collect_check:

                finish();

                break;

            case R.id.wallet_send_money:

                startActivity(new Intent(WalletActivity.this, SendActivity.class));

                break;
        }
    }

    @Override
    public void showView(UserWalletEntity.DataBean data) {

        //设置今日股价
        wallet_card_left_num.setText(data.getStockprice() + "");
        //设置我的股数
        wallet_card_rghit_num.setText(data.getStocknums() + "");
        //设置股价值
        wallet_money.setText(data.getStockprice()*data.getStocknums()+"");

        UserWalletEntity.DataBean.ListBean list = data.getList();
        initChart(list.getKey(),list.getValues());

    }

    private void initChart(List<String> key, List<Double> values) {

        //设置x轴的样式
        XAxis xAxis = wallet_chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(false);
        //显示个数
        xAxis.setLabelCount(7);
        xAxis.setTextColor(Color.RED);

        //设置左边y轴的样式
        YAxis yAxisLeft = wallet_chart.getAxisLeft();
        yAxisLeft.setDrawGridLines(true);
        yAxisLeft.setDrawAxisLine(false);
        yAxisLeft.setDrawLabels(false);
        yAxisLeft.enableGridDashedLine(10,10,1);

        //设置右边y轴的样式
        YAxis yAxisRight = wallet_chart.getAxisRight();
        yAxisRight.setDrawAxisLine(false);
        yAxisRight.setDrawLabels(false);

        Legend legend = wallet_chart.getLegend();//设置比例图
        legend.setEnabled(false);//图例不显示

        List<Entry> entries = new ArrayList<Entry>();

//        for (String s : key) {
//
//            for (Double value : values) {
//
//                entries.add(new Entry(Float.valueOf(s), Float.valueOf(value+"")));
//
//            }
//        }

        wallet_chart.setMarker(new ChartMarkerView(this, R.layout.item_chart_indicator));
        entries.add(new Entry(2, 6));
        entries.add(new Entry(3, 9));
        entries.add(new Entry(4, 5));
        entries.add(new Entry(5, 3));
        LineDataSet dataSet = new LineDataSet(entries, null);
        dataSet.setDrawFilled(true);
        dataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        //线的颜色
        dataSet.setColor(getResources().getColor(R.color.color_DB5544));
        dataSet.setLineWidth(1f);

        //阴影
        if (Utils.getSDKInt() >= 18) {
            // fill drawable only supported on api level 18 and above
            Drawable drawable = ContextCompat.getDrawable(this, R.drawable.shape_gradual_db5544_white);
            dataSet.setFillDrawable(drawable);
        }
        else {
            dataSet.setFillColor(getResources().getColor(R.color.color_DB5544));
        }

        LineData lineData = new LineData(dataSet);
        wallet_chart.setData(lineData);
        wallet_chart.invalidate();
    }

    @Override
    public void onError(String error) {

    }
}
