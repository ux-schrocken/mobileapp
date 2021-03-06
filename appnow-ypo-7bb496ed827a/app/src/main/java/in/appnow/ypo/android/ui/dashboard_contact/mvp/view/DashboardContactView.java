package in.appnow.ypo.android.ui.dashboard_contact.mvp.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import in.appnow.ypo.android.R;
import in.appnow.ypo.android.rest.response.ContactResponse;
import in.appnow.ypo.android.rest.response.TaskListResponse;
import in.appnow.ypo.android.rest.response.Tasks;
import in.appnow.ypo.android.ui.dashboard_contact.mvp.view.contact.ContactAdapter;
import in.appnow.ypo.android.ui.dashboard_contact.mvp.view.contact.ContactViewHolder;
import in.appnow.ypo.android.ui.dashboard_contact.mvp.view.dashboard.DashboardAdapter;
import in.appnow.ypo.android.ui.dashboard_contact.mvp.view.dashboard.DashboardViewHolder;

/**
 * Created by sonu on 18:13, 18/10/18
 * Copyright (c) 2018 . All rights reserved.
 */
public class DashboardContactView extends FrameLayout {

    @BindView(R.id.dashboard_active_request_label)
    TextView activeRequestLabel;
    @BindView(R.id.dashboard_recycler_view)
    public RecyclerView recyclerView;
    @BindView(R.id.empty_list_label)
    TextView emptyLabel;

    @BindString(R.string.active_request_message)
    String activeRequestString;
    @BindString(R.string.total_contact_message)
    String totalContactsString;

    public DashboardContactView(@NonNull Context context) {
        super(context);
        inflate(context, R.layout.dashboard_contact_fragment, this);
        ButterKnife.bind(this, this);
        setUpRecyclerView(context);
    }

    private void setUpRecyclerView(Context context) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setNestedScrollingEnabled(false);
    }

    public void isListEmpty(boolean isListEmpty, String message) {
        emptyLabel.setText(message);
        if (isListEmpty) {
            emptyLabel.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
            activeRequestLabel.setVisibility(View.GONE);
        } else {
            emptyLabel.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }
    }

    public void updateTaskList(TaskListResponse taskListResponseList, DashboardViewHolder.OnDenyOptionListener onDenyOptionListener, DashboardViewHolder.OnAcceptOptionListener onAcceptOptionListener) {
        DashboardAdapter adapter = new DashboardAdapter();
        adapter.setonDenyOptionListener(onDenyOptionListener,onAcceptOptionListener);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        adapter.swapData(taskListResponseList.getTasksList());
        isListEmpty(false, "");
        setLabelText(String.format(activeRequestString, taskListResponseList.getActiveRequest()));
    }

    private void setLabelText(String text) {
        if (TextUtils.isEmpty(text)) {
            activeRequestLabel.setVisibility(View.GONE);
            return;
        }
        in.appnow.ypo.android.utils.TextUtils.setHtmlString(text, activeRequestLabel);
        activeRequestLabel.setVisibility(View.VISIBLE);
    }

    public void updateContactList(ContactResponse contactResponseList, ContactViewHolder.OnContactMoreOptionListener onContactMoreOptionListener) {
        ContactAdapter adapter = new ContactAdapter(0);
        adapter.setOnContactMoreOptionListener(onContactMoreOptionListener);
        recyclerView.setAdapter(adapter);
        adapter.swapData(contactResponseList.getContacts());
        adapter.notifyDataSetChanged();
        isListEmpty(false, "");
        setLabelText(String.format(totalContactsString, contactResponseList.getContactsCount()));

    }


}
