package in.appnow.ypo.android.ui.date_time.mvp;

import android.view.View;

import in.appnow.ypo.android.mvp_base.BasePresenter;
import in.appnow.ypo.android.utils.FragmentUtils;

/**
 * Created by sonu on 13:31, 24/10/18
 * Copyright (c) 2018 . All rights reserved.
 */
public class DateTimePresenter implements BasePresenter {
    private final DateTimeView view;
    private final DateTimeModel model;

    private int currentSelectType;

    public DateTimePresenter(DateTimeView view, DateTimeModel model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void onCreate() {
        currentSelectType = model.getSelectType();
        view.updateViews(currentSelectType);

        view.onCancelButtonClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentSelectType == FragmentUtils.DATE) {
                    model.closeActivity();
                } else {
                    currentSelectType = FragmentUtils.DATE;
                    DateTimePresenter.this.view.updateViews(currentSelectType);
                }
            }
        });

        view.onDoneButtonClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentSelectType == FragmentUtils.DATE) {
                    currentSelectType = FragmentUtils.TIME;
                    DateTimePresenter.this.view.updateViews(currentSelectType);
                } else {
                    model.dataSelected(DateTimePresenter.this.view.getSelectedDate(), DateTimePresenter.this.view.getSelectedTime());
                }
            }
        });
    }

    @Override
    public void onDestroy() {

    }
}
