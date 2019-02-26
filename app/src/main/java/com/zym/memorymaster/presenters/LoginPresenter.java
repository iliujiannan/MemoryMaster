package com.zym.memorymaster.presenters;

import android.os.Handler;
import com.zym.memorymaster.base.BaseModel;
import com.zym.memorymaster.base.BasePresenter;
import com.zym.memorymaster.base.IBaseView;
import com.zym.memorymaster.base.ICallback;
import com.zym.memorymaster.models.LoginModel;

import java.util.Map;

/**
 * Created by 12390 on 2018/8/10.
 */
public class LoginPresenter extends BasePresenter<IBaseView> {

    public void login(final String userPhone, final String psw) {
        if (!isViewAttached()) {
            return;
        }

        new Handler().post(new Runnable() {
            @Override
            public void run() {
                LoginModel.doLogin(userPhone, psw, new ICallback<BaseModel>() {

                    @Override
                    public void onSuccess(BaseModel data) {

                        if(data.getStatus()==1) {
                            getView().onActionSucc(data);
                        }else{
                            getView().onActionFailed(data.getMsg());
                        }
                    }

                    @Override
                    public void onFailure(BaseModel data) {
                    }

                });
            }
        });

    }
}
