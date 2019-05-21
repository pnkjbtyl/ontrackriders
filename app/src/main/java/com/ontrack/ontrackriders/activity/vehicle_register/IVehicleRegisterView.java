package com.ontrack.ontrackriders.activity.vehicle_register;

public interface IVehicleRegisterView {
    void startProgress();
    void stopProgress();
    void onComplete(String message);
}
