package com.zym.memorymaster.presenters;

import android.os.Handler;
import com.zym.memorymaster.base.BaseModel;
import com.zym.memorymaster.base.BasePresenter;
import com.zym.memorymaster.base.ICallback;
import com.zym.memorymaster.models.PersonalModel;
import com.zym.memorymaster.views.abstract_views.IPersonalView;

/**
 * Created by 12390 on 2019/2/28.
 */
public class PersonalPresenter extends BasePresenter<IPersonalView> {
    public void getInfromation(final String secretKey, final Integer userId){
//        System.out.println("***************调用前2");
        if (!isViewAttached()) {
            return;
        }
//        System.out.println("***************调用前3");
        new Handler().post(new Runnable() {
            @Override
            public void run() {
//                System.out.println("***************调用前4");
                PersonalModel.doGetInformation(secretKey, userId, new ICallback<BaseModel>() {
                    @Override
                    public void onSuccess(BaseModel data) {
//                        System.out.println("***************调用前5");
                        if(data.getStatus()==1){
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
            }
        });

    }

    public void logout(final Integer userId, final String secretKey){
        if (!isViewAttached()) {
            return;
        }
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                PersonalModel.doLogout(userId, secretKey, new ICallback<BaseModel>() {
                    @Override
                    public void onSuccess(BaseModel data) {
                        if(data.getStatus()==1){
                            getView().logoutSucc(data);
                        }else{
                            onFailure(data);
                        }
                    }

                    @Override
                    public void onFailure(BaseModel data) {
                        getView().logoutFailed(data.getMsg());
                    }
                });
            }
        });
    }
    public void alterNickName(final Integer userId, final String secretKey, final String newNickname){
        if (!isViewAttached()) {
            return;
        }
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                PersonalModel.doChangeNickname(userId, secretKey, newNickname, new ICallback<BaseModel>() {
                    @Override
                    public void onSuccess(BaseModel data) {
                        if(data.getStatus()==1){
                            getView().changeNicknameSucc(data);
                        }else{
                            onFailure(data);
                        }
                    }

                    @Override
                    public void onFailure(BaseModel data) {
                        getView().changeNicknameFailed(data.getMsg());
                    }
                });
            }
        });
    }

    public void alterPsw(final Integer userId, final String oldPsw, final String newPsw){
        if (!isViewAttached()) {
            return;
        }
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                PersonalModel.doChangePsw(userId, oldPsw, newPsw, new ICallback<BaseModel>() {
                    @Override
                    public void onSuccess(BaseModel data) {
                        if(data.getStatus()==1){
                            getView().changePswSucc(data);
                        }else{
                            onFailure(data);
                        }
                    }

                    @Override
                    public void onFailure(BaseModel data) {

                        getView().changePswFailed(data.getMsg());

                    }
                });
            }
        });
    }


    public void recharge(final Integer userId, final String secretKey, final Integer money){
        if (!isViewAttached()) {
            return;
        }
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                PersonalModel.doRecharge(userId, secretKey, money, new ICallback<BaseModel>() {
                    @Override
                    public void onSuccess(BaseModel data) {
                        if(data.getStatus()==1){

                            getView().rechargeSucc(data);

                        }else{
                            onFailure(data);
                        }
                    }

                    @Override
                    public void onFailure(BaseModel data) {

                        getView().rechargeFailed(data.getMsg());

                    }
                });
            }
        });
    }
    public void getMoneyInfo(final Integer userId, final String secretKey){
        if (!isViewAttached()) {
            return;
        }
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                PersonalModel.mDoGetMoneyInfo(userId, secretKey, new ICallback<BaseModel>() {
                    @Override
                    public void onSuccess(BaseModel data) {
                        if(data.getStatus()==1){
                            getView().getMoneyInfoSucc(data);
                        }else{
                            onFailure(data);
                        }
                    }

                    @Override
                    public void onFailure(BaseModel data) {

                        getView().getMoneyInfoFailed(data.getMsg());
                    }
                });
            }
        });
    }
}
