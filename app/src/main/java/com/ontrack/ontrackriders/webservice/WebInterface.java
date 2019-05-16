package com.ontrack.ontrackriders.webservice;

import com.ontrack.ontrackriders.activity.login.LoginResponse;
import com.ontrack.ontrackriders.activity.signup.SignupResponse;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface WebInterface {

    //for signup
    @Headers("Content-Type: application/json")
    @POST("signup")
    Call<SignupResponse> requestSignup(@Body RequestBody signupBody);

    //for login
    @Headers("Content-Type: application/json")
    @POST("login")
    Call<LoginResponse> requestLogin(@Body RequestBody loginBody);

    //for Vehicle Registration

}
