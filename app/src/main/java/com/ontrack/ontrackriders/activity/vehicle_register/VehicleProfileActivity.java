package com.ontrack.ontrackriders.activity.vehicle_register;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.ontrack.ontrackriders.R;
import com.ontrack.ontrackriders.activity.VehicleResponse;
import com.ontrack.ontrackriders.activity.VehiclesList;
import com.ontrack.ontrackriders.webservice.Ret;
import com.ontrack.ontrackriders.webservice.WebInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VehicleProfileActivity extends AppCompatActivity {

    TextView reg_no,reg_name,make,type_veh,type_body,model,modelyear,color,seat_cap,engine_cc,fuel_type,interior,pets,music,smoke;
    private VehicleDetailResponse vehicleDetailResponse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_profile);

        String id = getIntent().getStringExtra("ID");

        reg_no = (TextView)findViewById(R.id.reg_no_data);
        reg_name = (TextView)findViewById(R.id.reg_name_data);
        make = (TextView)findViewById(R.id.make_data);
        type_veh = (TextView)findViewById(R.id.type_veh_data);
        type_body = (TextView)findViewById(R.id.type_body_data);
        model = (TextView)findViewById(R.id.model_data);
        modelyear = (TextView)findViewById(R.id.modelyear_data);
        color = (TextView)findViewById(R.id.color_data);
        seat_cap = (TextView)findViewById(R.id.seat_cap_data);
        engine_cc = (TextView)findViewById(R.id.engine_cc_data);
        fuel_type = (TextView)findViewById(R.id.fuel_type_data);
        interior = (TextView)findViewById(R.id.interior_data);
        pets = (TextView)findViewById(R.id.pets_data);
        music = (TextView)findViewById(R.id.music_data);
        smoke = (TextView)findViewById(R.id.smoke_data);

        WebInterface webInterface = Ret.getClient().create(WebInterface.class);

        Call<VehicleDetailResponse> response = webInterface.getVehicleDetails(id);

        response.enqueue(new Callback<VehicleDetailResponse>() {
            @Override
            public void onResponse(Call<VehicleDetailResponse> call, Response<VehicleDetailResponse> response) {

                String reg_no_string = response.body().getVehicleDetailData().getRegistrationNo();
                String reg_name_string = response.body().getVehicleDetailData().getRegistrantName();
                String make_string = response.body().getVehicleDetailData().getMake();
                String type_veh_string = response.body().getVehicleDetailData().getVehicleType();
                String type_body_string = response.body().getVehicleDetailData().getBodyType();
                String model_string = response.body().getVehicleDetailData().getModel();
                String modelyear_string = response.body().getVehicleDetailData().getModelYear();
                String color_string = response.body().getVehicleDetailData().getColor();
                String seat_cap_string = response.body().getVehicleDetailData().getSeatCapacity();
                String engine_cc_string = response.body().getVehicleDetailData().getEngineCc();
                String fuel_type_string = response.body().getVehicleDetailData().getFuelType();
                String interior_string = response.body().getVehicleDetailData().getInterior();
                String pets_string = response.body().getVehicleDetailData().getPetsAllowed();
                String music_string = response.body().getVehicleDetailData().getMusicAllowed();
                String smoke_string = response.body().getVehicleDetailData().getMusicAllowed();



                reg_no.setText(reg_no_string);
                reg_name.setText(reg_name_string);
                make.setText(make_string);
                type_veh.setText(type_veh_string);
                type_body.setText(type_body_string);
                model.setText(model_string);
                modelyear.setText(modelyear_string);
                color.setText(color_string);
                seat_cap.setText(seat_cap_string);
                engine_cc.setText(engine_cc_string);
                fuel_type.setText(fuel_type_string);
                interior.setText(interior_string);
                pets.setText(pets_string);
                music.setText(music_string);
                smoke.setText(smoke_string);

            }

            @Override
            public void onFailure(Call<VehicleDetailResponse> call, Throwable t) {
                Toast.makeText(VehicleProfileActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
