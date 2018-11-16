package in.appnow.ypo.android.ui.profile.mvp;

import android.support.annotation.Nullable;
import android.util.Log;

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
    }

    private void fetchMemberData() {
        model.fetchMemberData(this, MEMBER_DATA_REQUEST_CODE);
    }

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
                        model.fetchDefaultSharingData(this,DEFAULT_DATA_REQUEST_CODE,USER_ID);
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
