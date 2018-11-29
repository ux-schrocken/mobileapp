package in.appnow.ypo.android.ui.dashboard_contact.mvp.view.dashboard;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import in.appnow.ypo.android.R;
import in.appnow.ypo.android.rest.response.TaskListResponse;
import in.appnow.ypo.android.rest.response.Tasks;

/**
 * Created by sonu on 14:54, 23/10/18
 * Copyright (c) 2018 . All rights reserved.
 */
public class DashboardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public List<Tasks> tasksList = new ArrayList<>(0);
    private DashboardViewHolder.OnDenyOptionListener onDenyOptionListener;
    private DashboardViewHolder.OnAcceptOptionListener onAcceptOptionListener;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.dashboard_custom_row_layout, viewGroup, false);
        return new DashboardViewHolder(viewGroup.getContext(),onDenyOptionListener,onAcceptOptionListener,view);
    }

    public void setonDenyOptionListener(DashboardViewHolder.OnDenyOptionListener onDenyOptionListener,DashboardViewHolder.OnAcceptOptionListener onAcceptOptionListener) {
        this.onDenyOptionListener = onDenyOptionListener;
        this.onAcceptOptionListener = onAcceptOptionListener;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        Tasks response = tasksList.get(i);
        ((DashboardViewHolder) viewHolder).bindData(response);

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return tasksList.size();
    }

    public void swapData(List<Tasks> taskListResponseList) {
        if (taskListResponseList != null && taskListResponseList.size() > 0) {
            this.tasksList.clear();
            this.tasksList.addAll(taskListResponseList);
            notifyDataSetChanged();
        }
    }
}
