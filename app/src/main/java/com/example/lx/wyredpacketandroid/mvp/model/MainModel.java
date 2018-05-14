package com.example.lx.wyredpacketandroid.mvp.model;

import android.webkit.URLUtil;
import android.widget.Toast;

import com.example.lx.wyredpacketandroid.entity.GetPackEntity;
import com.example.lx.wyredpacketandroid.entity.GetpageEntity;
import com.example.lx.wyredpacketandroid.entity.LoginEntity;
import com.example.lx.wyredpacketandroid.entity.OpenPackEntity;
import com.example.lx.wyredpacketandroid.entity.OrderQueryEntity;
import com.example.lx.wyredpacketandroid.entity.UserInfoEntity;
import com.example.lx.wyredpacketandroid.entity.WXUserinfoEntity;
import com.example.lx.wyredpacketandroid.mvp.contract.MainContract;
import com.example.lx.wyredpacketandroid.ui.activity.MainActivity;
import com.example.lx.wyredpacketandroid.utils.GreendaoUtil;
import com.example.lx.wyredpacketandroid.utils.LogUtil;
import com.example.lx.wyredpacketandroid.utils.ToastUtil;
import com.example.lx.wyredpacketandroid.utils.UserInfoUtil;
import com.example.lx.wyredpacketandroid.utils.greendaoUtil.UserTokenDao;
import com.example.lx.wyredpacketandroid.utils.greendaoform.UserToken;
import com.example.lx.wyredpacketandroid.utils.networkutil.RetrofitUtil;
import com.example.lx.wyredpacketandroid.utils.networkutil.UrlUtil;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class MainModel implements MainContract.Model{

    @Override
    public void gainLogin(final MainContract.Presenter presenter, final String code) {

        Map<String, String> map = new HashMap<>();
        map.put("code", code);
        map.put("openid", "");
        map.put("access_token", "");
        map.put("refresh_token", "");

        RetrofitUtil.instance().postJson(UrlUtil.URL_LOGIN, map, LoginEntity.class, new Observer() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Object value) {

                LoginEntity entity = (LoginEntity) value;

                LogUtil.e("请求完毕");
                if (entity != null) {

                    UserTokenDao dao = GreendaoUtil.instance().getDao();

                    UserToken unique = dao.queryBuilder().build().unique();

                    if (unique != null) {
                        LogUtil.e("修改表单");
                        //修改表单
                        unique.setOpenid(entity.getData().getOpenid());
                        unique.setAccess_token(entity.getData().getAccess_token());
                        unique.setRefresh_token(entity.getData().getRefresh_token());
                        dao.update(unique);
                    } else {
                        LogUtil.e("增加表单");
                        //增加表单
                        UserToken userToken = new UserToken();
                        userToken.setOpenid(entity.getData().getOpenid());
                        userToken.setAccess_token(entity.getData().getAccess_token());
                        userToken.setRefresh_token(entity.getData().getRefresh_token());

                        dao.insert(userToken);
                    }

                    presenter.sendLogin();
                } else {
                    ToastUtil.showShortToast("登录失败" + code);

                }

            }

            @Override
            public void onError(Throwable e) {

                LogUtil.e("login失败："+e.toString());
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void gainToken(final MainContract.Presenter presenter) {

        UserTokenDao dao = GreendaoUtil.instance().getDao();

        UserToken unique = dao.queryBuilder().build().unique();

        if (unique != null) {

            Map<String, String> map = new HashMap<>();
            map.put("code", "");
            map.put("openid", unique.getOpenid());
            map.put("access_token", unique.getAccess_token());
            map.put("refresh_token", unique.getRefresh_token());

            RetrofitUtil.instance().postJson(UrlUtil.URL_LOGIN, map, LoginEntity.class, new Observer() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(Object value) {

                    LoginEntity entity = (LoginEntity) value;

                    if (entity.getErr_code().equals("404")) {
                        LogUtil.e("1");
                        presenter.sendTokenError();
                    } else {
                        LogUtil.e("2");
                        presenter.sendTokenSuccess();
                    }

                }

                @Override
                public void onError(Throwable e) {
                    LogUtil.e("失败"+e.toString());
                    presenter.sendTokenError();
                }

                @Override
                public void onComplete() {

                }
            });

        } else {
            LogUtil.e("3");
            presenter.sendTokenError();
        }
    }

    @Override
    public void gainUserInfo(final MainContract.MapPresenter presenter) {

        UserTokenDao dao = GreendaoUtil.instance().getDao();

        final UserToken unique = dao.queryBuilder().build().unique();

        HashMap<String, String> map = new HashMap<>();
        map.put("access_token", unique.getAccess_token());
        map.put("openid", unique.getOpenid());

        try {
            String url = UrlUtil.URL_WX+RetrofitUtil.Mosaic(UrlUtil.WX_USERINFO,map);
            LogUtil.e("aaa"+url);
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url(url).build();
            client.newCall(request).enqueue(new okhttp3.Callback() {
                @Override
                public void onFailure(okhttp3.Call call, IOException e) {

                }
                @Override
                public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {

                    WXUserinfoEntity entity = new Gson().fromJson(response.body().string(), WXUserinfoEntity.class);

                    if (entity != null) {

                        HashMap<String, String> map = new HashMap<>();
                        map.put("openid", unique.getOpenid());
                        map.put("unionid", entity.getUnionid());
                        map.put("nickname", entity.getNickname());
                        map.put("sex",  entity.getSex()+"");
                        map.put("headimgurl",  entity.getHeadimgurl());
                        map.put("country",  entity.getCountry());
                        map.put("province",  entity.getProvince());
                        map.put("city",  entity.getCity());

                        gainInfo(map,presenter);
                    }

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void gainGetPack(final MainContract.MapPresenter presenter, Map<String, String> map) {

        RetrofitUtil.instance().post(UrlUtil.GETPACK, map, GetPackEntity.class, new Observer() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Object value) {

                GetPackEntity entity = (GetPackEntity) value;
                if (entity != null) {

                    presenter.sendGetPack(entity.getData());
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void gainOpenPack(final MainContract.OpenPresenter presenter, Map<String, String> map) {

        RetrofitUtil.instance().post(UrlUtil.OPENPACK, map, OpenPackEntity.class, new Observer() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Object value) {

                OpenPackEntity entity = (OpenPackEntity) value;

                if (entity != null) {
                    if (entity.getErr_code().equals("200")) {
                        presenter.sendOpen(entity.getData());
                    } else {
                        presenter.sendError(entity.getReturn_msg());
                    }
                }
            }

            @Override
            public void onError(Throwable e) {
                LogUtil.e("失败"+e.toString());
                presenter.sendError(e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void gainOrder(final MainContract.Presenter presenter) {

        RetrofitUtil.instance().post(UrlUtil.ORDERQUERY, null, OrderQueryEntity.class, new Observer() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Object value) {

                OrderQueryEntity entity = (OrderQueryEntity) value;

                if (entity.getData() != null) {
                    presenter.sendOrder(entity.getData().getStatus());
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    public void gainInfo(HashMap<String, String> map, final MainContract.MapPresenter presenter){

        RetrofitUtil.instance().post(UrlUtil.USERINFO, map, UserInfoEntity.class, new Observer() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Object value) {

                UserInfoEntity entity = (UserInfoEntity) value;

                if (entity != null && entity.getData() != null) {


                    UserInfoEntity.DataBean data = entity.getData();
                    UserInfoUtil instance = UserInfoUtil.instance();
                    instance.setCity(data.getCity());
                    instance.setCountry(data.getCountry());
                    instance.setDistance(data.getDistance());
                    instance.setHeadimgurl(data.getHeadimgurl());
                    instance.setId(data.getId());
                    instance.setNickname(data.getNickname());
                    instance.setProvince(data.getProvince());
                    instance.setSex(data.getSex());

                    presenter.sendUserInfo(true);

                } else {

                    presenter.sendUserInfo(false);
                }

            }

            @Override
            public void onError(Throwable e) {

                presenter.sendUserInfo(false);
            }

            @Override
            public void onComplete() {

            }
        });
    }

}
