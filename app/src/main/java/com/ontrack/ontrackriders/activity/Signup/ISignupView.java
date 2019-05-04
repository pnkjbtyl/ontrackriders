package com.ontrack.ontrackriders.activity.Signup;

public interface ISignupView {

    void startProgress();
    void stopProgress();
    void onComplete(String message);
}
