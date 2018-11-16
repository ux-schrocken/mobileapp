package in.appnow.ypo.android.ui.meeting_request.dagger;

import dagger.Component;
import in.appnow.ypo.android.dagger.component.AppComponent;
import in.appnow.ypo.android.ui.meeting_request.MeetingRequestActivity;

/**
 * Created by sonu on 13:08, 24/10/18
 * Copyright (c) 2018 . All rights reserved.
 */
@MeetingRequestScope
@Component(modules = MeetingRequestModule.class, dependencies = AppComponent.class)
public interface MeetingRequestComponent {
    void inject(MeetingRequestActivity activity);
}
