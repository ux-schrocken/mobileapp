package in.appnow.ypo.android.ui.main.mvp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.appnow.ypo.android.R;

/**
 * Created by sonu on 18:13, 18/10/18
 * Copyright (c) 2018 . All rights reserved.
 */
public class MainActivityView extends FrameLayout {

    @BindView(R.id.main_toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;

    @BindView(R.id.dashboard_button)
    ImageView dashboardButton;
    @BindView(R.id.contact_button)
    ImageView contactButton;
    @BindView(R.id.meeting_button)
    ImageView meetingButton;
    @BindView(R.id.profile_button)
    ImageView profileButton;
    @BindView(R.id.create_new_button)
    ImageView createNewButton;
    private int currentPosition = -1;

    public MainActivityView(@NonNull AppCompatActivity context) {
        super(context);
        inflate(context, R.layout.activity_main, this);
        ButterKnife.bind(this, this);

        context.setSupportActionBar(toolbar);
        if (context.getSupportActionBar()!=null){
            context.getSupportActionBar().setTitle("");
        }
    }

    public void onDashboardClick(OnClickListener onClickListener) {
        dashboardButton.setOnClickListener(onClickListener);
    }

    public void onContactClick(OnClickListener onClickListener) {
        contactButton.setOnClickListener(onClickListener);
    }

    public void onMeetingClick(OnClickListener onClickListener) {
        meetingButton.setOnClickListener(onClickListener);
    }

    public void onProfileClick(OnClickListener onClickListener) {
        profileButton.setOnClickListener(onClickListener);
    }

    public void onCreateNewClick(OnClickListener onClickListener) {
        createNewButton.setOnClickListener(onClickListener);
    }

    public boolean onBottomButtonClick(int position) {
        if (currentPosition == position)
            return false;
        currentPosition = position;
        switch (position) {
            case 0:
                toolbarTitle.setText("Dashboard");
                dashboardButton.setImageResource(R.drawable.ic_dashboard_active);
                contactButton.setImageResource(R.drawable.ic_contacts_inactive);
                meetingButton.setImageResource(R.drawable.ic_meetings);
                profileButton.setImageResource(R.drawable.ic_profile_inactive);
                break;
            case 1:
                toolbarTitle.setText("All Contacts");
                dashboardButton.setImageResource(R.drawable.ic_dashboard_inactive);
                contactButton.setImageResource(R.drawable.ic_contacts_active);
                meetingButton.setImageResource(R.drawable.ic_meetings);
                profileButton.setImageResource(R.drawable.ic_profile_inactive);
                break;
            case 2:
                toolbarTitle.setText("Meetings");
                dashboardButton.setImageResource(R.drawable.ic_dashboard_inactive);
                contactButton.setImageResource(R.drawable.ic_contacts_inactive);
                meetingButton.setImageResource(R.drawable.ic_meetings_active);
                profileButton.setImageResource(R.drawable.ic_profile_inactive);
                break;
            case 3:
                toolbarTitle.setText("My Profile");
                dashboardButton.setImageResource(R.drawable.ic_dashboard_inactive);
                contactButton.setImageResource(R.drawable.ic_contacts_inactive);
                meetingButton.setImageResource(R.drawable.ic_meetings);
                profileButton.setImageResource(R.drawable.ic_profile_active);
                break;
            default:

                break;
        }
        return true;
    }
}
