package in.appnow.ypo.android.dagger.module;

import android.app.Application;
import android.content.Context;

import dagger.Module;
import dagger.Provides;
import in.appnow.ypo.android.dagger.scope.AppScope;
import in.appnow.ypo.android.helper.PreferenceManger;

@Module
public class SharedPreferencesModule {
    private final PreferenceManger preferenceManger;

    public SharedPreferencesModule(Application application) {
        preferenceManger = new PreferenceManger(application.getSharedPreferences(PreferenceManger.PREF_KEY, Context.MODE_PRIVATE));
    }

    @Provides
    @AppScope
    PreferenceManger provideSharedPreferences() {
        return preferenceManger;
    }
}
