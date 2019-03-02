package com.zym.memorymaster.views.concrete_views;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.text.method.PasswordTransformationMethod;
import android.view.*;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.zym.memorymaster.R;
import com.zym.memorymaster.base.BaseFragment;
import com.zym.memorymaster.base.BaseModel;
import com.zym.memorymaster.models.PersonalModel;
import com.zym.memorymaster.presenters.PersonalPresenter;
import com.zym.memorymaster.util.HttpUtil;
import com.zym.memorymaster.util.ImageUtil;
import com.zym.memorymaster.views.abstract_views.IPersonalView;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by 12390 on 2019/2/27.
 */
public class PersonalFragment extends BaseFragment implements View.OnClickListener, IPersonalView {

    private TextView mPersonalLogARegButton;
    private CircleImageView mHead;
    private TextView mTotalCompletedDays;
    private CardView mRechargeBt;
    private CardView mChangePswBt;
    private CardView mChangeNicknameBt;
    private CardView mLogoutBt;
    private RelativeLayout mLineBt;
    private RelativeLayout mCompletedBt;
    private RelativeLayout mMoneyBt;
    private PersonalPresenter mPersonalPresenter;

    private String secretKey;
    private Integer userId;
    private String nickName;



    @Override
    public int getContentViewId() {
        return R.layout.fragment_personal;
    }

    @Override
    protected void initAllMembersView(View mRootView) {
        mPersonalLogARegButton = (TextView) mRootView.findViewById(R.id.personal_nickname);
        mHead = (CircleImageView) mRootView.findViewById(R.id.personal_hp);
        mTotalCompletedDays = (TextView) mRootView.findViewById(R.id.personal_completed_days);
        mRechargeBt = (CardView) mRootView.findViewById(R.id.personal_recharge);
        mChangePswBt = (CardView) mRootView.findViewById(R.id.personal_change_psw);
        mChangeNicknameBt = (CardView) mRootView.findViewById(R.id.personal_channge_nickname);
        mLogoutBt = (CardView) mRootView.findViewById(R.id.personal_logout);
        mLineBt = (RelativeLayout) mRootView.findViewById(R.id.personal_line);
        mCompletedBt = (RelativeLayout) mRootView.findViewById(R.id.personal_completed);
        mMoneyBt = (RelativeLayout) mRootView.findViewById(R.id.personal_money);

        mLogoutBt.setVisibility(View.INVISIBLE);
        mMoneyBt.setOnClickListener(this);
        mLineBt.setOnClickListener(this);
        mCompletedBt.setOnClickListener(this);
        mLogoutBt.setOnClickListener(this);
        mChangeNicknameBt.setOnClickListener(this);
        mChangePswBt.setOnClickListener(this);
        mRechargeBt.setOnClickListener(this);
        mTotalCompletedDays.setOnClickListener(this);
        mPersonalLogARegButton.setOnClickListener(this);
        mHead.setOnClickListener(this);


    }

    private void initDatas(){
        secretKey = mSharedPreferences.getString("secretKey", null);
        userId = mSharedPreferences.getInt("userId", -1);
        if(secretKey!=null&&userId!=-1){
            mPersonalPresenter.getInfromation(secretKey, userId);
        }

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mPersonalPresenter = new PersonalPresenter();
        mPersonalPresenter.attachView(this);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onResume() {
        initDatas();
        super.onResume();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.personal_nickname:
                if (secretKey==null) {
                    startActivityForResult(new Intent(getActivity(), LoginActivity.class), 100);
                }
                break;
            case R.id.personal_logout:
                mPersonalPresenter.logout(userId, secretKey);
                break;
            case R.id.personal_change_psw:
                showDialogueChangePsw();
                break;
            case R.id.personal_channge_nickname:
                showDialogueChangeNickname();
                break;
            case R.id.personal_money:
                mPersonalPresenter.getMoneyInfo(userId, secretKey);
                break;
            case R.id.personal_recharge:
                showDialogueRecharge();
                break;
        }
    }

    @Override
    public void logoutSucc(BaseModel result) {
        clearUserData();
        updateUserInfromation(result);
    }

    private void clearUserData(){
        secretKey = null;
        userId = -1;
        mSharedPreferences.edit().putString("secretKey", null).commit();
        mSharedPreferences.edit().putInt("userId", -1).commit();
    }

    @Override
    public void logoutFailed(String msg) {
        onActionFailed(msg);
    }

    @Override
    public void changePswSucc(BaseModel result) {
        clearUserData();
        updateUserInfromation(result);
        startActivityForResult(new Intent(getActivity(), LoginActivity.class), 100);
    }

    @Override
    public void changePswFailed(String msg) {
        onActionFailed(msg);
    }

