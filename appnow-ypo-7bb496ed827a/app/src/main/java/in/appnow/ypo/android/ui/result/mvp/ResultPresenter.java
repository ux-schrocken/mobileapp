package in.appnow.ypo.android.ui.result.mvp;

import android.view.View;

import in.appnow.ypo.android.mvp_base.BasePresenter;

/**
 * Created by sonu on 17:23, 23/10/18
 * Copyright (c) 2018 . All rights reserved.
 */
public class ResultPresenter implements BasePresenter {
    private final ResultView view;
    private final ResultModel model;

    public ResultPresenter(ResultView view, ResultModel model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void onCreate() {
        view.updateViews(model.getResultEnum(), model.getUserName());
        view.onDoneButtonClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model.closeActivity();
            }
        });
    }

    @Override
    public void onDestroy() {

    }
}
