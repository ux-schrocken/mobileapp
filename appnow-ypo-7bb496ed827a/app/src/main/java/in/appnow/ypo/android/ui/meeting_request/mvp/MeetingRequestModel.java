package in.appnow.ypo.android.ui.meeting_request.mvp;

import android.support.v7.app.AppCompatActivity;

import in.appnow.ypo.android.interfaces.RetroAPICallback;
import in.appnow.ypo.android.mvp_base.BaseModel;
import in.appnow.ypo.android.rest.APIInterface;
import in.appnow.ypo.android.rest.BaseService;
import in.appnow.ypo.android.rest.response.ContactResponse;
import in.appnow.ypo.android.rest.response.Contacts;
import in.appnow.ypo.android.ui.date_time.DateTimeSelectionActivity;
import in.appnow.ypo.android.ui.meeting_request.MeetingRequestActivity;
import in.appnow.ypo.android.ui.result.ResultActivity;
import in.appnow.ypo.android.ui.result.ResultEnum;
import in.appnow.ypo.android.utils.Logger;

/**
 * Created by sonu on 13:05, 24/10/18
 * Copyright (c) 2018 . All rights reserved.
 */
public class MeetingRequestModel extends BaseModel {
    public static final int NEW_MEETING_REQUEST_CODE = 5;
    private final AppCompatActivity appCompatActivity;
    private final APIInterface apiInterface;

    public MeetingRequestModel(AppCompatActivity appCompatActivity, APIInterface apiInterface) {
        super(appCompatActivity);
        this.appCompatActivity = appCompatActivity;
        this.apiInterface = apiInterface;
    }

    public Contacts getContactResponse() {
        if (appCompatActivity.getIntent().hasExtra(MeetingRequestActivity.EXTRA_CONTACT))
            return appCompatActivity.getIntent().getParcelableExtra(MeetingRequestActivity.EXTRA_CONTACT);
        return null;
    }

    public void fetchContactList(RetroAPICallback retroAPICallback, int requestCode) {
        BaseService.getContactList(appCompatActivity,apiInterface,retroAPICallback,requestCode);
    }

    public void closeActivity() {
        appCompatActivity.finish();
    }

    public void openDateTimeSelection(int dateTime, int requestCode) {
        DateTimeSelectionActivity.openDateTimeSelectionActivity(appCompatActivity, dateTime, requestCode);
    }

    public void sendMeetingRequest(String userId,String memberId,String dateOfMeeting, String timeOfMeeting, String reasonForMeeting, RetroAPICallback retroAPICallback, int sendMeetingRequestCode) {
        BaseService.addMeeting(appCompatActivity,userId,memberId,dateOfMeeting,timeOfMeeting,reasonForMeeting,apiInterface,retroAPICallback,sendMeetingRequestCode);
    }

    public void meetingRequestSent(String message) {
        ResultActivity.openResultActivity(appCompatActivity,ResultEnum.NEW_MEETING,message,NEW_MEETING_REQUEST_CODE);
    }
}
