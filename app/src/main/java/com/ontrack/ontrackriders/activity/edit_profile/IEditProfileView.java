package com.ontrack.ontrackriders.activity.edit_profile;

public interface IEditProfileView {
    void startProgress();
    void stopProgress();
    void onComplete(String message);
}
