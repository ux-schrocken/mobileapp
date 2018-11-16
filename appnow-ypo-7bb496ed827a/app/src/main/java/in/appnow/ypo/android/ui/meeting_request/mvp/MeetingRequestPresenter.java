package in.appnow.ypo.android.ui.meeting_request.mvp;

import android.support.annotation.Nullable;

import java.util.List;

import in.appnow.ypo.android.interfaces.RetroAPICallback;
import in.appnow.ypo.android.mvp_base.BasePresenter;
import in.appnow.ypo.android.rest.response.ContactResponse;
import in.appnow.ypo.android.rest.response.MeetingResponse;
import in.appnow.ypo.android.utils.DateUtils;
import in.appnow.ypo.android.utils.FragmentUtils;
import in.appnow.ypo.android.utils.Logger;
import in.appnow.ypo.android.utils.TextUtils;
import in.appnow.ypo.android.utils.ToastUtils;
import retrofit2.Call;
import retrofit2.Response;

import static in.appnow.ypo.android.utils.StringUtils.USER_ID;

/**
 * Created by sonu on 13:06, 24/10/18
 * Copyright (c) 2018 . All rights reserved.
 */
public class MeetingRequestPresenter implements BasePresenter, RetroAPICallback {
    public static final int TIME_SELECT_REQUEST_CODE = 2;
    public static final int DATE_SELECT_REQUEST_CODE = 1;
    private static final int SEND_MEETING_REQUEST_CODE = 1;
    private static final String TAG = MeetingRequestPresenter.class.getSimpleName();
    private static final int FETCH_CONTACT_LIST_REQUEST_CODE = 4;
    private final MeetingRequestView view;
    private final MeetingRequestModel model;

    private long selectedDate;
    private long selectedTime;

    public MeetingRequestPresenter(MeetingRequestView view, MeetingRequestModel model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void onCreate() {
        if (model.getContactResponse() != null) {
            view.updateContactView(model.getContactResponse());
        } else {
            fetchContactList();
        }
        view.onCancelButtonClick(view -> {
            model.dismissKeyboard(view);
            model.closeActivity();
        });

        view.onDateSelection(view -> model.openDateTimeSelection(FragmentUtils.DATE, DATE_SELECT_REQUEST_CODE));

        view.onTimeSelection(view -> model.openDateTimeSelection(FragmentUtils.TIME, TIME_SELECT_REQUEST_CODE));

        view.meetingRequestButtonClick(view -> {
            if (MeetingRequestPresenter.this.view.validateData()) {
                model.sendMeetingRequest(USER_ID,MeetingRequestPresenter.this.view.getContactResponse().getMemberId(),TextUtils.getText(MeetingRequestPresenter.this.view.dateInput), TextUtils.getText(MeetingRequestPresenter.this.view.timeInput), TextUtils.getText(MeetingRequestPresenter.this.view.meetingReasonInput), MeetingRequestPresenter.this, SEND_MEETING_REQUEST_CODE);
            }
        });

    }

    private void fetchContactList() {
        model.fetchContactList(this, FETCH_CONTACT_LIST_REQUEST_CODE);
    }

    @Override
    public void onDestroy() {

    }

    public void updateDate(long date) {
        this.selectedDate = date;
        view.updateDate(date);
    }

    public void updateTime(long time) {
        this.selectedTime = time;
        view.updateTime(time);
    }

    @Override
    public void onResponse(Call<?> call, Response<?> response, int requestCode, @Nullable Object request) {
        switch (requestCode) {
            case SEND_MEETING_REQUEST_CODE:
                if (response.isSuccessful()) {
                    MeetingResponse meetingResponse = (MeetingResponse) response.body();
                    if (meetingResponse != null) {
                        String message = String.format(view.getNewMeetingDateMessage(), view.getContactResponse().getMemberName(), DateUtils.parseDate(selectedDate, DateUtils.MEETING_RESULT_DATE_FORMAT), DateUtils.parseDate(selectedTime, DateUtils.MEETING_RESULT_TIME_FORMAT));
                        model.meetingRequestSent(message);
                    } else {
                        ToastUtils.shortToast("Oops!! Some error occurred.");
                    }

                } else {
                    ToastUtils.shortToast("Oops!! Some error occurred.");
                }
                break;

            case FETCH_CONTACT_LIST_REQUEST_CODE:
                if (response.isSuccessful()) {
                    ContactResponse contactResponse = (ContactResponse) response.body();
                    if (contactResponse != null && contactResponse.getContacts().size() > 0) {
                        view.updateContactList(contactResponse.getContacts());
                    } else {
                        ToastUtils.shortToast("No contacts available.");
                        model.closeActivity();
                    }
                } else {
                    ToastUtils.shortToast("No contacts available.");
                    model.closeActivity();
                }
                break;
        }
        model.dismissProgressDialog();
    }

    @Override
    public void onFailure(Call<?> call, Throwable t, int requestCode, @Nullable Object request) {
        switch (requestCode) {
            case SEND_MEETING_REQUEST_CODE:
                Logger.ErrorLog(TAG, "Meeting Request failed : " + t.getLocalizedMessage());
                ToastUtils.shortToast("Oops!! Some error occurred.");
                break;
            case FETCH_CONTACT_LIST_REQUEST_CODE:
                Logger.ErrorLog(TAG, "CONTACT Error : " + t.getLocalizedMessage());
                ToastUtils.shortToast("No contacts available.");
                model.closeActivity();
                break;
        }
    }

    @Override
    public void onNoNetwork(int requestCode) {
        switch (requestCode) {
            case SEND_MEETING_REQUEST_CODE:
                break;
            case FETCH_CONTACT_LIST_REQUEST_CODE:
                model.closeActivity();
                break;
        }
    }
}
