package com.zym.memorymaster.views.abstract_views;


import com.zym.memorymaster.base.BaseModel;
import com.zym.memorymaster.base.IBaseView;

/**
 * Created by 12390 on 2018/8/10.
 */
public interface IRegView extends IBaseView {
    void onRegSuccess(BaseModel result);
    void onRegFailure(String msg);

}
