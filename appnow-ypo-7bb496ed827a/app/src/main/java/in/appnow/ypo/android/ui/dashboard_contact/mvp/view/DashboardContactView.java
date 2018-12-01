package in.appnow.ypo.android.ui.dashboard_contact.mvp.view;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
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
import in.appnow.ypo.android.ui.main.MainActivity;
import in.appnow.ypo.android.ui.main.mvp.MainActivityView;
import in.appnow.ypo.android.utils.FragmentUtils;

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
    @BindView(R.id.dashboard_all_button)
    TextView allButton;
    @BindView(R.id.dashboard_accepted_button)
    TextView acceptedButton;
    @BindView(R.id.dashboard_denied_button)
    TextView deniedButton;
    @BindView(R.id.orangetabs)
            LinearLayout orangeTabs;
    MainActivityView mainActivityView;
public int changer =0;
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

    // sam edited
    public void allClick(OnClickListener onClickListener) {
        allButton.setOnClickListener(onClickListener);

    }

    public void acceptedClick(OnClickListener onClickListener) {
        acceptedButton.setOnClickListener(onClickListener);
    }

    public void deniedClick(OnClickListener onClickListener){
        deniedButton.setOnClickListener((onClickListener));
    }


    public void colorChanger(int changer) {

        switch (changer) {
            case 1:

                allButton.setTextColor(Color.parseColor("#FFFFFF"));
                allButton.setBackgroundColor(Color.parseColor("#E16E38"));


                acceptedButton.setTextColor(Color.parseColor("#E16E38"));
                acceptedButton.setBackgroundColor(Color.parseColor("#FFFFFF"));

                deniedButton.setTextColor(Color.parseColor("#E16E38"));
                deniedButton.setBackgroundColor(Color.parseColor("#FFFFFF"));
                allButton.setBackground(ContextCompat.getDrawable(this.getContext(), R.drawable.segment_fill_bg) );

                break;

            case 2:
                allButton.setTextColor(Color.parseColor("#E16E38"));
                allButton.setBackgroundColor(Color.parseColor("#FFFFFF"));

                acceptedButton.setTextColor(Color.parseColor("#FFFFFF"));
                acceptedButton.setBackgroundColor(Color.parseColor("#E16E38"));

                deniedButton.setTextColor(Color.parseColor("#E16E38"));
                deniedButton.setBackgroundColor(Color.parseColor("#FFFFFF"));
                acceptedButton.setBackground(ContextCompat.getDrawable(this.getContext(), R.drawable.segment_fill_bg) );
                break;

            case 3:
                allButton.setTextColor(Color.parseColor("#E16E38"));
                allButton.setBackgroundColor(Color.parseColor("#FFFFFF"));

                acceptedButton.setTextColor(Color.parseColor("#E16E38"));
                acceptedButton.setBackgroundColor(Color.parseColor("#FFFFFF"));

                deniedButton.setTextColor(Color.parseColor("#FFFFFF"));
                deniedButton.setBackgroundColor(Color.parseColor("#E16E38"));
                deniedButton.setBackground(ContextCompat.getDrawable(this.getContext(), R.drawable.segment_fill_bg) );
                break;

            default:

                break;

        }
    }

    public void  hideArrows(){
        if (FragmentUtils.TAB_SELECTOR!=1){
            findViewById(R.id.dashboard_row_left_arrow).setVisibility(View.GONE);
            findViewById(R.id.toolbar_right_action_button).setVisibility(View.GONE);
        }
    }

    public void updateTaskList(TaskListResponse taskListResponseList, DashboardViewHolder.OnDenyOptionListener onDenyOptionListener, DashboardViewHolder.OnAcceptOptionListener onAcceptOptionListener) {
        DashboardAdapter adapter = new DashboardAdapter();
        adapter.setonDenyOptionListener(onDenyOptionListener, onAcceptOptionListener);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        adapter.swapData(taskListResponseList.getTasksList());
        isListEmpty(false, "");
        setLabelText(String.format(activeRequestString, taskListResponseList.getActiveRequest()));
    }
    public void updateTaskAcceptedList(TaskListResponse taskListResponseList, DashboardViewHolder.OnDenyOptionListener onDenyOptionListener, DashboardViewHolder.OnAcceptOptionListener onAcceptOptionListener) {
        DashboardAdapter adapter = new DashboardAdapter();
        adapter.setonDenyOptionListener(onDenyOptionListener, onAcceptOptionListener);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        adapter.swapData(taskListResponseList.getTasksList());
        isListEmpty(false, "");
        setLabelText(String.format(activeRequestString, taskListResponseList.getActiveRequest()));
    }
    public void updateTaskDeniedList(TaskListResponse taskListResponseList, DashboardViewHolder.OnDenyOptionListener onDenyOptionListener, DashboardViewHolder.OnAcceptOptionListener onAcceptOptionListener) {
        DashboardAdapter adapter = new DashboardAdapter();
        adapter.setonDenyOptionListener(onDenyOptionListener, onAcceptOptionListener);
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
    public void updateAcceptedContactList(ContactResponse contactResponseList, ContactViewHolder.OnContactMoreOptionListener onContactMoreOptionListener) {
        ContactAdapter adapter = new ContactAdapter(0);
        adapter.setOnContactMoreOptionListener(onContactMoreOptionListener);
        recyclerView.setAdapter(adapter);
        adapter.swapData(contactResponseList.getContacts());
        adapter.notifyDataSetChanged();
        isListEmpty(false, "");
        setLabelText(String.format(totalContactsString, contactResponseList.getContactsCount()));

    }
    public void updateDeniedContactList(ContactResponse contactResponseList, ContactViewHolder.OnContactMoreOptionListener onContactMoreOptionListener) {
        ContactAdapter adapter = new ContactAdapter(0);
        adapter.setOnContactMoreOptionListener(onContactMoreOptionListener);
        recyclerView.setAdapter(adapter);
        adapter.swapData(contactResponseList.getContacts());
        adapter.notifyDataSetChanged();
        isListEmpty(false, "");
        setLabelText(String.format(totalContactsString, contactResponseList.getContactsCount()));

    }


}
