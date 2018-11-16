package in.appnow.ypo.android.dialogs;

import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.support.v7.app.AlertDialog.Builder;

import in.appnow.ypo.android.R;


public class DialogHelper {
    public static void showMessageOKCancel(Context context, String message, String positiveButton, String negativeButton, OnClickListener okListener, OnClickListener cancelListener) {
        new Builder(context, R.style.MyAlertDialogStyle).setMessage(message)
                .setPositiveButton(positiveButton, okListener)
                .setNegativeButton(negativeButton, cancelListener)
                .setCancelable(false)
                .create()
                .show();
    }

    public static void showListDialog(Context context, String title, String[] itemList, OnClickListener okListener) {
        new Builder(context, R.style.MyAlertDialogStyle)
                .setTitle(title)
                .setItems(itemList, okListener)
                .setCancelable(true)
                .create()
                .show();
    }

    public static void showAlertDialogWithNeutralButton(Context context, String message, String positiveButton) {
        new Builder(context, R.style.MyAlertDialogStyle).setMessage(message)
                .setPositiveButton(positiveButton, null)
                .setCancelable(true)
                .create()
                .show();
    }
}
