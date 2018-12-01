package in.appnow.ypo.android.ui.contact_details.mvp;

import android.support.v7.app.AppCompatActivity;

import in.appnow.ypo.android.R;
import in.appnow.ypo.android.interfaces.RetroAPICallback;
import in.appnow.ypo.android.mvp_base.BaseModel;
import in.appnow.ypo.android.rest.APIInterface;
import in.appnow.ypo.android.rest.BaseService;
import in.appnow.ypo.android.ui.dashboard_contact.DashboardContactFragment;
import in.appnow.ypo.android.ui.result.ResultActivity;
import in.appnow.ypo.android.ui.result.ResultEnum;
import in.appnow.ypo.android.utils.FragmentUtils;

import static in.appnow.ypo.android.utils.StringUtils.USER_ID;

/**
 * Created by Abhishek Thanvi on 15/11/18.
 * Copyright © 2018 Abhishek Thanvi. All rights reserved.
 */

public class ContactDetailModel extends BaseModel {
    public static final int CONTACT_REQUEST_CODE = 3;
    private final AppCompatActivity appCompatActivity;
    private final APIInterface apiInterface;

    public ContactDetailModel(AppCompatActivity appCompatActivity, APIInterface apiInterface) {
        super(appCompatActivity);
        this.appCompatActivity = appCompatActivity;
        this.apiInterface = apiInterface;
    }



    public void getContactDetail(RetroAPICallback apiCallback, int requestCode, String contactId) {
        BaseService.getContactDetails(appCompatActivity, apiInterface, apiCallback, requestCode,contactId);

    }

    public void closeActivity() {

        appCompatActivity.finish();
    }

}
