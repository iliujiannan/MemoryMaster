package com.zym.memorymaster.presenters;

import android.os.Handler;
import com.zym.memorymaster.base.BaseModel;
import com.zym.memorymaster.base.BasePresenter;
import com.zym.memorymaster.base.ICallback;
import com.zym.memorymaster.models.BookDownloadModel;
import com.zym.memorymaster.models.BookInformation;
import com.zym.memorymaster.models.BookDetailModel;
import com.zym.memorymaster.views.abstract_views.IBookDetailView;

/**
 * Created by 12390 on 2019/3/3.
 */
public class BookDetailPresenter extends BasePresenter<IBookDetailView> {
    public void getBookDetail(final Integer bookInformationId){
        if(!isViewAttached()){
            return;
        }
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                BookDetailModel.doGetBookDetail(bookInformationId, new ICallback<BaseModel>() {
                    @Override
                    public void onSuccess(BaseModel data) {
                        getView().onActionSucc(data);
                    }

                    @Override
                    public void onFailure(BaseModel data) {

                        getView().onActionFailed(data.getMsg());

                    }
                });
            }
        });
    }

    public void addBook(final Integer userId, final String secretKey,
                        final Integer bookInformationId, final int flag){
        if(!isViewAttached()){
            return;
        }
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                BookDownloadModel.doAddAndDownloadBook(secretKey, userId, bookInformationId, flag, new ICallback<BaseModel>() {
                    @Override
                    public void onSuccess(BaseModel data) {
                        if(data.getStatus()==1) {
                            getView().onAddSucc(data);
                        }else {
                            onFailure(data);
                        }
                    }

                    @Override
                    public void onFailure(BaseModel data) {
                        getView().onAddFailed(data.getMsg());
                    }
                });
            }
        });
    }

}
