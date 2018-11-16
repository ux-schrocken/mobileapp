package in.appnow.ypo.android.ui.share_details.mvp;

import android.support.v7.app.AppCompatActivity;

import in.appnow.ypo.android.interfaces.RetroAPICallback;
import in.appnow.ypo.android.mvp_base.BaseModel;
import in.appnow.ypo.android.rest.APIInterface;
import in.appnow.ypo.android.rest.BaseService;
import in.appnow.ypo.android.ui.result.ResultActivity;
import in.appnow.ypo.android.ui.result.ResultEnum;

import static in.appnow.ypo.android.utils.StringUtils.USER_ID;

/**
 * Created by sonu on 18:38, 23/10/18
 * Copyright (c) 2018 . All rights reserved.
 */
public class ShareDetailsModel extends BaseModel {
    public static final int CONTACT_REQUEST_CODE = 3;
    private final AppCompatActivity appCompatActivity;
    private final APIInterface apiInterface;

    public ShareDetailsModel(AppCompatActivity appCompatActivity, APIInterface apiInterface) {
        super(appCompatActivity);
        this.appCompatActivity = appCompatActivity;
        this.apiInterface = apiInterface;
    }

    public void openResultActivity(String userName) {
        ResultActivity.openResultActivity(appCompatActivity, ResultEnum.ACCEPT_CONTACT_REQUEST, userName, CONTACT_REQUEST_CODE);
    }

    public void acceptMemberRequest(RetroAPICallback apiCallback, int requestCode) {
        BaseService.memberRequest(appCompatActivity, USER_ID, apiInterface, apiCallback, requestCode);
    }

    public void setShareDetails(RetroAPICallback apiCallback, int requestCode , String taskId, String location, String contact, String email, String social, String meetings, String about){
        BaseService.setShareDetails(appCompatActivity, apiInterface, apiCallback, requestCode,taskId,location,contact,email,social,meetings,about);

    }

    public void fetchDefaultSharingData(RetroAPICallback apiCallback, int requestCode,String contactId){
        BaseService.getDefaultSharingRule(appCompatActivity,apiInterface,apiCallback,requestCode,contactId);
    }

    public void closeActivity() {
        appCompatActivity.finish();
    }
}
