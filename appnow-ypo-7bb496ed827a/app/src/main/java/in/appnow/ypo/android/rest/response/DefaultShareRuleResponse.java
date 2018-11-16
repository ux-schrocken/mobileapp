package in.appnow.ypo.android.rest.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Abhishek Thanvi on 15/11/18.
 * Copyright © 2018 Abhishek Thanvi. All rights reserved.
 */

public class DefaultShareRuleResponse {

    @SerializedName("sMemberLoc")
    private String sMemberLoc;
    @SerializedName("sMemberContactNum")
    private String sMemberContactNum;
    @SerializedName("sMemberEmail")
    private String sMemberEmail;
    @SerializedName("sMemberSocialAcc")
    private String sMemberSocialAcc;
    @SerializedName("sSetMeetings")
    private String sSetMeetings;


    public String getsMemberLoc() {
        return sMemberLoc;
    }

    public void setsMemberLoc(String sMemberLoc) {
        this.sMemberLoc = sMemberLoc;
    }

    public String getsMemberContactNum() {
        return sMemberContactNum;
    }

    public void setsMemberContactNum(String sMemberContactNum) {
        this.sMemberContactNum = sMemberContactNum;
    }

    public String getsMemberEmail() {
        return sMemberEmail;
    }

    public void setsMemberEmail(String sMemberEmail) {
        this.sMemberEmail = sMemberEmail;
    }

    public String getsMemberSocialAcc() {
        return sMemberSocialAcc;
    }

    public void setsMemberSocialAcc(String sMemberSocialAcc) {
        this.sMemberSocialAcc = sMemberSocialAcc;
    }

    public String getsSetMeetings() {
        return sSetMeetings;
    }

    public void setsSetMeetings(String sSetMeetings) {
        this.sSetMeetings = sSetMeetings;
    }
}
