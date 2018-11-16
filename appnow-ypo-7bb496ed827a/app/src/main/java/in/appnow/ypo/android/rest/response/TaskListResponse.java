package in.appnow.ypo.android.rest.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by sonu on 20:36, 22/10/18
 * Copyright (c) 2018 . All rights reserved.
 */
public class TaskListResponse {


    @SerializedName("taskList")
    private List<Tasks> tasksList;
    @SerializedName("NoofActiveRequests")
    private int activeRequest;

    public List<Tasks> getTasksList() {
        return tasksList;
    }

    public void setTasksList(List<Tasks> tasksList) {
        this.tasksList = tasksList;
    }

    public int getActiveRequest() {
        return activeRequest;
    }

    public void setActiveRequest(int activeRequest) {
        this.activeRequest = activeRequest;
    }
}
