package com.zym.memorymaster.models;

import com.google.gson.Gson;
import com.zym.memorymaster.base.BaseModel;
import com.zym.memorymaster.base.BaseModelCallBack;
import com.zym.memorymaster.base.ICallback;
import com.zym.memorymaster.util.HttpUtil;
import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.Response;

import java.io.IOException;

/**
 * Created by 12390 on 2019/3/3.
 */
public class BookDetailModel extends BaseModel{
    private BookInformation bookInformation;

    public BookInformation getBookInformation() {
        return bookInformation;
    }

    public void setBookInformation(BookInformation bookInformation) {
        this.bookInformation = bookInformation;
    }


    public static void doGetBookDetail(Integer bookInformationId, final ICallback<BaseModel> callback){
        HttpUtil httpUtil = new HttpUtil();

        FormBody.Builder form = new FormBody.Builder();
        form.add("bookInformationId", bookInformationId.toString());
        httpUtil.doPost(form, "get_one_book", new BaseModelCallBack(callback) {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Gson gson = new Gson();
                String s = response.body().string();
                BookDetailModel bookDetailModel = gson.fromJson(s, BookDetailModel.class);
                callback.onSuccess(bookDetailModel);
            }
        });
    }

}
