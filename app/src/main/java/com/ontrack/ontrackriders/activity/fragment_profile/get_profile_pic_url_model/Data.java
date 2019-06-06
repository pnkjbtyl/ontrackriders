
package com.ontrack.ontrackriders.activity.fragment_profile.get_profile_pic_url_model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("profile_image")
    @Expose
    private List<ProfileImage> profileImage = null;

    public List<ProfileImage> getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(List<ProfileImage> profileImage) {
        this.profileImage = profileImage;
    }

}
