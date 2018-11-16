package in.appnow.ypo.android.rest;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

import in.appnow.ypo.android.app_base.YPOApplication;
import in.appnow.ypo.android.dialogs.ProgressDialogFragment;
import in.appnow.ypo.android.interfaces.RetroAPICallback;
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
import retrofit2.Callback;
import retrofit2.Response;

import static in.appnow.ypo.android.utils.StringUtils.USER_ID;

/**
 * Created by sonu on 07/01/18.
 */

public final class BaseService {
    public BaseService() {
    }


    public static void getTaskList(final Context context, APIInterface apiInterface, final RetroAPICallback retroAPICallback, final int requestCode) {
        if (!YPOApplication.getInstance().isInternetConnected(false)) {
            retroAPICallback.onNoNetwork(requestCode);
            return;
        }
        ProgressDialogFragment.showProgress(((AppCompatActivity) context).getSupportFragmentManager());

        Call<TaskListResponse> call = apiInterface.getTaskList(USER_ID);
        call.enqueue(new Callback<TaskListResponse>() {
            @Override
            public void onResponse(@NonNull Call<TaskListResponse> call, @NonNull Response<TaskListResponse> response) {
                retroAPICallback.onResponse(call, response, requestCode, null);
            }

            @Override
            public void onFailure(@NonNull Call<TaskListResponse> call, @NonNull Throwable t) {
                retroAPICallback.onFailure(call, t, requestCode, null);
                ProgressDialogFragment.dismissProgress(((AppCompatActivity) context).getSupportFragmentManager());
            }
        });
    }

    public static void memberRequest(final Context context, String memberId, APIInterface apiInterface, final RetroAPICallback retroAPICallback, final int requestCode) {
        if (!YPOApplication.getInstance().isInternetConnected(false)) {
            retroAPICallback.onNoNetwork(requestCode);
            return;
        }
        ProgressDialogFragment.showProgress(((AppCompatActivity) context).getSupportFragmentManager());

        Call<MemberRequestResponse> call = apiInterface.acceptRequest(memberId);
        call.enqueue(new Callback<MemberRequestResponse>() {
            @Override
            public void onResponse(@NonNull Call<MemberRequestResponse> call, @NonNull Response<MemberRequestResponse> response) {
                retroAPICallback.onResponse(call, response, requestCode, null);
            }

            @Override
            public void onFailure(@NonNull Call<MemberRequestResponse> call, @NonNull Throwable t) {
                retroAPICallback.onFailure(call, t, requestCode, null);
                ProgressDialogFragment.dismissProgress(((AppCompatActivity) context).getSupportFragmentManager());
            }
        });
    }






    public static void denyRequest(final Context context, APIInterface apiInterface, final RetroAPICallback retroAPICallback, final int requestCode, String taskId) {
        if (!YPOApplication.getInstance().isInternetConnected(false)) {
            retroAPICallback.onNoNetwork(requestCode);
            return;
        }
        ProgressDialogFragment.showProgress(((AppCompatActivity) context).getSupportFragmentManager());

        Call<DenyRequestResponse> call = apiInterface.denyRequest(taskId);
        call.enqueue(new Callback<DenyRequestResponse>() {
            @Override
            public void onResponse(@NonNull Call<DenyRequestResponse> call, @NonNull Response<DenyRequestResponse> response) {
                retroAPICallback.onResponse(call, response, requestCode, null);
            }

            @Override
            public void onFailure(@NonNull Call<DenyRequestResponse> call, @NonNull Throwable t) {
                retroAPICallback.onFailure(call, t, requestCode, null);
                ProgressDialogFragment.dismissProgress(((AppCompatActivity) context).getSupportFragmentManager());
            }
        });
    }

