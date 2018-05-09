package com.example.lx.wyredpacketandroid.mvp.contract;

import com.example.lx.wyredpacketandroid.base.BaseModel;
import com.example.lx.wyredpacketandroid.base.BasePresenter;
import com.example.lx.wyredpacketandroid.base.BaseView;
import com.example.lx.wyredpacketandroid.entity.GetPackEntity;
import com.example.lx.wyredpacketandroid.entity.OpenPackEntity;

import java.util.List;
import java.util.Map;

public interface MainContract {

    interface View extends BaseView {

        void showView();

        void Success(int state);

    }

    interface mapView extends BaseView {

        void showView(GetPackEntity.DataBean data);

    }

    interface openView extends BaseView {

        void showView(OpenPackEntity.DataBean data);

    }

    interface Model extends BaseModel {

        void gainLogin(Presenter presenter,String code);

        void gainToken(Presenter presenter);

        void gainUserInfo(MapPresenter presenter);

        void gainGetPack(MapPresenter presenter, Map<String,String> map);

        void gainOpenPack(OpenPresenter presenter,Map<String,String> map);

        void gainOrder(Presenter presenter);
    }

    abstract class Presenter extends BasePresenter<View, Model> {

        public abstract void obtainLogin(String code);

        public abstract void sendLogin();

        public abstract void obtainToken();

        public abstract void sendTokenSuccess();

        public abstract void sendTokenError();

        public abstract void sendError(String error);

        public abstract void obtainOrder();

        public abstract void sendOrder(int status);

    }


    abstract class MapPresenter extends BasePresenter<mapView, Model> {

        public abstract void obtainUserInfo();

        public abstract void obtainGetPack(Map<String,String> map);

        public abstract void sendGetPack(GetPackEntity.DataBean data);

    }

    abstract class OpenPresenter extends BasePresenter<openView, Model> {

        public abstract void obtainOpen(Map<String,String> map);

        public abstract void sendOpen(OpenPackEntity.DataBean data);

        public abstract void sendError(String error);

    }

}
