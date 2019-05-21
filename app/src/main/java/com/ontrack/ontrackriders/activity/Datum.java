package com.ontrack.ontrackriders.activity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("registration_no")
    @Expose
    private String registrationNo;
    @SerializedName("make")
    @Expose
    private String make;
    @SerializedName("model")
    @Expose
    private String model;
    @SerializedName("model_year")
    @Expose
    private String modelYear;

    public Integer getId() {
        return id;
    }
    
    public String getRegistrationNo() {
        return registrationNo;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public String getModelYear() {
        return modelYear;
    }
}