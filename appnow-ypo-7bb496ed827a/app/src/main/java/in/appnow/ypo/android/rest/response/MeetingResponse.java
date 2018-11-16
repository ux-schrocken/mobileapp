package in.appnow.ypo.android.rest.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sonu on 16:53, 24/10/18
 * Copyright (c) 2018 . All rights reserved.
 */
public class MeetingResponse {
    @SerializedName("taskId")
    private String taskId;

    public String getMeetingId() {
        return taskId;
    }

    public void setMeetingId(String meetingId) {
        this.taskId = meetingId;
    }
}
