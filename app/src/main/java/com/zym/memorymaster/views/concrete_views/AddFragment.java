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
    private RecyclerView vRv;
    private FragmentAddRVAdapter vRvAdapter;
    private TextView btVAdd;
    private TextView btVDelete;
    @Override
    public int getContentViewId() {
        return R.layout.fragment_add;
    }

    @Override
    protected void initAllMembersView(View rootView) {
        vRv = (RecyclerView) rootView.findViewById(R.id.fragment_add_rv);
        vRvAdapter = new FragmentAddRVAdapter(getActivity());
        vRv.setAdapter(vRvAdapter);
        vRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        vRvAdapter.addItem();


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
        vRvAdapter.deleteItem();
    }

    private void addItemInRv() {
        vRvAdapter.addItem();
    }
}
