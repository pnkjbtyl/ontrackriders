package com.ontrack.ontrackriders.activity.login;

public interface ILoginView {
    void startProgress();
    void stopProgress();
    void onComplete(String message);
}
