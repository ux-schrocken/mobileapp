package in.appnow.ypo.android.ui.add_new_contact.mvp;

import android.support.v7.app.AppCompatActivity;
import android.view.View;

import in.appnow.ypo.android.interfaces.RetroAPICallback;
import in.appnow.ypo.android.mvp_base.BaseModel;
import in.appnow.ypo.android.rest.APIInterface;
import in.appnow.ypo.android.rest.BaseService;
import in.appnow.ypo.android.ui.result.ResultActivity;
import in.appnow.ypo.android.ui.result.ResultEnum;
import in.appnow.ypo.android.utils.KeyboardUtils;

/**
 * Created by sonu on 16:23, 23/10/18
 * Copyright (c) 2018 . All rights reserved.
 */
public class AddNewContactModel extends BaseModel {
    public static final int ADD_NEW_CONTACT_REQUEST_CODE = 5;
    private final AppCompatActivity appCompatActivity;
    private final APIInterface apiInterface;

    public AddNewContactModel(AppCompatActivity appCompatActivity, APIInterface apiInterface) {
        super(appCompatActivity);
        this.appCompatActivity = appCompatActivity;
        this.apiInterface = apiInterface;
    }

    public void closeActivity() {
        appCompatActivity.finish();
    }

    public void giveRequestAccess(RetroAPICallback apiCallback, int requestCode,String memberId) {
        BaseService.addNewMember(appCompatActivity, memberId,apiInterface, apiCallback, requestCode);
    }

    public void openResultActivity(String identifier) {
        ResultActivity.openResultActivity(appCompatActivity, ResultEnum.NEW_CONTACT, identifier, ADD_NEW_CONTACT_REQUEST_CODE);
    }
}
