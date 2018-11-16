package in.appnow.ypo.android.ui.add_new_contact.dagger;

import dagger.Component;
import in.appnow.ypo.android.dagger.component.AppComponent;
import in.appnow.ypo.android.ui.add_new_contact.AddNewContactActivity;

/**
 * Created by sonu on 16:26, 23/10/18
 * Copyright (c) 2018 . All rights reserved.
 */
@AddNewContactScope
@Component(modules = AddNewContactModule.class, dependencies = AppComponent.class)
public interface AddNewContactComponent {
    void inject(AddNewContactActivity addNewContactActivity);
}
