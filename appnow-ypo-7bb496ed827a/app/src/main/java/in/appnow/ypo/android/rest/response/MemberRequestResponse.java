package in.appnow.ypo.android.rest.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sonu on 12:17, 24/10/18
 * Copyright (c) 2018 . All rights reserved.
 */
public class MemberRequestResponse {
    @SerializedName("memberName")
    private String memberName;
    @SerializedName("memberShortBio")
    private String memberShortBio;
    @SerializedName("membershipDate")
    private String membershipDate;
    @SerializedName("aboutMember")
    private String aboutMember;
    @SerializedName("memberLoc")
    private String memberLoc;
    @SerializedName("memberContactNum")
    private String memberContactNum;
    @SerializedName("memberEmail")
    private String memberEmail;
    @SerializedName("memberSocialAcc")
    private String memberSocialAcc;
    @SerializedName("setMeetings")
    private String setMeetings;
    @SerializedName("defaultMemDateFlag")
    private String defaultMemDateFlag;
    @SerializedName("defaultEmailFlag")
    private String defaultEmailFlag;
    @SerializedName("defaultPhoneFlag")
    private String defaultPhoneFlag;
    @SerializedName("defaultSocialFlag")
    private String defaultSocialFlag;
    @SerializedName("defaultLocationFlag")
    private String defaultLocationFlag;

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberShortBio() {
        return memberShortBio;
    }

    public void setMemberShortBio(String memberShortBio) {
        this.memberShortBio = memberShortBio;
    }

    public String getMembershipDate() {
        return membershipDate;
    }

    public void setMembershipDate(String membershipDate) {
        this.membershipDate = membershipDate;
    }

    public String getAboutMember() {
        return aboutMember;
    }

    public void setAboutMember(String aboutMember) {
        this.aboutMember = aboutMember;
    }

    public String getMemberLoc() {
        return memberLoc;
    }

    public void setMemberLoc(String memberLoc) {
        this.memberLoc = memberLoc;
    }

    public String getMemberContactNum() {
        return memberContactNum;
    }

    public void setMemberContactNum(String memberContactNum) {
        this.memberContactNum = memberContactNum;
    }

    public String getMemberEmail() {
        return memberEmail;
    }

    public void setMemberEmail(String memberEmail) {
        this.memberEmail = memberEmail;
    }

    public String getMemberSocialAcc() {
        return memberSocialAcc;
    }

    public void setMemberSocialAcc(String memberSocialAcc) {
        this.memberSocialAcc = memberSocialAcc;
    }

    public String getSetMeetings() {
        return setMeetings;
    }

    public void setSetMeetings(String setMeetings) {
        this.setMeetings = setMeetings;
    }

    public String getDefaultMemDateFlag() {
        return defaultMemDateFlag;
    }

    public void setDefaultMemDateFlag(String defaultMemDateFlag) {
        this.defaultMemDateFlag = defaultMemDateFlag;
    }

    public String getDefaultEmailFlag() {
        return defaultEmailFlag;
    }

    public void setDefaultEmailFlag(String defaultEmailFlag) {
        this.defaultEmailFlag = defaultEmailFlag;
    }

    public String getDefaultPhoneFlag() {
        return defaultPhoneFlag;
    }

    public void setDefaultPhoneFlag(String defaultPhoneFlag) {
        this.defaultPhoneFlag = defaultPhoneFlag;
    }

    public String getDefaultSocialFlag() {
        return defaultSocialFlag;
    }

    public void setDefaultSocialFlag(String defaultSocialFlag) {
        this.defaultSocialFlag = defaultSocialFlag;
    }

    public String getDefaultLocationFlag() {
        return defaultLocationFlag;
    }

    public void setDefaultLocationFlag(String defaultLocationFlag) {
        this.defaultLocationFlag = defaultLocationFlag;
    }
}
