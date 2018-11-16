package in.appnow.ypo.android.ui.meeting.mvp.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import in.appnow.ypo.android.R;
import in.appnow.ypo.android.rest.response.OpenMeetingResponse;

/**
 * Created by sonu on 12:46, 25/10/18
 * Copyright (c) 2018 . All rights reserved.
 */
public class MeetingAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<OpenMeetingResponse> openMeetingResponseList = new ArrayList<>();
    private boolean isEditMode;
    private MeetingViewHolder.OnRemoveMeetingListener onRemoveMeetingListener;

    public void setOnRemoveMeetingListener(MeetingViewHolder.OnRemoveMeetingListener onRemoveMeetingListener) {
        this.onRemoveMeetingListener = onRemoveMeetingListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.meeting_custom_row_layout, viewGroup, false);
        return new MeetingViewHolder(viewGroup.getContext(), view, onRemoveMeetingListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        OpenMeetingResponse previousObject = null;
        if (i > 0) {
            previousObject = openMeetingResponseList.get(i - 1);
        }
        ((MeetingViewHolder) viewHolder).bindData(i == 0, previousObject, openMeetingResponseList.get(i), isEditMode);
    }

    @Override
    public int getItemCount() {
        return openMeetingResponseList.size();
    }

    public void setEditMode(boolean editMode) {
        isEditMode = editMode;
        notifyDataSetChanged();
    }

    public void swapData(List<OpenMeetingResponse> openMeetingResponseList,boolean isEditMode) {
        if (openMeetingResponseList != null && openMeetingResponseList.size() > 0) {
            this.openMeetingResponseList.clear();
            this.openMeetingResponseList.addAll(openMeetingResponseList);
            this.isEditMode = isEditMode;
            notifyDataSetChanged();
        }
    }
}
