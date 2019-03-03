package com.zym.memorymaster.presenters;

import android.os.Handler;
import com.zym.memorymaster.base.BaseModel;
import com.zym.memorymaster.base.BasePresenter;
import com.zym.memorymaster.base.IBaseView;
import com.zym.memorymaster.base.ICallback;
import com.zym.memorymaster.models.BookSelectorModel;

/**
 * Created by 12390 on 2019/3/2.
 */
public class BookSelectorPresenter extends BasePresenter<IBaseView> {

    public void getBooks(final String label){
        if (!isViewAttached()) {
            return;
        }

        new Handler().post(new Runnable() {
            @Override
            public void run() {
                BookSelectorModel.doGetBooks(label, new ICallback<BaseModel>() {
                    @Override
                    public void onSuccess(BaseModel data) {

                        if(data.getStatus()==1) {
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
}
