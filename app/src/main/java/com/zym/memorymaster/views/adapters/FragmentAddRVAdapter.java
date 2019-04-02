package com.zym.memorymaster.views.adapters;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.zym.memorymaster.R;
import com.zym.memorymaster.views.concrete_views.AddFragment;

import java.util.List;
import java.util.Vector;

import static com.zym.memorymaster.views.concrete_views.AddFragment.tempPosition;

/**
 * Created by 12390 on 2019/4/2.
 */
public class FragmentAddRVAdapter extends RecyclerView.Adapter<FragmentAddRVAdapter.MyViewHolder> {
    private List<String> titles;
    private AddFragment addFragment;
    public static final int CHOOSE_PHOTO = 2;

    private MyViewHolder tempHolder = null;
    public FragmentAddRVAdapter(List<String> titles, AddFragment addFragment){
        this.addFragment = addFragment;
        this.titles = titles;
    }
    public FragmentAddRVAdapter(AddFragment addFragment){
        titles = new Vector<>();
        this.addFragment = addFragment;
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


    private void initViewHolder(final MyViewHolder holder, final int position){
        holder.txTitle.setText(titles.get(position));
        holder.btHAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tempHolder = holder;
                tempPosition = position;
                selectPhotos();
            }
        });
        holder.hrvAdapter = new FragmentAddHRVAdapter(position);
        holder.hRv.setAdapter(holder.hrvAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(addFragment.getActivity(), LinearLayoutManager.HORIZONTAL, false);
        holder.hRv.setLayoutManager(layoutManager);
    }

    private void selectPhotos(){
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        addFragment.startActivityForResult(intent, CHOOSE_PHOTO);
    }
    public void onPhotoSelected(String imagePath){
        tempHolder.hrvAdapter.addItem(imagePath);
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
