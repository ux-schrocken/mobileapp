package in.appnow.ypo.android.ui.meeting_request;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import in.appnow.ypo.android.app_base.YPOApplication;
import in.appnow.ypo.android.rest.response.ContactResponse;
import in.appnow.ypo.android.rest.response.Contacts;
import in.appnow.ypo.android.ui.date_time.dagger.DateTimeModule;
import in.appnow.ypo.android.ui.date_time.mvp.DateTimeModel;
import in.appnow.ypo.android.ui.meeting_request.dagger.DaggerMeetingRequestComponent;
import in.appnow.ypo.android.ui.meeting_request.dagger.MeetingRequestModule;
import in.appnow.ypo.android.ui.meeting_request.mvp.MeetingRequestModel;
import in.appnow.ypo.android.ui.meeting_request.mvp.MeetingRequestPresenter;
import in.appnow.ypo.android.ui.meeting_request.mvp.MeetingRequestView;

/**
 * Created by sonu on 13:01, 24/10/18
 * Copyright (c) 2018 . All rights reserved.
 */
public class MeetingRequestActivity extends AppCompatActivity {

    public static final String EXTRA_CONTACT = "contact_details";

    public static void openMeetingRequestActivity(Context context, @Nullable Contacts contacts) {
        Intent intent = new Intent(context, MeetingRequestActivity.class);
        if (contacts != null) {
            Bundle bundle = new Bundle();
            bundle.putParcelable(EXTRA_CONTACT, contacts);
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }

    @Inject
    MeetingRequestView view;
    @Inject
    MeetingRequestPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerMeetingRequestComponent.builder()
                .appComponent(YPOApplication.get(this).component())
                .meetingRequestModule(new MeetingRequestModule(this))
                .build()
                .inject(this);
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
            case MeetingRequestPresenter.DATE_SELECT_REQUEST_CODE:
                if (resultCode == RESULT_OK) {
                    if (data==null)
                        return;
                    if (data.hasExtra(DateTimeModel.EXTRA_DATE)) {
                        long date = data.getLongExtra(DateTimeModel.EXTRA_DATE, 0);
                        presenter.updateDate(date);
                    }
                    if (data.hasExtra(DateTimeModel.EXTRA_TIME)) {
                        long time = data.getLongExtra(DateTimeModel.EXTRA_TIME, 0);
                        presenter.updateTime(time);
                    }
                }
                break;
            case MeetingRequestPresenter.TIME_SELECT_REQUEST_CODE:
                if (resultCode == RESULT_OK) {
                    if (data==null)
                        return;
                    if (data.hasExtra(DateTimeModel.EXTRA_DATE)) {
                        long date = data.getLongExtra(DateTimeModel.EXTRA_DATE, 0);
                        presenter.updateDate(date);
                    }
                    if (data.hasExtra(DateTimeModel.EXTRA_TIME)) {
                        long time = data.getLongExtra(DateTimeModel.EXTRA_TIME, 0);
                        presenter.updateTime(time);
                    }
                }
                break;
            case MeetingRequestModel.NEW_MEETING_REQUEST_CODE:
                finish();
                break;
        }
    }
}
