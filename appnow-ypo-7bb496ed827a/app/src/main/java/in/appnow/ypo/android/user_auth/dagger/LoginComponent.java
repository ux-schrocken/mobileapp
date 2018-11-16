package in.appnow.ypo.android.user_auth.dagger;

import dagger.Component;
import in.appnow.ypo.android.dagger.component.AppComponent;
import in.appnow.ypo.android.user_auth.LoginActivity;

/**
 * Created by sonu on 18:37, 18/10/18
 * Copyright (c) 2018 . All rights reserved.
 */
@LoginScope
@Component(modules = LoginModule.class, dependencies = AppComponent.class)
public interface LoginComponent {
    void inject(LoginActivity loginActivity);
}