    @Override
    public void changeNicknameSucc(BaseModel result) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mPersonalLogARegButton.setText(nickName);
            }
        });
        showMessage(result.getMsg());
    }

    @Override
    public void changeNicknameFailed(String msg) {
        onActionFailed(msg);
    }

    @Override
    public void rechargeSucc(BaseModel result) {
        showMessage(result.getMsg());
    }

    @Override
    public void rechargeFailed(String msg) {
        onActionFailed(msg);
    }

    @Override
    public void getMoneyInfoSucc(final BaseModel result) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                showDialogueMyMoney(((PersonalModel) result).getUserMoney());
            }
        });
    }

    @Override
    public void getMoneyInfoFailed(String msg) {
        onActionFailed(msg);
    }

    @Override
    public void onActionSucc(BaseModel result) {
        updateUserInfromation(result);
    }

    private void updateUserInfromation(final BaseModel result){
        if (secretKey!=null) {
            final Bitmap bm = ImageUtil.getHttpBitmap(HttpUtil.baseUri + ((PersonalModel) result).getUserData().getUserPhoto());
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mPersonalLogARegButton.setText(((PersonalModel) result).getUserData().getUserNickName());
                    mTotalCompletedDays.setText("累计打卡" + ((PersonalModel) result).getUserData().getUserCompletedDays() + "天");
                    mLogoutBt.setVisibility(View.VISIBLE);
                    mHead.setImageBitmap(bm);

                }
            });


        } else {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mPersonalLogARegButton.setText("点击登录/注册");
                    mLogoutBt.setVisibility(View.INVISIBLE);

                }
            });


        }
    }

    private void showDialogueChangePsw() {

        final EditText mEditText1 = new EditText(mContext);
        final EditText mEditText2 = new EditText(mContext);

        mEditText1.setTextSize(16);
        mEditText2.setTextSize(16);


        mEditText1.setMaxEms(16);
        mEditText2.setMaxEms(16);

        mEditText1.setSingleLine(true);
        mEditText2.setSingleLine(true);

        mEditText1.setHintTextColor(getResources().getColor(R.color.sys_font_gray));
        mEditText2.setHintTextColor(getResources().getColor(R.color.sys_font_gray));
        mEditText1.setTransformationMethod(PasswordTransformationMethod.getInstance());
        mEditText2.setTransformationMethod(PasswordTransformationMethod.getInstance());
        mEditText1.setHint("旧密码");
        mEditText2.setHint("新密码");


        LinearLayout mLinearLayout = new LinearLayout(mContext);

        mLinearLayout.setOrientation(LinearLayout.VERTICAL);
        mLinearLayout.addView(mEditText1);
        mLinearLayout.addView(mEditText2);

        final AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String oldPsw = mEditText1.getText().toString();
                String newPsw = mEditText2.getText().toString();
                mPersonalPresenter.alterPsw(userId, oldPsw, newPsw);
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        AlertDialog mInputDialog = builder.create();
        mInputDialog.setTitle("修改密码");
        mInputDialog.setView(mLinearLayout);


        WindowManager m = getActivity().getWindowManager();
        Display d = m.getDefaultDisplay();
        Window window = mInputDialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        window.setGravity(Gravity.CENTER);
        lp.height = (int) (d.getHeight() * 0.3);
        lp.width = getResources().getDisplayMetrics().widthPixels;
        window.setAttributes(lp);

        mInputDialog.show();
    }

    private void showDialogueChangeNickname() {

        final EditText mEditText1 = new EditText(mContext);
        mEditText1.setTextSize(16);
        mEditText1.setMaxEms(20);
        mEditText1.setSingleLine(true);
        mEditText1.setHintTextColor(getResources().getColor(R.color.sys_font_gray));
        mEditText1.setHint("新昵称");
        LinearLayout mLinearLayout = new LinearLayout(mContext);
        mLinearLayout.setOrientation(LinearLayout.VERTICAL);
        mLinearLayout.addView(mEditText1);

        final AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                nickName = mEditText1.getText().toString();
                mPersonalPresenter.alterNickName(userId, secretKey, nickName);
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        AlertDialog mInputDialog = builder.create();
        mInputDialog.setTitle("修改昵称");
        mInputDialog.setView(mLinearLayout);


        WindowManager m = getActivity().getWindowManager();
        Display d = m.getDefaultDisplay();
        Window window = mInputDialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        window.setGravity(Gravity.CENTER);
        lp.height = (int) (d.getHeight() * 0.3);
        lp.width = getResources().getDisplayMetrics().widthPixels;
        window.setAttributes(lp);

        mInputDialog.show();
    }

    private void showDialogueRecharge() {
        final EditText mEditText1 = new EditText(mContext);
        mEditText1.setTextSize(16);
        mEditText1.setMaxEms(20);
        mEditText1.setSingleLine(true);
        mEditText1.setHintTextColor(getResources().getColor(R.color.sys_font_gray));
        mEditText1.setHint("充值金额");
        LinearLayout mLinearLayout = new LinearLayout(mContext);
        mLinearLayout.setOrientation(LinearLayout.VERTICAL);
        mLinearLayout.addView(mEditText1);

        final AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Integer money = Integer.valueOf(mEditText1.getText().toString());
                mPersonalPresenter.recharge(userId, secretKey, money);
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        AlertDialog mInputDialog = builder.create();
        mInputDialog.setTitle("充值");
        mInputDialog.setView(mLinearLayout);


        WindowManager m = getActivity().getWindowManager();
        Display d = m.getDefaultDisplay();
        Window window = mInputDialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        window.setGravity(Gravity.CENTER);
        lp.height = (int) (d.getHeight() * 0.3);
        lp.width = (int) getResources().getDisplayMetrics().widthPixels;
        window.setAttributes(lp);

        mInputDialog.show();


    }

    private void showDialogueMyMoney(Integer money) {
        TextView textView = new TextView(mContext);
        textView.setText("当前拥有" + money);
        textView.setGravity(Gravity.CENTER);
        LinearLayout mLinearLayout = new LinearLayout(mContext);
        mLinearLayout.setOrientation(LinearLayout.VERTICAL);
        mLinearLayout.addView(textView);
        final AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        AlertDialog mInputDialog = builder.create();
        mInputDialog.setView(mLinearLayout);

        mInputDialog.setTitle("我的余额");

        WindowManager m = getActivity().getWindowManager();
        Display d = m.getDefaultDisplay();
        Window window = mInputDialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        window.setGravity(Gravity.CENTER);
        lp.height = (int) (d.getHeight() * 0.3);
        lp.width = (int) getResources().getDisplayMetrics().widthPixels;
        window.setAttributes(lp);

        mInputDialog.show();
    }
}
