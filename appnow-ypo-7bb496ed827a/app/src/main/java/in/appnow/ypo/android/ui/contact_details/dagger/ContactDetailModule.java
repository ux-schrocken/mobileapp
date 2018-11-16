package in.appnow.ypo.android.ui.contact_details.dagger;

import android.support.v7.app.AppCompatActivity;

import dagger.Module;
import dagger.Provides;
import in.appnow.ypo.android.rest.APIInterface;
import in.appnow.ypo.android.ui.contact_details.mvp.ContactDetailModel;
import in.appnow.ypo.android.ui.contact_details.mvp.ContactDetailPresenter;
import in.appnow.ypo.android.ui.contact_details.mvp.ContactDetailView;

/**
 * Created by Abhishek Thanvi on 15/11/18.
 * Copyright © 2018 CollinsHarper. All rights reserved.
 */

@Module
public class ContactDetailModule {

    private final AppCompatActivity appCompatActivity;

    public ContactDetailModule(AppCompatActivity appCompatActivity) {
        this.appCompatActivity = appCompatActivity;
    }

    @Provides
    @ContactDetailScope
    public ContactDetailView contactDetailView() {
        return new ContactDetailView(appCompatActivity);
    }

    @Provides
    @ContactDetailScope
    public ContactDetailModel contactDetailModel(APIInterface apiInterface) {
        return new ContactDetailModel(appCompatActivity, apiInterface);
    }

    @Provides
    @ContactDetailScope
    public ContactDetailPresenter contactDetailPresenter(ContactDetailView view, ContactDetailModel model) {
        return new ContactDetailPresenter(view, model);
    }
}
