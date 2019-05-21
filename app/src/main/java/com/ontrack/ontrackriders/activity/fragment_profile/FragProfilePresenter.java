package com.ontrack.ontrackriders.activity.fragment_profile;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import com.ontrack.ontrackriders.MyApp;
import com.ontrack.ontrackriders.utils.Pref;
import com.ontrack.ontrackriders.webservice.Ret;
import com.ontrack.ontrackriders.webservice.Retro;
import com.ontrack.ontrackriders.webservice.WebInterface;

import org.json.JSONObject;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragProfilePresenter implements IFragProfilePresenter, Callback<ProfilePicResponse> {
    private Context context;
    private IFragProfileView iFragProfileView;
    private static final String TAG="ProfileFragment";
    private String message;

    public FragProfilePresenter(Context context, IFragProfileView iFragProfileView) {
        this.context = context;
        this.iFragProfileView = iFragProfileView;
    }


    @Override
    public void requestSetProfilePic(String uri) {
        Log.d("Profile Uri Received=> ",uri);
        Log.d(TAG,"Data received");
        //starting progress
        //now we will initialise Retrofit
        Log.d(TAG,"attemptimg to initialise retrofit");
        WebInterface webInterface= Ret.getClient().create(WebInterface.class);
        Log.d(TAG,"retrofit initialised");

        File profilePicFile = new File(uri);
        RequestBody requestFileProfilePic =
                RequestBody.create(MediaType.parse("image/*"), profilePicFile);

        // MultipartBody.Part is used to send also the actual file name
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("files", profilePicFile.getName(), requestFileProfilePic);

        Call<ProfilePicResponse> call=webInterface.requestUpdateProfilePic(body);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<ProfilePicResponse> call, Response<ProfilePicResponse> response) {
        if (response.isSuccessful() && response.code() == 200) {

            //if code is 200 and response is successfull means the user is updated successfully successfully
            Log.d("ProfileFragment", "Updated profile image completed");
            message = response.body().getMessage();
            Log.d("ProfileFragment", "Message: " + message);
            //String profile_id= String.valueOf(response.body().getData().getId());
            //Log.d(TAG,"Profile Pic Id=> "+profile_id);
            //Pref.putUserProfilePicId(MyApp.getContext(),profile_id);
            iFragProfileView.onComplete("Profile Pic Changed");
        } else if (response.code() == 401) {
            try {
                JSONObject jObjError = new JSONObject(response.errorBody().string());
                Toast.makeText(context, jObjError.getString("message"), Toast.LENGTH_LONG).show();
                Log.d("ProfileFragment", jObjError.getString("message"));
            } catch (Exception e) {
                Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
            }
        } else {
            try {
                JSONObject jObjError = new JSONObject(response.errorBody().string());
                Toast.makeText(context, jObjError.getString("message"), Toast.LENGTH_LONG).show();
                Log.d("ProfileFragment", jObjError.getString("message"));
            } catch (Exception e) {
                Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
            }

        }
    }

    @Override
    public void onFailure(Call<ProfilePicResponse> call, Throwable t) {
        Log.d("ProfileFragment",t.getLocalizedMessage());
        Toast.makeText(context, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

    }
}
