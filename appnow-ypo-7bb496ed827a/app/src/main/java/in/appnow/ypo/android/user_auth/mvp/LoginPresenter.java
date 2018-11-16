package in.appnow.ypo.android.user_auth.mvp;

import android.view.View;

import in.appnow.ypo.android.mvp_base.BasePresenter;
import in.appnow.ypo.android.ui.main.MainActivity;

/**
 * Created by sonu on 18:14, 18/10/18
 * Copyright (c) 2018 . All rights reserved.
 */
public class LoginPresenter implements BasePresenter {
    private final LoginView loginView;
    private final LoginModel model;

    public LoginPresenter(LoginView view, LoginModel model) {
        this.loginView = view;
        this.model = model;
    }

    @Override
    public void onCreate() {

        loginView.onLoginButtonClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (loginView.isValid()){
                    model.launchMainActivity(loginView.getUserId());
                }
            }
        });

    }

    @Override
    public void onDestroy() {

    }
}
