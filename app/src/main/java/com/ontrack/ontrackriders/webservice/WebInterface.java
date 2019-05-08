package com.ontrack.ontrackriders.webservice;

import com.ontrack.ontrackriders.activity.signup.SignupResponse;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface WebInterface {

    //for signup
    @Multipart
    @POST("signup")
    Call<SignupResponse> requestSignup(
            @Part("email")RequestBody email, @Part("password") RequestBody password,@Part("name") RequestBody name);


}