    public static void acceptMeetingRequest(final Context context, APIInterface apiInterface, final RetroAPICallback retroAPICallback, final int requestCode, String taskId) {
        if (!YPOApplication.getInstance().isInternetConnected(false)) {
            retroAPICallback.onNoNetwork(requestCode);
            return;
        }
        ProgressDialogFragment.showProgress(((AppCompatActivity) context).getSupportFragmentManager());

        Call<AcceptMeetingResponse> call = apiInterface.acceptMeetingRequest(taskId);
        call.enqueue(new Callback<AcceptMeetingResponse>() {
            @Override
            public void onResponse(@NonNull Call<AcceptMeetingResponse> call, @NonNull Response<AcceptMeetingResponse> response) {
                retroAPICallback.onResponse(call, response, requestCode, null);
            }

            @Override
            public void onFailure(@NonNull Call<AcceptMeetingResponse> call, @NonNull Throwable t) {
                retroAPICallback.onFailure(call, t, requestCode, null);
                ProgressDialogFragment.dismissProgress(((AppCompatActivity) context).getSupportFragmentManager());
            }
        });
    }





    public static void shareDetails(final Context context, APIInterface apiInterface, final RetroAPICallback retroAPICallback, final int requestCode) {
        if (!YPOApplication.getInstance().isInternetConnected(false)) {
            retroAPICallback.onNoNetwork(requestCode);
            return;
        }
        ProgressDialogFragment.showProgress(((AppCompatActivity) context).getSupportFragmentManager());

        Call<List<TaskListResponse>> call = apiInterface.shareDetails("21");
        call.enqueue(new Callback<List<TaskListResponse>>() {
            @Override
            public void onResponse(@NonNull Call<List<TaskListResponse>> call, @NonNull Response<List<TaskListResponse>> response) {
                retroAPICallback.onResponse(call, response, requestCode, null);
            }

            @Override
            public void onFailure(@NonNull Call<List<TaskListResponse>> call, @NonNull Throwable t) {
                retroAPICallback.onFailure(call, t, requestCode, null);
                ProgressDialogFragment.dismissProgress(((AppCompatActivity) context).getSupportFragmentManager());
            }
        });
    }

    public static void getContactList(final Context context, APIInterface apiInterface, final RetroAPICallback retroAPICallback, final int requestCode) {
        if (!YPOApplication.getInstance().isInternetConnected(false)) {
            retroAPICallback.onNoNetwork(requestCode);
            return;
        }
        ProgressDialogFragment.showProgress(((AppCompatActivity) context).getSupportFragmentManager());

        Call<ContactResponse> call = apiInterface.getContactList(USER_ID);
        call.enqueue(new Callback<ContactResponse>() {
            @Override
            public void onResponse(@NonNull Call<ContactResponse> call, @NonNull Response<ContactResponse> response) {
                retroAPICallback.onResponse(call, response, requestCode, null);
            }

            @Override
            public void onFailure(@NonNull Call<ContactResponse> call, @NonNull Throwable t) {
                retroAPICallback.onFailure(call, t, requestCode, null);
                ProgressDialogFragment.dismissProgress(((AppCompatActivity) context).getSupportFragmentManager());
            }
        });
    }

