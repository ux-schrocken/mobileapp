package in.appnow.ypo.android.ui.contact_details.mvp;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import in.appnow.ypo.android.R;
import in.appnow.ypo.android.rest.response.Contacts;
import in.appnow.ypo.android.rest.response.MemberRequestResponse;
import in.appnow.ypo.android.utils.VectorUtils;

/**
 * Created by Abhishek Thanvi on 15/11/18.
 * Copyright © 2018 Abhishek Thanvi. All rights reserved.
 */

public class ContactDetailView extends FrameLayout{
    @BindView(R.id.profile_root_layout)
    RelativeLayout rootLayout;

    @BindView(R.id.toolbar_left_action_button)
    Button cancelButton;
    @BindView(R.id.toolbar_right_action_button)
    Button titleButton;
    @BindView(R.id.share_details_button)
    Button shareDetailsButton;

    @BindView(R.id.profile_user_icon_image_view)
    CircleImageView userProfileImage;
    @BindView(R.id.profile_user_name_label)
    TextView userNameLabel;
    @BindView(R.id.profile_designation_label)
    TextView designationLabel;
    @BindView(R.id.profile_member_since_label)
    TextView memberSinceLabel;
    @BindView(R.id.profile_switch)
    SwitchCompat profileSwitch;
    @BindView(R.id.profile_about_switch)
    SwitchCompat profileAboutSwitch;
    @BindView(R.id.profile_about_label)
    TextView aboutLabel;
    @BindView(R.id.profile_location_switch)
    SwitchCompat locationSwitch;
    @BindView(R.id.profile_contact_switch)
    SwitchCompat contactSwitch;
    @BindView(R.id.profile_email_switch)
    SwitchCompat emailSwitch;
    @BindView(R.id.profile_social_switch)
    SwitchCompat socialSwitch;
    @BindView(R.id.profile_setup_meetings_switch)
    SwitchCompat setupMeetingSwitch;

    public ContactDetailView(@NonNull Context context) {
        super(context);
        inflate(context, R.layout.activity_share_details, this);
        ButterKnife.bind(this, this);
        shareDetailsButton.setVisibility(View.VISIBLE);
        profileSwitch.setVisibility(View.GONE);
        emailSwitch.setVisibility(View.GONE);
        locationSwitch.setVisibility(View.GONE);
        socialSwitch.setVisibility(View.GONE);
        profileAboutSwitch.setVisibility(View.GONE);
        setupMeetingSwitch.setVisibility(GONE);
        contactSwitch.setVisibility(GONE);
        shareDetailsButton.setVisibility(GONE);

        cancelButton.setText("Cancel");
        titleButton.setText("Select Details");
        titleButton.setVisibility(GONE);
        titleButton.setTypeface(titleButton.getTypeface(), Typeface.BOLD);

        VectorUtils.setVectorCompoundDrawable(locationSwitch, context, R.drawable.ic_location_flag, 0, 0, 0, R.color.violet);
        VectorUtils.setVectorCompoundDrawable(emailSwitch, context, R.drawable.ic_email, 0, 0, 0, R.color.violet);
        VectorUtils.setVectorCompoundDrawable(contactSwitch, context, R.drawable.ic_contact_phone, 0, 0, 0, R.color.violet);
        VectorUtils.setVectorCompoundDrawable(socialSwitch, context, R.drawable.ic_hashtag, 0, 0, 0, R.color.violet);

    }

    public void onCancelButtonClick(OnClickListener onClickListener) {
        cancelButton.setOnClickListener(onClickListener);
    }

    public void updateViews(Contacts contacts) {
        userNameLabel.setText(contacts.getMemberName());
        designationLabel.setText(contacts.getMemberShortBio());
        memberSinceLabel.setText(contacts.getMembershipDate());
        aboutLabel.setText(contacts.getMemberShortBio());
        locationSwitch.setText(contacts.getMemberLoc());
        emailSwitch.setText(contacts.getMemberEmail());
        contactSwitch.setText(contacts.getMemberContactNum());
        socialSwitch.setText(contacts.getMemberSocialAcc());

        rootLayout.setVisibility(View.VISIBLE);
    }

}
