package in.appnow.ypo.android.ui.result.mvp;

import android.support.v7.app.AppCompatActivity;

import in.appnow.ypo.android.mvp_base.BaseModel;
import in.appnow.ypo.android.rest.APIInterface;
import in.appnow.ypo.android.ui.result.ResultActivity;
import in.appnow.ypo.android.ui.result.ResultEnum;

/**
 * Created by sonu on 17:22, 23/10/18
 * Copyright (c) 2018 . All rights reserved.
 */
public class ResultModel extends BaseModel {
    private final AppCompatActivity appCompatActivity;
    private final APIInterface apiInterface;

    public ResultModel(AppCompatActivity appCompatActivity, APIInterface apiInterface) {
        super(appCompatActivity);
        this.appCompatActivity = appCompatActivity;
        this.apiInterface = apiInterface;
    }

    public ResultEnum getResultEnum() {
        return (ResultEnum) appCompatActivity.getIntent().getSerializableExtra(ResultActivity.EXTRA_ENUM);
    }

    public String getUserName() {
        return appCompatActivity.getIntent().getStringExtra(ResultActivity.EXTRA_NAME);

    }

    public void closeActivity() {
        appCompatActivity.finish();
    }
}
