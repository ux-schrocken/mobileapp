package in.appnow.ypo.android.ui.date_time.dagger;

import android.support.v7.app.AppCompatActivity;

import dagger.Module;
import dagger.Provides;
import in.appnow.ypo.android.rest.APIInterface;
import in.appnow.ypo.android.ui.date_time.mvp.DateTimeModel;
import in.appnow.ypo.android.ui.date_time.mvp.DateTimePresenter;
import in.appnow.ypo.android.ui.date_time.mvp.DateTimeView;

/**
 * Created by sonu on 13:32, 24/10/18
 * Copyright (c) 2018 . All rights reserved.
 */
@Module
public class DateTimeModule {
    private final AppCompatActivity appCompatActivity;

    public DateTimeModule(AppCompatActivity appCompatActivity) {
        this.appCompatActivity = appCompatActivity;
    }

    @Provides
    @DateTimeScope
    public DateTimeView dateTimeView() {
        return new DateTimeView(appCompatActivity);
    }

    @Provides
    @DateTimeScope
    public DateTimeModel dateTimeModel(APIInterface apiInterface) {
        return new DateTimeModel(appCompatActivity, apiInterface);
    }

    @Provides
    @DateTimeScope
    public DateTimePresenter dateTimePresenter(DateTimeView view, DateTimeModel model) {
        return new DateTimePresenter(view, model);
    }
}
