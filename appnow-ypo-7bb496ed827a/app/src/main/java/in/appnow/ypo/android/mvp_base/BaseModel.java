package in.appnow.ypo.android.mvp_base;

import android.support.v7.app.AppCompatActivity;
import android.view.View;

import in.appnow.ypo.android.dialogs.ProgressDialogFragment;
import in.appnow.ypo.android.utils.KeyboardUtils;


/**
 * Created by sonu on 16:08, 17/05/18
 * Copyright (c) 2018 . All rights reserved.
 */
public class BaseModel {
    private final AppCompatActivity appCompatActivity;

    public BaseModel(AppCompatActivity appCompatActivity) {
        this.appCompatActivity = appCompatActivity;
    }

    public void showProgressDialog() {
        ProgressDialogFragment.showProgress(appCompatActivity.getSupportFragmentManager());
    }

    public void dismissProgressDialog() {
        ProgressDialogFragment.dismissProgress(appCompatActivity.getSupportFragmentManager());
    }

    public AppCompatActivity getAppCompatActivity() {
        return appCompatActivity;
    }

    public void dismissKeyboard(View view){
        KeyboardUtils.hideSoftKeyboard(view, appCompatActivity);
    }
}
