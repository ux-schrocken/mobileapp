package in.appnow.ypo.android.user_auth.mvp;

import android.support.v7.app.AppCompatActivity;

import in.appnow.ypo.android.mvp_base.BaseModel;
import in.appnow.ypo.android.rest.APIInterface;
import in.appnow.ypo.android.ui.main.MainActivity;
import in.appnow.ypo.android.utils.StringUtils;

/**
 * Created by sonu on 18:13, 18/10/18
 * Copyright (c) 2018 . All rights reserved.
 */
public class LoginModel extends BaseModel {
    private final AppCompatActivity appCompatActivity;
    private final APIInterface apiInterface;

    public LoginModel(AppCompatActivity appCompatActivity, APIInterface apiInterface) {
        super(appCompatActivity);
        this.appCompatActivity = appCompatActivity;
        this.apiInterface = apiInterface;
    }

    public void launchMainActivity(String userId) {
        MainActivity.openMainActivity(appCompatActivity);
        appCompatActivity.finish();
    }
}
