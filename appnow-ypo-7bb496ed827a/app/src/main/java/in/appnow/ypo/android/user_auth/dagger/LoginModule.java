package in.appnow.ypo.android.user_auth.dagger;

import android.support.v7.app.AppCompatActivity;

import dagger.Module;
import dagger.Provides;
import in.appnow.ypo.android.rest.APIInterface;
import in.appnow.ypo.android.user_auth.mvp.LoginModel;
import in.appnow.ypo.android.user_auth.mvp.LoginPresenter;
import in.appnow.ypo.android.user_auth.mvp.LoginView;

/**
 * Created by sonu on 18:37, 18/10/18
 * Copyright (c) 2018 . All rights reserved.
 */
@Module
public class LoginModule {
    private final AppCompatActivity appCompatActivity;

    public LoginModule(AppCompatActivity appCompatActivity) {
        this.appCompatActivity = appCompatActivity;
    }

    @Provides
    @LoginScope
    public LoginView loginView() {
        return new LoginView(appCompatActivity);
    }

    @Provides
    @LoginScope
    public LoginModel loginModel(APIInterface apiInterface) {
        return new LoginModel(appCompatActivity,apiInterface);
    }
    @Provides
    @LoginScope
    public LoginPresenter loginPresenter(LoginView view,LoginModel model) {
        return new LoginPresenter(view,model);
    }
}
