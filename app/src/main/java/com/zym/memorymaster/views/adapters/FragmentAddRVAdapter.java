package com.zym.memorymaster.views.adapters;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
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
    private Context context;

    public FragmentAddRVAdapter(List<String> titles, Context context){
        this.context = context;
        this.titles = titles;
    }
    public FragmentAddRVAdapter(Context context){
        titles = new Vector<>();
        this.context = context;
    }

    public void addItem(){
        if(titles==null)
            titles = new Vector<>();
        titles.add("第" + (titles.size()+1) + "天：");
        notifyItemInserted(titles.size()-1);
    }
    public void deleteItem(){
        if(titles==null||titles.size()<=1)
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
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        initViewHolder(holder, position);
    }

    private void initViewHolder(final MyViewHolder holder, int position){
        holder.txTitle.setText(titles.get(position));
        holder.btHAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.hrvAdapter.addItem();
            }
        });
        holder.hrvAdapter = new FragmentAddHRVAdapter();
        holder.hRv.setAdapter(holder.hrvAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        holder.hRv.setLayoutManager(layoutManager);
    }
    @Override
    public int getItemCount() {
        return titles.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView txTitle;
        TextView btHAdd;
        RecyclerView hRv;
        FragmentAddHRVAdapter hrvAdapter;
        MyViewHolder(View itemView) {
            super(itemView);
            txTitle = (TextView) itemView.findViewById(R.id.item_fragment_add_rv_title);
            btHAdd = (TextView) itemView.findViewById(R.id.fragment_add_bt_h_add);
            hRv = (RecyclerView) itemView.findViewById(R.id.fragment_add_item_h_rv);
        }
    }
}
