package in.appnow.ypo.android.dagger.component;

import android.content.Context;

import dagger.Component;
import in.appnow.ypo.android.dagger.module.AppModule;
import in.appnow.ypo.android.dagger.module.NetworkModule;
import in.appnow.ypo.android.dagger.module.SharedPreferencesModule;
import in.appnow.ypo.android.dagger.scope.AppScope;
import in.appnow.ypo.android.helper.PreferenceManger;
import in.appnow.ypo.android.rest.APIInterface;

@AppScope
@Component(modules = {AppModule.class, NetworkModule.class, SharedPreferencesModule.class})
public interface AppComponent {

    Context context();

    APIInterface apiInterface();

    PreferenceManger preferenceManger();

}
