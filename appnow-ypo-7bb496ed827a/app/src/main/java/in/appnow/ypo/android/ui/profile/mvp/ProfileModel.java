package in.appnow.ypo.android.ui.profile.mvp;

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
public class ProfileModel extends BaseModel {
    private final AppCompatActivity appCompatActivity;
    private final APIInterface apiInterface;

    public ProfileModel(AppCompatActivity appCompatActivity, APIInterface apiInterface) {
        super(appCompatActivity);
        this.appCompatActivity = appCompatActivity;
        this.apiInterface = apiInterface;
    }

    public void fetchMemberData(RetroAPICallback apiCallback, int requestCode) {
        BaseService.memberRequest(appCompatActivity, USER_ID, apiInterface, apiCallback, requestCode);
    }

    public void fetchDefaultSharingData(RetroAPICallback apiCallback, int requestCode,String contactId){
        BaseService.getDefaultSharingRule(appCompatActivity,apiInterface,apiCallback,requestCode,contactId);
    }
}
