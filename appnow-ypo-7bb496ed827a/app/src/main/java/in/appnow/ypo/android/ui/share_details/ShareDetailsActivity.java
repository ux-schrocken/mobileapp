package in.appnow.ypo.android.ui.share_details;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import in.appnow.ypo.android.app_base.YPOApplication;
import in.appnow.ypo.android.rest.response.Contacts;
import in.appnow.ypo.android.rest.response.Tasks;
import in.appnow.ypo.android.ui.share_details.dagger.DaggerShareDetailsComponent;
import in.appnow.ypo.android.ui.share_details.dagger.ShareDetailsModule;
import in.appnow.ypo.android.ui.share_details.mvp.ShareDetailsModel;
import in.appnow.ypo.android.ui.share_details.mvp.ShareDetailsPresenter;
import in.appnow.ypo.android.ui.share_details.mvp.ShareDetailsView;

/**
 * Created by sonu on 18:37, 23/10/18
 * Copyright (c) 2018 . All rights reserved.
 */
public class ShareDetailsActivity extends AppCompatActivity {

    public Contacts contacts;
    public static String TASK = "TASK";

    public static void openShareDetailsActivity(Context context, Tasks tasks) {
        Intent intent = new Intent(context, ShareDetailsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(TASK,tasks);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Inject
    ShareDetailsView view;
    @Inject
    ShareDetailsPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerShareDetailsComponent.builder()
                .appComponent(YPOApplication.get(this).component())
                .shareDetailsModule(new ShareDetailsModule(this))
                .build()
                .inject(this);
        setContentView(view);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null && bundle.get(TASK) != null){
            Tasks tasks = (Tasks) bundle.getSerializable(TASK);
            if (tasks != null){
                presenter.taskDetail(tasks);
            }
        }
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
            case ShareDetailsModel.CONTACT_REQUEST_CODE:
                finish();
                break;
        }
    }
}
