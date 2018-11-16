package in.appnow.ypo.android.app_base;

import android.app.Application;
import android.app.Service;
import android.support.v7.app.AppCompatActivity;

import in.appnow.ypo.android.R;
import in.appnow.ypo.android.dagger.component.AppComponent;
import in.appnow.ypo.android.dagger.component.DaggerAppComponent;
import in.appnow.ypo.android.dagger.module.AppModule;
import in.appnow.ypo.android.dagger.module.SharedPreferencesModule;
import in.appnow.ypo.android.helper.CheckInternetConnection;
import in.appnow.ypo.android.helper.PreferenceManger;
import in.appnow.ypo.android.utils.ToastUtils;


/**
 * Created by Sonu on 16/08/17.
 */

public class YPOApplication extends Application {

    private static YPOApplication mInstance;
    private static PreferenceManger preferenceManger;
    private AppComponent appComponent;


    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .sharedPreferencesModule(new SharedPreferencesModule(this))
                .build();
    }

    public AppComponent component() {
        return appComponent;
    }

    public static YPOApplication getInstance() {
        return mInstance;
    }

    public static YPOApplication get(AppCompatActivity activity) {
        return (YPOApplication) activity.getApplication();
    }

    public static YPOApplication get(Service service) {
        return (YPOApplication) service.getApplication();
    }

    public boolean isInternetConnected(boolean showToast) {
        if (CheckInternetConnection.checkInternetConnection(this))
            return true;
        else {
            if (showToast)
                ToastUtils.longToast(R.string.no_internet_connection);
            return false;
        }
    }




}
