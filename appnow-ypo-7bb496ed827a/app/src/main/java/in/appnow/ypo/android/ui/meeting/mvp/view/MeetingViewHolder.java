package in.appnow.ypo.android.ui.meeting.mvp.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.appnow.ypo.android.R;
import in.appnow.ypo.android.rest.response.OpenMeetingResponse;
import in.appnow.ypo.android.utils.DateUtils;

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
    @BindView(R.id.meeting_row_title_label)
    TextView titleLabel;
    @BindView(R.id.meeting_row_description_label)
    TextView descriptionLabel;

    private Context context;
    private OnRemoveMeetingListener onRemoveMeetingListener;

    public MeetingViewHolder(Context context, @NonNull View itemView, OnRemoveMeetingListener onRemoveMeetingListener) {
        super(itemView);
        this.context = context;
        this.onRemoveMeetingListener = onRemoveMeetingListener;
        ButterKnife.bind(this, itemView);
    }

    public void bindData(boolean isFirstItem, OpenMeetingResponse previousObject, OpenMeetingResponse response, boolean isEditMode) {

        titleLabel.setText(response.getReasonForMeeting());
        descriptionLabel.setText(response.getTimeOfMeeting());

        if (isFirstItem) {
            dayLabel.setVisibility(View.VISIBLE);
            dayLabel.setText(DateUtils.parseDate(DateUtils.convertStringDateToMilliSecond(response.getDateOfMeeting(), DateUtils.OPEN_MEETING_DATE_FORMAT), DateUtils.OPEN_MEETING_DISPLAY_DATE_FORMAT));
        } else {
            if (previousObject != null) {
                if (previousObject.getDateOfMeeting().equalsIgnoreCase(response.getDateOfMeeting())) {
                    dayLabel.setVisibility(View.INVISIBLE);
                } else {
                    dayLabel.setVisibility(View.VISIBLE);
                    dayLabel.setText(DateUtils.parseDate(DateUtils.convertStringDateToMilliSecond(response.getDateOfMeeting(), DateUtils.OPEN_MEETING_DATE_FORMAT), DateUtils.OPEN_MEETING_DISPLAY_DATE_FORMAT));
                }
            }
        }

        if (isEditMode) {
            deleteButton.setVisibility(View.VISIBLE);
            deleteButton.setOnClickListener(view -> {
                if (onRemoveMeetingListener != null) {
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
}
