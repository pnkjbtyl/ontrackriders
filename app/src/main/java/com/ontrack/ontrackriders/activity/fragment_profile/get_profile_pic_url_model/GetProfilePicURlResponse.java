
package com.ontrack.ontrackriders.activity.fragment_profile.get_profile_pic_url_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetProfilePicURlResponse {

    @SerializedName("data")
    @Expose
    private Data data;
    @SerializedName("image_url")
    @Expose
    private String imageUrl;
    @SerializedName("message")
    @Expose
    private String message;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
