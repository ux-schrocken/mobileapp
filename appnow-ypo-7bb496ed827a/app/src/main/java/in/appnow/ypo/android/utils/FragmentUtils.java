package in.appnow.ypo.android.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by Sonu on 14/08/17.
 */

public class FragmentUtils {

    public static  int CONTACT = 1;
    public static final int DASHBOARD = 0;
    public static final int DATE = 0;
    public static final int TIME = 1;
    public static  int DASHBOARD_SELECTED = 1;
    public static  int TASKCONTACTSWITCHER = 1;
    public static  int TAB_SELECTOR = 1;


    public static final String LOGIN_FRAGMENT = "LoginFragment";
    public static final String REGISTER_FRAGMENT = "RegisterFragment";
    public static final String OTP_VERIFICATION_FRAGMENT = "OTPVerificationFragment";
    public static final String BOOK_CAB_FRAGMENT = "book_cab_fragment";
    public static final String HISTORY_FRAGMENT = "history_fragment";
    public static final String PROGRESS_DIALOG_FRAGMENT = "progress_dialog_fragment";
    public static final String DASHBOARD_FRAGMENT = "dashboard_fragment";
    public static final String PROFILE_FRAGMENT = "profile_fragment";
    public static final String MEETING_FRAGMENT = "meeting_fragment";
    public static final String CONTACT_FRAGMENT = "contact_fragment";
    public static final String CREATE_NEW_BOTTOM_SHEET_FRAGMENT = "create_new_bottom_sheet_fragment";

    public static final String TASKLIST_ACCEPTED_FRAGMENT = "taskList_accepted_fragment";
    public static final String TASKLIST_DENIED_FRAGMENT = "taskList_denied_fragment";

    public static final String CONTACT_ACCEPTED_FRAGMENT = "contact_accepted_fragment";
    public static final String CONTACT_DENIED_FRAGMENT = "contact_denied_fragment";


    public static void replaceFragment(FragmentManager fragmentManager, int containerId, Fragment fragment, String tag, boolean isAddToBackStack) {
     //   if (isFragmentReplaced(fragmentManager, tag) == null) {

           final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(containerId, fragment, tag);
            fragmentTransaction.detach(fragment);
            fragmentTransaction.attach(fragment);


            if (isAddToBackStack) {
                fragmentTransaction.addToBackStack(tag);
            }
            fragmentTransaction.commit();
       // }
    }

    public static Fragment isFragmentReplaced(FragmentManager fragmentManager, String tag) {
        return fragmentManager.findFragmentByTag(tag);
    }
}

/*
*
Fragment frg = null;
frg = getSupportFragmentManager().findFragmentByTag("Your_Fragment_TAG");
final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
ft.detach(frg);
ft.attach(frg);
ft.commit();
* */