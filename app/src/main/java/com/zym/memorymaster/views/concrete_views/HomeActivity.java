package com.zym.memorymaster.views.concrete_views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import com.zym.memorymaster.R;
import com.zym.memorymaster.base.BaseActivity;

/**
 * Created by 12390 on 2019/2/26.
 */
public class HomeActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    @Override
    protected void initComponent() {
    }
}
