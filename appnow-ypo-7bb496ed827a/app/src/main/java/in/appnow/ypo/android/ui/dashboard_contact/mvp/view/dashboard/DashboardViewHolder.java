package in.appnow.ypo.android.ui.dashboard_contact.mvp.view.dashboard;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daimajia.swipe.SwipeLayout;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import in.appnow.ypo.android.R;
import in.appnow.ypo.android.rest.response.TaskListResponse;
import in.appnow.ypo.android.rest.response.Tasks;
import in.appnow.ypo.android.ui.result.ResultActivity;
import in.appnow.ypo.android.ui.result.ResultEnum;
import in.appnow.ypo.android.ui.share_details.ShareDetailsActivity;
import in.appnow.ypo.android.utils.ToastUtils;

/**
 * Created by sonu on 14:56, 23/10/18
 * Copyright (c) 2018 . All rights reserved.
 */
public class DashboardViewHolder extends RecyclerView.ViewHolder {

    public static final int CONTACT_DENY_REQUEST_CODE = 1;
    public static final int MEETING_DENY_REQUEST_CODE = 2;
    @BindView(R.id.dashboard_swipe_reveal_layout)
    SwipeLayout swipeLayout;

    @BindView(R.id.dashboard_row_deny_request_button)
    LinearLayout denyRequestButton;
    @BindView(R.id.dashboard_deny_row_image_view)
    ImageView denyRequestImageView;
    @BindView(R.id.dashboard_deny_row_label)
    TextView denyRequestLabel;

    @BindView(R.id.dashboard_row_give_access_button)
    LinearLayout giveAccessButton;
    @BindView(R.id.dashboard_row_give_access_image_view)
    ImageView giveAccessImageView;
    @BindView(R.id.dashboard_row_give_access_label)
    TextView giveAccessLabel;

    @BindView(R.id.dashboard_row_left_arrow)
    ImageView leftArrow;
    @BindView(R.id.dashboard_row_right_arrow)
    ImageView rightArrow;
    @BindView(R.id.dashboard_user_icon_image_view)
    CircleImageView userImageView;

    // type label is not getting data from get request.
    @BindView(R.id.dashboard_type_label)
    TextView typeLabel;
    @BindView(R.id.dashboard_user_name_label)
    TextView userNameLabel;
    @BindView(R.id.dashboard_designation_label)
    TextView designationLabel;
    @BindView(R.id.dashboard_member_since_label)
    TextView memberSinceLabel;

    @BindColor(R.color.light_orange_color)
    int orangeColor;
    @BindColor(R.color.violet)
    int violetColor;
    @BindColor(R.color.gunmetal)
    int gunmetalColor;
    private Context context;

    private OnDenyOptionListener onDenyOptionListener;
    private OnAcceptOptionListener onAcceptOptionListener;

    public DashboardViewHolder(Context context,OnDenyOptionListener onDenyOptionListener, OnAcceptOptionListener onAcceptOptionListener  ,@NonNull View itemView) {
        super(itemView);
        this.context = context;
        this.onDenyOptionListener = onDenyOptionListener;
        this.onAcceptOptionListener = onAcceptOptionListener;
        ButterKnife.bind(this, itemView);
    }

    public void bindData(Tasks response) {
        swipeLayout.setShowMode(SwipeLayout.ShowMode.PullOut);
        swipeLayout.addDrag(SwipeLayout.DragEdge.Left, denyRequestButton);
        swipeLayout.addDrag(SwipeLayout.DragEdge.Right, giveAccessButton);

        userNameLabel.setText(response.getMemberName());
        designationLabel.setText(response.getMemberShortBio());
        typeLabel.setText(response.getTaskType());
        memberSinceLabel.setText(response.getMembershipDate());
if(response.getTaskType()!=null) {
    if (response.getTaskType().equalsIgnoreCase("contact")) {
        giveAccessLabel.setText("Give Access");
        denyRequestLabel.setText("Deny Access");
        typeLabel.setTextColor(violetColor);
    } else if (response.getTaskType().equalsIgnoreCase("meeting")) {
        giveAccessLabel.setText("Accept\nMeeting");
        denyRequestLabel.setText("Deny\nMeeting");
        typeLabel.setTextColor(orangeColor);
    } else {
        typeLabel.setTextColor(gunmetalColor);
    }

}
        denyRequestButton.setOnClickListener(view -> showDeniedScreen(response));
        denyRequestImageView.setOnClickListener(view -> showDeniedScreen(response));
        denyRequestLabel.setOnClickListener(view -> showDeniedScreen(response));
        giveAccessButton.setOnClickListener(view -> openShareActivity(response));
        giveAccessImageView.setOnClickListener(view -> openShareActivity(response));
        giveAccessLabel.setOnClickListener(view -> openShareActivity(response));

        leftArrow.setOnClickListener(view -> swipeLayout.open(SwipeLayout.DragEdge.Left));

        rightArrow.setOnClickListener(view -> swipeLayout.open(SwipeLayout.DragEdge.Right));

    }

    private void openShareActivity(Tasks tasks) {
        closeSwipeReveal();

        if (tasks.getTaskType().equalsIgnoreCase("contact")){
            ShareDetailsActivity.openShareDetailsActivity(context,tasks);
        }else{
            if (onAcceptOptionListener != null) {
                onAcceptOptionListener.onAcceptRequest(tasks);
            } else {
                ToastUtils.shortToast("Oops!! Unknown error occurred.");
            }
        }



    }

    private void showDeniedScreen(Tasks response) {
        closeSwipeReveal();

        if (onDenyOptionListener != null) {
            onDenyOptionListener.onDenyRequest(response);
        } else {
            ToastUtils.shortToast("Oops!! Unknown error occurred.");
        }

//        if (response.getTaskType().equalsIgnoreCase("contact")) {
//            ResultActivity.openResultActivity(context, ResultEnum.DENY_CONTACT_REQUEST, response.getMemberName(), CONTACT_DENY_REQUEST_CODE);
//        } else if (response.getTaskType().equalsIgnoreCase("meeting")) {
//            ResultActivity.openResultActivity(context, ResultEnum.DENY_MEETING_REQUEST, response.getMemberName(), MEETING_DENY_REQUEST_CODE);
//        }
    }

    public interface OnDenyOptionListener {
        public void onDenyRequest(Tasks response);
    }

    public interface OnAcceptOptionListener {
        public void onAcceptRequest(Tasks response);
    }

    private void closeSwipeReveal(){
        swipeLayout.close(true);
    }
}