    public static void deleteContact(final Context context, APIInterface apiInterface, final RetroAPICallback retroAPICallback, final int requestCode, String contactId) {
        if (!YPOApplication.getInstance().isInternetConnected(false)) {
            retroAPICallback.onNoNetwork(requestCode);
            return;
        }
        ProgressDialogFragment.showProgress(((AppCompatActivity) context).getSupportFragmentManager());

        Call<ResponseBody> call = apiInterface.deleteContact(contactId);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                retroAPICallback.onResponse(call, response, requestCode, null);
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                retroAPICallback.onFailure(call, t, requestCode, null);
                ProgressDialogFragment.dismissProgress(((AppCompatActivity) context).getSupportFragmentManager());
            }
        });
    }

    public static void getContactDetails(final Context context, APIInterface apiInterface, final RetroAPICallback retroAPICallback, final int requestCode,String contactId) {
        if (!YPOApplication.getInstance().isInternetConnected(false)) {
            retroAPICallback.onNoNetwork(requestCode);
            return;
        }
        ProgressDialogFragment.showProgress(((AppCompatActivity) context).getSupportFragmentManager());

        Call<Contacts> call = apiInterface.getContactDetails(contactId);
        call.enqueue(new Callback<Contacts>() {
            @Override
            public void onResponse(@NonNull Call<Contacts> call, @NonNull Response<Contacts> response) {
                retroAPICallback.onResponse(call, response, requestCode, null);
            }

            @Override
            public void onFailure(@NonNull Call<Contacts> call, @NonNull Throwable t) {
                retroAPICallback.onFailure(call, t, requestCode, null);
                ProgressDialogFragment.dismissProgress(((AppCompatActivity) context).getSupportFragmentManager());
            }
        });
    }

    public static void addNewMember(final Context context, String memberId,APIInterface apiInterface, final RetroAPICallback retroAPICallback, final int requestCode) {
        if (!YPOApplication.getInstance().isInternetConnected(false)) {
            retroAPICallback.onNoNetwork(requestCode);
            return;
        }
        ProgressDialogFragment.showProgress(((AppCompatActivity) context).getSupportFragmentManager());

        Call<ResponseBody> call = apiInterface.addNewMember(USER_ID,memberId);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                retroAPICallback.onResponse(call, response, requestCode, null);
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                retroAPICallback.onFailure(call, t, requestCode, null);
                ProgressDialogFragment.dismissProgress(((AppCompatActivity) context).getSupportFragmentManager());
            }
        });
    }


    public static void setShareDetails(final Context context, APIInterface apiInterface, final RetroAPICallback retroAPICallback, final int requestCode,
                                       String taskId, String location, String contact, String email, String social, String meetings, String about) {
        if (!YPOApplication.getInstance().isInternetConnected(false)) {
            retroAPICallback.onNoNetwork(requestCode);
            return;
        }
        ProgressDialogFragment.showProgress(((AppCompatActivity) context).getSupportFragmentManager());

        Call<ResponseBody> call = apiInterface.setShareDetails(taskId,location,contact,email,social,meetings,about);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                retroAPICallback.onResponse(call, response, requestCode, null);
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                retroAPICallback.onFailure(call, t, requestCode, null);
                ProgressDialogFragment.dismissProgress(((AppCompatActivity) context).getSupportFragmentManager());
            }
        });
    }

    //Deprecated
    public static void requestMeeting(final Context context, String dateOfMeeting, String timeOfMeeting, String reasonForMeeting, APIInterface apiInterface, final RetroAPICallback retroAPICallback, final int requestCode) {
        if (!YPOApplication.getInstance().isInternetConnected(false)) {
            retroAPICallback.onNoNetwork(requestCode);
            return;
        }
        ProgressDialogFragment.showProgress(((AppCompatActivity) context).getSupportFragmentManager());

        Call<MeetingResponse> call = apiInterface.requestMeeting("21", dateOfMeeting, timeOfMeeting, reasonForMeeting);
        call.enqueue(new Callback<MeetingResponse>() {
            @Override
            public void onResponse(@NonNull Call<MeetingResponse> call, @NonNull Response<MeetingResponse> response) {
                retroAPICallback.onResponse(call, response, requestCode, null);
            }

            @Override
            public void onFailure(@NonNull Call<MeetingResponse> call, @NonNull Throwable t) {
                retroAPICallback.onFailure(call, t, requestCode, null);
                ProgressDialogFragment.dismissProgress(((AppCompatActivity) context).getSupportFragmentManager());
            }
        });
    }

    public static void getOpenMeetings(final Context context, APIInterface apiInterface, final RetroAPICallback retroAPICallback, final int requestCode) {
        if (!YPOApplication.getInstance().isInternetConnected(false)) {
            retroAPICallback.onNoNetwork(requestCode);
            return;
        }
        ProgressDialogFragment.showProgress(((AppCompatActivity) context).getSupportFragmentManager());

        Call<List<OpenMeetingResponse>> call = apiInterface.getOpenMeetings(USER_ID);
        call.enqueue(new Callback<List<OpenMeetingResponse>>() {
            @Override
            public void onResponse(@NonNull Call<List<OpenMeetingResponse>> call, @NonNull Response<List<OpenMeetingResponse>> response) {
                retroAPICallback.onResponse(call, response, requestCode, null);
            }

            @Override
            public void onFailure(@NonNull Call<List<OpenMeetingResponse>> call, @NonNull Throwable t) {
                retroAPICallback.onFailure(call, t, requestCode, null);
                ProgressDialogFragment.dismissProgress(((AppCompatActivity) context).getSupportFragmentManager());
            }
        });
    }


    // Add New Meeting
    public static void addMeeting(final Context context, String userId ,String memberId ,String dateOfMeeting, String timeOfMeeting, String reasonForMeeting, APIInterface apiInterface, final RetroAPICallback retroAPICallback, final int requestCode) {
        if (!YPOApplication.getInstance().isInternetConnected(false)) {
            retroAPICallback.onNoNetwork(requestCode);
            return;
        }
        ProgressDialogFragment.showProgress(((AppCompatActivity) context).getSupportFragmentManager());

        Call<MeetingResponse> call = apiInterface.addMeeting(userId,memberId,dateOfMeeting,timeOfMeeting,reasonForMeeting);
        call.enqueue(new Callback<MeetingResponse>() {
            @Override
            public void onResponse(@NonNull Call<MeetingResponse> call, @NonNull Response<MeetingResponse> response) {
                retroAPICallback.onResponse(call, response, requestCode, null);
            }

            @Override
            public void onFailure(@NonNull Call<MeetingResponse> call, @NonNull Throwable t) {
                retroAPICallback.onFailure(call, t, requestCode, null);
                ProgressDialogFragment.dismissProgress(((AppCompatActivity) context).getSupportFragmentManager());
            }
        });
    }

    public static void removeMeetings(final Context context, APIInterface apiInterface, final RetroAPICallback retroAPICallback, final int requestCode,String meetingId) {
        if (!YPOApplication.getInstance().isInternetConnected(false)) {
            retroAPICallback.onNoNetwork(requestCode);
            return;
        }
        ProgressDialogFragment.showProgress(((AppCompatActivity) context).getSupportFragmentManager());

        Call<ResponseBody> call = apiInterface.removeMeetings(meetingId);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                retroAPICallback.onResponse(call, response, requestCode, null);
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                retroAPICallback.onFailure(call, t, requestCode, null);
                ProgressDialogFragment.dismissProgress(((AppCompatActivity) context).getSupportFragmentManager());
            }
        });
    }

    public static void getDefaultSharingRule(final Context context, APIInterface apiInterface, final RetroAPICallback retroAPICallback, final int requestCode,String contactId) {
        if (!YPOApplication.getInstance().isInternetConnected(false)) {
            retroAPICallback.onNoNetwork(requestCode);
            return;
        }
        ProgressDialogFragment.showProgress(((AppCompatActivity) context).getSupportFragmentManager());

        Call<DefaultShareRuleResponse> call = apiInterface.getDefaultSharingRule(contactId);
        call.enqueue(new Callback<DefaultShareRuleResponse>() {
            @Override
            public void onResponse(@NonNull Call<DefaultShareRuleResponse> call, @NonNull Response<DefaultShareRuleResponse> response) {
                retroAPICallback.onResponse(call, response, requestCode, null);
            }

            @Override
            public void onFailure(@NonNull Call<DefaultShareRuleResponse> call, @NonNull Throwable t) {
                retroAPICallback.onFailure(call, t, requestCode, null);
                ProgressDialogFragment.dismissProgress(((AppCompatActivity) context).getSupportFragmentManager());
            }
        });
    }


    public static void getMemberData(final Context context, APIInterface apiInterface, final RetroAPICallback retroAPICallback, final int requestCode) {
        if (!YPOApplication.getInstance().isInternetConnected(false)) {
            retroAPICallback.onNoNetwork(requestCode);
            return;
        }
        ProgressDialogFragment.showProgress(((AppCompatActivity) context).getSupportFragmentManager());

        Call<MemberRequestResponse> call = apiInterface.getMemberData("21");
        call.enqueue(new Callback<MemberRequestResponse>() {
            @Override
            public void onResponse(@NonNull Call<MemberRequestResponse> call, @NonNull Response<MemberRequestResponse> response) {
                retroAPICallback.onResponse(call, response, requestCode, null);
            }

            @Override
            public void onFailure(@NonNull Call<MemberRequestResponse> call, @NonNull Throwable t) {
                retroAPICallback.onFailure(call, t, requestCode, null);
                ProgressDialogFragment.dismissProgress(((AppCompatActivity) context).getSupportFragmentManager());
            }
        });
    }


}

