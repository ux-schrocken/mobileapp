package in.appnow.ypo.android.ui.main.mvp;

import android.view.View;
import android.util.Log;
import in.appnow.ypo.android.mvp_base.BasePresenter;

/**
 * Created by sonu on 18:14, 18/10/18
 * Copyright (c) 2018 . All rights reserved.
 */
public class MainActivityPresenter implements BasePresenter {
    private final MainActivityView view;
    private final MainActivityModel model;
public static final String TAG = "myactivity";
    public MainActivityPresenter(MainActivityView view, MainActivityModel model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void onCreate() {
        onDashboardSelect();
        view.onDashboardClick(view -> {
            onDashboardSelect();
        });
        view.onContactClick(view -> {
            onContactSelect();
        });
        view.onMeetingClick(view -> {
            boolean isClicked = MainActivityPresenter.this.view.onBottomButtonClick(2);
            if (isClicked) {
                model.replaceMeetingFragment();
            }
        });
        view.onProfileClick(view -> {
            boolean isClicked = MainActivityPresenter.this.view.onBottomButtonClick(3);
            if (isClicked) {
                model.replaceProfileFragment();
            }
        });
        view.onCreateNewClick(view -> {
            model.openCreateNewBottomSheet();
        });

    }
    public void swipe(){
        model.replaceDashboardFragment();

      //  presenter.onContactSelect(); //refreshData(); // your code
    }

    public void onDashboardSelect() {
        boolean isClicked = MainActivityPresenter.this.view.onBottomButtonClick(0);
        if (isClicked) {
            model.replaceDashboardFragment();
        }
    }

    public void onContactSelect(){
        boolean isClicked = MainActivityPresenter.this.view.onBottomButtonClick(1);
        if (isClicked) {

            model.replaceContactFragment();

        }
    }

    @Override
    public void onDestroy() {

    }
}
