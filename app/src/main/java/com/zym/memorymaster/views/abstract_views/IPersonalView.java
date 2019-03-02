package com.zym.memorymaster.views.abstract_views;


import com.zym.memorymaster.base.BaseModel;
import com.zym.memorymaster.base.IBaseView;

/**
 * Created by 12390 on 2018/8/17.
 */
public interface IPersonalView extends IBaseView {
    void logoutSucc(BaseModel result);
    void logoutFailed(String msg);

    void changePswSucc(BaseModel result);
    void changePswFailed(String msg);

    void changeNicknameSucc(BaseModel result);
    void changeNicknameFailed(String msg);

    void rechargeSucc(BaseModel result);
    void rechargeFailed(String msg);

    void getMoneyInfoSucc(BaseModel result);
    void getMoneyInfoFailed(String msg);

}
