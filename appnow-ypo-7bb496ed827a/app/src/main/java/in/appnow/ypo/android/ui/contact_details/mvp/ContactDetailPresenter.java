package in.appnow.ypo.android.ui.contact_details.mvp;

import android.support.annotation.Nullable;
import android.view.View;

import in.appnow.ypo.android.interfaces.RetroAPICallback;
import in.appnow.ypo.android.mvp_base.BasePresenter;
import in.appnow.ypo.android.rest.response.Contacts;
import in.appnow.ypo.android.rest.response.MemberRequestResponse;
import in.appnow.ypo.android.rest.response.Tasks;
import in.appnow.ypo.android.ui.share_details.mvp.ShareDetailsModel;
import in.appnow.ypo.android.ui.share_details.mvp.ShareDetailsView;
import in.appnow.ypo.android.utils.Logger;
import in.appnow.ypo.android.utils.ToastUtils;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Abhishek Thanvi on 15/11/18.
 * Copyright © 2018 Abhishek Thanvi. All rights reserved.
 */

public class ContactDetailPresenter implements BasePresenter, RetroAPICallback {

    private static final int CONTACT_DETAIL_REQUEST_CODE = 1;
    private final ContactDetailView view;
    private final ContactDetailModel model;
    private String contactId;
    private Contacts contacts;
    private Tasks tasks = null;


    public ContactDetailPresenter(ContactDetailView view, ContactDetailModel model) {
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

        getContactDetails(contactId);

    }


    public void setShareDetails(){
        if (contacts != null){
            view.emailSwitch.setChecked(false);
            view.locationSwitch.setChecked(false);
            view.contactSwitch.setChecked(false);
            view.socialSwitch.setChecked(false);

        }

    }

    private void getContactDetails(String contactId) {
        model.getContactDetail(this, CONTACT_DETAIL_REQUEST_CODE,contactId);
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onResponse(Call<?> call, Response<?> response, int requestCode, @Nullable Object request) {
        switch (requestCode) {
            case CONTACT_DETAIL_REQUEST_CODE:
                if (response.isSuccessful()) {
                    Contacts requestResponse = (Contacts) response.body();
                    if (requestResponse!=null){
                        contacts = requestResponse;

                        view.updateViews(requestResponse);
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
        }
        model.dismissProgressDialog();
    }

    @Override
    public void onFailure(Call<?> call, Throwable t, int requestCode, @Nullable Object request) {
        switch (requestCode) {
            case CONTACT_DETAIL_REQUEST_CODE:
                ToastUtils.shortToast("Failed to fetch details.");
                model.closeActivity();
                break;

        }
    }

    @Override
    public void onNoNetwork(int requestCode) {
        switch (requestCode) {
            case CONTACT_DETAIL_REQUEST_CODE:
                model.closeActivity();
                break;
        }
    }

    public void setContactId(String contactId){
        this.contactId = contactId;
    }
}
