package in.appnow.ypo.android.ui.date_time.dagger;

import dagger.Component;
import in.appnow.ypo.android.dagger.component.AppComponent;
import in.appnow.ypo.android.ui.date_time.DateTimeSelectionActivity;

/**
 * Created by sonu on 13:33, 24/10/18
 * Copyright (c) 2018 . All rights reserved.
 */
@DateTimeScope
@Component(modules = DateTimeModule.class, dependencies = AppComponent.class)
public interface DateTimeComponent {
    void inject(DateTimeSelectionActivity dateTimeSelectionActivity);
}
