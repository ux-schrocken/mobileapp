package in.appnow.ypo.android.rest;

import java.util.List;

import in.appnow.ypo.android.rest.response.AcceptMeetingResponse;
import in.appnow.ypo.android.rest.response.ContactDetail;
import in.appnow.ypo.android.rest.response.ContactResponse;
import in.appnow.ypo.android.rest.response.Contacts;
import in.appnow.ypo.android.rest.response.DefaultShareRuleResponse;
import in.appnow.ypo.android.rest.response.DenyRequestResponse;
import in.appnow.ypo.android.rest.response.MeetingResponse;
import in.appnow.ypo.android.rest.response.MemberRequestResponse;
import in.appnow.ypo.android.rest.response.OpenMeetingResponse;
import in.appnow.ypo.android.rest.response.TaskListResponse;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by sonu on 07/01/18.
 */

public interface APIInterface {

    @GET("getTaskList/{userId}")
    Call<TaskListResponse> getTaskList(@Path("userId") String userId);

    @GET("memberRequest/{reqId}")
    Call<MemberRequestResponse> memberRequest(@Path("reqId") String requestId, @Query("action") String action);


    @GET("setShareDetails/{reqId}")
    Call<List<TaskListResponse>> shareDetails(@Path("reqId") String requestId);

    @GET("getContactList/{userId}")
    Call<ContactResponse> getContactList(@Path("userId") String userId);

    @POST("deleteContact/{contactId}")
    Call<ResponseBody> deleteContact(@Path("contactId") String contactId);

    @GET("getContactDetails/{contactId}")
    Call<Contacts> getContactDetails(@Path("contactId") String contactId);

    @FormUrlEncoded
    @POST("addNewMember/{userId}")
    Call<ResponseBody> addNewMember(@Path("userId") String userId,@Field("memberId") String memberId);

    @POST("requestMeeting")
    Call<MeetingResponse> requestMeeting(@Query("memberId") String memberId, @Query("dateOfMeeting") String dateOfMeeting, @Query("timeOfMeeting") String timeOfMeeting, @Query("reasonForMeeting") String reasonForMeeting);

    @GET("getOpenMeetings/{userId}")
    Call<List<OpenMeetingResponse>> getOpenMeetings(@Path("userId") String userId);

    @POST("removeMeetings/{meetingId}")
    Call<ResponseBody> removeMeetings(@Path("meetingId") String meetingId);

    @GET("getDefaultSharingRule/{userId}")
    Call<DefaultShareRuleResponse> getDefaultSharingRule(@Path("userId") String userId);

    @GET("getMembershipDate/{memberId}")
    Call<MemberRequestResponse> getMemberData(@Path("memberId") String memberId);


    //New Added APIs

    @POST("memberRequest/{memberId}")
    Call<MemberRequestResponse> acceptRequest(@Path("memberId") String memberId);

    @GET("denyRequest/{taskId}")
    Call<DenyRequestResponse> denyRequest(@Path("taskId") String taskId);

    @FormUrlEncoded
    @POST("setShareDetails/{taskId}")
    Call<ResponseBody> setShareDetails(@Path("taskId") String taskId, @Field("memberLoc") String memberLoc, @Field("memberContactNum1") String memberContactNum1
            , @Field("memberEmail1") String memberEmail1, @Field("memberSocialAcc1") String memberSocialAcc1
            , @Field("setMeetings1") String setMeetings1, @Field("aboutMember1") String aboutMember1);


    @FormUrlEncoded
    @POST("addNewMeeting/{userId}")
    Call<MeetingResponse> addMeeting(@Path("userId") String userId, @Field("memberId") String memberId, @Field("meetingDate") String meetingDate
            , @Field("meetingTime") String meetingTime, @Field("meetingReason") String meetingReason);

    @POST("acceptMeetingRequest/{taskId}")
    Call<AcceptMeetingResponse> acceptMeetingRequest(@Path("taskId") String taskId);


}
