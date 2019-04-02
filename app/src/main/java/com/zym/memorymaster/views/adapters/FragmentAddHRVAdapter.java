package com.zym.memorymaster.views.adapters;

import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.zym.memorymaster.R;
import com.zym.memorymaster.views.concrete_views.AddFragment;

import java.util.List;
import java.util.Vector;

/**
 * Created by 12390 on 2019/4/2.
 */
public class FragmentAddHRVAdapter extends RecyclerView.Adapter<FragmentAddHRVAdapter.VH> {
    private List<String> imgPaths;
    private int pos;
    public FragmentAddHRVAdapter(int pos){
        imgPaths = new Vector<>();
        this.pos = pos;
    }
    public FragmentAddHRVAdapter(List<String> imgPaths){
        this.imgPaths = imgPaths;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fragment_add_item_h_rv, parent, false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        holder.imageView.setImageBitmap(BitmapFactory.decodeFile(imgPaths.get(position)));
        holder.imageView.setTag(position);
        holder.imageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                deleteItem((Integer) v.getTag());
                return false;
            }
        });
    }
    public void addItem(String imgPath){
        if(imgPaths==null)
            imgPaths = new Vector<>();
        if(imgPaths.size()>=4)
            return;
        imgPaths.add(imgPath);
        notifyItemInserted(imgPaths.size()-1);
    }
    public void deleteItem(int tag){
        notifyItemRemoved(tag);
        imgPaths.remove(tag);
        AddFragment.paths.get(pos).remove(tag);
    }

    @Override
    public int getItemCount() {
        return imgPaths.size();
    }

    static class VH extends RecyclerView.ViewHolder{
        ImageView imageView;
        VH(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.item_fragment_add_h_rv_img);
        }
    }

}
