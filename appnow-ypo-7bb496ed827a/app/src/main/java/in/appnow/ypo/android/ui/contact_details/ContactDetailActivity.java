package in.appnow.ypo.android.ui.contact_details;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import in.appnow.ypo.android.app_base.YPOApplication;
import in.appnow.ypo.android.rest.response.Contacts;
import in.appnow.ypo.android.ui.contact_details.dagger.ContactDetailModule;
import in.appnow.ypo.android.ui.contact_details.dagger.DaggerContactDetailComponent;
import in.appnow.ypo.android.ui.contact_details.mvp.ContactDetailPresenter;
import in.appnow.ypo.android.ui.contact_details.mvp.ContactDetailView;

/**
 * Created by Abhishek Thanvi on 15/11/18.
 * Copyright © 2018 Abhishek Thanvi. All rights reserved.
 */


public class ContactDetailActivity extends AppCompatActivity {

    public Contacts contacts;
    public static String CONTACT_ID = "CONTACT_ID";

    public static void openContactDetailsActivity(Context context,String contactId) {
        Intent intent = new Intent(context, ContactDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(CONTACT_ID,contactId);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Inject
    ContactDetailView view;
    @Inject
    ContactDetailPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaggerContactDetailComponent.builder().appComponent(YPOApplication.get(this).component())
                .contactDetailModule(new ContactDetailModule(this))
                .build()
                .inject(this);

        setContentView(view);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null && bundle.getString(CONTACT_ID) != null){
            presenter.setContactId(bundle.getString(CONTACT_ID));
        }
        presenter.onCreate();


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }


}
