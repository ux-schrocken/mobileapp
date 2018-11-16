package in.appnow.ypo.android.ui.add_new_contact.mvp;

import android.support.annotation.Nullable;
import android.view.View;

import in.appnow.ypo.android.interfaces.RetroAPICallback;
import in.appnow.ypo.android.mvp_base.BasePresenter;
import in.appnow.ypo.android.utils.KeyboardUtils;
import in.appnow.ypo.android.utils.Logger;
import in.appnow.ypo.android.utils.TextUtils;
import in.appnow.ypo.android.utils.ToastUtils;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by sonu on 16:23, 23/10/18
 * Copyright (c) 2018 . All rights reserved.
 */
public class AddNewContactPresenter implements BasePresenter, RetroAPICallback {
    private static final int GIVE_ACCESS_REQUEST_CODE = 1;
    private static final String TAG = AddNewContactPresenter.class.getSimpleName();
    private final AddNewContactView view;
    private final AddNewContactModel model;

    public AddNewContactPresenter(AddNewContactView view, AddNewContactModel model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void onCreate() {
        view.onAddContactButtonClick(view -> {
            if (AddNewContactPresenter.this.view.validateMemberIdentifier()) {
                model.dismissKeyboard(view);
                model.giveRequestAccess(this, GIVE_ACCESS_REQUEST_CODE,AddNewContactPresenter.this.view.memberIdentifierInput.getText().toString());
            }
        });
        view.onCancelButtonClick(view -> {
            model.dismissKeyboard(view);
            AddNewContactPresenter.this.view.clearInput();
            model.closeActivity();
        });
        view.onClearButtonClick(view -> AddNewContactPresenter.this.view.clearInput());
    }


    @Override
    public void onDestroy() {

    }

    @Override
    public void onResponse(Call<?> call, Response<?> response, int requestCode, @Nullable Object request) {
        switch (requestCode) {
            case GIVE_ACCESS_REQUEST_CODE:
                if (response.isSuccessful()) {
                    model.openResultActivity(TextUtils.getText(view.memberIdentifierInput));
                } else {
                    ToastUtils.shortToast("Failed to share contact details.");
                }
                break;
        }
        model.dismissProgressDialog();
    }

    @Override
    public void onFailure(Call<?> call, Throwable t, int requestCode, @Nullable Object request) {
        switch (requestCode) {
            case GIVE_ACCESS_REQUEST_CODE:
                Logger.ErrorLog(TAG, "Contact Share Failed: " + t.getLocalizedMessage());
                ToastUtils.shortToast("Failed to share contact details.");
                break;
        }
    }

    @Override
    public void onNoNetwork(int requestCode) {
        switch (requestCode) {
            case GIVE_ACCESS_REQUEST_CODE:
                break;
        }
    }
}
