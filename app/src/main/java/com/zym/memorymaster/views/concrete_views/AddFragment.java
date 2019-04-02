package com.zym.memorymaster.views.concrete_views;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import com.zym.memorymaster.R;
import com.zym.memorymaster.base.BaseFragment;
import com.zym.memorymaster.views.adapters.FragmentAddRVAdapter;

import java.util.List;
import java.util.Vector;

/**
 * Created by 12390 on 2019/2/27.
 */
public class AddFragment extends BaseFragment implements View.OnClickListener{
    private RecyclerView rv;
    private FragmentAddRVAdapter rvAdapter;
    private TextView btVAdd;
    private TextView btVDelete;
    @Override
    public int getContentViewId() {
        return R.layout.fragment_add;
    }

    @Override
    protected void initAllMembersView(View rootView) {
        rv = (RecyclerView) rootView.findViewById(R.id.fragment_add_rv);
        rvAdapter = new FragmentAddRVAdapter();
        rv.setAdapter(rvAdapter);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvAdapter.addItem();


        btVAdd = (TextView) rootView.findViewById(R.id.fragment_add_bt_v_add);
        btVAdd.setOnClickListener(this);

        btVDelete = (TextView) rootView.findViewById(R.id.fragment_add_bt_v_delete);
        btVDelete.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fragment_add_bt_v_add:
                addItemInRv();
                break;
            case R.id.fragment_add_bt_v_delete:
                deleteItemInRv();
        }
    }

    private void deleteItemInRv() {
        rvAdapter.deleteItem();
    }

    private void addItemInRv() {
        rvAdapter.addItem();
    }
}
