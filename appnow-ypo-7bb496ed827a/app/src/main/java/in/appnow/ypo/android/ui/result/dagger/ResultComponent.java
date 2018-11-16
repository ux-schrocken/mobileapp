package in.appnow.ypo.android.ui.result.dagger;

import dagger.Component;
import in.appnow.ypo.android.dagger.component.AppComponent;
import in.appnow.ypo.android.ui.result.ResultActivity;
import in.appnow.ypo.android.ui.result.mvp.ResultModel;

/**
 * Created by sonu on 17:23, 23/10/18
 * Copyright (c) 2018 . All rights reserved.
 */
@ResultScope
@Component(modules = ResultModule.class, dependencies = AppComponent.class)
public interface ResultComponent {
    void inject(ResultActivity activity);
}
