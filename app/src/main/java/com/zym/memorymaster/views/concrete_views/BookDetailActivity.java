package com.zym.memorymaster.views.concrete_views;

import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.zym.memorymaster.R;
import com.zym.memorymaster.base.BaseActivity;
import com.zym.memorymaster.base.BaseModel;
import com.zym.memorymaster.dao.entities.LocalBookContent;
import com.zym.memorymaster.dao.greendao.DaoSession;
import com.zym.memorymaster.models.BookDetailModel;
import com.zym.memorymaster.models.BookDownloadModel;
import com.zym.memorymaster.models.BookInformation;
import com.zym.memorymaster.presenters.BookDetailPresenter;
import com.zym.memorymaster.util.HttpUtil;
import com.zym.memorymaster.util.ImageUtil;
import com.zym.memorymaster.views.abstract_views.IBookDetailView;
import org.michaelevans.colorart.library.ColorArt;

import java.util.List;

/**
 * Created by 12390 on 2019/3/2.
 */
public class BookDetailActivity extends BaseActivity implements IBookDetailView, View.OnClickListener{
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
    private Integer mBookInformationId = -1;

    private BookInformation bookInformation;

    public static String[] labels = {"全部", "英语", "数学"};

    private BookDetailPresenter bookDetailPresenter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);
        bookDetailPresenter = new BookDetailPresenter();
        bookDetailPresenter.attachView(this);

        mSharedPreferences = getSharedPreferences(BaseActivity.SP_NAME, MODE_PRIVATE);
        userId = mSharedPreferences.getInt("userId", -1);
        secretKey = mSharedPreferences.getString("secretKey", "");
        mBookInformationId = getIntent().getIntExtra("bookInformationId", -1);
        initComponent();
    }

    @Override
    protected void initComponent() {
        mBackButton = (ImageView) findViewById(R.id.book_detail_back_bt);
        mTitle = (TextView) findViewById(R.id.book_detail_title);
        mAuthor = (TextView) findViewById(R.id.book_detail_author);
        mLabel = (TextView) findViewById(R.id.book_detail_lable);
        mCa = (TextView) findViewById(R.id.book_detail_ca);
        mPrice = (TextView) findViewById(R.id.book_detail_price);
        mLa = (TextView) findViewById(R.id.book_detail_la);
        mDes = (TextView) findViewById(R.id.book_detail_description);
        mBottBt = (TextView) findViewById(R.id.book_detail_bootombt);
        mBottImg = (ImageView) findViewById(R.id.book_detail_botoomic);
        mBott = (RelativeLayout) findViewById(R.id.book_detail_bott);
        mBookImg = (ImageView) findViewById(R.id.book_detail_bookimg);
        mTopBar = (RelativeLayout) findViewById(R.id.book_detail_top_bar);

        mBackButton.setOnClickListener(this);

        mBott.setOnClickListener(this);

        bookDetailPresenter.getBookDetail(mBookInformationId);
    }

    @Override
    public void onAddSucc(BaseModel result) {

        updateDBAfterAddBook(result);
        showMessage(result.getMsg());

    }

    private void updateDBAfterAddBook(BaseModel result){
        DaoSession daoSession = MainActivity.getDaoSession();
        List<LocalBookContent> bookContentList = ((BookDownloadModel)result).getBookContents();
        for (LocalBookContent content: bookContentList) {
            content.setBookContentId(null);
            daoSession.getLocalBookContentDao().insert(content);
        }


    }

    @Override
    public void onAddFailed(String msg){
        onActionFailed(msg);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.book_detail_back_bt:
                finish();
                break;
            case R.id.book_detail_bott:
                if (userId != -1) {
                    if (mBottBt.getText().toString().equals("背诵此书")) {
                        addBook(1);
                    } else {
                        addBook(0);
                    }
                } else {
                    showMessage("请先登录后再操作");
                }
                break;
        }
    }
    private void addBook(int flag) {
        checkPermission();
        bookDetailPresenter.addBook(userId, secretKey, mBookInformationId, flag);
    }
    @Override
    public void onActionSucc(BaseModel result) {
        bookInformation = ((BookDetailModel) result).getBookInformation();
        updateUiAfterGetBookDetail();
    }
    private void updateUiAfterGetBookDetail(){
        final Bitmap bitmap = ImageUtil.getHttpBitmap(HttpUtil.baseUri + bookInformation.getBookImgSrc());

        final ColorArt colorArt = new ColorArt(bitmap);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                mTitle.setText(bookInformation.getBookName());
                mAuthor.setText(bookInformation.getBookAuthor());
                mLabel.setText(labels[Integer.valueOf(bookInformation.getBookType())]);
                mCa.setText("共" + bookInformation.getBookChapterNum().toString() + "节");
                mPrice.setText("￥" + bookInformation.getBookPrice().toString());
                mLa.setText(bookInformation.getBookLoadingNum().toString() + "次下载");
                mBookImg.setImageBitmap(bitmap);
                mTopBar.setBackgroundColor(colorArt.getBackgroundColor());
                String des = bookInformation.getBookDescription();
                des.replaceAll(" ", "");
                if (des.length() > 200) {
                    des = bookInformation.getBookDescription().substring(0, 200) + "...";
                }

                mDes.setText(des);
                if (bookInformation.getBookPrice() == 0) {
                    mBottImg.setImageResource(R.drawable.ic_add2shelf);
                    mBottBt.setText("背诵此书");
                } else {
                    mBottImg.setImageResource(R.drawable.ic_shopcart_white);
                    mBottBt.setText("购买并背诵此书");
                }
            }
        });
    }

    private void checkPermission() {

        String msg;
        PackageManager pm = getPackageManager();
        boolean permission = (PackageManager.PERMISSION_GRANTED ==
                pm.checkPermission("android.permission.WRITE_EXTERNAL_STORAGE", "com.ljn.xiaoruireading"));
        if (!permission) {
            msg = "未获得文件读取权限，请添加权限后重新打开此应用";
            showMessage(msg);
            finish();
        }

    }
}
