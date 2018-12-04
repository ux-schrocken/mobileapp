package in.appnow.ypo.android.ui.dashboard_contact.mvp;

import android.graphics.Color;
import android.os.Handler;
import android.support.annotation.Nullable;

import java.util.List;

import in.appnow.ypo.android.interfaces.RetroAPICallback;
import in.appnow.ypo.android.mvp_base.BasePresenter;
import in.appnow.ypo.android.rest.response.ContactResponse;
import in.appnow.ypo.android.rest.response.Contacts;
import in.appnow.ypo.android.rest.response.TaskListResponse;
import in.appnow.ypo.android.rest.response.Tasks;

import android.util.Log;

import in.appnow.ypo.android.ui.contact_details.ContactDetailActivity;
import in.appnow.ypo.android.ui.dashboard_contact.mvp.view.DashboardContactView;
import in.appnow.ypo.android.ui.dashboard_contact.mvp.view.contact.ContactViewHolder;
import in.appnow.ypo.android.ui.dashboard_contact.mvp.view.dashboard.DashboardViewHolder;
import in.appnow.ypo.android.ui.result.ResultActivity;
import in.appnow.ypo.android.ui.result.ResultEnum;
import in.appnow.ypo.android.utils.FragmentUtils;
import in.appnow.ypo.android.utils.Logger;
import in.appnow.ypo.android.utils.ToastUtils;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by sonu on 18:14, 18/10/18
 * Copyright (c) 2018 . All rights reserved.
 */
public class DashboardContactPresenter implements BasePresenter, RetroAPICallback {
    private static final int FETCH_TASK_LIST_REQUEST_CODE = 1;
    private static final int FETCH_ACCEPTED_TASK_LIST_REQUEST_CODE = 6;
    private static final int FETCH_DENIED_TASK_LIST_REQUEST_CODE = 7;

    private static final String TAG = DashboardContactPresenter.class.getSimpleName();
    private static final int FETCH_CONTACT_LIST_REQUEST_CODE = 2;
    private static final int FETCH_ACCEPTED_CONTACT_LIST_REQUEST_CODE = 8;
    private static final int FETCH_DENIED_CONTACT_LIST_REQUEST_CODE = 9;

    private static final int DELETE_CONTACT_REQUEST_CODE = 3;
    private static final int DENY_REQUEST_CODE = 4;
    private static final int ACCEPT_MEETING_REQUEST_CODE = 5;
    private final DashboardContactView view;
    private final DashboardContactModel model;
    private int isContact;
    private Tasks tasks = null;
    private int position = -1;
    public int addColor = 0;


