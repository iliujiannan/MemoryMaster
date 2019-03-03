package com.zym.memorymaster.views.concrete_views;

import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.zym.memorymaster.base.BaseActivity;
import com.zym.memorymaster.models.Book;

/**
 * Created by 12390 on 2019/3/2.
 */
public class BookDetailActivity extends BaseActivity {
    private ImageView mBackButton;
    private TextView mTitle;
    private TextView mAuthor;
    private TextView mLabel;
    private TextView mCa;
    private TextView mPrice;
    private TextView mLa;
    private TextView mDes;
    private TextView mBottBt;
    private ImageView mBottImg;
    private ImageView mBookImg;
    private RelativeLayout mTopBar;

    private RelativeLayout mBott;

    private Integer userId = -1;
    private String secretKey = "";
    private Integer mBookId = -1;

    private Book book;

    public static String[] labels = {"全部", "英语", "数学"};

    private BookDetailPresenter bookDetailPresenter;
    @Override
    protected void initComponent() {

    }
}
