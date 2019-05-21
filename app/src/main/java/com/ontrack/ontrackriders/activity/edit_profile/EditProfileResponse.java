package com.ontrack.ontrackriders.activity.edit_profile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.ontrack.ontrackriders.activity.signup.Data;

public class EditProfileResponse {

    @SerializedName("message")
    @Expose
    private String message;
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
