package in.appnow.ypo.android.ui.main.dagger;

import android.support.v7.app.AppCompatActivity;

import dagger.Module;
import dagger.Provides;
import in.appnow.ypo.android.rest.APIInterface;
import in.appnow.ypo.android.ui.dashboard_contact.mvp.DashboardContactModel;
import in.appnow.ypo.android.ui.dashboard_contact.mvp.DashboardContactPresenter;
import in.appnow.ypo.android.ui.dashboard_contact.mvp.view.DashboardContactView;
import in.appnow.ypo.android.ui.main.mvp.MainActivityModel;
import in.appnow.ypo.android.ui.main.mvp.MainActivityPresenter;
import in.appnow.ypo.android.ui.main.mvp.MainActivityView;
import in.appnow.ypo.android.ui.meeting.mvp.MeetingModel;
import in.appnow.ypo.android.ui.meeting.mvp.MeetingPresenter;
import in.appnow.ypo.android.ui.meeting.mvp.view.MeetingView;
import in.appnow.ypo.android.ui.profile.mvp.ProfileModel;
import in.appnow.ypo.android.ui.profile.mvp.ProfilePresenter;
import in.appnow.ypo.android.ui.profile.mvp.ProfileView;

/**
 * Created by sonu on 18:11, 18/10/18
 * Copyright (c) 2018 . All rights reserved.
 */
@Module
public class MainActivityModule {
    private final AppCompatActivity appCompatActivity;

    public MainActivityModule(AppCompatActivity appCompatActivity) {
        this.appCompatActivity = appCompatActivity;
    }

    @Provides
    @MainActivityScope
    public MainActivityView mainActivityView() {
        return new MainActivityView(appCompatActivity);
    }

    @Provides
    @MainActivityScope
    public MainActivityModel mainActivityModel(APIInterface apiInterface) {
        return new MainActivityModel(appCompatActivity, apiInterface);
    }

    @Provides
    @MainActivityScope
    public MainActivityPresenter mainActivityPresenter(MainActivityView view, MainActivityModel model) {
        return new MainActivityPresenter(view, model);
    }


    @Provides
    @MainActivityScope
    public DashboardContactView dashboardContactView() {
        return new DashboardContactView(appCompatActivity);
    }

    @Provides
    @MainActivityScope
    public DashboardContactModel dashboardContactModel(APIInterface apiInterface) {
        return new DashboardContactModel(appCompatActivity, apiInterface);
    }

    @Provides
    @MainActivityScope
    public DashboardContactPresenter dashboardContactPresenter(DashboardContactView view, DashboardContactModel model) {
        return new DashboardContactPresenter(view, model);
    }


    @Provides
    @MainActivityScope
    public MeetingView meetingView() {
        return new MeetingView(appCompatActivity);
    }

    @Provides
    @MainActivityScope
    public MeetingModel meetingModel(APIInterface apiInterface) {
        return new MeetingModel(appCompatActivity, apiInterface);
    }

    @Provides
    @MainActivityScope
    public MeetingPresenter meetingPresenter(MeetingView view, MeetingModel model) {
        return new MeetingPresenter(view, model);
    }


    @Provides
    @MainActivityScope
    public ProfileView profileView() {
        return new ProfileView(appCompatActivity);
    }

    @Provides
    @MainActivityScope
    public ProfileModel profileModel(APIInterface apiInterface) {
        return new ProfileModel(appCompatActivity, apiInterface);
    }

    @Provides
    @MainActivityScope
    public ProfilePresenter profilePresenter(ProfileView view, ProfileModel model) {
        return new ProfilePresenter(view, model);
    }
}
