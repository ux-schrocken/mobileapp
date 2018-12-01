package in.appnow.ypo.android.ui.result.mvp;

import android.view.View;

import in.appnow.ypo.android.mvp_base.BasePresenter;
import in.appnow.ypo.android.ui.main.mvp.MainActivityPresenter;
import in.appnow.ypo.android.ui.main.mvp.MainActivityView;

/**
 * Created by sonu on 17:23, 23/10/18
 * Copyright (c) 2018 . All rights reserved.
 */
public class ResultPresenter implements BasePresenter {
    private final ResultView view;
    private final ResultModel model;
    MainActivityView mainActivityView;

    public ResultPresenter(ResultView view, ResultModel model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void onCreate() {
        view.updateViews(model.getResultEnum(), model.getUserName());
//        view.onDoneButtonClick(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                model.closeActivity();
//               // swipe();
//
//            }
//        });
        view.onDoneButtonClick(view->{
           // swipe();
            model.closeActivity();
        });
    }


    // sam added
//    public void swipe() {
//
//        switch (mainActivityView.currentPosition) {
//
//            case 0:
//                model.replaceDashboardFragment();
//                break;
//            case 1:
//                model.replaceContactFragment();
//                break;
//            case 2:
//                model.replaceMeetingFragment();
//                break;
//            case 3:
//                model.replaceProfileFragment();
//                break;
//            default:
//                break;
//        }
//        //model.closeActivity();
//    }


    @Override
    public void onDestroy() {

    }


}
