package com.example.lx.wyredpacketandroid.ui.activity.packdetails.mvp.contract;

import com.example.lx.wyredpacketandroid.base.BaseModel;
import com.example.lx.wyredpacketandroid.base.BasePresenter;
import com.example.lx.wyredpacketandroid.base.BaseView;
import com.example.lx.wyredpacketandroid.ui.activity.packdetails.entity.MessageListEntity;
import com.example.lx.wyredpacketandroid.ui.activity.packdetails.entity.SLReplyEntity;

import java.util.List;
import java.util.Map;

public interface DetailsContract {

    interface View extends BaseView {

        void showLoadMore(List<MessageListEntity.DataBean> data);

        void showAddReply(String msg);

        void showPraise(int praiseNum);

    }

    interface ReplyView extends BaseView {

        void shwoView(SLReplyEntity.DataBean data);

        void showAddReplyDetail(String msg);

    }

    interface Model extends BaseModel {

        void gainLoadMore(Presenter presenter, Map<String, String> map);

        void gainAddReply(Presenter presenter, Map<String, String> map);

        void gainAddReply(ReplyPresenter presenter, Map<String, String> map);

        void gainPraise(Presenter presenter, Map<String, String> map);

        void gainReplyDetail(ReplyPresenter presenter, Map<String, String> map);
    }

    abstract class Presenter extends BasePresenter<View, Model> {

        public abstract void obtainLoadMore(Map<String, String> map);

        public abstract void sendLoadMore(List<MessageListEntity.DataBean> data);

        public abstract void obtainAddReply(Map<String, String> map);

        public abstract void sendAddReply(String msg);

        public abstract void obtainPraise(Map<String, String> map);

        public abstract void sendPraise(int praiseNum);

    }

    abstract class ReplyPresenter extends BasePresenter<ReplyView, Model> {

        public abstract void obtainReplyDetail(Map<String, String> map);

        public abstract void sendReplyDetail(SLReplyEntity.DataBean data);

        public abstract void obtainAddReplyDetail(Map<String, String> map);

        public abstract void sendAddReplyDetail(String msg);

    }
}
