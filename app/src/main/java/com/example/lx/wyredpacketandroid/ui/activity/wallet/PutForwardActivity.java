package com.example.lx.wyredpacketandroid.ui.activity.wallet;

import android.app.Dialog;
import android.text.TextUtils;
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

public class PutForwardActivity extends BaseActivity implements View.OnClickListener {

    private ImageView withdrawal_back;
    private TextView pforward_record;
    private TextView pforward_card_tip;
    private TextView pforward_money_tv;
    private EditText pforward_edit;
    private RelativeLayout pforward_edit_layout;
    private TextView pforward_num_tv;
    private TextView pforward_tips_one;
    private Button pforward_bt;

    @Override
    protected void initData() {

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
        pforward_edit.setOnClickListener(this);
        pforward_edit_layout = (RelativeLayout) findViewById(R.id.pforward_edit_layout);
        pforward_edit_layout.setOnClickListener(this);
        pforward_num_tv = (TextView) findViewById(R.id.pforward_num_tv);
        pforward_num_tv.setOnClickListener(this);
        pforward_tips_one = (TextView) findViewById(R.id.pforward_tips_one);
        pforward_tips_one.setOnClickListener(this);
        pforward_bt = (Button) findViewById(R.id.pforward_bt);
        pforward_bt.setOnClickListener(this);
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_put_forward;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.pforward_bt:

                Dialog dialog = new Dialog(this,R.style.CustomDialog);
                View view = LayoutInflater.from(this).inflate(R.layout.pforward_dialog, null);
                dialog.setContentView(view);
                dialog.show();

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
}
