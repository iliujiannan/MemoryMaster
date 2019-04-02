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
    private int currWordsIndex = -1;
    @Override
    public int getContentViewId() {
        return R.layout.fragment_review;
    }

    @Override
    protected void initAllMembersView(View rootView) {
        mWordTxt = (TextView) rootView.findViewById(R.id.fragment_review_txt_word);
        mAnswerTxt = (TextView) rootView.findViewById(R.id.fragment_review_txt_answer);
        mShowBt = (TextView) rootView.findViewById(R.id.fragment_review_bt_show);
        mShowBt.setOnClickListener(this);
        mRememberBt = (TextView) rootView.findViewById(R.id.fragment_review_bt_remember);
        mRememberBt.setOnClickListener(this);
        mForgetBt = (TextView) rootView.findViewById(R.id.fragment_review_bt_forget);
        mForgetBt.setOnClickListener(this);
        mHintTxt = (TextView) rootView.findViewById(R.id.fragment_review_txt_hint);


    }

    private void initData() {
        todayWordsList = DataBaseUtil.getTodayWordsList();
        toNextWord();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fragment_review_bt_show:
                hideTopView();
                break;
            case R.id.fragment_review_bt_remember:
                toNextWord();
                break;
            case R.id.fragment_review_bt_forget:
                onForget();
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    private void hideTopView(){
        if(todayWordsList.size()>currWordsIndex) {
            mShowBt.setVisibility(View.GONE);
        }
    }
    private void toNextWord(){
        if(currWordsIndex==-1){
            if(todayWordsList.size()>0) {
                showInit();
                currWordsIndex++;
                LocalBookContent nextContent = todayWordsList.get(currWordsIndex);
                updateViewAfterNextWord(nextContent);
            }else{
                showCompleted();
            }
            return;
        }

        if(todayWordsList.size()>1) {
            LocalBookContent content = todayWordsList.get(currWordsIndex);
            updateDBAfterNextWord(content);
            todayWordsList.remove(content);

            LocalBookContent nextContent = todayWordsList.get(currWordsIndex);
            updateViewAfterNextWord(nextContent);
        }else {
            showCompleted();
        }
    }

    private void onForget(){
        LocalBookContent content = todayWordsList.get(currWordsIndex);
        todayWordsList.remove(currWordsIndex);
        todayWordsList.add(content);
        LocalBookContent nextContent = todayWordsList.get(currWordsIndex);
        updateViewAfterNextWord(nextContent);

    }

    private void showInit() {
        mShowBt.setText(R.string.fragment_review_hintwords_init);
        mShowBt.setClickable(true);
    }

    private void showCompleted(){
        currWordsIndex = -1;
        mShowBt.setVisibility(View.VISIBLE);
        mShowBt.setText(R.string.fragment_review_hintwords_completed);
        mShowBt.setClickable(false);
        mWordTxt.setText(R.string.fragment_review_words_completed);
    }
    private void updateDBAfterNextWord(LocalBookContent content) {
        //更新数据库
        DataBaseUtil.updateDBAfterRemembered(content);
    }

    private void updateViewAfterNextWord(LocalBookContent content) {
        if(content.getContentHint()!="img") {
            mWordTxt.setText(content.getContentA());
            mAnswerTxt.setText(content.getContentQ());
            mHintTxt.setText(content.getContentHint());
            mShowBt.setVisibility(View.VISIBLE);
        }else{
            //展示图
        }
    }

}
