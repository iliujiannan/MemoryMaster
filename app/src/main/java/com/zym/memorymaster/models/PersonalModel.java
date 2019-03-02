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
 * Created by 12390 on 2019/2/28.
 */
public class PersonalModel extends BaseModel {

    private User userData;

    private Integer userMoney;


    public User getUserData() {
        return userData;
    }

    public Integer getUserMoney() {
        return userMoney;
    }

    public void setUserMoney(Integer userMoney) {
        this.userMoney = userMoney;
    }

    public void setUserData(User userData) {
        this.userData = userData;
    }

    public class User{
        private Integer userId;

        private String userPhone;

        private String userPhoto;

        private String userNickName;

        private String psw;

        private String secretKey;

        private Integer userCurrentBookId;

        private Integer userCompletedDays;

        public Integer getUserId() {
            return userId;
        }

        public void setUserId(Integer userId) {
            this.userId = userId;
        }

        public String getUserPhone() {
            return userPhone;
        }

        public void setUserPhone(String userPhone) {
            this.userPhone = userPhone;
        }

        public String getUserPhoto() {
            return userPhoto;
        }

        public void setUserPhoto(String userPhoto) {
            this.userPhoto = userPhoto;
        }

        public String getUserNickName() {
            return userNickName;
        }

        public void setUserNickName(String userNickName) {
            this.userNickName = userNickName;
        }

        public String getPsw() {
            return psw;
        }

        public void setPsw(String psw) {
            this.psw = psw;
        }

        public String getSecretKey() {
            return secretKey;
        }

        public void setSecretKey(String secretKey) {
            this.secretKey = secretKey;
        }

        public Integer getUserCurrentBookId() {
            return userCurrentBookId;
        }

        public void setUserCurrentBookId(Integer userCurrentBookId) {
            this.userCurrentBookId = userCurrentBookId;
        }

        public Integer getUserCompletedDays() {
            return userCompletedDays;
        }

        public void setUserCompletedDays(Integer userCompletedDays) {
            this.userCompletedDays = userCompletedDays;
        }
    }


    public static void doGetInformation(String secretKey, Integer userId, final ICallback<BaseModel> callback){
        HttpUtil httpUtil = new HttpUtil();

        FormBody.Builder form = new FormBody.Builder();
        form.add("userId", userId.toString());
        form.add("secretKey", secretKey);
        httpUtil.doPost(form, "personal_information", new BaseModelCallBack(callback) {

            @Override
            public void onResponse(Call call, Response response) throws IOException {
//                System.out.println("***************调用前7");
                //System.out.println(response.body().string());
                String s = response.body().string();
                PersonalModel personalModel = new Gson().fromJson(s, PersonalModel.class);
                callback.onSuccess(personalModel);
            }
        });
    }
    public static void doLogout(Integer userId, String secretKey, final ICallback<BaseModel> callback){
        HttpUtil httpUtil = new HttpUtil();

        FormBody.Builder form = new FormBody.Builder();
        form.add("userId", userId.toString());
        form.add("secretKey", secretKey);
        httpUtil.doPost(form, "log_out", new BaseModelCallBack(callback));
    }

    public static void doChangePsw(Integer userId, String oldPsw, String newPsw, final ICallback<BaseModel> callback){
        HttpUtil httpUtil = new HttpUtil();

        FormBody.Builder form = new FormBody.Builder();
        form.add("userId", userId.toString());
        form.add("psw", oldPsw);
        form.add("newpsw", newPsw);
        httpUtil.doPost(form, "modify_psw", new BaseModelCallBack(callback));
    }


    public static void doChangeNickname(Integer userId, String secretKey, String nickname, final ICallback<BaseModel> callback){
        HttpUtil httpUtil = new HttpUtil();
        FormBody.Builder form = new FormBody.Builder();
        form.add("userId", userId.toString());
        form.add("secretKey", secretKey);
        form.add("newNickname", nickname);
        httpUtil.doPost(form, "modify_nickname", new BaseModelCallBack(callback));
    }

    public static void doRecharge(Integer userId, String secretKey, Integer money, final ICallback<BaseModel> callback){
        HttpUtil httpUtil = new HttpUtil();

        FormBody.Builder form = new FormBody.Builder();
        form.add("userId", userId.toString());
        form.add("secretKey", secretKey);
        form.add("chargeMoneyAmount", money.toString());
        httpUtil.doPost(form, "charge_money", new BaseModelCallBack(callback));
    }

    public static void mDoGetMoneyInfo(Integer userId, String secretKey, final ICallback<BaseModel> callback){
        HttpUtil httpUtil = new HttpUtil();

        FormBody.Builder form = new FormBody.Builder();
        form.add("userId", userId.toString());
        form.add("secretKey", secretKey);
        httpUtil.doPost(form, "get_purse_money", new BaseModelCallBack(callback) {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Gson gson = new Gson();
                String s = response.body().string();
                System.out.println(s);
                PersonalModel personalModel = gson.fromJson(s, PersonalModel.class);
                callback.onSuccess(personalModel);
            }
        });
    }

}
