package com.zym.memorymaster.views.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.zym.memorymaster.R;
import com.zym.memorymaster.models.Book;

import java.util.List;

/**
 * Created by 12390 on 2018/8/15.
 */
public class BookSelectorListAdapter extends ArrayAdapter {
    List<Bitmap> bitmaps;
    int mResourceId;
    public BookSelectorListAdapter(Context context, int resource, List objects) {
        super(context, resource, objects);
        this.mResourceId = resource;

    }

    public void setBitmaps(List<Bitmap> bitmaps){
        this.bitmaps = bitmaps;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final Book book = (Book) getItem(position);


        View view = LayoutInflater.from(getContext()).inflate(mResourceId, null);//实例化一个对象
        TextView bookName = (TextView) view.findViewById(R.id.book_selector_list_title);//获取该布局内的文本视图
        TextView description = (TextView) view.findViewById(R.id.book_selector_list_description);
        TextView author = (TextView) view.findViewById(R.id.book_selector_author);
        TextView la = (TextView) view.findViewById(R.id.book_selector_list_load);
        final ImageView img = (ImageView) view.findViewById(R.id.book_selector_list_image);
        if(book.getBookName()!=null&&bitmaps!=null) {
            bookName.setText(book.getBookName());
            description.setText(book.getBookDescription());
            author.setText(book.getBookAuthor());
            img.setImageBitmap(bitmaps.get(position));
            if(book.getBookLoadingNum()==0){
                la.setText("0");
            }else {
                la.setText(book.getBookLoadingNum().toString());
            }


        }

        //set resource
        return view;
    }
}
