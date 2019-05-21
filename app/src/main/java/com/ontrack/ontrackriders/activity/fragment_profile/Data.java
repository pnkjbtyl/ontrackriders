package com.ontrack.ontrackriders.activity.fragment_profile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

@SerializedName("id")
@Expose
private Integer id;
@SerializedName("name")
@Expose
private String name;
@SerializedName("email")
@Expose
private String email;
@SerializedName("age")
@Expose
private String age;
@SerializedName("dob")
@Expose
private String dob;
@SerializedName("location")
@Expose
private String location;
@SerializedName("driving_licence")
@Expose
private String drivingLicence;
@SerializedName("identification_no")
@Expose
private String identificationNo;
@SerializedName("gender")
@Expose
private String gender;
@SerializedName("blood_group")
@Expose
private String bloodGroup;
@SerializedName("marital_status")
@Expose
private String maritalStatus;
@SerializedName("smoke")
@Expose
private String smoke;
@SerializedName("drink")
@Expose
private String drink;
@SerializedName("spectacles")
@Expose
private String spectacles;
@SerializedName("profile_pic")
@Expose
private Object profilePic;
@SerializedName("created_at")
@Expose
private String createdAt;
@SerializedName("updated_at")
@Expose
private String updatedAt;

public Integer getId() {
return id;
}

public void setId(Integer id) {
this.id = id;
}

public String getName() {
return name;
}

public void setName(String name) {
this.name = name;
}

public String getEmail() {
return email;
}

public void setEmail(String email) {
this.email = email;
}

public String getAge() {
return age;
}

public void setAge(String age) {
this.age = age;
}

public String getDob() {
return dob;
}

public void setDob(String dob) {
this.dob = dob;
}

public String getLocation() {
return location;
}

public void setLocation(String location) {
this.location = location;
}

public String getDrivingLicence() {
return drivingLicence;
}

public void setDrivingLicence(String drivingLicence) {
this.drivingLicence = drivingLicence;
}

public String getIdentificationNo() {
return identificationNo;
}

public void setIdentificationNo(String identificationNo) {
this.identificationNo = identificationNo;
}

public String getGender() {
return gender;
}

public void setGender(String gender) {
this.gender = gender;
}

public String getBloodGroup() {
return bloodGroup;
}

public void setBloodGroup(String bloodGroup) {
this.bloodGroup = bloodGroup;
}

public String getMaritalStatus() {
return maritalStatus;
}

public void setMaritalStatus(String maritalStatus) {
this.maritalStatus = maritalStatus;
}

public String getSmoke() {
return smoke;
}

public void setSmoke(String smoke) {
this.smoke = smoke;
}

public String getDrink() {
return drink;
}

public void setDrink(String drink) {
this.drink = drink;
}

public String getSpectacles() {
return spectacles;
}

public void setSpectacles(String spectacles) {
this.spectacles = spectacles;
}

public Object getProfilePic() {
return profilePic;
}

public void setProfilePic(Object profilePic) {
this.profilePic = profilePic;
}

public String getCreatedAt() {
return createdAt;
}

public void setCreatedAt(String createdAt) {
this.createdAt = createdAt;
}

public String getUpdatedAt() {
return updatedAt;
}

public void setUpdatedAt(String updatedAt) {
this.updatedAt = updatedAt;
}

}