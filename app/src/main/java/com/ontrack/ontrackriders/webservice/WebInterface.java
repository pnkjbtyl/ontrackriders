package com.ontrack.ontrackriders.webservice;

import com.ontrack.ontrackriders.activity.edit_profile.EditProfileResponse;
import com.ontrack.ontrackriders.activity.fragment_profile.FetchProfileResponse;
import com.ontrack.ontrackriders.activity.fragment_profile.ProfilePicResponse;
import com.ontrack.ontrackriders.activity.login.LoginResponse;
import com.ontrack.ontrackriders.activity.signup.SignupResponse;

import java.util.List;

import okhttp3.MultipartBody;
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
    //for profile details update
    @Headers("Content-Type: application/json")
    @POST("users/update")
    Call<EditProfileResponse> requestUpdateProfile(@Body RequestBody editProfileBody);
    //for profile pic update
    @Multipart
    @POST("users/uploads")
    Call<ProfilePicResponse> requestUpdateProfilePic(@Part MultipartBody.Part files);
    //for fetching user profile dtails
    @Headers("Content-Type: application/json")
    @GET("users")
    Call<FetchProfileResponse> requestFetchProfile();
    //for Vehicle Registration
    @Multipart
    @Headers({"Content-Type: application/json"})
    @POST("/vehicle/registration")
    Call<ResponseBody> requestVehicleReg(@Body RequestBody vehiclereg_body);
}
