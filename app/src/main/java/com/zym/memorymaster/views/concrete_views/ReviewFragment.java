package com.zym.memorymaster.views.concrete_views;

import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.zym.memorymaster.R;
import com.zym.memorymaster.base.BaseFragment;
import com.zym.memorymaster.dao.entities.LocalBookContent;
import com.zym.memorymaster.util.DataBaseUtil;

import java.util.List;
import java.util.Vector;

/**
 * Created by 12390 on 2019/2/27.
 */
public class ReviewFragment extends BaseFragment implements View.OnClickListener{

    private TextView mWordTxt, mAnswerTxt, mShowBt, mRememberBt, mForgetBt, mHintTxt;

    private List<LocalBookContent> todayWordsList;
    private int currWordsIndex = 0;
    @Override
    public int getContentViewId() {
        return R.layout.fragment_review;
    }

    @Override
    protected void initAllMembersView(View mRootView) {
        mWordTxt = (TextView) mRootView.findViewById(R.id.fragment_review_txt_word);
        mAnswerTxt = (TextView) mRootView.findViewById(R.id.fragment_review_txt_answer);
        mShowBt = (TextView) mRootView.findViewById(R.id.fragment_review_bt_show);
        mShowBt.setOnClickListener(this);
        mRememberBt = (TextView) mRootView.findViewById(R.id.fragment_review_bt_remember);
        mRememberBt.setOnClickListener(this);
        mForgetBt = (TextView) mRootView.findViewById(R.id.fragment_review_bt_forget);
        mForgetBt.setOnClickListener(this);
        mHintTxt = (TextView) mRootView.findViewById(R.id.fragment_review_txt_hint);


    }

    private void initData() {
        todayWordsList = DataBaseUtil.getTodayWordsList();
        Log.i("ljn", todayWordsList.size()+"");
        toNextWords();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fragment_review_bt_show:
                mShowBt.setVisibility(View.GONE);
                break;
            case R.id.fragment_review_bt_remember:
                mShowBt.setVisibility(View.VISIBLE);
                toNextWords();
                break;
            case R.id.fragment_review_bt_forget:
                mShowBt.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    private void toNextWords(){

        if(todayWordsList.size()>currWordsIndex) {
            LocalBookContent content = todayWordsList.get(currWordsIndex);
            mWordTxt.setText(content.getContentA());
            mAnswerTxt.setText(content.getContentQ());
            mHintTxt.setText(content.getContentHint());
            currWordsIndex++;
        }
    }
}
