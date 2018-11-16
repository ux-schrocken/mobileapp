package in.appnow.ypo.android.rest.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Abhishek Thanvi on 14/11/18.
 * Copyright © 2018 Abhishek Thanvi. All rights reserved.
 */

public class Contacts implements Parcelable{

    @SerializedName("memberName")
    private String memberName;
    @SerializedName("memberLocation")
    private String memberLoc;
    @SerializedName("memberAbout")
    private String memberShortBio;
    @SerializedName("membershipDate")
    private String membershipDate;
    @SerializedName("memberPhonenumber")
    private String memberContactNum;
    @SerializedName("memberEmail")
    private String memberEmail;
    @SerializedName("memberSocialAcc")
    private String memberSocialAcc;
    @SerializedName("memberId")
    private String memberId;
    @SerializedName("contactId")
    private String contactId;



    public Contacts() {
    }

    protected Contacts(Parcel in) {
        memberId = in.readString();
        contactId = in.readString();
        memberName = in.readString();
        memberLoc = in.readString();
        memberShortBio = in.readString();
        membershipDate = in.readString();
        memberContactNum = in.readString();
        memberEmail = in.readString();
        memberSocialAcc = in.readString();
    }

    public static final Creator<Contacts> CREATOR = new Creator<Contacts>() {
        @Override
        public Contacts createFromParcel(Parcel in) {
            return new Contacts(in);
        }

        @Override
        public Contacts[] newArray(int size) {
            return new Contacts[size];
        }
    };

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getContactId() {
        return contactId;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberLoc() {
        return memberLoc;
    }

    public void setMemberLoc(String memberLoc) {
        this.memberLoc = memberLoc;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(memberId);
        parcel.writeString(contactId);
        parcel.writeString(memberName);
        parcel.writeString(memberLoc);
        parcel.writeString(memberShortBio);
        parcel.writeString(membershipDate);
        parcel.writeString(memberContactNum);
        parcel.writeString(memberEmail);
        parcel.writeString(memberSocialAcc);
    }
}
