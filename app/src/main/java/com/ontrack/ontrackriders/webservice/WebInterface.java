package com.ontrack.ontrackriders.webservice;

import com.ontrack.ontrackriders.activity.fragment_profile.EditProfileResponse;
import com.ontrack.ontrackriders.activity.login.LoginResponse;
import com.ontrack.ontrackriders.activity.signup.SignupResponse;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;

public interface WebInterface {

    //for signup
    @Headers("Content-Type: application/json")
    @POST("signup")
    Call<SignupResponse> requestSignup(@Body RequestBody signupBody);
    @Headers("Content-Type: application/json")
    @GET("refreshtoken")
    Call<RefreshToken> requestRefreshToken(@Header("refreshtoken")String refreshToken);
    //for login
    @Headers("Content-Type: application/json")
    @POST("login")
    Call<LoginResponse> requestLogin(@Body RequestBody loginBody);
    //for profile update
    @Headers({"Content-Type: application/json","x-access-code:eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJlbWFpbCI6ImFiY0AxMjMuY29tIiwiaWQiOjcwLCJuYW1lIjoia2F1IiwiaWF0IjoxNTU4MDA1MjAyLCJleHAiOjE1NTgwOTE2MDJ9.F31tgeq6M4YPDe63nwiFt31KOCKNQjwrFvDcH2kXgqg"})
    @POST("users/update")
    Call<EditProfileResponse> requestUpdateProfile(@Body RequestBody editProfileBody);

    //for Vehicle Registration
    @Multipart
    @PUT("/vehicle/registration")
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
