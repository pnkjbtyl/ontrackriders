package com.ontrack.ontrackriders.activity.signup;

public interface ISignupView {

    void startProgress();
    void stopProgress();
    void onComplete(String message);
}
