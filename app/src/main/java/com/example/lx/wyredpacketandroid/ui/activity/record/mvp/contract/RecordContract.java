package com.example.lx.wyredpacketandroid.ui.activity.record.mvp.contract;

import com.example.lx.wyredpacketandroid.base.BaseModel;
import com.example.lx.wyredpacketandroid.base.BasePresenter;
import com.example.lx.wyredpacketandroid.base.BaseView;
import com.example.lx.wyredpacketandroid.ui.activity.record.entity.RecordFiveEntity;
import com.example.lx.wyredpacketandroid.ui.activity.record.entity.RecordFourEntity;
import com.example.lx.wyredpacketandroid.ui.activity.record.entity.RecordOneEntity;
import com.example.lx.wyredpacketandroid.ui.activity.record.entity.RecordThreeEntity;
import com.example.lx.wyredpacketandroid.ui.activity.record.entity.RecordTwoEntity;

import java.util.Map;


public interface RecordContract {

    interface View extends BaseView {

        void showView(RecordOneEntity.DataBean oEntity, RecordTwoEntity.DataBean tEntity, RecordThreeEntity.DataBean thEntity, RecordFourEntity.DataBean fEntity, RecordFiveEntity.DataBean fvEntity);

    }

    interface Model extends BaseModel {

        void gainRecordOne(Presenter presenter, Map<String, String> map);

        void gainRecordTwo(Presenter presenter, Map<String, String> map);

        void gainRecordThree(Presenter presenter, Map<String, String> map);

        void gainRecordFour(Presenter presenter, Map<String, String> map);

        void gainRecordFive(Presenter presenter, Map<String, String> map);

    }


    abstract class Presenter extends BasePresenter<View, Model> {

        public abstract void obtainRecord(Map<String, String> map);

        public abstract void sendLogin(RecordOneEntity.DataBean oEntity, RecordTwoEntity.DataBean tEntity, RecordThreeEntity.DataBean thEntity, RecordFourEntity.DataBean fEntity, RecordFiveEntity.DataBean fvEntity);

    }
}
