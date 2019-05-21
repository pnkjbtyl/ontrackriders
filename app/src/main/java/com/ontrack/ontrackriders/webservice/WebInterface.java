package com.ontrack.ontrackriders.webservice;
import com.ontrack.ontrackriders.activity.VehicleResponse;
import com.ontrack.ontrackriders.activity.fragment_profile.EditProfileResponse;
import com.ontrack.ontrackriders.activity.login.LoginResponse;
import com.ontrack.ontrackriders.activity.signup.SignupResponse;
import com.ontrack.ontrackriders.activity.vehicle_register.VehicleRegisterResponse;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
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
    @Headers({"Content-Type: application/json"})
    @POST("/vehicle/registration")
    Call<VehicleRegisterResponse> requestVehicleReg(@Body RequestBody vehiclereg_body);

    @Headers({"Content-Type: application/json"})
    @GET("vehicles")
    Call<VehicleResponse> getVehicleData();

    //for profile update
    @Headers("Content-Type: application/json")
    @PUT("users/update")
    Call<EditProfileResponse> requestUpdateProfile(@Body RequestBody editProfileBody);


}
