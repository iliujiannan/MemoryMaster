package com.zym.memorymaster.models;

import com.google.gson.Gson;
import com.zym.memorymaster.base.BaseModel;
import com.zym.memorymaster.base.ICallback;
import com.zym.memorymaster.util.HttpUtil;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Response;

import java.io.IOException;
import java.util.Map;

/**
 * Created by 12390 on 2019/2/26.
 */
public class LoginModel extends BaseModel {
    private Integer userId;
    private String secretKey;
    private String currBook;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getCurrBook() {
        return currBook;
    }

    public void setCurrBook(String currBook) {
        this.currBook = currBook;
    }


    public static void doLogin(final String userPhone, final String psw, final ICallback<BaseModel> callback) {
        FormBody.Builder formBody = new FormBody.Builder();//创建表单请求体

        formBody.add("userPhone", userPhone);//传递键值对参数

        formBody.add("psw", psw);

        HttpUtil httpUtil = new HttpUtil();
        httpUtil.doPost(formBody, "login", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

                LoginModel model = new LoginModel();
                model.setStatus(0);
                model.setMsg("server error");
                callback.onFailure(model);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Gson gson = new Gson();
                LoginModel loginModel = gson.fromJson(response.body().string(), LoginModel.class);
//                        System.out.println("****还没爆"+response.body().string());
                callback.onSuccess(loginModel);
            }
        });

    }
}
