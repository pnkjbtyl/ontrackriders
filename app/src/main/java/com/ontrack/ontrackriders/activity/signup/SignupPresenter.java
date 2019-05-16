package com.ontrack.ontrackriders.activity.signup;
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

public class SignupPresenter implements ISignupPresenter,IBaseUrl, Callback<SignupResponse> {
    private ISignupView iSignupView;
    private Activity activity;
    private String message;

    public SignupPresenter(ISignupView iSignupView, Activity activity) {
        this.iSignupView = iSignupView;
        this.activity = activity;
    }

    @Override
    public void requestRegister(String name, String email, String password) {
        Log.d("SignUpActivity","Data received");
            //starting progress
        //now we will initialise Retrofit
        Log.d("SignupActivity","attemptimg to initialise retrofit");
        iSignupView.startProgress();
        WebInterface webInterface=Retro.getClient().create(WebInterface.class);
        Log.d("SignupActivity","retrofit initialised");

        //creating request body to parse form data

        try {
            JSONObject paramObject = new JSONObject();
            paramObject.put("email", email);
            paramObject.put("password", password);
            paramObject.put("name", name);
            RequestBody body = RequestBody.create(MediaType.parse("text/plain"),(paramObject).toString());

            Call<SignupResponse> call= webInterface.requestSignup(body);
            //exeuting the service
            call.enqueue(this);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onResponse(Call<SignupResponse> call, Response<SignupResponse> response) {
        if(response.isSuccessful() && response.code()==200)
        {
            //if code is 200 and response is successfull means the user is registered successfully
            String resp = response.body().getData().toString();
            Log.d("SignupActivity","RESPONSE FETCHED=> "+resp);
            Log.d("SignupActivity","FETCHINg USER DETAILS");
            String name=response.body().getData().getName();
            String email=response.body().getData().getEmail();
            String token=response.body().getData().getToken();
            String refreshToken=response.body().getData().getRefreshtoken();
            message=response.body().getMessage();
            Log.d("SignupActivity","Message: "+message);
            Log.d("SignupActivity","DETAILS FETCHED => "+"Name: "+name+ "\n" +"Email:" + email+ "\n"+
                    "Token: "+token+ "\n"+"RefreshToken: "+ refreshToken);
            Log.d("SignupActivity","User Registration successful");
            iSignupView.stopProgress();
            iSignupView.onComplete(message);
        }
        else if(response.code()==409)
        {

            iSignupView.stopProgress();
            try {
                JSONObject jObjError = new JSONObject(response.errorBody().string());
                Toasty.info(activity, jObjError.getString("message")).show();
                Log.d("SignupActivity",jObjError.getString("message"));
            } catch (Exception e) {
                Toast.makeText(activity, e.getMessage(), Toast.LENGTH_LONG).show();
            }

        }
        else
        {
            iSignupView.stopProgress();
            try {
                JSONObject jObjError = new JSONObject(response.errorBody().string());
                Toast.makeText(activity, jObjError.getString("message"), Toast.LENGTH_LONG).show();
                Log.d("SignupActivity",jObjError.getString("message"));
            } catch (Exception e) {
                Toast.makeText(activity, e.getMessage(), Toast.LENGTH_LONG).show();
            }
        }

    }

    @Override
    public void onFailure(Call<SignupResponse> call, Throwable t) {
        Log.d("SignupActivity",t.getLocalizedMessage());
        Toast.makeText(activity, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
        iSignupView.stopProgress();

    }
}
