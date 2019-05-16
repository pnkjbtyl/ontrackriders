package com.ontrack.ontrackriders.webservice;

import com.android.volley.RequestQueue;
import com.ontrack.ontrackriders.activity.login.LoginResponse;
import com.ontrack.ontrackriders.activity.signup.SignupResponse;
import com.ontrack.ontrackriders.activity.vehicle_register.VehicleRegisterResponse;

import java.util.List;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface WebInterface {

    //for signup
    @Headers("Content-Type: application/json")
    @Multipart
    @POST("signup")
    Call<SignupResponse> requestSignup(
            @Part("email")RequestBody email, @Part("password") RequestBody password,@Part("name") RequestBody name);


    //for login
    @Headers("Content-Type: application/json")
    @POST("login")
    Call<LoginResponse> requestLogin(@Body RequestBody loginBody);

    //for Vehicle Registration
    @Headers({"Content-Type: application/json"})
    @POST("/vehicle/registration")
    Call<VehicleRegisterResponse> requestVehicleReg(@Body RequestBody vehiclereg_body);

    @Headers({"\"Content-Type: application/json"})
    @GET("users")
    Call<List<ResponseBody>> response(@Body RequestQueue requestQueue);
}
