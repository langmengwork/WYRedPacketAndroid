package com.example.lx.wyredpacketandroid.ui.activity.record.mvp.presenter;

import com.example.lx.wyredpacketandroid.ui.activity.record.entity.RecordFiveEntity;
import com.example.lx.wyredpacketandroid.ui.activity.record.entity.RecordFourEntity;
import com.example.lx.wyredpacketandroid.ui.activity.record.entity.RecordOneEntity;
import com.example.lx.wyredpacketandroid.ui.activity.record.entity.RecordThreeEntity;
import com.example.lx.wyredpacketandroid.ui.activity.record.entity.RecordTwoEntity;
import com.example.lx.wyredpacketandroid.ui.activity.record.mvp.contract.RecordContract;
import com.example.lx.wyredpacketandroid.ui.activity.record.mvp.model.RecordModel;

import java.util.Map;

public class RecordPresenter extends RecordContract.Presenter {

    public RecordPresenter(RecordContract.View view) {

        mView = view;
        mModel = new RecordModel();
    }

    @Override
    public void obtainRecord(Map<String, String> map) {

        String type = map.get("type");

        if (type.equals("1")) {
            mModel.gainRecordOne(this,map);
        }else if (type.equals("2")) {
            mModel.gainRecordTwo(this, map);
        }else if (type.equals("3")) {
            mModel.gainRecordThree(this, map);
        }else if (type.equals("4")) {
            mModel.gainRecordFour(this, map);
        }else if (type.equals("5")) {
            mModel.gainRecordFive(this, map);
        }
    }

    @Override
    public void sendLogin(RecordOneEntity.DataBean oEntity, RecordTwoEntity.DataBean tEntity, RecordThreeEntity.DataBean thEntity, RecordFourEntity.DataBean fEntity, RecordFiveEntity.DataBean fvEntity) {

        mView.showView(oEntity,tEntity,thEntity,fEntity,fvEntity);
    }

}
