package in.appnow.ypo.android.ui.main.dagger;

import dagger.Component;
import in.appnow.ypo.android.dagger.component.AppComponent;
import in.appnow.ypo.android.ui.dashboard_contact.DashboardContactFragment;
import in.appnow.ypo.android.ui.meeting.MeetingFragment;
import in.appnow.ypo.android.ui.profile.ProfileFragment;
import in.appnow.ypo.android.ui.main.MainActivity;

/**
 * Created by sonu on 18:11, 18/10/18
 * Copyright (c) 2018 . All rights reserved.
 */
@MainActivityScope
@Component(modules = MainActivityModule.class, dependencies = AppComponent.class)
public interface MainActivityComponent {
    void inject(MainActivity activity);
    void inject(DashboardContactFragment dashboardContactFragment);
    void inject(MeetingFragment meetingFragment);
    void inject(ProfileFragment profileFragment);
}
