package com.zym.memorymaster.views.concrete_views;

import android.Manifest;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;
import com.zym.memorymaster.R;
import com.zym.memorymaster.base.BaseActivity;

/**
 * Created by 12390 on 2019/2/26.
 */
public class HomeActivity extends BaseActivity implements View.OnClickListener{

    private TextView mTabReview;
    private TextView mTabBuy;
    private TextView mTabAdd;
    private TextView mTabPersonal;

    private FragmentTransaction transaction;
    private Fragment mReviewFragment,mAddFragment,mBuyFragment,mPersonalFragment;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initComponent();
        mSharedPreferences = getSharedPreferences(BaseActivity.SP_NAME, MODE_PRIVATE);

        requestAllPower();
        setStatusBar(getResources().getColor(R.color.sys_transparent));
    }

    @Override
    protected void initComponent() {
        transaction = getFragmentManager().beginTransaction();

        mTabPersonal = (TextView)this.findViewById(R.id.txt_personal);
        mTabBuy = (TextView)this.findViewById(R.id.txt_buy);
        mTabAdd = (TextView)this.findViewById(R.id.txt_add);
        mTabReview = (TextView)this.findViewById(R.id.txt_review);

        mTabReview.setSelected(true);
        mReviewFragment = new ReviewFragment();
        transaction.add(R.id.fragment_container,mReviewFragment);
        transaction.commit();

        mTabPersonal.setOnClickListener(this);
        mTabReview.setOnClickListener(this);
        mTabAdd.setOnClickListener(this);
        mTabBuy.setOnClickListener(this);
    }

    public void hideAllFragment(FragmentTransaction transaction){
        if(mReviewFragment!=null) {
            transaction.hide(mReviewFragment);
        }
        if(mAddFragment!=null) {
            transaction.hide(mAddFragment);
        }
        if(mBuyFragment!=null){
            transaction.hide(mBuyFragment);
        }
        if(mPersonalFragment!=null){
            transaction.hide(mPersonalFragment);
        }



    }
    public void selected(){
        mTabBuy.setSelected(false);
        mTabAdd.setSelected(false);
        mTabReview.setSelected(false);
        mTabPersonal.setSelected(false);
    }
    @Override
    public void onClick(View v) {
        transaction = getFragmentManager().beginTransaction();
        hideAllFragment(transaction);
        switch(v.getId()){
            case R.id.txt_review:
                selected();
                mTabReview.setSelected(true);
                if(mReviewFragment==null){
                    mReviewFragment = new ReviewFragment();
                    transaction.add(R.id.fragment_container,mReviewFragment);
                }else{
                    transaction.show(mReviewFragment);
                }
                break;

            case R.id.txt_add:
                selected();

                mTabAdd.setSelected(true);
                if(mAddFragment==null){
                    mAddFragment = new AddFragment();
                    transaction.add(R.id.fragment_container,mAddFragment);
                }else{
//                    ((BookcityFragment) mAddFragment).mUpdateData();
                    transaction.show(mAddFragment);
                }


                break;

            case R.id.txt_buy:
                selected();
                mTabBuy.setSelected(true);
                if(mBuyFragment==null){
                    mBuyFragment = new BuyFragment();
                    transaction.add(R.id.fragment_container,mBuyFragment);
                }else{
//                    ((ArticleFragment) mBuyFragment).mUpdateData();
                    transaction.show(mBuyFragment);
                }

                break;

            case R.id.txt_personal:
                selected();
                mTabPersonal.setSelected(true);
                if(mPersonalFragment==null){
                    mPersonalFragment = new PersonalFragment();
                    transaction.add(R.id.fragment_container,mPersonalFragment);
                }else{
                    transaction.show(mPersonalFragment);
                }
                break;
        }

        transaction.commit();
    }


    public void requestAllPower() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
            }
        }
    }
}
