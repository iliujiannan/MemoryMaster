package com.zym.memorymaster.presenters;



import com.zym.memorymaster.base.BaseModel;
import com.zym.memorymaster.base.BasePresenter;
import com.zym.memorymaster.base.ICallback;
import com.zym.memorymaster.models.RegModel;
import com.zym.memorymaster.util.PhoneNumberCheckUtil;
import com.zym.memorymaster.views.abstract_views.IRegView;

import java.util.List;

/**
 * Created by 12390 on 2018/8/10.
 */
public class RegPresenter extends BasePresenter<IRegView> {


    public void registe(List<String> keys, final List<String> values){
        if (!isViewAttached()) {
            return;
        }
        if(values.get(1).equals(values.get(2))){
            RegModel.doReg(keys,values, new ICallback<BaseModel>() {
                @Override
                public void onSuccess(BaseModel data) {
                    if(data.getStatus()==1){
                        getView().onRegSuccess(data);
                    }else{
                        onFailure(data);
                    }
                }

                @Override
                public void onFailure(BaseModel data) {
                    getView().onActionFailed(data.getMsg());
                }

            });
        }else{

            getView().onActionFailed("密码不一致");
        }

    }

    public void getCheckCode(String phone){
        if (!isViewAttached()) {
            return;
        }
        if(PhoneNumberCheckUtil.isMobiPhoneNum(phone)){
            RegModel.doGetCheckCode(phone, new ICallback<BaseModel>() {
                @Override
                public void onSuccess(BaseModel data) {
                    if(data.getStatus()==1) {
//                        System.out.println("还没爆**********111");

                        getView().onActionSucc(data);

                    }else{
                        onFailure(data);
                    }
                }

                @Override
                public void onFailure(BaseModel data) {
                    getView().onActionFailed(data.getMsg());
                }
            });
        }else{

            getView().onActionFailed("请检查手机号");
        }

    }
}
