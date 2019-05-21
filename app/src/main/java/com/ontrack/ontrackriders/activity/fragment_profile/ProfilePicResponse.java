package com.ontrack.ontrackriders.activity.fragment_profile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProfilePicResponse {

@SerializedName("data")
@Expose
private ProfilePicData data;
@SerializedName("message")
@Expose
private String message;

public ProfilePicData getData() {
return data;
}

public void setData(ProfilePicData data) {
this.data = data;
}

public String getMessage() {
return message;
}

public void setMessage(String message) {
this.message = message;
}

}