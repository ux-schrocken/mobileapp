package in.appnow.ypo.android.dagger.module;

import android.app.Application;
import android.content.Context;

import dagger.Module;
import dagger.Provides;
import in.appnow.ypo.android.dagger.scope.AppScope;

@Module
public class AppModule {

    private final Context context;

    public AppModule(Application application) {
        this.context = application.getApplicationContext();
    }

    @AppScope
    @Provides
    public Context context() {
        return context;
    }
}
