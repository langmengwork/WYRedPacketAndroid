package com.example.lx.wyredpacketandroid.utils;

import com.example.lx.wyredpacketandroid.base.MainApplication;
import com.example.lx.wyredpacketandroid.utils.greendaoUtil.DaoMaster;
import com.example.lx.wyredpacketandroid.utils.greendaoUtil.DaoSession;
import com.example.lx.wyredpacketandroid.utils.greendaoUtil.UserTokenDao;

public class GreendaoUtil {

    private static GreendaoUtil greendaoUtil;

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

        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(MainApplication.activity, "user.db", null);
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDb());
        DaoSession daoSession = daoMaster.newSession();
        return daoSession.getUserTokenDao();
    }
}
