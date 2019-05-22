package com.ontrack.ontrackriders.activity.vehicle_register;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VehicleDetailResponse {

@SerializedName("data")
@Expose
private VehicleDetailData vehicleDetailData;
@SerializedName("message")
@Expose
private String message;

public VehicleDetailData getVehicleDetailData() {
return vehicleDetailData;
}

public void setVehicleDetailData(VehicleDetailData data) {
this.vehicleDetailData = data;
}

public String getMessage() {
return message;
}

public void setMessage(String message) {
this.message = message;
}

}