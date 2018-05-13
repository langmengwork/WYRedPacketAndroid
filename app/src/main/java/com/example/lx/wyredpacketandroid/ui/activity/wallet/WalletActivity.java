package com.example.lx.wyredpacketandroid.ui.activity.wallet;

import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.example.lx.wyredpacketandroid.R;
import com.example.lx.wyredpacketandroid.base.BaseActivity;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;

public class WalletActivity extends BaseActivity implements View.OnClickListener {

    private TextView wallet_money;
    private TextView wallet_extract;
    private TextView wallet_tip_one;
    private TextView wallet_card_left_num;
    private TextView wallet_card_rghit_num;
    private LineChart wallet_chart;

    @Override
    protected void initData() {

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

        //设置右边y轴的样式
        YAxis yAxisRight = wallet_chart.getAxisRight();
        yAxisRight.setDrawAxisLine(false);


//        XAxis xAxis = wallet_chart.getXAxis();
//        YAxis axisRight = wallet_chart.getAxisRight();
//        axisRight.setDrawZeroLine(false);
//        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);


        List<Entry> entries = new ArrayList<Entry>();
        entries.add(new Entry(1, 0));
        entries.add(new Entry(3, 2));
        entries.add(new Entry(6, 9));
        entries.add(new Entry(4, 3));
        LineDataSet dataSet = new LineDataSet(entries, null);
        dataSet.setDrawFilled(true);
        LineData lineData = new LineData(dataSet);
        wallet_chart.setData(lineData);
        wallet_chart.invalidate();
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
        }
    }

}
