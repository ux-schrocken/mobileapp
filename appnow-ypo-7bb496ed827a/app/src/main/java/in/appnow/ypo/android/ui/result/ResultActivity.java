package in.appnow.ypo.android.ui.result;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import in.appnow.ypo.android.app_base.YPOApplication;
import in.appnow.ypo.android.ui.result.dagger.DaggerResultComponent;
import in.appnow.ypo.android.ui.result.dagger.ResultModule;
import in.appnow.ypo.android.ui.result.mvp.ResultPresenter;
import in.appnow.ypo.android.ui.result.mvp.ResultView;

/**
 * Created by sonu on 17:19, 23/10/18
 * Copyright (c) 2018 . All rights reserved.
 */
public class ResultActivity extends AppCompatActivity {

    public static final String EXTRA_NAME = "extra_name";
    public static final String EXTRA_ENUM = "extra_enum";

    public static void openResultActivity(Context context, ResultEnum resultEnum, String userName, int requestCode) {
        Bundle bundle  = new Bundle();
        bundle.putSerializable(EXTRA_ENUM, resultEnum);
        Intent intent = new Intent(context, ResultActivity.class);
        intent.putExtra(EXTRA_NAME, userName);
        intent.putExtras(bundle);
        ((AppCompatActivity) context).startActivityForResult(intent, requestCode);
    }

    @Inject
    ResultView view;
    @Inject
    ResultPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        DaggerResultComponent.builder()
                .appComponent(YPOApplication.get(this).component())
                .resultModule(new ResultModule(this))
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
