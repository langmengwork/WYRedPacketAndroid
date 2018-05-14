package com.example.lx.wyredpacketandroid.ui.activity.wallet;

import android.app.Dialog;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lx.wyredpacketandroid.R;
import com.example.lx.wyredpacketandroid.base.BaseActivity;
import com.example.lx.wyredpacketandroid.ui.activity.record.RecordActivity;
import com.example.lx.wyredpacketandroid.ui.activity.wallet.entity.PutForwardEntity;
import com.example.lx.wyredpacketandroid.ui.activity.wallet.mvp.contract.WalletContract;
import com.example.lx.wyredpacketandroid.ui.activity.wallet.mvp.presenter.PutForwardPresenter;
import com.example.lx.wyredpacketandroid.utils.GreendaoUtil;
import com.example.lx.wyredpacketandroid.utils.UserInfoUtil;
import com.example.lx.wyredpacketandroid.utils.greendaoUtil.UserTokenDao;
import com.example.lx.wyredpacketandroid.utils.greendaoform.UserToken;

import java.util.HashMap;

public class PutForwardActivity extends BaseActivity implements View.OnClickListener,WalletContract.PFView, TextWatcher {

    private ImageView withdrawal_back;
    private TextView pforward_record;
    private TextView pforward_card_tip;
    private TextView pforward_money_tv;
    private EditText pforward_edit;
    private RelativeLayout pforward_edit_layout;
    private TextView pforward_num_tv;
    private TextView pforward_tips_one;
    private Button pforward_bt;
    private PutForwardPresenter presenter;
    private TextView pforward_num_all;
    private double stockNum;
    private double stock;
    private Double ed_num;


    @Override
    protected void initData() {

        presenter = new PutForwardPresenter(this);
        presenter.obtainPutForward();

    }

    @Override
    protected void initView() {

        withdrawal_back = (ImageView) findViewById(R.id.withdrawal_back);
        withdrawal_back.setOnClickListener(this);
        pforward_record = (TextView) findViewById(R.id.pforward_record);
        pforward_record.setOnClickListener(this);
        pforward_card_tip = (TextView) findViewById(R.id.pforward_card_tip);
        pforward_card_tip.setOnClickListener(this);
        pforward_money_tv = (TextView) findViewById(R.id.pforward_money_tv);
        pforward_money_tv.setOnClickListener(this);
        pforward_edit = (EditText) findViewById(R.id.pforward_edit);
        pforward_edit.addTextChangedListener(this);
        pforward_edit_layout = (RelativeLayout) findViewById(R.id.pforward_edit_layout);
        pforward_edit_layout.setOnClickListener(this);
        pforward_num_tv = (TextView) findViewById(R.id.pforward_num_tv);
        pforward_num_tv.setOnClickListener(this);
        pforward_tips_one = (TextView) findViewById(R.id.pforward_tips_one);
        pforward_tips_one.setOnClickListener(this);
        pforward_bt = (Button) findViewById(R.id.pforward_bt);
        pforward_bt.setOnClickListener(this);
        pforward_num_all = findViewById(R.id.pforward_num_all);
        pforward_num_all.setOnClickListener(this);
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_put_forward;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.pforward_bt:

                String openId = GreendaoUtil.instance().getOpenId();

                HashMap<String, String> map = new HashMap<>();
                map.put("uid", UserInfoUtil.instance().getId()+"");
                map.put("money",pforward_edit.getText().toString().trim());
                map.put("stock",stock+"");
                map.put("openid",openId);
                presenter.obtainTiXian(map);

                break;
            case R.id.pforward_num_all:

                pforward_edit.setText(stockNum+"");

                break;

            case R.id.pforward_record:

                RecordActivity.StartRecordActivity(this, 5);

                break;

            case R.id.withdrawal_back:

                finish();

                break;
        }
    }

    private void submit() {
        // validate
        String edit = pforward_edit.getText().toString().trim();
        if (TextUtils.isEmpty(edit)) {
            Toast.makeText(this, "edit不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
    }

    @Override
    public void showView(PutForwardEntity.DataBean data) {

        stockNum = data.getStockNum();
        stock = data.getStock();
        //设置股数
        pforward_num_tv.setText(stockNum + "");

    }

    @Override
    public void PfState(String state) {

        final Dialog dialog = new Dialog(this,R.style.CustomDialog);
        View view = LayoutInflater.from(this).inflate(R.layout.pforward_dialog, null);
        TextView pforward_dialog_state = view.findViewById(R.id.pforward_dialog_state);
        pforward_dialog_state.setText(state);
        TextView pforward_dialog_content = view.findViewById(R.id.pforward_dialog_content);
        pforward_dialog_content.setText("提现余额¥"+ed_num*stock+",(24小时到账)");
        Button pforward_dialog_bt = view.findViewById(R.id.pforward_dialog_bt);
        pforward_dialog_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.setContentView(view);
        dialog.show();
    }

    @Override
    public void onError(String error) {

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

        if (s.toString().trim().equals("")) {
            ed_num = 0.00;
        } else {
            ed_num = Double.valueOf(s.toString().trim());
        }

        if (ed_num > stockNum) {
            pforward_edit.setText(stockNum+"");
            ed_num = stockNum;
        }

        pforward_money_tv.setText("="+ed_num*stock+"元");

    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
