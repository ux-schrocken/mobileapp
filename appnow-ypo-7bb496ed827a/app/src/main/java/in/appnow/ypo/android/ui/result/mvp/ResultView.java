package in.appnow.ypo.android.ui.result.mvp;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import in.appnow.ypo.android.R;
import in.appnow.ypo.android.ui.result.ResultEnum;
import in.appnow.ypo.android.utils.ImageUtils;
import in.appnow.ypo.android.utils.TextUtils;

/**
 * Created by sonu on 17:20, 23/10/18
 * Copyright (c) 2018 . All rights reserved.
 */
public class ResultView extends FrameLayout {

    @BindView(R.id.toolbar_left_action_button)
    Button cancelButton;
    @BindView(R.id.toolbar_right_action_button)
    Button doneButton;

    @BindView(R.id.result_image_view)
    ImageView resultImageView;
    @BindView(R.id.result_title_label)
    TextView titleLabel;
    @BindView(R.id.result_detail_label)
    TextView detailsLabel;
    @BindView(R.id.result_other_label)
    TextView otherLabel;

    @BindString(R.string.denied_request_message)
    String deniedRequestString;
    @BindString(R.string.denied_request_sub_title_message)
    String deniedSubString;
    @BindString(R.string.new_meeting_request_message)
    String newMeetingRequestMessage;

    public ResultView(@NonNull Context context) {
        super(context);
        inflate(context, R.layout.activity_result, this);
        ButterKnife.bind(this, this);

        doneButton.setText("Done");
        doneButton.setTextColor(context.getResources().getColor(R.color.blue));

    }

    public void onDoneButtonClick(OnClickListener onClickListener) {
        doneButton.setOnClickListener(onClickListener);
    }

    public void updateViews(ResultEnum resultEnum, String userName) {
        switch (resultEnum) {
            case ACCEPT_CONTACT_REQUEST:
                resultImageView.setImageResource(R.drawable.ic_check_circle_black_24dp);
                ImageUtils.changeImageColor(resultImageView, getContext(), R.color.green_color);
                titleLabel.setText("Your contact details has been shared with " + userName + ".");
                titleLabel.setTypeface(titleLabel.getTypeface() , Typeface.BOLD);
                break;
            case DENY_CONTACT_REQUEST:
                resultImageView.setImageResource(R.drawable.ic_cancel_black_24dp);
                ImageUtils.changeImageColor(resultImageView, getContext(), R.color.red_color);
                TextUtils.setHtmlString(String.format(deniedRequestString, "Contact Request", userName), titleLabel);
                TextUtils.setHtmlString(String.format(deniedSubString, "contacts", "'New Contact'"), detailsLabel);
                break;
            case ACCEPT_MEETING_REQUEST:
                resultImageView.setImageResource(R.drawable.ic_check_circle_black_24dp);
                ImageUtils.changeImageColor(resultImageView, getContext(), R.color.green_color);
                titleLabel.setText("Meeting Request Accepted with");
                detailsLabel.setText(userName);
               // TextUtils.setHtmlString(String.format(deniedSubString, "meetings", "'New Meeting Request'"), detailsLabel);
                break;
            case DENY_MEETING_REQUEST:
                resultImageView.setImageResource(R.drawable.ic_cancel_black_24dp);
                ImageUtils.changeImageColor(resultImageView, getContext(), R.color.red_color);
                TextUtils.setHtmlString(String.format(deniedRequestString, "Meeting Request", userName), titleLabel);
                TextUtils.setHtmlString(String.format(deniedSubString, "meetings", "'New Meeting Request'"), detailsLabel);
                break;
            case NEW_CONTACT:
                resultImageView.setImageResource(R.drawable.ic_check_circle_black_24dp);
                ImageUtils.changeImageColor(resultImageView, getContext(), R.color.green_color);
                titleLabel.setText("Your request has been sent!");
                detailsLabel.setText("Member Identifier");
                otherLabel.setText(userName);
                break;
            case NEW_MEETING:
                resultImageView.setImageResource(R.drawable.ic_check_circle_black_24dp);
                ImageUtils.changeImageColor(resultImageView, getContext(), R.color.green_color);
                titleLabel.setText("Your request has been sent!");
                TextUtils.setHtmlString(String.format(newMeetingRequestMessage,userName),otherLabel);
                break;

            default:
                break;
        }
    }

}
