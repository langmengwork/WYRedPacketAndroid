package com.example.lx.wyredpacketandroid.utils;

import com.example.lx.wyredpacketandroid.base.MainApplication;
import com.example.lx.wyredpacketandroid.utils.greendaoUtil.DaoMaster;
import com.example.lx.wyredpacketandroid.utils.greendaoUtil.DaoSession;
import com.example.lx.wyredpacketandroid.utils.greendaoUtil.UserTokenDao;
import com.example.lx.wyredpacketandroid.utils.greendaoform.UserToken;

public class GreendaoUtil {

    private static GreendaoUtil greendaoUtil;
    private UserTokenDao dao;

    private GreendaoUtil() {
    }

    public static GreendaoUtil instance() {

        if (greendaoUtil == null) {
            synchronized (GreendaoUtil.class) {
                if (greendaoUtil == null) {
                    greendaoUtil = new GreendaoUtil();
                }
            }
        }
        return greendaoUtil;
    }

    public UserTokenDao getDao(){

        if (dao == null) {
            DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(MainApplication.activity, "user.db", null);
            DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDb());
            DaoSession daoSession = daoMaster.newSession();
            dao = daoSession.getUserTokenDao();
        }

        return dao;
    }

    public String getOpenId() {

        if (dao == null) {
            getDao();
        }

        UserToken unique = dao.queryBuilder().build().unique();
        String openid = unique.getOpenid();

        return openid;
    }

    public void exitLogin() {

        if (dao == null) {
            getDao();
        }

        dao.deleteAll();
    }
}
