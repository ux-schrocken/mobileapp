package in.appnow.ypo.android.ui.meeting.mvp;

import android.support.v7.app.AppCompatActivity;

import in.appnow.ypo.android.interfaces.RetroAPICallback;
import in.appnow.ypo.android.mvp_base.BaseModel;
import in.appnow.ypo.android.rest.APIInterface;
import in.appnow.ypo.android.rest.BaseService;

import static in.appnow.ypo.android.utils.StringUtils.USER_ID;

/**
 * Created by sonu on 18:13, 18/10/18
 * Copyright (c) 2018 . All rights reserved.
 */
public class MeetingModel extends BaseModel {
    private final AppCompatActivity appCompatActivity;
    private final APIInterface apiInterface;

    public MeetingModel(AppCompatActivity appCompatActivity, APIInterface apiInterface) {
        super(appCompatActivity);
        this.appCompatActivity = appCompatActivity;
        this.apiInterface = apiInterface;
    }

    public void fetchMeetings(RetroAPICallback apiCallback, int requestCode) {
        BaseService.getOpenMeetings(appCompatActivity, apiInterface, apiCallback, requestCode);
    }
    public void removeMeeting(RetroAPICallback retroAPICallback, int requestCode,String meetingId) {
        BaseService.removeMeetings(appCompatActivity,apiInterface,retroAPICallback, requestCode,meetingId);
    }

//    public void fetchMemberData(RetroAPICallback apiCallback, int requestCode) {
//
//        BaseService.memberRequest(appCompatActivity, USER_ID, apiInterface, apiCallback, requestCode);
//    }
}
