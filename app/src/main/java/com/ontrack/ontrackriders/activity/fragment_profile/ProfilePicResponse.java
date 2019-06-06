package com.ontrack.ontrackriders.activity.fragment_profile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProfilePicResponse {

    @SerializedName("data")
    @Expose
    private List<ProfilePicData> data = null;
    @SerializedName("message")
    @Expose
    private String message;

    public List<ProfilePicData> getData() {
        return data;
    }

    public void setData(List<ProfilePicData> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}