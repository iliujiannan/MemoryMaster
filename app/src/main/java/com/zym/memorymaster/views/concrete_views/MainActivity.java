package com.zym.memorymaster.views.concrete_views;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import com.zym.memorymaster.R;
import com.zym.memorymaster.base.BaseActivity;
import com.zym.memorymaster.dao.greendao.DaoMaster;
import com.zym.memorymaster.dao.greendao.DaoSession;


/**
 * Created by 12390 on 2018/8/9.
 */
public class MainActivity extends BaseActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                mSharedPreferences = getSharedPreferences(SP_NAME, MODE_PRIVATE);

                if (mSharedPreferences.getBoolean("isFirst", true)) {
                    mSharedPreferences.edit().putBoolean("isFirst", false).commit();
                    startActivity(new Intent(MainActivity.this, FirstWelcomActivity.class));
                    finish();
                } else {
                    startActivity(new Intent(MainActivity.this, HomeActivity.class));
                    finish();
                }
            }
        }, 3000);


    }

    @Override
    protected void initComponent() {
    }



}
