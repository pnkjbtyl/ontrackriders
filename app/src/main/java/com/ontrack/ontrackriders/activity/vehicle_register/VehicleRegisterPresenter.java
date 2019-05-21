package com.ontrack.ontrackriders.activity.vehicle_register;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import com.ontrack.ontrackriders.webservice.IBaseUrl;
import com.ontrack.ontrackriders.webservice.Ret;
import com.ontrack.ontrackriders.webservice.Retro;
import com.ontrack.ontrackriders.webservice.WebInterface;

import org.json.JSONException;
import org.json.JSONObject;

import es.dmoral.toasty.Toasty;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VehicleRegisterPresenter implements IVehicleRegisterPresenter, IBaseUrl,Callback<VehicleRegisterResponse> {
    Activity activity;
    IVehicleRegisterView iVehicleRegisterView;
    private String message;

    public VehicleRegisterPresenter(Activity activity, IVehicleRegisterView iVehicleRegisterView) {
        this.activity = activity;
        this.iVehicleRegisterView = iVehicleRegisterView;
    }

    @Override
    public void requestVehicleRegister(String reg_no, String reg_name, String make, String type_veh, String type_body,
                                       String model, String model_year, String color, String seat_cap, String engine_cc,
                                       String fuel_type, String interior, String pets, String music, String smoke) {
        WebInterface webInterface= Ret.getClient().create(WebInterface.class);
        try {
            JSONObject paramObject = new JSONObject();
            paramObject.put("registration_no", reg_no);
            paramObject.put("registrant_name", reg_name);
            paramObject.put("make", make);
            paramObject.put("vehicle_type", type_veh);
            paramObject.put("body_type", type_body);
            paramObject.put("model", model);
            paramObject.put("model_year", model_year);
            paramObject.put("color", color);
            paramObject.put("seat_capacity", seat_cap);
            paramObject.put("engine_cc", engine_cc);
            paramObject.put("fuel_type", fuel_type);
            paramObject.put("interior", interior);
            paramObject.put("pets_allowed", pets);
            paramObject.put("music_allowed", music);
            paramObject.put("smoking_allowed", smoke);


            RequestBody body = RequestBody.create(MediaType.parse("text/plain"), (paramObject).toString());

            Call<VehicleRegisterResponse> call = webInterface.requestVehicleReg(body);
            //exeuting the service
            call.enqueue(this);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onResponse(Call<VehicleRegisterResponse> call, Response<VehicleRegisterResponse> response) {

        if (response.isSuccessful() && response.code() == 200) {
            //if code is 200 and response is successfull means the user is logged in successfully
            //Log.d("VehicleRegisterActivity", "RESPONSE FETCHED=> " + resp);
            //Log.d("LoginActivity", "FETCHINg USER DETAILS");
            message = response.body().getMessage();
            iVehicleRegisterView.stopProgress();
            iVehicleRegisterView.onComplete(message);
        } else if (response.code() == 401) {
            iVehicleRegisterView.stopProgress();
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
    public void onFailure(Call<VehicleRegisterResponse> call, Throwable t) {
        Log.d("LoginActivity",t.getLocalizedMessage());
        Toast.makeText(activity, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
        iVehicleRegisterView.stopProgress();

    }
}
