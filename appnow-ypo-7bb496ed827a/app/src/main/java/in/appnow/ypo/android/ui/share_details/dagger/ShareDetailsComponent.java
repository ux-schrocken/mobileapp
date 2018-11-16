package in.appnow.ypo.android.ui.share_details.dagger;

import dagger.Component;
import in.appnow.ypo.android.dagger.component.AppComponent;
import in.appnow.ypo.android.ui.share_details.ShareDetailsActivity;

/**
 * Created by sonu on 18:41, 23/10/18
 * Copyright (c) 2018 . All rights reserved.
 */
@ShareDetailsScope
@Component(modules = ShareDetailsModule.class, dependencies = AppComponent.class)
public interface ShareDetailsComponent {
    void inject(ShareDetailsActivity activity);
}
