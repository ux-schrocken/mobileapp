package in.appnow.ypo.android.ui.meeting_request.mvp;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import in.appnow.ypo.android.R;
import in.appnow.ypo.android.helper.RecyclerClickListener;
import in.appnow.ypo.android.rest.response.ContactResponse;
import in.appnow.ypo.android.rest.response.Contacts;
import in.appnow.ypo.android.ui.dashboard_contact.mvp.view.contact.ContactAdapter;
import in.appnow.ypo.android.utils.DateUtils;
import in.appnow.ypo.android.utils.ToastUtils;
import in.appnow.ypo.android.utils.VectorUtils;

/**
 * Created by sonu on 13:04, 24/10/18
 * Copyright (c) 2018 . All rights reserved.
 */
public class MeetingRequestView extends FrameLayout {

    @BindView(R.id.toolbar_left_action_button)
    Button cancelButton;
    @BindView(R.id.toolbar_right_action_button)
    Button titleButton;

    @BindView(R.id.meeting_request_date_input)
    TextView dateInput;
    @BindView(R.id.meeting_request_time_input)
    TextView timeInput;
    @BindView(R.id.meeting_request_enter_meeting_reason_input)
    EditText meetingReasonInput;
    @BindView(R.id.meeting_request_button)
    Button meetingRequestButton;

    @BindView(R.id.request_meeting_recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.request_meeting_select_contact_label)
    TextView selectContactLabel;

    @BindView(R.id.request_meeting_selected_layout)
    RelativeLayout selectedLayout;
    @BindView(R.id.meeting_request_user_name_label)
    TextView userNameLabel;
    @BindView(R.id.meeting_request_designation_label)
    TextView designationLabel;
    @BindView(R.id.meeting_request_member_since_label)
    TextView sinceLabel;

    @BindString(R.string.meeting_request_label)
    String meetingRequestString;
    @BindString(R.string.new_meeting_date_message)
    String newMeetingDateMessage;

    private Contacts contactResponse;

    public MeetingRequestView(@NonNull Context context) {
        super(context);
        inflate(context, R.layout.activity_meeting_request, this);
        ButterKnife.bind(this, this);
        cancelButton.setText("Cancel");
        titleButton.setText(meetingRequestString);
        titleButton.setTypeface(titleButton.getTypeface(), Typeface.BOLD);

        VectorUtils.setVectorCompoundDrawable(dateInput, context, 0, 0, R.drawable.ic_date_range_black_24dp, 0, R.color.gunmetal);
        VectorUtils.setVectorCompoundDrawable(timeInput, context, 0, 0, R.drawable.ic_access_time_black_24dp, 0, R.color.gunmetal);

        setUpRecyclerView();
    }

    private void setUpRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setNestedScrollingEnabled(false);
    }

    public void onCancelButtonClick(OnClickListener onClickListener) {
        cancelButton.setOnClickListener(onClickListener);
    }

    public void onDateSelection(OnClickListener onClickListener) {
        dateInput.setOnClickListener(onClickListener);
    }

    public void onTimeSelection(OnClickListener onClickListener) {
        timeInput.setOnClickListener(onClickListener);
    }

    public void meetingRequestButtonClick(OnClickListener onClickListener) {
        meetingRequestButton.setOnClickListener(onClickListener);
    }

    public void updateContactView(Contacts contactResponse) {
        if (contactResponse != null) {
            this.contactResponse = contactResponse;
            userNameLabel.setText(contactResponse.getMemberName());
            designationLabel.setText(contactResponse.getMemberShortBio());
            sinceLabel.setText(contactResponse.getMembershipDate());
            showHideView(true);
        }
    }

    private void showHideView(boolean isContactSelected) {
        if (isContactSelected) {
            recyclerView.setVisibility(View.GONE);
            selectContactLabel.setVisibility(View.GONE);
            selectedLayout.setVisibility(View.VISIBLE);
            dateInput.setVisibility(View.VISIBLE);
            timeInput.setVisibility(View.VISIBLE);
            meetingReasonInput.setVisibility(View.VISIBLE);
            meetingRequestButton.setVisibility(View.VISIBLE);
        } else {
            selectedLayout.setVisibility(View.GONE);
            dateInput.setVisibility(View.GONE);
            timeInput.setVisibility(View.GONE);
            meetingReasonInput.setVisibility(View.GONE);
            meetingRequestButton.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            selectContactLabel.setVisibility(View.VISIBLE);
        }
    }


    public void updateDate(long date) {
        if (date > 0)
            dateInput.setText(DateUtils.parseDate(date, DateUtils.MEETING_DATE_FORMAT));
    }

    public void updateTime(long time) {
        if (time > 0)
            timeInput.setText(DateUtils.parseDate(time, DateUtils.MEETING_TIME_FORMAT));
    }

    public boolean validateData() {
        String date = in.appnow.ypo.android.utils.TextUtils.getText(dateInput);
        String time = in.appnow.ypo.android.utils.TextUtils.getText(timeInput);
        String message = in.appnow.ypo.android.utils.TextUtils.getText(meetingReasonInput);
        if (TextUtils.isEmpty(date)) {
            ToastUtils.shortToast("Please select date.");
            return false;
        } else if (TextUtils.isEmpty(time)) {
            ToastUtils.shortToast("Please select time.");
            return false;
        } else if (TextUtils.isEmpty(message)) {
            ToastUtils.shortToast("Please enter meeting message.");
            return false;
        } else {
            return true;
        }
    }

    public void updateContactList(List<Contacts> contactResponseList) {
        ContactAdapter adapter = new ContactAdapter(1);
        recyclerView.setAdapter(adapter);
        adapter.swapData(contactResponseList);
        showHideView(false);

        recyclerView.addOnItemTouchListener(new RecyclerClickListener(getContext(), (view, i) -> {
            if (contactResponseList != null && contactResponseList.size() > 0) {
                updateContactView(contactResponseList.get(i));
            }
        }));
    }

    public String getNewMeetingDateMessage() {
        return newMeetingDateMessage;
    }

    public Contacts getContactResponse() {
        return contactResponse;
    }
}
