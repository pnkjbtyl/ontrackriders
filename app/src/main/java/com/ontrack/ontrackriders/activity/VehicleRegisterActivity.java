package com.ontrack.ontrackriders.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.ontrack.ontrackriders.R;
import com.ontrack.ontrackriders.webservice.Retro;
import com.ontrack.ontrackriders.webservice.WebInterface;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VehicleRegisterActivity extends AppCompatActivity {

    EditText reg_no,reg_name,make,model,modelyear,color;
    Spinner type_vehicle,type_body,seat_cap,engine_cc,fuel_type,interior;
    RadioGroup pets,music,smoke;
    RadioButton pets_btn,music_btn,smoke_btn;

    String reg_no_string,reg_name_string,make_string,model_string,modelyear_string,color_string;
    String type_veh_string,type_body_string,seat_cap_string,engine_cc_string,fuel_type_string,interior_string;
    String pets_string,music_string,smoke_string;

    int selectedPet,selectedMusic,selectedSmoke;

    Button veh_reg_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_register);

        reg_no = (EditText)findViewById(R.id.editTextRno);
        reg_name = (EditText)findViewById(R.id.editTextRn);
        make = (EditText)findViewById(R.id.editTextmake);
        model = (EditText)findViewById(R.id.etmodel);
        modelyear = (EditText)findViewById(R.id.etmodelyear);
        color = (EditText)findViewById(R.id.etcolor);

        type_vehicle = (Spinner)findViewById(R.id.typeveh_spinner);
        type_body = (Spinner)findViewById(R.id.typebody_spinner);
        seat_cap = (Spinner)findViewById(R.id.seatcap_spinner);
        engine_cc = (Spinner)findViewById(R.id.cc_spinner);
        fuel_type = (Spinner)findViewById(R.id.fueltype_spinner);
        interior = (Spinner)findViewById(R.id.interior_spinner);

        pets = (RadioGroup)findViewById(R.id.petsgroup);
        music = (RadioGroup)findViewById(R.id.musicgroup);
        smoke = (RadioGroup)findViewById(R.id.smokegroup);

        veh_reg_btn = (Button)findViewById(R.id.veh_reg_btn);

        // Type of Vehicle Spinner Value
        type_vehicle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                type_veh_string = type_vehicle.getItemAtPosition(position).toString();
                //Toast.makeText(VehicleRegisterActivity.this, type_veh_string, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //Type of Body Spinner Value
        type_body.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                type_body_string = type_body.getItemAtPosition(position).toString();
                //Toast.makeText(VehicleRegisterActivity.this, type_body_string, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //Seat Capacity Spinner Value
        seat_cap.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                seat_cap_string = seat_cap.getItemAtPosition(position).toString();
                //Toast.makeText(VehicleRegisterActivity.this, seat_cap_string, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //Engine CC Spinner Value
        engine_cc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                engine_cc_string = engine_cc.getItemAtPosition(position).toString();
                //Toast.makeText(VehicleRegisterActivity.this, engine_cc_string, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //Fuel Type Spinner Value
        fuel_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                fuel_type_string = fuel_type.getItemAtPosition(position).toString();
                //Toast.makeText(VehicleRegisterActivity.this, fuel_type_string, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //Interior Spinner Value
        interior.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                interior_string = interior.getItemAtPosition(position).toString();
                //Toast.makeText(VehicleRegisterActivity.this, interior_string, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        veh_reg_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                WebInterface webInterface = Retro.getClient().create(WebInterface.class);

                reg_no_string = reg_no.getText().toString();
                reg_name_string = reg_name.getText().toString();
                make_string = make.getText().toString();
                model_string = model.getText().toString();
                modelyear_string = modelyear.getText().toString();
                color_string = color.getText().toString();


                selectedPet = pets.getCheckedRadioButtonId();
                pets_btn = (RadioButton)findViewById(selectedPet);
                pets_string = pets_btn.getText().toString();


                selectedMusic = music.getCheckedRadioButtonId();
                music_btn = (RadioButton)findViewById(selectedMusic);
                music_string = music_btn.getText().toString();


                selectedSmoke = smoke.getCheckedRadioButtonId();
                smoke_btn = (RadioButton)findViewById(selectedSmoke);
                smoke_string = smoke_btn.getText().toString();

                RequestBody reg_no = RequestBody.create(MediaType.parse("text/plain"),reg_no_string);
                RequestBody reg_name = RequestBody.create(MediaType.parse("text/plain"),reg_name_string);
                RequestBody make = RequestBody.create(MediaType.parse("text/plain"),make_string);
                RequestBody type_veh = RequestBody.create(MediaType.parse("text/plain"),type_veh_string);
                RequestBody type_body = RequestBody.create(MediaType.parse("text/plain"),type_body_string);
                RequestBody model = RequestBody.create(MediaType.parse("text/plain"),model_string);
                RequestBody model_year = RequestBody.create(MediaType.parse("text/plain"),modelyear_string);
                RequestBody color = RequestBody.create(MediaType.parse("text/plain"),color_string);
                RequestBody seat_cap = RequestBody.create(MediaType.parse("text/plain"),seat_cap_string);
                RequestBody engine_cc = RequestBody.create(MediaType.parse("text/plain"),engine_cc_string);
                RequestBody fuel_type = RequestBody.create(MediaType.parse("text/plain"),fuel_type_string);
                RequestBody interior = RequestBody.create(MediaType.parse("text/plain"),interior_string);
                RequestBody pets = RequestBody.create(MediaType.parse("text/plain"),pets_string);
                RequestBody music = RequestBody.create(MediaType.parse("text/plain"),music_string);
                RequestBody smoke = RequestBody.create(MediaType.parse("text/plain"),smoke_string);

                Call<ResponseBody> call = webInterface.requestVehicleReg(reg_no,reg_name,make,type_veh,
                        type_body,model,model_year,color,seat_cap,engine_cc,fuel_type,
                        interior,pets,music,smoke);
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        try {
                            Log.d("RESP",response.body().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(VehicleRegisterActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });


            }
        });


    }
}
