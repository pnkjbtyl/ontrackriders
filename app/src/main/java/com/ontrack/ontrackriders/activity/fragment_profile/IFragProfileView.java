package com.ontrack.ontrackriders.activity.fragment_profile;

public interface IFragProfileView {
    void startProgress();
    void stopProgress();
    void onComplete(String message);
    void setProfilePic(String url);
}
