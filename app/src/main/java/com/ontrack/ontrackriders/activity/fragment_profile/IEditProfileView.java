package com.ontrack.ontrackriders.activity.fragment_profile;

public interface IEditProfileView {
    void startProgress();
    void stopProgress();
    void onComplete(String message);
}
