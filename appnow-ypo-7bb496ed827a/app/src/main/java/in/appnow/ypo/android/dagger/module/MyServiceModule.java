package in.appnow.ypo.android.dagger.module;

import android.app.Service;

import dagger.Module;
import dagger.Provides;
import in.appnow.ypo.android.dagger.scope.MyServiceScope;


@Module
public class MyServiceModule {
    private final Service myIntentService;

    public MyServiceModule(Service myIntentService) {
        this.myIntentService = myIntentService;
    }

    @MyServiceScope
    @Provides
    Service providesMyIntentService() {
        return myIntentService;
    }
}