    public DashboardContactPresenter(DashboardContactView view, DashboardContactModel model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void onCreate() {
        if (isContact == FragmentUtils.CONTACT) {
            fetchContactList();
            abc(1);
        }
        else {
            fetchTaskList();
            abc(1);
        }

        view.allClick(view -> {
            FragmentUtils.TAB_SELECTOR=1;
            if(FragmentUtils.TASKCONTACTSWITCHER==1){

                fetchTaskList();
                abc(1);
            }
            if(FragmentUtils.TASKCONTACTSWITCHER==2){
                fetchContactList();
                abc(1);
            }
        });

        view.acceptedClick(view -> {
            FragmentUtils.TAB_SELECTOR=2;
            if(FragmentUtils.TASKCONTACTSWITCHER==1){
                fetchTaskAcceptedList();
                abc(2);
            }
            if(FragmentUtils.TASKCONTACTSWITCHER==2){
                fetchContactAcceptedList();
                abc(2);
            }
        });

        view.deniedClick(view -> {
            FragmentUtils.TAB_SELECTOR=3;
            if(FragmentUtils.TASKCONTACTSWITCHER==1){
                fetchTaskDeniedList();
                abc(3);
            }
            if(FragmentUtils.TASKCONTACTSWITCHER==2){
                fetchContactDeniedList();
                abc(3);
            }
        });
    }

    public void abc(int temp) {
        view.colorChanger(temp);
    }


    private void fetchTaskList() {
        model.fetchTaskList(this, FETCH_TASK_LIST_REQUEST_CODE);
    }
    private void fetchTaskAcceptedList() {
        model.fetchTaskAcceptedList(this, FETCH_ACCEPTED_TASK_LIST_REQUEST_CODE);
    }
    private void fetchTaskDeniedList() {
        model.fetchTaskDeniedList(this, FETCH_DENIED_TASK_LIST_REQUEST_CODE);
    }




    private void fetchContactList() {
        model.fetchContactList(this, FETCH_CONTACT_LIST_REQUEST_CODE);

        Log.e(TAG, model.toString());
    }

    private void fetchContactAcceptedList() {
        model.fetchContactAcceptedList(this, FETCH_ACCEPTED_CONTACT_LIST_REQUEST_CODE);
    }

    private void fetchContactDeniedList() {
        model.fetchContactDeniedList(this, FETCH_DENIED_CONTACT_LIST_REQUEST_CODE);
    }

    @Override
    public void onDestroy() {

    }

    public void setUpFragment(int isContact) {
        this.isContact = isContact;
    }

    @Override
    public void onResponse(Call<?> call, Response<?> response, int requestCode, @Nullable Object request) {
        switch (requestCode) {
            case FETCH_TASK_LIST_REQUEST_CODE:
                if (response.isSuccessful()) {
                    TaskListResponse taskListResponseList = (TaskListResponse) response.body();
                    if (taskListResponseList.getTasksList() != null && taskListResponseList.getTasksList().size() > 0) {
                        view.updateTaskList(taskListResponseList, new DashboardViewHolder.OnDenyOptionListener() {
                            @Override
                            public void onDenyRequest(Tasks response) {
                                tasks = response;
                                model.denyRequest(DashboardContactPresenter.this, DENY_REQUEST_CODE, response.getTaskId());
                            }
                        }, new DashboardViewHolder.OnAcceptOptionListener() {
                            @Override
                            public void onAcceptRequest(Tasks response) {
                                tasks = response;
                                if (response.getTaskType().equalsIgnoreCase("meeting")) {
                                    model.acceptMeeting(DashboardContactPresenter.this, ACCEPT_MEETING_REQUEST_CODE, response.getTaskId());
                                }
                            }
                        });
                    } else {
                        view.isListEmpty(true, "No tasks.");
                    }
                } else {
                    view.isListEmpty(true, "Server error!!");
                }
                break;
            case FETCH_ACCEPTED_TASK_LIST_REQUEST_CODE:
                if (response.isSuccessful()) {
                    TaskListResponse taskListResponseList = (TaskListResponse) response.body();
                    if (taskListResponseList.getTasksList() != null && taskListResponseList.getTasksList().size() > 0) {
                        view.updateTaskAcceptedList(taskListResponseList, new DashboardViewHolder.OnDenyOptionListener() {
                            @Override
                            public void onDenyRequest(Tasks response) {
                                tasks = response;
                                model.denyRequest(DashboardContactPresenter.this, DENY_REQUEST_CODE, response.getTaskId());
                            }
                        }, new DashboardViewHolder.OnAcceptOptionListener() {
                            @Override
                            public void onAcceptRequest(Tasks response) {
                                tasks = response;
                                if (response.getTaskType().equalsIgnoreCase("meeting")) {
                                    model.acceptMeeting(DashboardContactPresenter.this, ACCEPT_MEETING_REQUEST_CODE, response.getTaskId());
                                }
                            }
                        });
                    } else {
                        view.isListEmpty(true, "No tasks.");
                    }
                } else {
                    view.isListEmpty(true, "Server error!!");
                }
                break;
            case FETCH_DENIED_TASK_LIST_REQUEST_CODE:
                if (response.isSuccessful()) {
                    TaskListResponse taskListResponseList = (TaskListResponse) response.body();
                    if (taskListResponseList.getTasksList() != null && taskListResponseList.getTasksList().size() > 0) {
                        view.updateTaskDeniedList(taskListResponseList, new DashboardViewHolder.OnDenyOptionListener() {
                            @Override
                            public void onDenyRequest(Tasks response) {
                                tasks = response;
                                model.denyRequest(DashboardContactPresenter.this, DENY_REQUEST_CODE, response.getTaskId());
                            }
                        }, new DashboardViewHolder.OnAcceptOptionListener() {
                            @Override
                            public void onAcceptRequest(Tasks response) {
                                tasks = response;
                                if (response.getTaskType().equalsIgnoreCase("meeting")) {
                                    model.acceptMeeting(DashboardContactPresenter.this, ACCEPT_MEETING_REQUEST_CODE, response.getTaskId());
                                }
                            }
                        });
                    } else {
                        view.isListEmpty(true, "No tasks.");
                    }
                } else {
                    view.isListEmpty(true, "Server error!!");
                }
                break;
            case FETCH_CONTACT_LIST_REQUEST_CODE:
                if (response.isSuccessful()) {
                    ContactResponse contactResponse = (ContactResponse) response.body();
                    if (contactResponse != null && contactResponse.getContacts().size() > 0) {
                        view.updateContactList(contactResponse, new ContactViewHolder.OnContactMoreOptionListener() {
                            @Override
                            public void onDeleteContact(Contacts response, int pos) {
                                position = pos;
                                model.deleteContact(DashboardContactPresenter.this, DELETE_CONTACT_REQUEST_CODE, response.getContactId());
                                Log.e(TAG, model.toString());
                            }

                            @Override
                            public void onViewDetails(Contacts response) {
                                model.viewContactDetail(response.getContactId());
                            }
                        });
                    } else {
                        view.isListEmpty(true, "No contacts.");
                    }
                } else {
                    view.isListEmpty(true, "Server error!!");
                }
                break;

                // sam added
            case FETCH_ACCEPTED_CONTACT_LIST_REQUEST_CODE:
                if (response.isSuccessful()) {
                    ContactResponse contactResponse = (ContactResponse) response.body();
                    if (contactResponse != null && contactResponse.getContacts().size() > 0) {
                        view.updateAcceptedContactList(contactResponse, new ContactViewHolder.OnContactMoreOptionListener() {
                            @Override
                            public void onDeleteContact(Contacts response, int pos) {
                                position = pos;
                                model.deleteContact(DashboardContactPresenter.this, DELETE_CONTACT_REQUEST_CODE, response.getContactId());
                                Log.e(TAG, model.toString());
                            }

                            @Override
                            public void onViewDetails(Contacts response) {
                                model.viewContactDetail(response.getContactId());
                            }
                        });
                    } else {
                        view.isListEmpty(true, "No contacts.");
                    }
                } else {
                    view.isListEmpty(true, "Server error!!");
                }
                break;

            case FETCH_DENIED_CONTACT_LIST_REQUEST_CODE:
                if (response.isSuccessful()) {
                    ContactResponse contactResponse = (ContactResponse) response.body();
                    if (contactResponse != null && contactResponse.getContacts().size() > 0) {
                        view.updateDeniedContactList(contactResponse, new ContactViewHolder.OnContactMoreOptionListener() {
                            @Override
                            public void onDeleteContact(Contacts response, int pos) {
                                position = pos;
                                model.deleteContact(DashboardContactPresenter.this, DELETE_CONTACT_REQUEST_CODE, response.getContactId());
                                Log.e(TAG, model.toString());
                            }

                            @Override
                            public void onViewDetails(Contacts response) {
                                model.viewContactDetail(response.getContactId());
                            }
                        });
                    } else {
                        view.isListEmpty(true, "No contacts.");
                    }
                } else {
                    view.isListEmpty(true, "Server error!!");
                }
                break;



            case DELETE_CONTACT_REQUEST_CODE:
                if (response.isSuccessful()) {
                    ToastUtils.shortToast("Contact deleted.");

                    (new Handler()).postDelayed(new Runnable() {
                        public void run() {
                            // Actions to do after 5 seconds
                            model.replaceContactFragment();
                        }
                    }, 500);

                } else {
                    ToastUtils.shortToast("Failed to delete contact. Please try again.");
                }
                break;
            case DENY_REQUEST_CODE:
                if (response.isSuccessful()) {
                    if (tasks != null) {

                        (new Handler()).postDelayed(new Runnable() {
                            public void run() {
                                // Actions to do after 5 seconds
                                fetchTaskList();
                            }
                        }, 1000);

                        model.showResult(tasks);
                        tasks = null;
                    }
                } else {
                    ToastUtils.shortToast("Failed to delete contact. Please try again.");
                }
                break;
            case ACCEPT_MEETING_REQUEST_CODE:
                if (response.isSuccessful()) {
                    if (tasks != null) {

                        (new Handler()).postDelayed(new Runnable() {
                            public void run() {
                                // Actions to do after 5 seconds
                                fetchTaskList();
                            }
                        }, 1000);


                        model.showMeetingResult(tasks);
                        tasks = null;
                    }
                } else {
                    ToastUtils.shortToast("Failed to accept meeting request. Please try again.");
                }
                break;
        }
        model.dismissProgressDialog();
    }

    @Override
    public void onFailure(Call<?> call, Throwable t, int requestCode, @Nullable Object request) {
        switch (requestCode) {
            case FETCH_TASK_LIST_REQUEST_CODE:
                Logger.ErrorLog(TAG, "TASK LIST Error : " + t.getLocalizedMessage());
                view.isListEmpty(true, "Server error!!");
                break;
            case FETCH_CONTACT_LIST_REQUEST_CODE:
                Logger.ErrorLog(TAG, "CONTACT Error : " + t.getLocalizedMessage());
                view.isListEmpty(true, "Server error!!");
                break;
            case DELETE_CONTACT_REQUEST_CODE:
                ToastUtils.shortToast("Failed to delete contact. Please try again.");
                break;
            case DENY_REQUEST_CODE:
                ToastUtils.shortToast("Failed to deny request. Please try again.");
                break;
            case ACCEPT_MEETING_REQUEST_CODE:
                ToastUtils.shortToast("Failed to accept meeting request. Please try again.");
                break;
        }
    }


    @Override
    public void onNoNetwork(int requestCode) {
        switch (requestCode) {
            case FETCH_CONTACT_LIST_REQUEST_CODE:
            case FETCH_TASK_LIST_REQUEST_CODE:
                view.isListEmpty(true, "Please connect to internet");
                break;
        }
    }
}
