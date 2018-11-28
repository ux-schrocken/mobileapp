package in.appnow.ypo.android.ui.dashboard_contact.mvp;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import in.appnow.ypo.android.R;
import in.appnow.ypo.android.interfaces.RetroAPICallback;
import in.appnow.ypo.android.mvp_base.BaseModel;
import in.appnow.ypo.android.rest.APIInterface;
import in.appnow.ypo.android.rest.BaseService;
import in.appnow.ypo.android.rest.response.ContactDetail;
import in.appnow.ypo.android.rest.response.Tasks;
import in.appnow.ypo.android.ui.contact_details.ContactDetailActivity;
import in.appnow.ypo.android.ui.dashboard_contact.DashboardContactFragment;
import in.appnow.ypo.android.ui.dashboard_contact.mvp.view.dashboard.DashboardViewHolder;
import in.appnow.ypo.android.ui.result.ResultActivity;
import in.appnow.ypo.android.ui.result.ResultEnum;
import in.appnow.ypo.android.utils.FragmentUtils;

/**
 * Created by sonu on 18:13, 18/10/18
 * Copyright (c) 2018 . All rights reserved.
 */
public class DashboardContactModel extends BaseModel {
    private final AppCompatActivity appCompatActivity;
    private final APIInterface apiInterface;
    public DashboardContactModel(AppCompatActivity appCompatActivity, APIInterface apiInterface) {
        super(appCompatActivity);
        this.appCompatActivity = appCompatActivity;
        this.apiInterface = apiInterface;
    }



    // API CALLS
    public void fetchTaskList(RetroAPICallback retroAPICallback, int requestCode) {
        BaseService.getTaskList(appCompatActivity,apiInterface,retroAPICallback,requestCode);
    }

    public void fetchContactList(RetroAPICallback retroAPICallback, int requestCode) {
        Log.e("test", String.valueOf(requestCode));
        BaseService.getContactList(appCompatActivity,apiInterface,retroAPICallback,requestCode);
    }
    public void fetchContactAcceptedList(RetroAPICallback retroAPICallback, int requestCode) {
        Log.e("test", String.valueOf(requestCode));
        BaseService.getContactAcceptedList(appCompatActivity,apiInterface,retroAPICallback,requestCode);
    }
    public void fetchContactDeniedList(RetroAPICallback retroAPICallback, int requestCode) {
        Log.e("test", String.valueOf(requestCode));
        BaseService.getContactDeniedList(appCompatActivity,apiInterface,retroAPICallback,requestCode);
    }


    public void deleteContact(RetroAPICallback retroAPICallback,int requestCode,String contactId) {
        BaseService.deleteContact(appCompatActivity,apiInterface,retroAPICallback,requestCode,contactId);
    }

    public void denyRequest(RetroAPICallback retroAPICallback,int requestCode,String taskId) {
        BaseService.denyRequest(appCompatActivity,apiInterface,retroAPICallback,requestCode,taskId);
    }

    public void acceptMeeting(RetroAPICallback retroAPICallback,int requestCode,String taskId) {
        BaseService.acceptMeetingRequest(appCompatActivity,apiInterface,retroAPICallback,requestCode,taskId);
    }




    public void showResult(Tasks response){
        if (response.getTaskType().equalsIgnoreCase("contact")) {
            ResultActivity.openResultActivity(appCompatActivity, ResultEnum.DENY_CONTACT_REQUEST, response.getMemberName(), DashboardViewHolder.CONTACT_DENY_REQUEST_CODE);
        } else if (response.getTaskType().equalsIgnoreCase("meeting")) {
            ResultActivity.openResultActivity(appCompatActivity, ResultEnum.DENY_MEETING_REQUEST, response.getMemberName(), DashboardViewHolder.MEETING_DENY_REQUEST_CODE);
        }
    }
    public void showMeetingResult(Tasks response){
        if (response.getTaskType().equalsIgnoreCase("meeting")) {
            ResultActivity.openResultActivity(appCompatActivity, ResultEnum.ACCEPT_MEETING_REQUEST, response.getMemberName(), DashboardViewHolder.MEETING_DENY_REQUEST_CODE);
        }
    }

    public void replaceContactFragment() {
        FragmentUtils.replaceFragment(appCompatActivity.getSupportFragmentManager(), R.id.main_container, DashboardContactFragment.newInstance(FragmentUtils.CONTACT), FragmentUtils.CONTACT_FRAGMENT, false);
    }
    public void replaceAcceptedContactFragment() {
        FragmentUtils.replaceFragment(appCompatActivity.getSupportFragmentManager(), R.id.main_container, DashboardContactFragment.newInstance(FragmentUtils.CONTACT), FragmentUtils.CONTACT_ACCEPTED_FRAGMENT, false);
    }
    public void replaceContactDeniedFragment() {
        FragmentUtils.replaceFragment(appCompatActivity.getSupportFragmentManager(), R.id.main_container, DashboardContactFragment.newInstance(FragmentUtils.CONTACT), FragmentUtils.CONTACT_DENIED_FRAGMENT, false);
    }




    public void viewContactDetail(String contactId){
        ContactDetailActivity.openContactDetailsActivity(appCompatActivity, contactId);

    }
    public void replaceDashboardFragment() {
        FragmentUtils.replaceFragment(appCompatActivity.getSupportFragmentManager(), R.id.main_container, DashboardContactFragment.newInstance(FragmentUtils.DASHBOARD), FragmentUtils.DASHBOARD_FRAGMENT, false);
    }
}
