package com.ontrack.ontrackriders.activity.vehicle_register;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.ontrack.ontrackriders.MyApp;
import com.ontrack.ontrackriders.R;
import com.ontrack.ontrackriders.activity.login.LoginResponse;
import com.ontrack.ontrackriders.utils.PathUtil;
import com.ontrack.ontrackriders.webservice.Retro;
import com.ontrack.ontrackriders.webservice.WebInterface;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URISyntaxException;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VehicleRegisterActivity extends AppCompatActivity implements IVehicleRegisterView{

    EditText reg_no,reg_name,make,model,modelyear,color;
    Spinner type_vehicle,type_body,seat_cap,engine_cc,fuel_type,interior;
    RadioGroup pets,music,smoke;
    RadioButton pets_btn,music_btn,smoke_btn;
    @BindView(R.id.imageViewAddVehicle)
    ImageView imageViewAddVehicle;
    @BindView(R.id.imageViewCar)
    ImageView imageViewCar;
    private Uri destination=null;
    private String filePath;
    private static final String TAG="VehicleRegisterActivity";

    RadioButton petsyes,petsno,musicyes,musicno,smokeyes,smokeno;

    private String reg_no_string,reg_name_string,make_string,model_string,modelyear_string,color_string;
    private String type_veh_string,type_body_string,seat_cap_string,engine_cc_string,fuel_type_string,interior_string;
    private String pets_string,music_string,smoke_string;

    int selectedPet,selectedMusic,selectedSmoke;

    Button veh_reg_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_register);
        final VehicleRegisterPresenter vehicleRegisterPresenter=new
                VehicleRegisterPresenter(this,this);
        ButterKnife.bind(this);


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

        petsyes = (RadioButton)findViewById(R.id.petsyes);
        petsno = (RadioButton)findViewById(R.id.petsno);

        musicyes = (RadioButton)findViewById(R.id.musicyes);
        musicno = (RadioButton)findViewById(R.id.musicno);

        smokeyes = (RadioButton)findViewById(R.id.smokeyes);
        smokeno = (RadioButton)findViewById(R.id.smokeno);

        veh_reg_btn = (Button)findViewById(R.id.veh_reg_btn);

        //gettimg image from gallery

        imageViewAddVehicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getVehicleImage();
            }
        });

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

                validatedata();
            }

            private void validatedata(){

                WebInterface webInterface = Retro.getClient().create(WebInterface.class);

                reg_no_string = reg_no.getText().toString().trim();
                reg_name_string = reg_name.getText().toString().trim();
                make_string = make.getText().toString().trim();
                model_string = model.getText().toString().trim();
                modelyear_string = modelyear.getText().toString().trim();
                color_string = color.getText().toString().trim();

                if(TextUtils.isEmpty(reg_no_string))
                {
                    reg_no.setError("Registration No. Required");
                }
                else if(TextUtils.isEmpty(reg_name_string))
                {
                    reg_name.setError("Name is Required");
                }
                else if(TextUtils.isEmpty(make_string))
                {
                    make.setError("Maker is Required");
                }
                else if (type_veh_string.equals("Type of Vehicle"))
                {
                    Toasty.warning(VehicleRegisterActivity.this,"Select the Type of Vehicle").show();
                }
                else if (type_body_string.equals("Type of Body"))
                {
                    Toasty.warning(VehicleRegisterActivity.this,"Select the Type of Body").show();
                }
                else if(TextUtils.isEmpty(model_string))
                {
                    model.setError("Model is Required");
                }
                else if(TextUtils.isEmpty(modelyear_string))
                {
                    modelyear.setError("Model Year is Required");
                }
                else if(TextUtils.isEmpty(color_string))
                {
                    color.setError("Color is Required");
                }
                else if (seat_cap_string.equals("Seat Capacity"))
                {
                    Toasty.warning(VehicleRegisterActivity.this,"Select the Seat Capacity").show();
                }
                else if (engine_cc_string.equals("Engine CC"))
                {
                    Toasty.warning(VehicleRegisterActivity.this,"Select the Engine Cubic Capacity").show();
                }
                else if (fuel_type_string.equals("Fuel Type"))
                {
                    Toasty.warning(VehicleRegisterActivity.this,"Select the Fuel Type").show();
                }
                else if (interior_string.equals("Interior"))
                {
                    Toasty.warning(VehicleRegisterActivity.this,"Select the Interior Quality").show();
                }
                else if(!petsyes.isChecked()&&!petsno.isChecked())
                {
                    Toasty.warning(VehicleRegisterActivity.this,"are Pets allowed?").show();
                }
                else if(!musicyes.isChecked()&&!musicno.isChecked())
                {
                    Toasty.warning(VehicleRegisterActivity.this,"Is Music allowed?").show();
                }
                else if(!smokeyes.isChecked()&&!smokeno.isChecked())
                {
                    Toasty.warning(VehicleRegisterActivity.this,"Is Smoke allowed?").show();
                }
                else if(destination==null)
                {
                    Toasty.warning(VehicleRegisterActivity.this,"Please Upload Vehicle Image").show();
                }
                else {
                    selectedPet = pets.getCheckedRadioButtonId();
                    pets_btn = (RadioButton)findViewById(selectedPet);
                    pets_string = pets_btn.getText().toString();

                    selectedMusic = music.getCheckedRadioButtonId();
                    music_btn = (RadioButton)findViewById(selectedMusic);
                    music_string= music_btn.getText().toString();

                    selectedSmoke = smoke.getCheckedRadioButtonId();
                    smoke_btn = (RadioButton)findViewById(selectedSmoke);
                    smoke_string = smoke_btn.getText().toString();

                    vehicleRegisterPresenter.requestVehicleRegister(reg_no_string, reg_name_string, make_string, type_veh_string,
                            type_body_string, model_string, modelyear_string, color_string,
                            seat_cap_string, engine_cc_string, fuel_type_string, interior_string,
                            pets_string, music_string, smoke_string);
                }
            }
        });
    }

    private void getVehicleImage() {
        CropImage.activity()
                .setGuidelines(CropImageView.Guidelines.ON).
                setOutputCompressQuality(70)
                .start(this);
    }

    @Override
    public void startProgress() {

    }

    @Override
    public void stopProgress() {

    }

    @Override
    public void onComplete(String message) {
        Toasty.success(this,message).show();
        finish();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                destination = result.getUri();
                imageViewCar.setImageURI(destination);
                try {
                    filePath= PathUtil.getPath(MyApp.getContext(),destination);
                    Log.d(TAG,"Image Path is=> "+ filePath);

                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }


            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
                Log.d(TAG,"Error"+error.getMessage());
            }
        }
    }}