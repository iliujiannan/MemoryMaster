package com.zym.memorymaster.models;

import com.google.gson.Gson;
import com.zym.memorymaster.base.BaseModel;
import com.zym.memorymaster.base.BaseModelCallBack;
import com.zym.memorymaster.base.ICallback;
import com.zym.memorymaster.dao.entities.LocalBookContent;
import com.zym.memorymaster.util.HttpUtil;
import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.Response;

import java.io.IOException;
import java.util.List;

/**
 * Created by 12390 on 2019/3/5.
 */
public class BookDownloadModel extends BaseModel{

    private List<LocalBookContent> bookContents;

    public List<LocalBookContent> getBookContents() {
        return bookContents;
    }

    public void setBookContents(List<LocalBookContent> bookContents) {
        this.bookContents = bookContents;
    }

    public static void doAddAndDownloadBook(String secretKey, Integer userId,
                                            Integer bookInformationId, Integer flag,
                                            final ICallback<BaseModel> callback){
        HttpUtil httpUtil = new HttpUtil();

        FormBody.Builder form = new FormBody.Builder();
        form.add("bookInformationId", bookInformationId.toString());
        form.add("userId", userId.toString());
        form.add("secretKey", secretKey);
        form.add("flag", flag.toString());
        httpUtil.doPost(form, "add_book", new BaseModelCallBack(callback) {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Gson gson = new Gson();
                String s = response.body().string();
                BookDownloadModel bookDownloadModel = gson.fromJson(s, BookDownloadModel.class);
                callback.onSuccess(bookDownloadModel);
            }
        });

    }


}
