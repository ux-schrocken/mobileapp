package in.appnow.ypo.android.ui.share_details.mvp;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;

import in.appnow.ypo.android.interfaces.RetroAPICallback;
import in.appnow.ypo.android.mvp_base.BasePresenter;
import in.appnow.ypo.android.rest.response.DefaultShareRuleResponse;
import in.appnow.ypo.android.rest.response.MemberRequestResponse;
import in.appnow.ypo.android.rest.response.Tasks;
import in.appnow.ypo.android.ui.result.ResultActivity;
import in.appnow.ypo.android.utils.Logger;
import in.appnow.ypo.android.utils.ToastUtils;
import retrofit2.Call;
import retrofit2.Response;

import static in.appnow.ypo.android.utils.StringUtils.USER_ID;

/**
 * Created by sonu on 18:39, 23/10/18
 * Copyright (c) 2018 . All rights reserved.
 */
public class ShareDetailsPresenter implements BasePresenter, RetroAPICallback {

    private static final int ACCEPT_MEMBER_REQUEST_CODE = 1;
    private static final int SET_DETAILS_REQUEST_CODE = 2;
    private static final int DEFAULT_DATA_REQUEST_CODE = 3;
    private final ShareDetailsView view;
    private final ShareDetailsModel model;
    private String userName;
    private MemberRequestResponse memberRequestResponse;
    private Tasks tasks = null;


    public ShareDetailsPresenter(ShareDetailsView view, ShareDetailsModel model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void onCreate() {

        view.onCancelButtonClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model.closeActivity();
            }
        });
        view.onShareDetailsButtonClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getShareDetails();
            }
        });

        acceptMemberRequest();

    }


    public void getShareDetails(){
        String location = "n"; String contact = "n"; String email = "n"; String social = "n"; String meetings = "n"; String about = "n";
        if (view.locationSwitch.isChecked()){
            location = "y";
        }
        if (view.contactSwitch.isChecked()){
            contact = "y";
        }
        if (view.emailSwitch.isChecked()){
            email = "y";
        }
        if (view.socialSwitch.isChecked()){
            social = "y";
        }
        if (view.profileAboutSwitch.isChecked()){
            about = "y";
        }
        if (view.setupMeetingSwitch.isChecked()){
            meetings = "y";
        }

        if (tasks != null){
            model.setShareDetails(this,SET_DETAILS_REQUEST_CODE,tasks.getTaskId(),location,contact,email,social,meetings,about);
        }else{
            ToastUtils.shortToast("Something went wrong");
        }

    }

    public void setShareDetails(){
        if (memberRequestResponse != null){
            view.emailSwitch.setChecked(false);
            view.locationSwitch.setChecked(false);
            view.contactSwitch.setChecked(false);
            view.socialSwitch.setChecked(false);
            if (memberRequestResponse.getDefaultEmailFlag().equalsIgnoreCase("y")){
                view.emailSwitch.setChecked(true);
            }
            if (memberRequestResponse.getDefaultLocationFlag().equalsIgnoreCase("y")){
                view.locationSwitch.setChecked(true);
            }
            if (memberRequestResponse.getDefaultPhoneFlag().equalsIgnoreCase("y")){
                view.contactSwitch.setChecked(true);
            }
            if (memberRequestResponse.getDefaultSocialFlag().equalsIgnoreCase("y")){
                view.socialSwitch.setChecked(true);
            }
        }

    }

    private void acceptMemberRequest() {
        model.acceptMemberRequest(this, ACCEPT_MEMBER_REQUEST_CODE);
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onResponse(Call<?> call, Response<?> response, int requestCode, @Nullable Object request) {
        switch (requestCode) {
            case ACCEPT_MEMBER_REQUEST_CODE:
                if (response.isSuccessful()) {
                    MemberRequestResponse requestResponse = (MemberRequestResponse) response.body();
                    if (requestResponse!=null){
                        memberRequestResponse = requestResponse;
                        userName = requestResponse.getMemberName();
                        view.updateViews(requestResponse);
                        model.fetchDefaultSharingData(this,DEFAULT_DATA_REQUEST_CODE,USER_ID);
                        setShareDetails();
                    }else{
                        ToastUtils.shortToast("Failed to fetch details.");
                        model.closeActivity();
                    }
                } else {
                    ToastUtils.shortToast("Failed to fetch details.");
                    model.closeActivity();
                }
                break;

            case SET_DETAILS_REQUEST_CODE:
                if (response.isSuccessful()){
                    model.openResultActivity(tasks.getMemberName());

                }else{
                    ToastUtils.shortToast("Failed to grant access.");
                }
                break;
            case DEFAULT_DATA_REQUEST_CODE:
                if (response.isSuccessful()) {
                    DefaultShareRuleResponse defaultShareRuleResponse = (DefaultShareRuleResponse) response.body();
                    if (defaultShareRuleResponse != null) {
                        view.updateSwitches(defaultShareRuleResponse);

                    } else {
                        ToastUtils.shortToast("Oops!! Some error occurred.");
                    }
                } else {
                    ToastUtils.shortToast("Oops!! Some error occurred.");
                }
                break;
        }
        model.dismissProgressDialog();
    }

    @Override
    public void onFailure(Call<?> call, Throwable t, int requestCode, @Nullable Object request) {
        switch (requestCode) {
            case ACCEPT_MEMBER_REQUEST_CODE:
                ToastUtils.shortToast("Failed to fetch details.");
                model.closeActivity();
                break;
            case SET_DETAILS_REQUEST_CODE:
                ToastUtils.shortToast("Failed to share details.");
                 break;
            case DEFAULT_DATA_REQUEST_CODE:
                Logger.ErrorLog("ShareDetailPresenter", "Default Member data fetch failed : " + t.getLocalizedMessage());
                ToastUtils.shortToast("Oops!! Some error occurred.");
                break;
        }
    }

    @Override
    public void onNoNetwork(int requestCode) {
        switch (requestCode) {
            case ACCEPT_MEMBER_REQUEST_CODE:
                model.closeActivity();
                break;
        }
    }

    public void taskDetail(Tasks tasks){
        this.tasks = tasks;
    }
}
