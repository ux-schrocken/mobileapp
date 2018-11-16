package in.appnow.ypo.android.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtils {
    public static final String DATE_FORMAT_WITH_2_DIGIT_YEAR = "dd MMM yy";
    public static final long ONE_DAY_TIME = 86400000;
    public static final String SQL_DATE_TYPE_FORMAT = "yyyy-MM-dd";
    public static final String BOOKING_SERVER_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String BOOKING_DEVICE_DATE_FORMAT = "EEE, MMM dd, hh:mm aa";
    public static final String TRANSACTION_FORMAT = "dd MMM yyyy, hh:mm aa";
    public static final String MEETING_TIME_FORMAT = "HH:mm";
    public static final String MEETING_DATE_FORMAT = "mm/dd/yyyy";
    public static final String MEETING_RESULT_DATE_FORMAT ="dd MMM, yyyy";
    public static final String MEETING_RESULT_TIME_FORMAT="HH:mm z";
    public static final String OPEN_MEETING_DATE_FORMAT = "dd/mm/yyyy";
    public static final String OPEN_MEETING_DISPLAY_DATE_FORMAT = "dd\nEEE";

    private static final String TAG = DateUtils.class.getSimpleName();
    private static final String TODAY = "Today";
    private static final String TOMORROW = "Tomorrow";
    private static final String YESTERDAY = "Yesterday";
    private static final long oneDateTime = 86400000;
    private static final long sixDaysTime = 518400000;

    public static long getCurrentTimeStamp() {
        return System.currentTimeMillis();
    }

    public static String getDateForServer(long timestamp) {
        return new SimpleDateFormat(SQL_DATE_TYPE_FORMAT, Locale.getDefault()).format(new Date(timestamp));
    }

    public static ArrayList<String> getCurrentSixDays() {
        ArrayList<String> dateList = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            dateList.add(getFullTime(getCurrentTimeStamp(), ((long) i) * 86400000));
        }
        return dateList;
    }

    public static String getFullTime(Long timestamp, long increment) {
        return new SimpleDateFormat(SQL_DATE_TYPE_FORMAT, Locale.getDefault()).format(new Date(new Date(timestamp).getTime() + increment));
    }

    public static String getTodayDate() {
        Date date = new Date(getCurrentTimeStamp());
        SimpleDateFormat fullDateFormat = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());
        Logger.ErrorLog("getTodayDate", fullDateFormat.format(date));
        return fullDateFormat.format(date);
    }


    public static String parseDate(long timeStamp, String dateFormat) {
        Date date = new Date(timeStamp);
        SimpleDateFormat fullDateFormat = new SimpleDateFormat(dateFormat, Locale.getDefault());
        return fullDateFormat.format(date);
    }

    public static String getDateWithIncrement(long increment, String dateFormat) {
        Date newDate = new Date(new Date(getCurrentTimeStamp()).getTime() + increment);
        SimpleDateFormat fullDateFormat = new SimpleDateFormat(dateFormat, Locale.getDefault());
        Logger.ErrorLog("getDateWithIncrement", fullDateFormat.format(newDate));
        return fullDateFormat.format(newDate);
    }


    public static String getMonth(int monthId) {
        String month = "";
        switch (monthId) {
            case 0:
                return "Jan";
            case 1:
                return "Feb";
            case 2:
                return "Mar";
            case 3:
                return "Apr";
            case 4:
                return "May";
            case 5:
                return "Jun";
            case 6:
                return "Jul";
            case 7:
                return "Aug";
            case 8:
                return "Sep";
            case 9:
                return "Oct";
            case 10:
                return "Nov";
            case 11:
                return "Dec";
            default:
                return month;
        }
    }


    private static String getDayType(String date) {
        String dateType = "";
        if (date.equals(getTodayDate())) {
            dateType = TODAY;
        }
        Logger.ErrorLog("getDayType", dateType);
        return dateType;
    }


    public static Date getParsedDate(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        return calendar.getTime();
    }

    public static boolean isTodayDate(long timestamp) {
        Logger.ErrorLog("Is today date : ", "  - " + (timestamp == getCurrentTimeStamp()));
        return getFullTime(timestamp, 0).equals(getFullTime(getCurrentTimeStamp(), 0));
    }

    public static long getLongDateTimeMills(int dateJump) {
        return ((long) dateJump) * 86400000;
    }

    public static long convertStringDateToMilliSecond(String stringDate, String dateFormat) {
        try {
            return new SimpleDateFormat(dateFormat, Locale.getDefault()).parse(stringDate).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static long getTimeDifference(long fromDate, long toDate) {
        long days = (toDate - fromDate) / 86400000;
        Logger.ErrorLog(TAG, "Time Difference in days : " + days);
        return days;
    }

}
