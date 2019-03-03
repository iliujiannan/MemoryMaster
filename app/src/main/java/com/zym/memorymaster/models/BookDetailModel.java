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
    private Book book;

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
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
    public static void doAddBook(Integer userId, String secretKey,
                                  Integer bookInformationId, Integer flag, ICallback<BaseModel> callback){
        HttpUtil httpUtil = new HttpUtil();

        FormBody.Builder form = new FormBody.Builder();
        form.add("userId", userId.toString());
        form.add("secretKey", secretKey);
        form.add("bookInformationId", bookInformationId.toString());
        form.add("flag", flag.toString());

        httpUtil.doPost(form, "add_books",new BaseModelCallBack(callback));

    }
}
