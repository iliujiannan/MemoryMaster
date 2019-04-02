package com.zym.memorymaster.views.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.zym.memorymaster.R;

import java.util.List;
import java.util.Vector;

/**
 * Created by 12390 on 2019/4/2.
 */
public class FragmentAddRVAdapter extends RecyclerView.Adapter<FragmentAddRVAdapter.MyViewHolder> {
    private List<String> titles;

    public FragmentAddRVAdapter(List<String> titles){
        this.titles = titles;
    }
    public FragmentAddRVAdapter(){}

    public void addItem(){
        if(titles==null)
            titles = new Vector<>();
        titles.add("第" + (titles.size()+1) + "天：");
        notifyItemInserted(titles.size()-1);
    }
    public void deleteItem(){
        if(titles==null||titles.size()==0)
            return;
        notifyItemRemoved(titles.size()-1);
        titles.remove(titles.size()-1);
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fragment_add_recycler, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.txTitle.setText(titles.get(position));
    }
    @Override
    public int getItemCount() {
        return titles.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView txTitle;
        MyViewHolder(View itemView) {
            super(itemView);
            txTitle = (TextView) itemView.findViewById(R.id.item_fragment_add_rv_title);
        }
    }
}
