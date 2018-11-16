package in.appnow.ypo.android.ui.add_new_contact.dagger;

import android.support.v7.app.AppCompatActivity;

import dagger.Module;
import dagger.Provides;
import in.appnow.ypo.android.rest.APIInterface;
import in.appnow.ypo.android.ui.add_new_contact.mvp.AddNewContactModel;
import in.appnow.ypo.android.ui.add_new_contact.mvp.AddNewContactPresenter;
import in.appnow.ypo.android.ui.add_new_contact.mvp.AddNewContactView;

/**
 * Created by sonu on 16:24, 23/10/18
 * Copyright (c) 2018 . All rights reserved.
 */
@Module
public class AddNewContactModule {
    private final AppCompatActivity appCompatActivity;

    public AddNewContactModule(AppCompatActivity appCompatActivity) {
        this.appCompatActivity = appCompatActivity;
    }

    @Provides
    @AddNewContactScope
    public AddNewContactView addNewContactView() {
        return new AddNewContactView(appCompatActivity);
    }

    @Provides
    @AddNewContactScope
    public AddNewContactModel addNewContactModel(APIInterface apiInterface) {
        return new AddNewContactModel(appCompatActivity, apiInterface);
    }

    @Provides
    @AddNewContactScope
    public AddNewContactPresenter addNewContactPresenter(AddNewContactView view, AddNewContactModel model) {
        return new AddNewContactPresenter(view, model);
    }
}
