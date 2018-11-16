package in.appnow.ypo.android.rest.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sonu on 12:56, 23/10/18
 * Copyright (c) 2018 . All rights reserved.
 */
public class ContactDetail extends ContactResponse {
    @SerializedName("aboutMember")
    private String aboutMember;

    public String getAboutMember() {
        return aboutMember;
    }

    public void setAboutMember(String aboutMember) {
        this.aboutMember = aboutMember;
    }
}
