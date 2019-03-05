package com.zym.memorymaster.views.concrete_views;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;
import com.zym.memorymaster.R;
import com.zym.memorymaster.base.BaseFragment;
import com.zym.memorymaster.base.BaseModel;
import com.zym.memorymaster.models.BookInformation;
import com.zym.memorymaster.models.BookSelectorModel;
import com.zym.memorymaster.presenters.BookSelectorPresenter;
import com.zym.memorymaster.util.HttpUtil;
import com.zym.memorymaster.util.ImageUtil;
import com.zym.memorymaster.views.adapters.BookSelectorListAdapter;
import com.zym.memorymaster.views.adapters.BookSelectorPagerAdapter;
import com.zym.memorymaster.views.adapters.BookSelectorTurnAdapter;

import java.util.*;

/**
 * Created by 12390 on 2019/2/27.
 */
public class BookSelectorFragment extends BaseFragment implements View.OnClickListener{

    private BookSelectorPresenter bookSelectorPresenter;

    //最外层pager
    private ViewPager mPager;
    private BookSelectorPagerAdapter mPagerAdapter;
    private List<View> mPagers;

    //轮播图
    private List<ViewPager> mBookCityTurnList = new Vector<>();
    private List<BookSelectorTurnAdapter> mTurnAdapterList = new Vector<>();
    private List<ImageView> mDocImgs = new Vector<>();

    //end

    //内层listview

    private List<ListView> mBookCityListViewList = new ArrayList<>();
    private List<BookSelectorListAdapter> mBookCityListAdapterList = new ArrayList<>();

    private List<List<BookInformation>> mBookList = new Vector<>();


    //分类
    private List<TextView> mClassViews;
    int[] ids = {R.id.page_bookcity_class1, R.id.page_bookcity_class2,
            R.id.page_bookcity_class3};


    //当前页
    private Integer mCurrPage = 0;

    //每个轮播图的轮播图片数量
    private final Integer NUM_PER_TURN = 4;
    //总共的分类数
    private final Integer TOTAL_CATEGRIES = 3;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        bookSelectorPresenter = new BookSelectorPresenter();
        bookSelectorPresenter.attachView(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        updateAllData();
        super.onResume();
    }

    @Override
    public int getContentViewId() {
        return R.layout.fragment_book_selector;
    }

    @Override
    protected void initAllMembersView(View mRootView) {
        mPager = (ViewPager) mRootView.findViewById(R.id.book_selector_pager);

        mClassViews = new Vector<>();
        mPagers = new Vector<>();

        for (int i = 0; i < TOTAL_CATEGRIES; i++) {
            TextView tmpv = (TextView) mRootView.findViewById(ids[i]);
            tmpv.setOnClickListener(this);
            mClassViews.add(tmpv);
        }

        changeClassTextColor(0);



        for (int i = 0; i < TOTAL_CATEGRIES; i++) {

            initOnePage(i);
        }

        mPagerAdapter = new BookSelectorPagerAdapter(mContext, mPagers);
        mPager.setAdapter(mPagerAdapter);
        mPager.setOnPageChangeListener(new MyPagerListener());
    }

