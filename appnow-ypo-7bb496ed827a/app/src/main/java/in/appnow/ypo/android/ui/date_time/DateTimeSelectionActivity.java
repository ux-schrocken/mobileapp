package in.appnow.ypo.android.ui.date_time;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import in.appnow.ypo.android.app_base.YPOApplication;
import in.appnow.ypo.android.ui.date_time.dagger.DaggerDateTimeComponent;
import in.appnow.ypo.android.ui.date_time.dagger.DateTimeModule;
import in.appnow.ypo.android.ui.date_time.mvp.DateTimePresenter;
import in.appnow.ypo.android.ui.date_time.mvp.DateTimeView;

/**
 * Created by sonu on 13:28, 24/10/18
 * Copyright (c) 2018 . All rights reserved.
 */
public class DateTimeSelectionActivity extends AppCompatActivity {

    public static final String EXTRA_DATE_SELECT = "date_select";

    public static void openDateTimeSelectionActivity(Context context, int dateSelect,int requestCode) {
        Intent intent = new Intent(context, DateTimeSelectionActivity.class);
        intent.putExtra(EXTRA_DATE_SELECT, dateSelect);
        ((AppCompatActivity)context).startActivityForResult(intent,requestCode);
    }

    @Inject
    DateTimeView view;
    @Inject
    DateTimePresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerDateTimeComponent.builder()
                .appComponent(YPOApplication.get(this).component())
                .dateTimeModule(new DateTimeModule(this))
                .build()
                .inject(this);
        setContentView(view);
        presenter.onCreate();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
}
