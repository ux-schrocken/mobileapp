package in.appnow.ypo.android.ui.dashboard_contact.mvp.view.contact;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import in.appnow.ypo.android.R;
import in.appnow.ypo.android.rest.response.ContactDetail;
import in.appnow.ypo.android.rest.response.ContactResponse;
import in.appnow.ypo.android.rest.response.Contacts;
import in.appnow.ypo.android.ui.meeting_request.MeetingRequestActivity;
import in.appnow.ypo.android.utils.ToastUtils;
import in.appnow.ypo.android.utils.VectorUtils;

/**
 * Created by sonu on 14:56, 23/10/18
 * Copyright (c) 2018 . All rights reserved.
 */
public class ContactViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.profile_user_icon_image_view)
    CircleImageView userProfileImageView;
    @BindView(R.id.profile_user_name_label)
    TextView userNameLabel;
    @BindView(R.id.profile_designation_label)
    TextView designationLabel;
    @BindView(R.id.profile_member_since_label)
    TextView memberSinceLabel;
    @BindView(R.id.profile_location_switch)
    TextView locationLabel;
    @BindView(R.id.profile_contact_switch)
    TextView contactLabel;
    @BindView(R.id.profile_email_switch)
    TextView emailLabel;
    @BindView(R.id.profile_social_switch)
    TextView socialLabel;
    @BindView(R.id.contact_row_more_button)
    ImageView moreButton;

    private Context context;
    private int isRequestMeetingCall;

    private OnContactMoreOptionListener onContactMoreOptionListener;

    public ContactViewHolder(Context context, @NonNull View itemView, OnContactMoreOptionListener onContactMoreOptionListener, int isRequestMeetingCall) {
        super(itemView);
        this.context = context;
        this.isRequestMeetingCall = isRequestMeetingCall;
        this.onContactMoreOptionListener = onContactMoreOptionListener;
        ButterKnife.bind(this, itemView);
    }

    public void bindData(Contacts response) {
        userNameLabel.setText(response.getMemberName());
        designationLabel.setText(response.getMemberShortBio());
        memberSinceLabel.setText(response.getMembershipDate());
        if (isRequestMeetingCall == 0) {
            locationLabel.setText(response.getMemberLoc());
            emailLabel.setText(response.getMemberEmail());
            contactLabel.setText(response.getMemberContactNum());
            socialLabel.setText(response.getMemberSocialAcc());

            VectorUtils.setVectorCompoundDrawable(locationLabel, context, R.drawable.ic_location_flag, 0, 0, 0, R.color.violet);
            VectorUtils.setVectorCompoundDrawable(emailLabel, context, R.drawable.ic_email, 0, 0, 0, R.color.violet);
            VectorUtils.setVectorCompoundDrawable(contactLabel, context, R.drawable.ic_contact_phone, 0, 0, 0, R.color.violet);
            VectorUtils.setVectorCompoundDrawable(socialLabel, context, R.drawable.ic_hashtag, 0, 0, 0, R.color.violet);

            moreButton.setOnClickListener(view -> showMoreMenu(response));

            moreButton.setVisibility(View.VISIBLE);
            locationLabel.setVisibility(View.GONE);
            emailLabel.setVisibility(View.GONE);
            contactLabel.setVisibility(View.GONE);
            socialLabel.setVisibility(View.GONE);

            if (response.getMemberLoc() != null && !response.getMemberLoc().equalsIgnoreCase("")){
                locationLabel.setVisibility(View.VISIBLE);
            }
            if (response.getMemberEmail() != null &&!response.getMemberEmail().equalsIgnoreCase("")){
                emailLabel.setVisibility(View.VISIBLE);
            }
            if (response.getMemberContactNum() != null &&!response.getMemberContactNum().equalsIgnoreCase("")){
                contactLabel.setVisibility(View.VISIBLE);
            }
            if (response.getMemberSocialAcc() != null &&!response.getMemberSocialAcc().equalsIgnoreCase("")){
                socialLabel.setVisibility(View.VISIBLE);
            }

        } else {
            locationLabel.setVisibility(View.GONE);
            emailLabel.setVisibility(View.GONE);
            contactLabel.setVisibility(View.GONE);
            socialLabel.setVisibility(View.GONE);
            moreButton.setVisibility(View.GONE);
        }
    }

    private void showMoreMenu(Contacts response) {
        Context wrapper = new ContextThemeWrapper(context, R.style.MyAlertDialogStyle);

        PopupMenu popup = new PopupMenu(wrapper, moreButton);

        try {
            Field[] fields = popup.getClass().getDeclaredFields();
            for (Field field : fields) {
                if ("mPopup".equals(field.getName())) {
                    field.setAccessible(true);
                    Object menuPopupHelper = field.get(popup);
                    Class<?> classPopupHelper = Class.forName(menuPopupHelper.getClass().getName());
                    Method setForceIcons = classPopupHelper.getMethod("setForceShowIcon", boolean.class);
                    setForceIcons.invoke(menuPopupHelper, true);
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        popup.getMenuInflater().inflate(R.menu.contact_pop_menu, popup.getMenu());


        popup.setOnMenuItemClickListener(menuItem -> {
            switch (menuItem.getItemId()) {
                case R.id.action_view_full_details:
                    if (onContactMoreOptionListener != null) {
                        onContactMoreOptionListener.onViewDetails(response);
                    } else {
                        ToastUtils.shortToast("Oops!! Unknown error occurred.");
                    }
                    break;
                case R.id.action_schedule_meeting:
                    MeetingRequestActivity.openMeetingRequestActivity(context, response);
                    break;
                case R.id.action_delete_contact:
                    if (onContactMoreOptionListener != null) {
                        onContactMoreOptionListener.onDeleteContact(response,getAdapterPosition());
                    } else {
                        ToastUtils.shortToast("Oops!! Unknown error occurred.");
                    }
                    break;
            }
            return true;
        });
        popup.show();

    }

    public interface OnContactMoreOptionListener {
        public void onDeleteContact(Contacts response,int pos);
        public void onViewDetails(Contacts response);
    }
}
