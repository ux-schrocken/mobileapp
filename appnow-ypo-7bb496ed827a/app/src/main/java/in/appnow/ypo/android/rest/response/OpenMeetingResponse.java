package in.appnow.ypo.android.rest.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by sonu on 12:54, 23/10/18
 * Copyright (c) 2018 . All rights reserved.
 */

// gets data from getOpenMeetings() in BaseService.java

public class OpenMeetingResponse {
    @SerializedName("dateOfMeeting")
    private String dateOfMeeting;
    @SerializedName("timeOfMeeting")
    private String timeOfMeeting;
    @SerializedName("reasonForMeeting")
    private String reasonForMeeting;
    @SerializedName("memberId")
    private String memberId;
    // sam : solved meetingID not deleting
    @SerializedName("MeetingId")
    private String meetingId;
    //added for adding data to accepted and denied list
    @SerializedName("contactList")
    private List<Tasks> contactList;
    @SerializedName("memberName")
    private String memberName;


    public String getDateOfMeeting() {
        return dateOfMeeting;
    }

    public void setDateOfMeeting(String dateOfMeeting) {
        this.dateOfMeeting = dateOfMeeting;
    }

    public String getTimeOfMeeting() {
        return timeOfMeeting;
    }

    public void setTimeOfMeeting(String timeOfMeeting) {
        this.timeOfMeeting = timeOfMeeting;
    }

    public String getReasonForMeeting() {
        return reasonForMeeting;
    }

    public void setReasonForMeeting(String reasonForMeeting) {
        this.reasonForMeeting = reasonForMeeting;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(String meetingId) {
        this.meetingId = meetingId;
    }

    public String getmemberName() {
        return memberName;
    }

    public List<Tasks> getContactList(){
        return contactList;
    }

}
