package in.appnow.ypo.android.dagger.component;

import dagger.Component;
import in.appnow.ypo.android.dagger.module.MyServiceModule;
import in.appnow.ypo.android.dagger.scope.MyServiceScope;

@MyServiceScope
@Component(modules = MyServiceModule.class, dependencies = AppComponent.class)
public interface MyServiceComponent {

  /*void inject(MyFirebaseMessagingService myFirebaseMessagingService);*/
 /*
    void inject(ConversationIntentService conversationIntentService);*/
}