    private void initOnePage(int index){
        LinearLayout dotContainer;

        initData();

        //轮播图
        View vpView = LayoutInflater.from(mContext).inflate(R.layout.item_book_selector_turn, null);
        //每一页
        View pgView = LayoutInflater.from(mContext).inflate(R.layout.page_book_selector, null);
        //小圆点
        dotContainer = (LinearLayout) vpView.findViewById(R.id.book_selector_turn_dot);

        ViewPager bookSelectorTurn = (ViewPager) vpView.findViewById(R.id.book_selector_turn_pager);
        BookSelectorTurnAdapter turnAdapter = new BookSelectorTurnAdapter(mContext);
        turnAdapter.updateData(null);

        //每一页的listview

        ListView bookSelectorListView = (ListView) pgView.findViewById(R.id.page_book_selector_listview);
        bookSelectorListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showBookDetail(position - 1 + NUM_PER_TURN);
            }
        });
        BookSelectorListAdapter bookSelectorListAdapter =
                new BookSelectorListAdapter(mContext, R.layout.item_book_selector_list, mBookList.get(index));

        bookSelectorTurn.setAdapter(turnAdapter);
        bookSelectorListView.setAdapter(bookSelectorListAdapter);

        bookSelectorListView.addHeaderView(vpView);


        initDoc(dotContainer);

        bookSelectorTurn.setOnPageChangeListener(new MyTurnListener(bookSelectorTurn, NUM_PER_TURN));
        turnAdapter.setmListener(new BookSelectorTurnAdapter.LPagerImgClickListener() {
            @Override
            public void ImgClick(int position) {
                showBookDetail(position);
            }
        });


        //添加新的一页
        mBookCityTurnList.add(bookSelectorTurn);
        mBookCityListViewList.add(bookSelectorListView);
        mBookCityListAdapterList.add(bookSelectorListAdapter);
        mTurnAdapterList.add(turnAdapter);
        mPagers.add(pgView);

    }

    private void initDoc(LinearLayout dotContainer) {
        dotContainer.removeAllViews();


        for (int i = 0; i < NUM_PER_TURN; i++) {
            ImageView imageView = new ImageView(mContext);
            if (i == 0) {
                imageView.setImageResource(R.drawable.doc_select);
            } else {
                imageView.setImageResource(R.drawable.doc_select_off);
            }
            //添加到集合
            mDocImgs.add(imageView);
            //添加到线性布局
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(8, 0, 8, 0);
            dotContainer.addView(imageView, params);
        }
    }

    private void showBookDetail(int ind) {
        Intent intent = new Intent(getActivity(), BookDetailActivity.class);
        intent.putExtra("bookInformationId", mBookList.get(mCurrPage).get(ind).getBookInformationId());
        startActivityForResult(intent, 999);
    }

    private void initData() {
        initListViewData();

    }
    private void initListViewData() {
        List<BookInformation> bookInformations = new Vector<>();
        for (int i = 0; i < 5; i++) {
            BookInformation bookInformation = new BookInformation();
            bookInformations.add(bookInformation);
        }
        mBookList.add(bookInformations);

    }

    private void changeClassTextColor(int index){
        for (int i = 0; i < ids.length; i++) {
            if (i != index) {
                mClassViews.get(i).setTextColor(mContext.getResources().getColor(R.color.sys_font_gray));
            } else {
                mClassViews.get(i).setTextColor(mContext.getResources().getColor(R.color.sys_red));
            }
        }

    }


    private void updateAllData(){
        bookSelectorPresenter.getBooks(mCurrPage.toString());
    }

    @Override
    public void onActionSucc(BaseModel result) {
        int i = mBookList.get(mCurrPage).size();
        for (int j = 0; j < i; j++) {
            mBookList.get(mCurrPage).remove(0);
        }
        for (BookInformation bookInformation : ((BookSelectorModel) result).getBookInformations()) {
            mBookList.get(mCurrPage).add(bookInformation);
        }
        updatePageData();
    }

    private void updatePageData(){
        updateTurnData();

        final List<Bitmap> bitmaps = new Vector<>();
        for (int i = NUM_PER_TURN; i < mBookList.get(mCurrPage).size(); i++) {
            BookInformation bookInformation = mBookList.get(mCurrPage).get(i);
            Bitmap bitmap = ImageUtil.getHttpBitmap(HttpUtil.baseUri + bookInformation.getBookImgSrc());
            bitmaps.add(bitmap);
        }
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                updateListViewData(bitmaps);
                mTurnAdapterList.get(mCurrPage).notifyDataSetChanged();
                mPagerAdapter.notifyDataSetChanged();
            }
        });
    }

    private void updateListViewData(List<Bitmap> bitmaps){
        List<BookInformation> bookInformations = new Vector<>();
        for (int i = NUM_PER_TURN; i < mBookList.get(mCurrPage).size(); i++) {
            bookInformations.add(mBookList.get(mCurrPage).get(i));
        }
        BookSelectorListAdapter mBookCityListAdapter =
                new BookSelectorListAdapter(mContext, R.layout.item_book_selector_list, bookInformations);
        mBookCityListAdapter.setBitmaps(bitmaps);
        mBookCityListViewList.get(mCurrPage).setAdapter(mBookCityListAdapter);
    }

    private void updateTurnData(){
        List<BookInformation> bookInformations = new Vector<>();
        for (int i = 0; i < NUM_PER_TURN; i++) {
            bookInformations.add(mBookList.get(mCurrPage).get(i));
        }
        final List<ImageView> imageViews = new Vector<>();
        for (BookInformation bookInformation : bookInformations) {
            ImageView iv = new ImageView(mContext);
            iv.setScaleType(ImageView.ScaleType.CENTER_CROP);

            Bitmap bitmap = ImageUtil.getHttpBitmap(HttpUtil.baseUri + bookInformation.getBookImgSrc());
            iv.setImageBitmap(bitmap);
            imageViews.add(iv);
            mTurnAdapterList.get(mCurrPage).updateData(imageViews);

        }
    }

    @Override
    public void onClick(View v) {
        for (int i = 0; i < ids.length; i++) {
            if (ids[i] == v.getId()) {
                mPager.setCurrentItem(i);
                changeClassTextColor(i);
            }
        }

    }


    class MyPagerListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//            System.out.println(position);

        }

        @Override
        public void onPageSelected(int position) {
            changeClassTextColor(position);
            mCurrPage = position;
            updateAllData();
        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }
    }

    class MyTurnListener implements ViewPager.OnPageChangeListener {
        private Handler mTurnHandler;
        private Timer mTurnTimer;
        private TimerTask mTurnTask;
        private boolean mTurnIsTurning = false;
        private ViewPager __turn;

        int _size;

        MyTurnListener(ViewPager _turn, int size) {
            __turn = _turn;
            _size = size;
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            imgPlay(position, __turn);
        }

        @Override
        public void onPageSelected(int position) {

        }


        @Override
        public void onPageScrollStateChanged(int state) {


        }

        private void imgPlay(final int pos, final ViewPager turn) {
            if (!mTurnIsTurning) {
                mTurnIsTurning = true;
                mTurnTask = new TimerTask() {
                    @Override
                    public void run() {
                        Message msg = new Message();
                        msg.what = 1;
                        mTurnHandler.sendMessage(msg);
                    }
                };
                mTurnTimer = new Timer();
                mTurnTimer.schedule(mTurnTask, 3000, 3000);
                mTurnHandler = new Handler() {
                    @Override
                    public void handleMessage(Message msg) {
                        int currPos = (pos + 1) % _size;
                        turn.setCurrentItem(currPos);
                    }
                };
            } else {
                mTurnHandler.removeMessages(1);
                mTurnTimer.cancel();
                mTurnTask.cancel();
                mTurnIsTurning = false;
                imgPlay(pos % _size, turn);
            }

        }

    }

}
