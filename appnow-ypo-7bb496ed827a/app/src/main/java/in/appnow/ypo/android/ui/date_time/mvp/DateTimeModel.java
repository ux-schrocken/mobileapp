package in.appnow.ypo.android.ui.date_time.mvp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import in.appnow.ypo.android.mvp_base.BaseModel;
import in.appnow.ypo.android.rest.APIInterface;
import in.appnow.ypo.android.ui.date_time.DateTimeSelectionActivity;
import in.appnow.ypo.android.utils.FragmentUtils;
import in.appnow.ypo.android.utils.Logger;

/**
 * Created by sonu on 13:31, 24/10/18
 * Copyright (c) 2018 . All rights reserved.
 */
public class DateTimeModel extends BaseModel {
    public static final String EXTRA_DATE = "extra_date";
    public static final String EXTRA_TIME = "extra_time";
    private final AppCompatActivity appCompatActivity;
    private final APIInterface apiInterface;

    public DateTimeModel(AppCompatActivity appCompatActivity, APIInterface apiInterface) {
        super(appCompatActivity);
        this.appCompatActivity = appCompatActivity;
        this.apiInterface = apiInterface;
    }

    public int getSelectType() {
        return appCompatActivity.getIntent().getIntExtra(DateTimeSelectionActivity.EXTRA_DATE_SELECT, FragmentUtils.DATE);
    }

    public void closeActivity() {
        appCompatActivity.finish();
    }

    public void dataSelected(long date, long time) {
        Intent intent = new Intent();
        intent.putExtra(EXTRA_DATE, date);
        intent.putExtra(EXTRA_TIME, time);
        appCompatActivity.setResult(Activity.RESULT_OK, intent);
        appCompatActivity.finish();
    }
}
