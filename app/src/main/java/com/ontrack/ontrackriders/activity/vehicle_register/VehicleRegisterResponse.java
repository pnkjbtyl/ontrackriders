package com.ontrack.ontrackriders.activity.vehicle_register;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.ontrack.ontrackriders.activity.login.Data;

public class VehicleRegisterResponse {

    @SerializedName("vehicles")
    @Expose
    private Data data;
    @SerializedName("message")
    @Expose
    private String message;

    public Data getData() {
        return data;
    }

    public void setData(Data vehicles) {
        this.data = vehicles;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
