package in.appnow.ypo.android.ui.result.dagger;

import android.support.v7.app.AppCompatActivity;

import dagger.Module;
import dagger.Provides;
import in.appnow.ypo.android.rest.APIInterface;
import in.appnow.ypo.android.ui.result.mvp.ResultModel;
import in.appnow.ypo.android.ui.result.mvp.ResultPresenter;
import in.appnow.ypo.android.ui.result.mvp.ResultView;

/**
 * Created by sonu on 17:24, 23/10/18
 * Copyright (c) 2018 . All rights reserved.
 */
@Module
public class ResultModule {
    private final AppCompatActivity appCompatActivity;

    public ResultModule(AppCompatActivity appCompatActivity) {
        this.appCompatActivity = appCompatActivity;
    }

    @Provides
    @ResultScope
    public ResultView resultView() {
        return new ResultView(appCompatActivity);
    }

    @Provides
    @ResultScope
    public ResultModel resultModel(APIInterface apiInterface) {
        return new ResultModel(appCompatActivity, apiInterface);
    }

    @Provides
    @ResultScope
    public ResultPresenter resultPresenter(ResultView view, ResultModel model) {
        return new ResultPresenter(view, model);
    }
}
