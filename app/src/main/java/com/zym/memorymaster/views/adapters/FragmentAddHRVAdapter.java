package com.zym.memorymaster.views.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.zym.memorymaster.R;

import java.util.List;
import java.util.Vector;

/**
 * Created by 12390 on 2019/4/2.
 */
public class FragmentAddHRVAdapter extends RecyclerView.Adapter<FragmentAddHRVAdapter.VH> {
    private List<Integer> imgSrcs;
    public FragmentAddHRVAdapter(){
        imgSrcs = new Vector<>();
    }
    public FragmentAddHRVAdapter(List<Integer> imgSrcs){
        this.imgSrcs = imgSrcs;
    }
    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fragment_add_item_h_rv, parent, false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        holder.imageView.setImageResource(R.drawable.bg_sys_head);
        holder.imageView.setTag(position);
        holder.imageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                deleteItem((Integer) v.getTag());
                return false;
            }
        });
    }
    public void addItem(){
        if(imgSrcs==null)
            imgSrcs = new Vector<>();
        if(imgSrcs.size()>=4)
            return;
        imgSrcs.add(imgSrcs.size());
        notifyItemInserted(imgSrcs.size()-1);
    }
    public void deleteItem(Integer tag){
        notifyItemRemoved(tag);
        imgSrcs.remove(tag);
    }

    @Override
    public int getItemCount() {
        return imgSrcs.size();
    }

    static class VH extends RecyclerView.ViewHolder{
        ImageView imageView;
        VH(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.item_fragment_add_h_rv_img);
        }
    }

}
