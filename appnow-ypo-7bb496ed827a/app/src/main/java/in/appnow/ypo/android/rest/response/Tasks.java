package in.appnow.ypo.android.rest.response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Abhishek Thanvi on 14/11/18.
 * Copyright © 2018 Abhishek Thanvi. All rights reserved.
 */

public class Tasks implements Serializable {

    @SerializedName("memberName")
    private String memberName;
    @SerializedName("taskType")
    private String taskType;
    @SerializedName("memberShortBio")
    private String memberShortBio;
    @SerializedName("memberDetails")
    private String membershipDate;
    @SerializedName("memberAbout")
    private String memberAbout;
    @SerializedName("taskId")
    private String taskId;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public String getMembershipDate() {
        return membershipDate;
    }

    public void setMembershipDate(String membershipDate) {
        this.membershipDate = membershipDate;
    }

    public String getMemberShortBio() {
        return memberShortBio;
    }

    public void setMemberShortBio(String memberShortBio) {
        this.memberShortBio = memberShortBio;
    }

    public String getMemberAbout() {
        return memberAbout;
    }

    public void setMemberAbout(String memberAbout) {
        this.memberAbout = memberAbout;
    }
}
