package in.appnow.ypo.android.ui.result.mvp;

import android.support.v7.app.AppCompatActivity;

import in.appnow.ypo.android.R;
import in.appnow.ypo.android.mvp_base.BaseModel;
import in.appnow.ypo.android.rest.APIInterface;
import in.appnow.ypo.android.ui.create_new_bottom_sheet.CreateNewBottomSheet;
import in.appnow.ypo.android.ui.dashboard_contact.DashboardContactFragment;
import in.appnow.ypo.android.ui.meeting.MeetingFragment;
import in.appnow.ypo.android.ui.profile.ProfileFragment;
import in.appnow.ypo.android.ui.result.ResultActivity;
import in.appnow.ypo.android.ui.result.ResultEnum;
import in.appnow.ypo.android.utils.FragmentUtils;

/**
 * Created by sonu on 17:22, 23/10/18
 * Copyright (c) 2018 . All rights reserved.
 */
public class ResultModel extends BaseModel {
    private final AppCompatActivity appCompatActivity;
    private final APIInterface apiInterface;

    public ResultModel(AppCompatActivity appCompatActivity, APIInterface apiInterface) {
        super(appCompatActivity);
        this.appCompatActivity = appCompatActivity;
        this.apiInterface = apiInterface;
    }

    public ResultEnum getResultEnum() {
        return (ResultEnum) appCompatActivity.getIntent().getSerializableExtra(ResultActivity.EXTRA_ENUM);
    }

    public String getUserName() {
        return appCompatActivity.getIntent().getStringExtra(ResultActivity.EXTRA_NAME);

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


    public void closeActivity() {
        appCompatActivity.finish();
    }
}
