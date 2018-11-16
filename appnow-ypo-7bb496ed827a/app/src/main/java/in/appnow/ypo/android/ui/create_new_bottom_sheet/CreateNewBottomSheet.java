package in.appnow.ypo.android.ui.create_new_bottom_sheet;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.CoordinatorLayout;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindArray;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;
import butterknife.Unbinder;
import in.appnow.ypo.android.R;
import in.appnow.ypo.android.ui.add_new_contact.AddNewContactActivity;
import in.appnow.ypo.android.ui.meeting_request.MeetingRequestActivity;
import in.appnow.ypo.android.utils.Logger;
import in.appnow.ypo.android.utils.ToastUtils;

/**
 * Created by sonu on 13:20, 23/10/18
 * Copyright (c) 2018 . All rights reserved.
 */
public class CreateNewBottomSheet extends BottomSheetDialogFragment {

    private static final String TAG = CreateNewBottomSheet.class.getSimpleName();
    private View view;
    private Unbinder unbinder;

    private List<CreateNewModel> createNewModelList;

    @BindView(R.id.cancel_create_new_button)
    Button cancelButton;
    @BindView(R.id.create_new_list_view)
    ListView createNewListView;
    @BindArray(R.array.create_new_array)
    String[] createNewArray;
    private static final int[] icons = {R.drawable.ic_add_contact_green, R.drawable.ic_add_meeting_green};

    public static CreateNewBottomSheet newInstance() {

        Bundle args = new Bundle();

        CreateNewBottomSheet fragment = new CreateNewBottomSheet();
        fragment.setArguments(args);
        return fragment;
    }

    private BottomSheetBehavior.BottomSheetCallback mBottomSheetBehaviorCallback = new BottomSheetBehavior.BottomSheetCallback() {
        @Override
        public void onStateChanged(@NonNull View bottomSheet, int newState) {
            if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                Logger.DebugLog(TAG, "STATE_HIDDEN");
                dismiss();

            } else if (newState == BottomSheetBehavior.STATE_COLLAPSED) {
                Logger.DebugLog(TAG, "STATE_COLLAPSED");

            } else if (newState == BottomSheetBehavior.STATE_DRAGGING) {
                Logger.DebugLog(TAG, "STATE_DRAGGING");

            } else if (newState == BottomSheetBehavior.STATE_EXPANDED) {
                Logger.DebugLog(TAG, "STATE_EXPANDED");

            } else if (newState == BottomSheetBehavior.STATE_SETTLING) {
                Logger.DebugLog(TAG, "STATE_SETTLING");

            }


        }

        @Override
        public void onSlide(@NonNull View bottomSheet, float slideOffset) {
        }
    };

    @SuppressLint("RestrictedApi")
    @Override
    public void setupDialog(Dialog dialog, int style) {
        super.setupDialog(dialog, style);
        //Get the content View
        view = View.inflate(getContext(), R.layout.create_new_bottom_sheet, null);
        dialog.setContentView(view);
        unbinder = ButterKnife.bind(this, view);
        init();
        //Set the coordinator layout behavior
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) ((View) view.getParent()).getLayoutParams();
        CoordinatorLayout.Behavior behavior = params.getBehavior();

        //Set callback
        if (behavior instanceof BottomSheetBehavior) {
            final BottomSheetBehavior mBottomSheetBehavior = (BottomSheetBehavior) behavior;
            ((BottomSheetBehavior) behavior).setBottomSheetCallback(mBottomSheetBehaviorCallback);
            view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    int height = view.getMeasuredHeight();
                    // mBottomSheetBehavior.setPeekHeight(height);
                }
            });
        }
    }

    private void init() {
        createNewModelList = new ArrayList<>();
        for (int i = 0; i < createNewArray.length; i++) {
            CreateNewModel model = new CreateNewModel();
            model.setIcon(icons[i]);
            model.setTitle(createNewArray[i]);
            createNewModelList.add(model);
        }
        CreateNewAdapter adapter = new CreateNewAdapter(getContext(), createNewModelList);
        createNewListView.setAdapter(adapter);
    }

    @OnItemClick(R.id.create_new_list_view)
    void onItemClick(int position) {
        switch (position) {
            case 0:
                AddNewContactActivity.openAddNewContactActivity(getContext());
                break;
            case 1:
                MeetingRequestActivity.openMeetingRequestActivity(getContext(),null);
                break;
            default:
                break;
        }
        dismiss();
    }

    @OnClick(R.id.cancel_create_new_button)
    void onCancelClick() {
        dismiss();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
