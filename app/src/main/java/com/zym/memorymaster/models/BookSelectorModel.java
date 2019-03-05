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
import java.util.List;

/**
 * Created by 12390 on 2019/3/2.
 */
public class BookSelectorModel extends BaseModel{
    private List<BookInformation> bookInformations;

    public List<BookInformation> getBookInformations() {
        return bookInformations;
    }

    public void setBookInformations(List<BookInformation> bookInformations) {
        this.bookInformations = bookInformations;
    }

    public static void doGetBooks(String bookType, final ICallback<BaseModel> callback){
        HttpUtil httpUtil = new HttpUtil();

        FormBody.Builder form = new FormBody.Builder();
        form.add("bookType", bookType);
        httpUtil.doPost(form, "get_all_books", new BaseModelCallBack(callback) {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Gson gson = new Gson();
                String s = response.body().string();
                BookSelectorModel bookSelectorModel = gson.fromJson(s, BookSelectorModel.class);
                callback.onSuccess(bookSelectorModel);
            }
        });
    }
}

