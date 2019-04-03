package com.zym.memorymaster.base;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;
import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.zym.memorymaster.dao.greendao.DaoMaster;
import com.zym.memorymaster.dao.greendao.DaoSession;
import okhttp3.OkHttpClient;

/**
 * Created by 12390 on 2019/3/6.
 */
public class MyApplication extends Application {
    private static DaoSession daoSession;

    public MyApplication() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initGreenDao();
        initDBPlugin();
    }

    private void initDBPlugin() {

        Stetho.initializeWithDefaults(this);
        new OkHttpClient.Builder()
                .addNetworkInterceptor(new StethoInterceptor())
                .build();

    }

    private void initGreenDao() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(getApplicationContext(), "memorymaster.db");
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }


    public static DaoSession getDaoSession() {
        return daoSession;

    }
}

