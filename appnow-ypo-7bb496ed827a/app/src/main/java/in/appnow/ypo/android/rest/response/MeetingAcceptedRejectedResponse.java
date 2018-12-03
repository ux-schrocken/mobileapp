package in.appnow.ypo.android.rest.response;

import com.google.gson.annotations.SerializedName;


public class MeetingAcceptedRejectedResponse {
    @SerializedName("memberName")
    private String memberName;


    public String getMemberName() {
        return memberName;
    }



}
