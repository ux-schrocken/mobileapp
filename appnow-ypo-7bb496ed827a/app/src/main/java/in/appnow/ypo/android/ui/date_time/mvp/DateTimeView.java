package in.appnow.ypo.android.ui.date_time.mvp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.appnow.ypo.android.R;
import in.appnow.ypo.android.utils.DateUtils;
import in.appnow.ypo.android.utils.FragmentUtils;
import in.appnow.ypo.android.utils.Logger;
import picker.ugurtekbas.com.Picker.Picker;
import picker.ugurtekbas.com.Picker.TimeChangedListener;

/**
 * Created by sonu on 13:30, 24/10/18
 * Copyright (c) 2018 . All rights reserved.
 */
public class DateTimeView extends FrameLayout {

    private static final String TAG = DateTimeView.class.getSimpleName();

    @BindView(R.id.toolbar_left_action_button)
    Button cancelButton;
    @BindView(R.id.toolbar_right_action_button)
    Button doneButton;

    @BindView(R.id.select_date_time_title_label)
    TextView titleLabel;
    @BindView(R.id.select_date_time_desc_label)
    TextView descLabel;
    @BindView(R.id.select_date_card_view)
    CardView dateCardView;
    @BindView(R.id.select_date_calendar_view)
    MaterialCalendarView materialCalendarView;
    @BindView(R.id.select_time_dial_picker)
    Picker timePicker;

    private long selectedTime;
    private long selectedDate;

    public DateTimeView(@NonNull Context context) {
        super(context);
        inflate(context, R.layout.activity_select_date_time, this);
        ButterKnife.bind(this, this);
        cancelButton.setText("Cancel");
        doneButton.setText("Done");
        doneButton.setTextColor(context.getResources().getColor(R.color.blue));

        materialCalendarView.setOnDateChangedListener((materialCalendarView, calendarDay, b) -> {
            String selectedDateString = calendarDay.getMonth() + "/" + calendarDay.getDay() + "/" + calendarDay.getYear();
            selectedDate = DateUtils.convertStringDateToMilliSecond(selectedDateString, DateUtils.MEETING_DATE_FORMAT);
        });

       /* timePicker.setTimeChangedListener(date -> {
            selectedTime = date.getTime();
        });*/
    }

    public void updateViews(int selectDate) {
        if (selectDate == FragmentUtils.DATE) {
            titleLabel.setText("Select Date");
            descLabel.setText("Select Date from the calendar");
            materialCalendarView.setCurrentDate(CalendarDay.today());
            materialCalendarView.state().edit()
                    .setMinimumDate(CalendarDay.today())
                    .commit();
            dateCardView.setVisibility(View.VISIBLE);
            timePicker.setVisibility(View.GONE);
        } else {
            titleLabel.setText("Select Time");
            descLabel.setText("Just rotate the knob to adjust & select time");
            dateCardView.setVisibility(View.GONE);
            timePicker.setHourFormat(true);
            timePicker.setDialAdjust(true);
            timePicker.setTime(Calendar.getInstance());
            timePicker.setVisibility(View.VISIBLE);
        }
    }

    public void onDoneButtonClick(OnClickListener onClickListener) {
        doneButton.setOnClickListener(onClickListener);
    }

    public void onCancelButtonClick(OnClickListener onClickListener) {
        cancelButton.setOnClickListener(onClickListener);
    }

    public long getSelectedDate() {
        return selectedDate;
    }

    public long getSelectedTime() {
        return timePicker.getTime().getTime();
    }
}
