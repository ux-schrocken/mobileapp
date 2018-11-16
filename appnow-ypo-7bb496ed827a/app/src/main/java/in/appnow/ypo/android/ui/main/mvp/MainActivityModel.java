package in.appnow.ypo.android.ui.main.mvp;

import android.support.v7.app.AppCompatActivity;

import in.appnow.ypo.android.R;
import in.appnow.ypo.android.mvp_base.BaseModel;
import in.appnow.ypo.android.rest.APIInterface;
import in.appnow.ypo.android.ui.create_new_bottom_sheet.CreateNewBottomSheet;
import in.appnow.ypo.android.ui.dashboard_contact.DashboardContactFragment;
import in.appnow.ypo.android.ui.meeting.MeetingFragment;
import in.appnow.ypo.android.ui.profile.ProfileFragment;
import in.appnow.ypo.android.utils.FragmentUtils;

/**
 * Created by sonu on 18:13, 18/10/18
 * Copyright (c) 2018 . All rights reserved.
 */
public class MainActivityModel extends BaseModel {
    private final AppCompatActivity appCompatActivity;
    private final APIInterface apiInterface;

    public MainActivityModel(AppCompatActivity appCompatActivity, APIInterface apiInterface) {
        super(appCompatActivity);
        this.appCompatActivity = appCompatActivity;
        this.apiInterface = apiInterface;
    }

    public void replaceDashboardFragment() {
        FragmentUtils.replaceFragment(appCompatActivity.getSupportFragmentManager(), R.id.main_container, DashboardContactFragment.newInstance(FragmentUtils.DASHBOARD), FragmentUtils.DASHBOARD_FRAGMENT, false);
    }

    public void replaceContactFragment() {
        FragmentUtils.replaceFragment(appCompatActivity.getSupportFragmentManager(), R.id.main_container, DashboardContactFragment.newInstance(FragmentUtils.CONTACT), FragmentUtils.CONTACT_FRAGMENT, false);
    }

    public void replaceMeetingFragment() {
        FragmentUtils.replaceFragment(appCompatActivity.getSupportFragmentManager(), R.id.main_container, MeetingFragment.newInstance(), FragmentUtils.MEETING_FRAGMENT, false);
    }

    public void replaceProfileFragment() {
        FragmentUtils.replaceFragment(appCompatActivity.getSupportFragmentManager(), R.id.main_container, ProfileFragment.newInstance(), FragmentUtils.PROFILE_FRAGMENT, false);
    }

    public void openCreateNewBottomSheet() {
        CreateNewBottomSheet.newInstance().show(appCompatActivity.getSupportFragmentManager(), FragmentUtils.CREATE_NEW_BOTTOM_SHEET_FRAGMENT);
    }
}
