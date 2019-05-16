package com.ontrack.ontrackriders.webservice;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

@SerializedName("email")
@Expose
private String email;
@SerializedName("token")
@Expose
private String token;
@SerializedName("refreshSecretKey")
@Expose
private String refreshSecretKey;

public String getEmail() {
return email;
}

public void setEmail(String email) {
this.email = email;
}

public String getToken() {
return token;
}

public void setToken(String token) {
this.token = token;
}

public String getRefreshSecretKey() {
return refreshSecretKey;
}

public void setRefreshSecretKey(String refreshSecretKey) {
this.refreshSecretKey = refreshSecretKey;
}

}