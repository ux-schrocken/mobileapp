package in.appnow.ypo.android.rest.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Abhishek Thanvi on 14/11/18.
 * Copyright © 2018 Abhishek Thanvi. All rights reserved.
 */

public class DenyRequestResponse {

    @SerializedName("success")
    private String success;

    @SerializedName("result")
    private String result;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
