package com.ontrack.ontrackriders.webservice;

import com.ontrack.ontrackriders.activity.login.LoginResponse;
import com.ontrack.ontrackriders.activity.signup.SignupResponse;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
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
    @Multipart
    @POST("/vehicle/registration")
    Call<ResponseBody> requestVehicleReg(
            @Part("registration_no") RequestBody reg_no_string, @Part("registrant_name") RequestBody reg_name_string,
            @Part("make") RequestBody make_string,@Part("vehicle_type") RequestBody type_veh_string,
            @Part("body_type")RequestBody type_body_string,@Part("model") RequestBody model_string,
            @Part("model_year") RequestBody modelyear_string, @Part("color") RequestBody color_string,
            @Part("seat_capacity") RequestBody seat_cap_string,@Part("engine_cc") RequestBody engine_cc_string,
            @Part("fuel_type") RequestBody fuel_type_string,@Part("interior") RequestBody interior_string,
            @Part("pets_allowed") RequestBody pets_string,@Part("music_allowed") RequestBody music_string,
            @Part("smoking_allowed") RequestBody smoke_string);
}
