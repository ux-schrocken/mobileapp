package in.appnow.ypo.android.ui.profile.mvp;

import android.content.Context;
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
import in.appnow.ypo.android.rest.response.DefaultShareRuleResponse;
import in.appnow.ypo.android.rest.response.MemberRequestResponse;
import in.appnow.ypo.android.utils.VectorUtils;

/**
 * Created by sonu on 18:13, 18/10/18
 * Copyright (c) 2018 . All rights reserved.
 */
public class ProfileView extends FrameLayout {

    @BindView(R.id.profile_root_layout)
    RelativeLayout rootLayout;

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


    public ProfileView(@NonNull Context context) {
        super(context);
        inflate(context, R.layout.profile_fragment, this);
        ButterKnife.bind(this, this);

        VectorUtils.setVectorCompoundDrawable(locationSwitch, context, R.drawable.ic_location_flag, 0, 0, 0, R.color.violet);
        VectorUtils.setVectorCompoundDrawable(emailSwitch, context, R.drawable.ic_email, 0, 0, 0, R.color.violet);
        VectorUtils.setVectorCompoundDrawable(contactSwitch, context, R.drawable.ic_contact_phone, 0, 0, 0, R.color.violet);
        VectorUtils.setVectorCompoundDrawable(socialSwitch, context, R.drawable.ic_hashtag, 0, 0, 0, R.color.violet);

    }

    public void onSwitchClick(OnClickListener onClickListener){
        profileSwitch.setOnClickListener(onClickListener);
        profileAboutSwitch.setOnClickListener(onClickListener);
        locationSwitch.setOnClickListener(onClickListener);
        contactSwitch.setOnClickListener(onClickListener);
        emailSwitch.setOnClickListener(onClickListener);
        socialSwitch.setOnClickListener(onClickListener);
setupMeetingSwitch.setOnClickListener(onClickListener);
    }


    public void updateViews(MemberRequestResponse requestResponse) {
        userNameLabel.setText(requestResponse.getMemberName());
        designationLabel.setText(requestResponse.getMemberShortBio());
        memberSinceLabel.setText(requestResponse.getMembershipDate());
        aboutLabel.setText(requestResponse.getAboutMember());
        locationSwitch.setText(requestResponse.getMemberLoc());
        emailSwitch.setText(requestResponse.getMemberEmail());
        contactSwitch.setText(requestResponse.getMemberContactNum());
        socialSwitch.setText(requestResponse.getMemberSocialAcc());


        //public String sharingdefaultmemdate, sharingdefaultemail, sharingdefaultphone, sharingdefaultsocialacc, sharingdefaultlocation, sharingdefaultsetmeetings;
//       S_username=requestResponse.getMembershipDate();
//       S_designation=requestResponse.getMemberShortBio();
//       S_memberSince=requestResponse.getMembershipDate();
//       S_about=requestResponse.getAboutMember();
//        sharingdefaultlocation=requestResponse.getMemberLoc();
//        sharingdefaultemail=requestResponse.getMemberEmail();
//        sharingdefaultphone=requestResponse.getMemberContactNum();
//        sharingdefaultsocialacc=requestResponse.getMemberSocialAcc();


//(@Path("memberId") String taskId, @Field("sharingdefaultmemdate") String memberLoc, @Field("sharingdefaultemail") String memberContactNum1
//            , @Field("sharingdefaultphone") String memberEmail1, @Field("sharingdefaultsocialacc") String memberSocialAcc1
//            , @Field("sharingdefaultlocation") String setMeetings1, @Field("sharingdefaultsetmeetings") String aboutMember1)



        if (requestResponse.getDefaultLocationFlag().equalsIgnoreCase("0")) {
            locationSwitch.setChecked(false);
        } else {
            locationSwitch.setChecked(true);
        }

        if (requestResponse.getDefaultEmailFlag().equalsIgnoreCase("0")) {
            emailSwitch.setChecked(false);
        } else {
            emailSwitch.setChecked(true);
        }

        if (requestResponse.getDefaultPhoneFlag().equalsIgnoreCase("0")) {
            contactSwitch.setChecked(false);
        } else {
            contactSwitch.setChecked(true);
        }

        if (requestResponse.getDefaultSocialFlag().equalsIgnoreCase("0")) {
            socialSwitch.setChecked(false);
        } else {
            socialSwitch.setChecked(true);
        }

        if (requestResponse.getDefaultMemDateFlag().equalsIgnoreCase("0")) {
            profileAboutSwitch.setChecked(false);
        } else {
            profileAboutSwitch.setChecked(true);
        }

        if (requestResponse.getSetMeetings().equalsIgnoreCase("0")) {
            setupMeetingSwitch.setChecked(false);
        } else {
            setupMeetingSwitch.setChecked(true);
        }
        rootLayout.setVisibility(View.VISIBLE);
    }


    public void updateSwitches(DefaultShareRuleResponse defaultShareRuleResponse){
        if (defaultShareRuleResponse.getsMemberLoc().equalsIgnoreCase("n")) {
            locationSwitch.setChecked(false);
        } else {
            locationSwitch.setChecked(true);
        }

        if (defaultShareRuleResponse.getsMemberEmail().equalsIgnoreCase("n")) {
            emailSwitch.setChecked(false);
        } else {
            emailSwitch.setChecked(true);
        }

        if (defaultShareRuleResponse.getsMemberContactNum().equalsIgnoreCase("n")) {
            contactSwitch.setChecked(false);
        } else {
            contactSwitch.setChecked(true);
        }

        if (defaultShareRuleResponse.getsMemberSocialAcc().equalsIgnoreCase("n")) {
            socialSwitch.setChecked(false);
        } else {
            socialSwitch.setChecked(true);
        }


        if (defaultShareRuleResponse.getsSetMeetings().equalsIgnoreCase("n")) {
            setupMeetingSwitch.setChecked(false);
        } else {
            setupMeetingSwitch.setChecked(true);
        }
    }

}
