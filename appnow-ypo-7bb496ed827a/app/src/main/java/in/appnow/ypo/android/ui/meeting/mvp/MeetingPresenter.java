package in.appnow.ypo.android.ui.meeting.mvp;

import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import in.appnow.ypo.android.interfaces.RetroAPICallback;
import in.appnow.ypo.android.mvp_base.BasePresenter;
import in.appnow.ypo.android.rest.response.OpenMeetingResponse;
import in.appnow.ypo.android.ui.meeting.mvp.view.MeetingView;
import in.appnow.ypo.android.ui.meeting.mvp.view.MeetingViewHolder;
import in.appnow.ypo.android.utils.Logger;
import in.appnow.ypo.android.utils.ToastUtils;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by sonu on 18:14, 18/10/18
 * Copyright (c) 2018 . All rights reserved.
 */
public class MeetingPresenter implements BasePresenter, RetroAPICallback {
    private static final int FETCH_OPEN_MEETINGS_REQUEST_CODE = 1;
    private static final int REMOVE_MEETING_REQUEST_CODE = 2;
    private static final int REFRESH_OPEN_MEETINGS_REQUEST_CODE = 3;
    private static final int FETCH_MEETING_ACCEPTED_REQUEST_CODE = 4;
    private static final int FETCH_MEETING_DENIED_REQUEST_CODE = 5;
    private static final String TAG = MeetingPresenter.class.getSimpleName();
    private final MeetingView view;
    private final MeetingModel model;

    public MeetingPresenter(MeetingView view, MeetingModel model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void onCreate() {
        fetchMeetings(FETCH_OPEN_MEETINGS_REQUEST_CODE);


        view.meetingAllClick(view -> {
            fetchMeetings(FETCH_OPEN_MEETINGS_REQUEST_CODE);
            ToastUtils.shortToast("all button is clicked");
        });
        view.meetingAcceptedClick(view -> {
            fetchMeetingAccepted(FETCH_MEETING_ACCEPTED_REQUEST_CODE);
            ToastUtils.shortToast("Accepted Button is clicked");
        });
        view.meetingDeniedClick(view -> {
            fetchGetMeetingDeniedList(FETCH_MEETING_DENIED_REQUEST_CODE);
            ToastUtils.shortToast("Meeting denied clicked");
        });

    }


    private void fetchMeetings(int requestCode) {
        model.fetchMeetings(this, requestCode);

    }

    private void fetchMeetingAccepted(int requestCode) {
        model.fetchGetMeetingAcceptedList(this, requestCode);
    }

    private void fetchGetMeetingDeniedList(int requestCode) {
        model.fetchGetMeetingDeniedList(this, requestCode);
    }


    private void removetheMeeting(int requestCode) {
        //  model.removeMeeting(this, requestCode, String meetingId);
    }


    @Override
    public void onDestroy() {

    }

    @Override
    public void onResponse(Call<?> call, Response<?> response, int requestCode, @Nullable Object request) {
        switch (requestCode) {
            case REFRESH_OPEN_MEETINGS_REQUEST_CODE:
                break;
            case FETCH_OPEN_MEETINGS_REQUEST_CODE:
                if (response.isSuccessful()) {
                    List<OpenMeetingResponse> openMeetingResponseList = (List<OpenMeetingResponse>) response.body();
                    if (openMeetingResponseList != null && openMeetingResponseList.size() > 0) {
                        // here meetingID = null
                        view.updateOpenMeetings(requestCode == REFRESH_OPEN_MEETINGS_REQUEST_CODE, openMeetingResponseList, response1 -> model.removeMeeting(MeetingPresenter.this, REMOVE_MEETING_REQUEST_CODE, response1.getMeetingId()));
                    } else {
                        view.isListEmpty(true, "No meetings.");
                    }
                } else {
                    view.isListEmpty(true, "Server error!!");
                }
                break;
            case FETCH_MEETING_ACCEPTED_REQUEST_CODE:
                if (response.isSuccessful()) {
                    //here happening cant be cast error 1/12
                    List<OpenMeetingResponse> openMeetingResponseList = (List<OpenMeetingResponse>) response.body();
                    Log.e("samtest", openMeetingResponseList.toString());
                    if (openMeetingResponseList != null && openMeetingResponseList.size() > 0) {
                        // here meetingID = null
                        view.updateMeetingsAccepted(requestCode == FETCH_MEETING_ACCEPTED_REQUEST_CODE, openMeetingResponseList);
                    } else {
                        view.isListEmpty(true, "No meetings.");
                    }
                } else {
                    view.isListEmpty(true, "Server error!!");
                }
                break;
            case FETCH_MEETING_DENIED_REQUEST_CODE:
                if (response.isSuccessful()) {
                    //here happening cant be cast error 1/12
                    List<OpenMeetingResponse> openMeetingResponseList = (List<OpenMeetingResponse>) response.body();
                    Log.e("samtest", openMeetingResponseList.toString());
                    if (openMeetingResponseList != null && openMeetingResponseList.size() > 0) {
                        // here meetingID = null
                        view.updateMeetingDenied(requestCode == FETCH_MEETING_DENIED_REQUEST_CODE, openMeetingResponseList);
                    } else {
                        view.isListEmpty(true, "No meetings.");
                    }
                } else {
                    view.isListEmpty(true, "Server error!!");
                }
                break;
            case REMOVE_MEETING_REQUEST_CODE:
                if (response.isSuccessful()) {
                   // (new Handler()).postDelayed(this::fetchMeetings(this, FETCH_OPEN_MEETINGS_REQUEST_CODE);, 1000);
                    (new Handler()).postDelayed(new Runnable() {
                        public void run() {
                            // Actions to do after 5 seconds
                            fetchMeetings(FETCH_OPEN_MEETINGS_REQUEST_CODE);
                        }
                    }, 1000);

                    ToastUtils.shortToast("Meeting removed successfully.");

                    // refresh meeting after deleting
                   // model.fetchMeetings(this, FETCH_OPEN_MEETINGS_REQUEST_CODE);
                   // fetchMeetings(REFRESH_OPEN_MEETINGS_REQUEST_CODE);
                } else {
                    ToastUtils.shortToast("Oops!! Some error occurred onResponse removing Meeting.");
                }
                break;
        }
        model.dismissProgressDialog();
    }

    @Override
    public void onFailure(Call<?> call, Throwable t, int requestCode, @Nullable Object request) {
        switch (requestCode) {
            case FETCH_OPEN_MEETINGS_REQUEST_CODE:
                Logger.ErrorLog(TAG, "Meetings Error : " + t.getLocalizedMessage());
                view.isListEmpty(true, "Server error!!");
                break;
            case REMOVE_MEETING_REQUEST_CODE:
                Logger.ErrorLog(TAG, "Remove Meetings Error : " + t.getLocalizedMessage());
                ToastUtils.shortToast("Oops!! Some error occurred onFailure removing Meeting.");
                break;
        }
    }

    @Override
    public void onNoNetwork(int requestCode) {
        switch (requestCode) {
            case FETCH_OPEN_MEETINGS_REQUEST_CODE:
                view.isListEmpty(true, "Please connect to internet");
                break;
        }
    }
}
