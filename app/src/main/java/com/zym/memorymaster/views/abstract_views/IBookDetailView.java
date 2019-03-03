package com.zym.memorymaster.views.abstract_views;


import com.zym.memorymaster.base.BaseModel;
import com.zym.memorymaster.base.IBaseView;

/**
 * Created by 12390 on 2018/8/20.
 */
public interface IBookDetailView extends IBaseView {
    void onAddSucc(BaseModel result);

    void onAddFailed(String msg);
}
