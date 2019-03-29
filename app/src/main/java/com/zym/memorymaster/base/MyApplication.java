package com.zym.memorymaster.base;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;
import com.zym.memorymaster.dao.greendao.DaoMaster;
import com.zym.memorymaster.dao.greendao.DaoSession;

/**
 * Created by 12390 on 2019/3/6.
 */
public class MyApplication extends Application {
    private static DaoSession daoSession;

    public MyApplication(){
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initGreenDao();
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
