package com.example.lx.wyredpacketandroid.utils.customview;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.amap.api.maps.model.Marker;
import com.bumptech.glide.Glide;
import com.example.lx.wyredpacketandroid.R;
import com.example.lx.wyredpacketandroid.entity.GetPackEntity;
import com.example.lx.wyredpacketandroid.entity.OpenPackEntity;
import com.example.lx.wyredpacketandroid.mvp.contract.MainContract;
import com.example.lx.wyredpacketandroid.mvp.presenter.MainPresenter;
import com.example.lx.wyredpacketandroid.mvp.presenter.OpenPresenter;
import com.example.lx.wyredpacketandroid.ui.activity.StartActivity;
import com.example.lx.wyredpacketandroid.utils.LogUtil;
import com.example.lx.wyredpacketandroid.utils.ToastUtil;
import com.example.lx.wyredpacketandroid.utils.UserInfoUtil;

import java.util.HashMap;
import java.util.List;


public class OpenDialogUtil implements View.OnClickListener, PasswordView.PasswordListener ,MainContract.openView{

    private static OpenDialogUtil dialogUtil;
    private View view;
    private ImageView open_pack_deleter_dialog, open_pack_bt_dialog;
    private ImageView open_pack_icon_dialog;
    private TextView open_pack_title_dialog;
    private TextView open_pack_content_dialog;
    private ImageView open_encr_deleter_dialog;
    private ImageView open_encr_icon_dialog;
    private TextView open_encr_title_dialog;
    private PasswordView open_encr_password_dialog;
    private TextView open_encr_time_dialog;
    private Dialog dialog;
    private HashMap<String, String> map;
    private OpenPresenter presenter;
    private Context context;
    private Marker marker;
    private openListner openListner;

    private OpenDialogUtil() {
    }

    public static OpenDialogUtil instance() {

        if (dialogUtil == null) {
            synchronized (OpenDialogUtil.class) {
                if (dialogUtil == null) {
                    dialogUtil = new OpenDialogUtil();
                }
            }
        }
        return dialogUtil;
    }


    public void showDialog(Context context, GetPackEntity.DataBean entity, Marker marker) {

        this.context = context;
        this.marker = marker;

        if (entity.isSecret()) {

            view = LayoutInflater.from(context).inflate(R.layout.open_encr_pack_dialog, null);

            open_encr_deleter_dialog = view.findViewById(R.id.open_encr_deleter_dialog);
            open_encr_deleter_dialog.setOnClickListener(this);
            open_encr_icon_dialog = view.findViewById(R.id.open_encr_icon_dialog);
            open_encr_title_dialog = view.findViewById(R.id.open_encr_title_dialog);
            open_encr_password_dialog = view.findViewById(R.id.open_encr_password_dialog);
            open_encr_password_dialog.setPasswordListener(this);
            open_encr_time_dialog = view.findViewById(R.id.open_encr_time_dialog);

            Glide.with(context).load(entity.getLogo()).into(open_encr_icon_dialog);

            open_encr_title_dialog.setText(entity.getOwner());

//            DateUtil dateUtil = new DateUtil();
//            String s = dateUtil.timesOne(entity.getCreated_at());
//            LogUtil.e(s+"111111");

        } else {

            view = LayoutInflater.from(context).inflate(R.layout.open_ord_pack_dialog, null);

            open_pack_deleter_dialog = view.findViewById(R.id.open_pack_deleter_dialog);
            open_pack_deleter_dialog.setOnClickListener(this);
            open_pack_icon_dialog = view.findViewById(R.id.open_pack_icon_dialog);
            open_pack_title_dialog = view.findViewById(R.id.open_pack_title_dialog);
            open_pack_content_dialog = view.findViewById(R.id.open_pack_content_dialog);
            open_pack_bt_dialog = view.findViewById(R.id.open_pack_bt_dialog);
            open_pack_bt_dialog.setOnClickListener(this);

            Glide.with(context).load(entity.getLogo()).into(open_pack_icon_dialog);

            open_pack_title_dialog.setText(entity.getOwner());

        }
        presenter = new OpenPresenter(this);
        dialog = new Dialog(context, R.style.CustomDialog);
        dialog.setContentView(view);
        dialog.show();

        map = new HashMap<>();
        map.put("uid", UserInfoUtil.instance().getId()+"");
        map.put("pack_id", entity.getId()+"");

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.open_pack_deleter_dialog:

                dialog.dismiss();

                break;

            case R.id.open_encr_deleter_dialog:

                dialog.dismiss();

                break;

            case R.id.open_pack_bt_dialog:

                presenter.obtainOpen(map);

                break;
        }
    }

    @Override
    public void passwordChange(String changeText) {

    }

    //输入完成
    @Override
    public void passwordComplete() {

        map.put("secret", open_encr_password_dialog.getPassword()+"");

        presenter.obtainOpen(map);

    }

    @Override
    public void keyEnterPress(String password, boolean isComplete) {

    }

    @Override
    public void onError(String error) {

        if (openListner != null) {
            dialog.dismiss();
            openListner.openError(error,marker);
        }
    }

    @Override
    public void showView(OpenPackEntity.DataBean data) {

        if (openListner != null) {
            dialog.dismiss();
            openListner.openSuccess(data,marker);
        }
    }

    public void setOpenListner(openListner openListner){
        this.openListner = openListner;
    }

    public interface openListner{

        void openSuccess(OpenPackEntity.DataBean data,Marker marker);

        void openError(String error, Marker marker);
    }
}
