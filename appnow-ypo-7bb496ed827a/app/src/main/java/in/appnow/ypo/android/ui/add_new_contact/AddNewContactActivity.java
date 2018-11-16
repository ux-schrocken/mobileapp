package in.appnow.ypo.android.ui.add_new_contact;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import in.appnow.ypo.android.app_base.YPOApplication;
import in.appnow.ypo.android.ui.add_new_contact.dagger.AddNewContactModule;
import in.appnow.ypo.android.ui.add_new_contact.dagger.DaggerAddNewContactComponent;
import in.appnow.ypo.android.ui.add_new_contact.mvp.AddNewContactModel;
import in.appnow.ypo.android.ui.add_new_contact.mvp.AddNewContactPresenter;
import in.appnow.ypo.android.ui.add_new_contact.mvp.AddNewContactView;
import in.appnow.ypo.android.ui.share_details.mvp.ShareDetailsModel;

/**
 * Created by sonu on 16:22, 23/10/18
 * Copyright (c) 2018 . All rights reserved.
 */
public class AddNewContactActivity extends AppCompatActivity {

    public static void openAddNewContactActivity(Context context) {
        Intent intent = new Intent(context, AddNewContactActivity.class);
        context.startActivity(intent);
    }

    @Inject
    AddNewContactView view;
    @Inject
    AddNewContactPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerAddNewContactComponent.builder()
                .appComponent(YPOApplication.get(this).component())
                .addNewContactModule(new AddNewContactModule(this))
                .build().inject(this);
        setContentView(view);
        presenter.onCreate();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case AddNewContactModel.ADD_NEW_CONTACT_REQUEST_CODE:
                finish();
                break;
        }
    }
}
