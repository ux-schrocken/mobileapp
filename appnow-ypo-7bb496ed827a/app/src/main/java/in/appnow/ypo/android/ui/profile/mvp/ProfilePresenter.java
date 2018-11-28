package in.appnow.ypo.android.ui.profile.mvp;

import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import in.appnow.ypo.android.interfaces.RetroAPICallback;
import in.appnow.ypo.android.mvp_base.BasePresenter;
import in.appnow.ypo.android.rest.response.DefaultShareRuleResponse;
import in.appnow.ypo.android.rest.response.MemberRequestResponse;
import in.appnow.ypo.android.utils.Logger;
import in.appnow.ypo.android.utils.ToastUtils;
import retrofit2.Call;
import retrofit2.Response;

import static in.appnow.ypo.android.utils.StringUtils.USER_ID;

/**
 * Created by sonu on 18:14, 18/10/18
 * Copyright (c) 2018 . All rights reserved.
 */
public class ProfilePresenter implements BasePresenter, RetroAPICallback {
    private static final int MEMBER_DATA_REQUEST_CODE = 1;
    private static final int DEFAULT_DATA_REQUEST_CODE = 2;
    private static final String TAG = ProfilePresenter.class.getSimpleName();
    private final ProfileView view;
    private final ProfileModel model;
    public ProfilePresenter(ProfileView view, ProfileModel model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void onCreate() {
        fetchMemberData();

        view.onSwitchClick(view -> {
            setSwitchDetails();
            Toast.makeText(model.getAppCompatActivity(), "Default Sharing Rules Changed!", Toast.LENGTH_SHORT).show();
        });

    }

    private void fetchMemberData() {
        model.fetchMemberData(this, MEMBER_DATA_REQUEST_CODE);
    }


    public void setSwitchDetails(){
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

        if (USER_ID != null){
            model.editDefaultSharingRule(this, 0, USER_ID, about, email, contact, social, location, meetings);

        }else{
            ToastUtils.shortToast("Something went wrong");
        }

    }


//(@Path("memberId") String taskId, @Field("sharingdefaultmemdate") String memberLoc, @Field("sharingdefaultemail") String memberContactNum1
//            , @Field("sharingdefaultphone") String memberEmail1, @Field("sharingdefaultsocialacc") String memberSocialAcc1
//            , @Field("sharingdefaultlocation") String setMeetings1, @Field("sharingdefaultsetmeetings") String aboutMember1)

    @Override
    public void onDestroy() {

    }

    @Override
    public void onResponse(Call<?> call, Response<?> response, int requestCode, @Nullable Object request) {
        switch (requestCode) {
            case MEMBER_DATA_REQUEST_CODE:
                if (response.isSuccessful()) {
                    MemberRequestResponse memberRequestResponse = (MemberRequestResponse) response.body();
                    if (memberRequestResponse != null) {
                        view.updateViews(memberRequestResponse);
                        model.fetchDefaultSharingData(this, DEFAULT_DATA_REQUEST_CODE, USER_ID);
                    } else {
                        ToastUtils.shortToast("Oops!! Some error occurred.");
                    }
                } else {
                    ToastUtils.shortToast("Oops!! Some error occurred.");
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
            case MEMBER_DATA_REQUEST_CODE:
                Logger.ErrorLog(TAG, "Member data fetch failed : " + t.getLocalizedMessage());
                ToastUtils.shortToast("Oops!! Some error occurred.");
                break;

            case DEFAULT_DATA_REQUEST_CODE:
                Logger.ErrorLog(TAG, "Member data fetch failed : " + t.getLocalizedMessage());
                ToastUtils.shortToast("Oops!! Some error occurred.");
                break;
        }
    }

    @Override
    public void onNoNetwork(int requestCode) {
        switch (requestCode) {
            case MEMBER_DATA_REQUEST_CODE:
                break;
        }
    }
}
