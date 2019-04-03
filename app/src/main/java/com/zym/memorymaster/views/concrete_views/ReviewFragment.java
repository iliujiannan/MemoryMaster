package com.zym.memorymaster.views.concrete_views;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
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
public class ReviewFragment extends BaseFragment implements View.OnClickListener {

    private TextView mWordTxt, mAnswerTxt, mShowBt, mRememberBt, mForgetBt, mHintTxt;

    private ScrollView mScrollView;
    private List<LocalBookContent> mTodayWordsList;
    private int mCurrWordsIndex = -1;
    private ImageView mQuestionImage;

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

        mScrollView = (ScrollView) rootView.findViewById(R.id.fragment_review_scroll);

        mQuestionImage = (ImageView) rootView.findViewById(R.id.fragment_review_img);
    }

    private void initData() {
        mTodayWordsList = DataBaseUtil.getTodayWordsList();
        toNextWord();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
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
    public void onStart() {
        super.onStart();
        initData();
    }

    private void hideTopView() {
        if (mTodayWordsList.size() > mCurrWordsIndex) {
            mShowBt.setVisibility(View.GONE);
        }
    }

    private void toNextWord() {
        if (mTodayWordsList == null || mTodayWordsList.size() == 0) {
            showCompleted();
            return;
        }
        if (mCurrWordsIndex == -1) {
            showInit();
            mCurrWordsIndex++;
            LocalBookContent nextContent = mTodayWordsList.get(mCurrWordsIndex);
            updateViewAfterNextWord(nextContent);
            return;
        }
        if (mTodayWordsList.size() >= 2) {
            LocalBookContent content = mTodayWordsList.get(mCurrWordsIndex);
            updateDBAfterNextWord(content);
            mTodayWordsList.remove(content);

            LocalBookContent nextContent = mTodayWordsList.get(mCurrWordsIndex);
            updateViewAfterNextWord(nextContent);
            return;
        }
        if (mTodayWordsList.size() == 1) {
            LocalBookContent content = mTodayWordsList.get(mCurrWordsIndex);
            updateDBAfterNextWord(content);
            mTodayWordsList.remove(content);
            showCompleted();
        }

//        if(mCurrWordsIndex==-1){
//            if(mTodayWordsList.size()>0) {
//                showInit();
//                mCurrWordsIndex++;
//                LocalBookContent nextContent = mTodayWordsList.get(mCurrWordsIndex);
//                updateViewAfterNextWord(nextContent);
//            }else{
//                showCompleted();
//            }
//            return;
//        }
//
//        if(mTodayWordsList.size()>1) {
//            LocalBookContent content = mTodayWordsList.get(mCurrWordsIndex);
//            updateDBAfterNextWord(content);
//            mTodayWordsList.remove(content);
//
//            LocalBookContent nextContent = mTodayWordsList.get(mCurrWordsIndex);
//            updateViewAfterNextWord(nextContent);
//        }else if(mTodayWordsList.size()==1){
//            LocalBookContent content = mTodayWordsList.get(mCurrWordsIndex);
//            updateDBAfterNextWord(content);
//            mTodayWordsList.remove(content);
//            showCompleted();
//        }else{
//            showCompleted();
//        }
    }

    private void onForget() {
        LocalBookContent content = mTodayWordsList.get(mCurrWordsIndex);
        mTodayWordsList.remove(mCurrWordsIndex);
        mTodayWordsList.add(content);
        LocalBookContent nextContent = mTodayWordsList.get(mCurrWordsIndex);
        updateViewAfterNextWord(nextContent);

    }

    private void showInit() {
        mShowBt.setText(R.string.fragment_review_hintwords_init);
        mShowBt.setClickable(true);
    }

    private void showCompleted() {
        mCurrWordsIndex = -1;
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
        if (!content.getContentHint().equals("img")) {
            resumeWordPat();
            mWordTxt.setText(content.getContentA());
            mAnswerTxt.setText(content.getContentQ());
            mHintTxt.setText(content.getContentHint());

        } else {
            //展示图
            Bitmap bitmap = BitmapFactory.decodeFile(content.getContentQ());
            mQuestionImage.setImageBitmap(bitmap);
            imgPat();


        }
    }

    private void imgPat() {
        mWordTxt.setText("背图模式");
        mScrollView.setVisibility(View.GONE);
        mQuestionImage.setVisibility(View.VISIBLE);
        mShowBt.setVisibility(View.GONE);
    }

    private void resumeWordPat() {
        mScrollView.setVisibility(View.VISIBLE);
        mQuestionImage.setVisibility(View.GONE);
        mShowBt.setVisibility(View.VISIBLE);
    }
}
