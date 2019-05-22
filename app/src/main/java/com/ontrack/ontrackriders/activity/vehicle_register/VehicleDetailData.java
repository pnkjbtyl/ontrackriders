package com.ontrack.ontrackriders.activity.vehicle_register;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VehicleDetailData {

@SerializedName("id")
@Expose
private Integer id;
@SerializedName("user_id")
@Expose
private Integer userId;
@SerializedName("registration_no")
@Expose
private String registrationNo;
@SerializedName("registrant_name")
@Expose
private String registrantName;
@SerializedName("make")
@Expose
private String make;
@SerializedName("vehicle_type")
@Expose
private String vehicleType;
@SerializedName("body_type")
@Expose
private String bodyType;
@SerializedName("model")
@Expose
private String model;
@SerializedName("model_year")
@Expose
private String modelYear;
@SerializedName("color")
@Expose
private String color;
@SerializedName("seat_capacity")
@Expose
private String seatCapacity;
@SerializedName("engine_cc")
@Expose
private String engineCc;
@SerializedName("fuel_type")
@Expose
private String fuelType;
@SerializedName("interior")
@Expose
private String interior;
@SerializedName("pets_allowed")
@Expose
private String petsAllowed;
@SerializedName("music_allowed")
@Expose
private String musicAllowed;
@SerializedName("smoking_allowed")
@Expose
private String smokingAllowed;

public Integer getId() {
return id;
}

public void setId(Integer id) {
this.id = id;
}

public Integer getUserId() {
return userId;
}

public void setUserId(Integer userId) {
this.userId = userId;
}

public String getRegistrationNo() {
return registrationNo;
}

public void setRegistrationNo(String registrationNo) {
this.registrationNo = registrationNo;
}

public String getRegistrantName() {
return registrantName;
}

public void setRegistrantName(String registrantName) {
this.registrantName = registrantName;
}

public String getMake() {
return make;
}

public void setMake(String make) {
this.make = make;
}

public String getVehicleType() {
return vehicleType;
}

public void setVehicleType(String vehicleType) {
this.vehicleType = vehicleType;
}

public String getBodyType() {
return bodyType;
}

public void setBodyType(String bodyType) {
this.bodyType = bodyType;
}

public String getModel() {
return model;
}

public void setModel(String model) {
this.model = model;
}

public String getModelYear() {
return modelYear;
}

public void setModelYear(String modelYear) {
this.modelYear = modelYear;
}

public String getColor() {
return color;
}

public void setColor(String color) {
this.color = color;
}

public String getSeatCapacity() {
return seatCapacity;
}

public void setSeatCapacity(String seatCapacity) {
this.seatCapacity = seatCapacity;
}

public String getEngineCc() {
return engineCc;
}

public void setEngineCc(String engineCc) {
this.engineCc = engineCc;
}

public String getFuelType() {
return fuelType;
}

public void setFuelType(String fuelType) {
this.fuelType = fuelType;
}

public String getInterior() {
return interior;
}

public void setInterior(String interior) {
this.interior = interior;
}

public String getPetsAllowed() {
return petsAllowed;
}

public void setPetsAllowed(String petsAllowed) {
this.petsAllowed = petsAllowed;
}

public String getMusicAllowed() {
return musicAllowed;
}

public void setMusicAllowed(String musicAllowed) {
this.musicAllowed = musicAllowed;
}

public String getSmokingAllowed() {
return smokingAllowed;
}

public void setSmokingAllowed(String smokingAllowed) {
this.smokingAllowed = smokingAllowed;
}

}