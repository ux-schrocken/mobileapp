package in.appnow.ypo.android.user_auth;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import in.appnow.ypo.android.app_base.YPOApplication;
import in.appnow.ypo.android.user_auth.dagger.DaggerLoginComponent;
import in.appnow.ypo.android.user_auth.dagger.LoginModule;
import in.appnow.ypo.android.user_auth.mvp.LoginPresenter;
import in.appnow.ypo.android.user_auth.mvp.LoginView;

/**
 * Created by sonu on 18:34, 18/10/18
 * Copyright (c) 2018 . All rights reserved.
 */
public class LoginActivity extends AppCompatActivity {

    @Inject
    LoginView view;
    @Inject
    LoginPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerLoginComponent.builder()
                .appComponent(YPOApplication.get(this).component())
                .loginModule(new LoginModule(this))
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
