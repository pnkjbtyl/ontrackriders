package com.ontrack.ontrackriders.activity.fragment_profile;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import com.ontrack.ontrackriders.activity.signup.SignupResponse;
import com.ontrack.ontrackriders.utils.Pref;
import com.ontrack.ontrackriders.webservice.Ret;
import com.ontrack.ontrackriders.webservice.Retro;
import com.ontrack.ontrackriders.webservice.WebInterface;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfilePresenter implements IEditProfilePresenter, Callback<EditProfileResponse> {
    private Activity activity;
    private IEditProfileView editProfileView;
    private String message;

    public EditProfilePresenter(Activity activity, IEditProfileView editProfileView) {
        this.activity = activity;
        this.editProfileView = editProfileView;
    }

    @Override
    public void requestEditProfile(String name, String dob, String age, String location, String dlNo, String IdNo, String gender, String bloodGroup, String marital, String smoke, String drink, String specs) {
        Log.d("EditProfileActivity","Data received");
        //starting progress
        //now we will initialise Retrofit
        Log.d("EditProfileActivity","attemptimg to initialise retrofit");
        WebInterface webInterface= Ret.getClient().create(WebInterface.class);
        Log.d("EditProfileActivity","retrofit initialised");

        //creating request body to parse form data

        try {
            JSONObject paramObject = new JSONObject();
            paramObject.put("name", name);
            paramObject.put("age", age);
            paramObject.put("dob", dob);
            paramObject.put("location", location);
            paramObject.put("driving_licence", dlNo);
            paramObject.put("identification_no", IdNo);
            paramObject.put("gender", gender);
            paramObject.put("blood_group", bloodGroup);
            paramObject.put("marital_status", marital);
            paramObject.put("smoke", smoke);
            paramObject.put("drink", drink);
            paramObject.put("spectacles", specs);
            RequestBody body = RequestBody.create(MediaType.parse("text/plain"),(paramObject).toString());
            String token=Pref.getUserToken(activity);
            Log.d("TOEK",token);

            Call<EditProfileResponse> call= webInterface.requestUpdateProfile(body);
            //exeuting the service
            call.enqueue(this);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onResponse(Call<EditProfileResponse> call, Response<EditProfileResponse> response) {
        if(response.isSuccessful() && response.code()==200) {
            //if code is 200 and response is successfull means the user is registered successfully
            Log.d("EditProfileActivity","Updated");
        }
        else if(response.code()==401)
        {
            try {
                JSONObject jObjError = new JSONObject(response.errorBody().string());
                Toast.makeText(activity, jObjError.getString("message"), Toast.LENGTH_LONG).show();
                Log.d("EditProfileActivity",jObjError.getString("message"));
            } catch (Exception e) {
                Toast.makeText(activity, e.getMessage(), Toast.LENGTH_LONG).show();
            }
        }
        else {
            try {
                JSONObject jObjError = new JSONObject(response.errorBody().string());
                Toast.makeText(activity, jObjError.getString("message"), Toast.LENGTH_LONG).show();
                Log.d("EditProfileActivity",jObjError.getString("message"));
            } catch (Exception e) {
                Toast.makeText(activity, e.getMessage(), Toast.LENGTH_LONG).show();
            }

        }

    }

    @Override
    public void onFailure(Call<EditProfileResponse> call, Throwable t) {
        Log.d("EditProfileActivity",t.getLocalizedMessage());
        Toast.makeText(activity, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

    }
}

