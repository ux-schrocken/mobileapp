package in.appnow.ypo.android.ui.meeting.mvp.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.appnow.ypo.android.BuildConfig;
import in.appnow.ypo.android.R;
import in.appnow.ypo.android.app_base.YPOApplication;
import in.appnow.ypo.android.dialogs.ProgressDialogFragment;
import in.appnow.ypo.android.rest.APIInterface;
import in.appnow.ypo.android.rest.response.MeetingResponse;
import in.appnow.ypo.android.rest.response.MemberRequestResponse;
import in.appnow.ypo.android.rest.response.OpenMeetingResponse;
import in.appnow.ypo.android.utils.DateUtils;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
// sam edited

import in.appnow.ypo.android.interfaces.RetroAPICallback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import okhttp3.OkHttpClient;

/**
 * Created by sonu on 12:46, 25/10/18
 * Copyright (c) 2018 . All rights reserved.
 */
public class MeetingViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.meeting_row_delete_button)
    ImageView deleteButton;
    @BindView(R.id.meeting_row_day_label)
    TextView dayLabel;
    @BindView(R.id.meeting_child_row_root_layout)
    LinearLayout rootLayout;
    //MEETING_ROW_TITLE_LABEL
    @BindView(R.id.meeting_row_title_label)
    TextView titleLabel;
    @BindView(R.id.meeting_row_description_label)
    TextView descriptionLabel;
    @BindView(R.id.meeting_row_with_person_name_label)
    TextView withPersonName;

    // sam " added meetingidView to add meeting id to be used while deleting
    @BindView(R.id.meetingIDView)
    TextView meetingIdView;


    private Context context;
    private OnRemoveMeetingListener onRemoveMeetingListener;

    public MeetingViewHolder(Context context, @NonNull View itemView, OnRemoveMeetingListener onRemoveMeetingListener) {
        super(itemView);
        this.context = context;
        this.onRemoveMeetingListener = onRemoveMeetingListener;
        ButterKnife.bind(this, itemView);
    }

    // gets the data from MeetingResponse

    public void bindData(boolean isFirstItem, OpenMeetingResponse previousObject, OpenMeetingResponse response, boolean isEditMode) {
        String memberId;
        titleLabel.setText(response.getReasonForMeeting());
        memberId = response.getMemberId();
        descriptionLabel.setText(response.getTimeOfMeeting());
       // meetingIdView.setText(response.getMeetingId());
        getMemberNamefromID(memberId);


        // sam : changed from OPEN_MEETING_DATE_FORMAT to MEETING_DATE_FORMAT
        if (isFirstItem) {
            dayLabel.setVisibility(View.VISIBLE);
            dayLabel.setText(DateUtils.parseDate(DateUtils.convertStringDateToMilliSecond(response.getDateOfMeeting(), DateUtils.MEETING_DATE_FORMAT), DateUtils.OPEN_MEETING_DISPLAY_DATE_FORMAT));
        } else {
            if (previousObject != null) {
                if (previousObject.getDateOfMeeting().equalsIgnoreCase(response.getDateOfMeeting())) {
                    dayLabel.setVisibility(View.INVISIBLE);
                } else {
                    dayLabel.setVisibility(View.VISIBLE);
                    dayLabel.setText(DateUtils.parseDate(DateUtils.convertStringDateToMilliSecond(response.getDateOfMeeting(), DateUtils.MEETING_DATE_FORMAT), DateUtils.OPEN_MEETING_DISPLAY_DATE_FORMAT));
                }
            }
        }

        if (isEditMode) {
            deleteButton.setVisibility(View.VISIBLE);
            deleteButton.setOnClickListener(view -> {
                meetingIdView.getText();
                if (onRemoveMeetingListener != null) {
                    //from here meetingId = null
                   String temp= response.getMeetingId();
                    onRemoveMeetingListener.onRemoveMeeting(response);
                }
            });
        } else {
            deleteButton.setVisibility(View.GONE);
        }
    }

    public interface OnRemoveMeetingListener {
        public void onRemoveMeeting(OpenMeetingResponse response);
    }


    // sam : added
    public void getMemberNamefromID(String memberId) {


        Retrofit retrofit = new Retrofit.Builder()
                //.client(httpClient)

                .baseUrl(BuildConfig.END_POINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIInterface api = retrofit.create(APIInterface.class);

        api.memberRequest(memberId).enqueue(new Callback<MemberRequestResponse>() {
            @Override
            public void onResponse(Call<MemberRequestResponse> call,
                                   Response<MemberRequestResponse> response) {
                //  Log.d(TAG, "onResponse: ");
                MemberRequestResponse memberRequestResponse = response.body();
                // System.out.println("Light :"+memberRequestResponse.getMemberName());
                withPersonName.setText("with " + memberRequestResponse.getMemberName());

            }

            @Override
            public void onFailure(Call<MemberRequestResponse> call, Throwable t) {
                // Log.d(TAG, "onFailure: ");
            }
        });

    }


    public void removeMeetingfromID(String removeMeetingID){
        Retrofit retrofit = new Retrofit.Builder()
                //.client(httpClient)
                .baseUrl(BuildConfig.END_POINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIInterface api = retrofit.create(APIInterface.class);
        api.removeMeetings(removeMeetingID).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                // use response.code, response.headers, etc.
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                // handle failure
            }
        });
    }


}
