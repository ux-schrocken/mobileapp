package in.appnow.ypo.android.ui.contact_details.dagger;

import dagger.Component;
import in.appnow.ypo.android.dagger.component.AppComponent;
import in.appnow.ypo.android.ui.contact_details.ContactDetailActivity;

/**
 * Created by Abhishek Thanvi on 15/11/18.
 * Copyright © 2018 CollinsHarper. All rights reserved.
 */

@ContactDetailScope
@Component(modules = ContactDetailModule.class, dependencies = AppComponent.class)
public interface ContactDetailComponent {
    void inject(ContactDetailActivity activity);
}