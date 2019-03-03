package com.zym.memorymaster.views.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.zym.memorymaster.util.BookSelectorViewUtil;


import java.util.ArrayList;
import java.util.List;
import java.util.Vector;


/**
 * Created by 12390 on 2018/8/15.
 */

public class BookSelectorTurnAdapter extends PagerAdapter {

    private Context ctx;
    private List<View> mTurnViews;
    private boolean isFirst = true;

    public void setmListener(LPagerImgClickListener mListener) {
        this.mListener = mListener;
    }

    LPagerImgClickListener mListener;

    public BookSelectorTurnAdapter(Context ctx) {
        this.ctx = ctx;
        this.mTurnViews = new ArrayList<>();


    }

    public void updateData(List<ImageView> imageViews){
        if(isFirst){
            firstInit();
            isFirst = false;
        }else {

            int ind = 0;
            for (ImageView img: imageViews) {
                if(ind<mTurnViews.size()){
                    mTurnViews.set(ind++, img);
                }else{
                    mTurnViews.add(img);
                }
            }
        }
    }

    private void firstInit(){
        String[] array = BookSelectorViewUtil.listAssets(ctx);
        List<String> realList = new Vector<>();
        for (int i = 0; i < array.length; i++) {
            String temp = array[i];
            if (isLegle(temp)) {
                realList.add(temp);
            }
        }
        for (String name : realList) {
            ImageView iv = new ImageView(ctx);
            iv.setScaleType(ImageView.ScaleType.CENTER_CROP);

            Bitmap bitmap = BookSelectorViewUtil.readCover(name, ctx);

            iv.setImageBitmap(bitmap);

            mTurnViews.add(iv);
        }
    }
    private boolean isLegle(String temp) {
        if (temp.contains("article")) {
            return true;
        }
        return false;
    }
    @Override
    public int getCount() {
        return mTurnViews.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        container.addView(mTurnViews.get(position));
        mTurnViews.get(position).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(ctx, "点击了" + position, Toast.LENGTH_SHORT).show();
                if (mListener != null) {
                    mListener.ImgClick(position);
                }
            }
        });
        return mTurnViews.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mTurnViews.get(position));

    }

    public interface LPagerImgClickListener {
        void ImgClick(int position);
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

}
