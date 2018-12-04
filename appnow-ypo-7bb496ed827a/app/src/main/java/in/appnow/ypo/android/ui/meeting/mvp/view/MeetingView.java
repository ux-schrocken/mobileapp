package in.appnow.ypo.android.ui.meeting.mvp.view;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.appnow.ypo.android.R;
import in.appnow.ypo.android.rest.response.OpenMeetingResponse;

/**
 * Created by sonu on 18:13, 18/10/18
 * Copyright (c) 2018 . All rights reserved.
 */
public class MeetingView extends FrameLayout {

    @BindView(R.id.meeting_recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.empty_list_label)
    TextView emptyLabel;
    @BindView(R.id.meeting_all_button)
    TextView meetingAllButton;
    @BindView(R.id.meeting_accepted_button)
    TextView meetingAcceptedButton;
    @BindView(R.id.meeting_denied_button)
    TextView meetingDeniedButton;


    private final MeetingAdapter adapter = new MeetingAdapter();

    public MeetingView(@NonNull Context context) {
        super(context);
        inflate(context, R.layout.meeting_fragment, this);
        ButterKnife.bind(this, this);
        setUpRecyclerView();
    }

    public void meetingAllClick(OnClickListener onClickListener) {
        meetingAllButton.setOnClickListener(onClickListener);
    }

    public void meetingAcceptedClick(OnClickListener onClickListener) {
        meetingAcceptedButton.setOnClickListener(onClickListener);
    }

    public void meetingDeniedClick(OnClickListener onClickListener) {
        meetingDeniedButton.setOnClickListener(onClickListener);
    }

    public void colorChanger(int changer) {

        switch (changer) {
            case 1:

                meetingAllButton.setTextColor(Color.parseColor("#FFFFFF"));
                meetingAllButton.setBackgroundColor(Color.parseColor("#E16E38"));

                meetingAcceptedButton.setTextColor(Color.parseColor("#E16E38"));
                meetingAcceptedButton.setBackgroundColor(Color.parseColor("#FFFFFF"));

                meetingDeniedButton.setTextColor(Color.parseColor("#E16E38"));
                meetingDeniedButton.setBackgroundColor(Color.parseColor("#FFFFFF"));
                meetingAllButton.setBackground(ContextCompat.getDrawable(this.getContext(), R.drawable.segment_fill_bg));

                break;

            case 2:
                meetingAllButton.setTextColor(Color.parseColor("#E16E38"));
                meetingAllButton.setBackgroundColor(Color.parseColor("#FFFFFF"));

                meetingAcceptedButton.setTextColor(Color.parseColor("#FFFFFF"));
                meetingAcceptedButton.setBackgroundColor(Color.parseColor("#E16E38"));

                meetingDeniedButton.setTextColor(Color.parseColor("#E16E38"));
                meetingDeniedButton.setBackgroundColor(Color.parseColor("#FFFFFF"));
                meetingAcceptedButton.setBackground(ContextCompat.getDrawable(this.getContext(), R.drawable.segment_fill_bg));
                break;

            case 3:
                meetingAllButton.setTextColor(Color.parseColor("#E16E38"));
                meetingAllButton.setBackgroundColor(Color.parseColor("#FFFFFF"));

                meetingAcceptedButton.setTextColor(Color.parseColor("#E16E38"));
                meetingAcceptedButton.setBackgroundColor(Color.parseColor("#FFFFFF"));

                meetingDeniedButton.setTextColor(Color.parseColor("#FFFFFF"));
                meetingDeniedButton.setBackgroundColor(Color.parseColor("#E16E38"));
                meetingDeniedButton.setBackground(ContextCompat.getDrawable(this.getContext(), R.drawable.segment_fill_bg));
                break;

            default:

                break;

        }
    }


    private void setUpRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setNestedScrollingEnabled(false);
    }

    public void isListEmpty(boolean isListEmpty, String message) {
        emptyLabel.setText(message);
        if (isListEmpty) {
            emptyLabel.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        } else {
            emptyLabel.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }
    }

    public void toggleEditMode(boolean isEditMode) {
        adapter.setEditMode(isEditMode);
    }

    public void updateOpenMeetings(boolean isEditMode, List<OpenMeetingResponse> openMeetingResponseList, MeetingViewHolder.OnRemoveMeetingListener onRemoveMeetingListener) {
        adapter.setOnRemoveMeetingListener(onRemoveMeetingListener);
        adapter.swapData(openMeetingResponseList, isEditMode);
        recyclerView.setAdapter(adapter);
        isListEmpty(false, "");
    }

    public void updateMeetingsAccepted(boolean isEditMode, List<OpenMeetingResponse> openMeetingResponseList) {
        //adapter.setOnRemoveMeetingListener(onRemoveMeetingListener);
        adapter.swapData(openMeetingResponseList, isEditMode);
        recyclerView.setAdapter(adapter);
        isListEmpty(false, "");
    }

    public void updateMeetingDenied(boolean isEditMode, List<OpenMeetingResponse> openMeetingResponseList) {
       // adapter.setOnRemoveMeetingListener(onRemoveMeetingListener);
        adapter.swapData(openMeetingResponseList, isEditMode);
        recyclerView.setAdapter(adapter);
        isListEmpty(false, "");
    }
}
