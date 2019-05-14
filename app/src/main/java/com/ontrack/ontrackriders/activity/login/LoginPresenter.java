package com.ontrack.ontrackriders.activity.login;
import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import com.ontrack.ontrackriders.webservice.IBaseUrl;
import com.ontrack.ontrackriders.webservice.Retro;
import com.ontrack.ontrackriders.webservice.WebInterface;

import org.json.JSONException;
import org.json.JSONObject;

import es.dmoral.toasty.Toasty;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter implements ILoginPresenter, IBaseUrl, Callback<LoginResponse> {
    private ILoginView iLoginView;
    private Activity activity;
    private String message;

    public LoginPresenter(ILoginView iLoginView, Activity activity) {
        this.iLoginView = iLoginView;
        this.activity = activity;
    }

    @Override
    public void requestLogin(String email, String password) {
        Log.d("LoginActivity","Data received");
        //starting progress
        //now we will initialise Retrofit
        Log.d("LoginActivity","attemptimg to initialise retrofit");
        iLoginView.startProgress();
        WebInterface webInterface= Retro.getClient().create(WebInterface.class);
        Log.d("LoginActivity","retrofit initialised");

        //creating request body to parse form data

        try {
            JSONObject paramObject = new JSONObject();
            paramObject.put("email", email);
            paramObject.put("password", password);
            RequestBody body = RequestBody.create(MediaType.parse("text/plain"),(paramObject).toString());

            Call<LoginResponse> call= webInterface.requestLogin(body);
            //exeuting the service
            call.enqueue(this);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
        if (response.isSuccessful() && response.code() == 200) {
            //if code is 200 and response is successfull means the user is logged in successfully
            String resp = response.body().getData().toString();
            Log.d("LoginActivity", "RESPONSE FETCHED=> " + resp);
            Log.d("LoginActivity", "FETCHINg USER DETAILS");
            String name = response.body().getData().getName();
            String email = response.body().getData().getEmail();
            String token = response.body().getData().getToken();
            String refreshToken = response.body().getData().getRefreshtoken();
            message = response.body().getMessage();
            Log.d("LoginActivity", "Message: " + message);
            Log.d("LoginActivity", "DETAILS FETCHED => " + "Name: " + name + "\n" + "Email:" + email + "\n" +
                    "Token: " + token + "\n" + "RefreshToken: " + refreshToken);
            Log.d("LoginActivity", "User Login successful");
            iLoginView.stopProgress();
            iLoginView.onComplete(message);
        } else if (response.code() == 401) {
            iLoginView.stopProgress();
            try {
                JSONObject jObjError = new JSONObject(response.errorBody().string());
                Toasty.info(activity, jObjError.getString("message")).show();
                Log.d("LoginActivity", jObjError.getString("message"));
            } catch (Exception e) {
                Toast.makeText(activity, e.getMessage(), Toast.LENGTH_LONG).show();
            }


        }
        else {
            try {
            JSONObject jObjError = new JSONObject(response.errorBody().string());
            Toasty.info(activity, jObjError.getString("message")).show();
            Log.d("LoginActivity", jObjError.getString("message"));
        } catch (Exception e) {
            Toast.makeText(activity, e.getMessage(), Toast.LENGTH_LONG).show();
        }
        }



    }

    @Override
    public void onFailure(Call<LoginResponse> call, Throwable t) {
        Log.d("LoginActivity",t.getLocalizedMessage());
        Toast.makeText(activity, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
        iLoginView.stopProgress();

    }
}
