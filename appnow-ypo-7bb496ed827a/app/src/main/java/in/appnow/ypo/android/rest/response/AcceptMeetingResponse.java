package in.appnow.ypo.android.rest.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Abhishek Thanvi on 15/11/18.
 * Copyright © 2018 Abhishek Thanvi. All rights reserved.
 */

public class AcceptMeetingResponse {

    @SerializedName("meetingId1")
    public String meetingId1;

    @SerializedName("meetingId2")
    public String meetingId2;

    public String getMeetingId1() {
        return meetingId1;
    }

    public void setMeetingId1(String meetingId1) {
        this.meetingId1 = meetingId1;
    }

    public String getMeetingId2() {
        return meetingId2;
    }

    public void setMeetingId2(String meetingId2) {
        this.meetingId2 = meetingId2;
    }
}
