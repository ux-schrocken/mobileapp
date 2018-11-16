package in.appnow.ypo.android.helper;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.File;

public class FileNameCreation {
    private static final String TAG = FileNameCreation.class.getSimpleName();

    public static File getMainDirectoryName(Context context) {
        File mainDir = new File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), "WritOn");
        if (!mainDir.exists() && mainDir.mkdir()) {
            Log.e(TAG, "Main Directory Created : " + mainDir);
        }
        return mainDir;
    }
}
