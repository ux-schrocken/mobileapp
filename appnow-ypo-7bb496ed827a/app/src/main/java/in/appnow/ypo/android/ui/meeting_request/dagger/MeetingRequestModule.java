package in.appnow.ypo.android.ui.meeting_request.dagger;

import android.support.v7.app.AppCompatActivity;

import dagger.Module;
import dagger.Provides;
import in.appnow.ypo.android.rest.APIInterface;
import in.appnow.ypo.android.ui.meeting_request.mvp.MeetingRequestModel;
import in.appnow.ypo.android.ui.meeting_request.mvp.MeetingRequestPresenter;
import in.appnow.ypo.android.ui.meeting_request.mvp.MeetingRequestView;

/**
 * Created by sonu on 13:06, 24/10/18
 * Copyright (c) 2018 . All rights reserved.
 */
@Module
public class MeetingRequestModule {

    private final AppCompatActivity appCompatActivity;

    public MeetingRequestModule(AppCompatActivity appCompatActivity) {
        this.appCompatActivity = appCompatActivity;
    }

    @Provides
    @MeetingRequestScope
    public MeetingRequestView meetingRequestView() {
        return new MeetingRequestView(appCompatActivity);
    }

    @Provides
    @MeetingRequestScope
    public MeetingRequestModel meetingRequestModel(APIInterface apiInterface) {
        return new MeetingRequestModel(appCompatActivity, apiInterface);
    }

    @Provides
    @MeetingRequestScope
    public MeetingRequestPresenter meetingRequestPresenter(MeetingRequestView view, MeetingRequestModel model) {
        return new MeetingRequestPresenter(view, model);
    }
}
