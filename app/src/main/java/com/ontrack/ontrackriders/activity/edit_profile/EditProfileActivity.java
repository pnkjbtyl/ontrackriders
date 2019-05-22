package com.ontrack.ontrackriders.activity.edit_profile;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.model.PlaceLikelihood;
import com.google.android.libraries.places.api.net.FindCurrentPlaceRequest;
import com.google.android.libraries.places.api.net.FindCurrentPlaceResponse;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;
import com.ontrack.ontrackriders.MyApp;
import com.ontrack.ontrackriders.R;
import com.ontrack.ontrackriders.utils.CustomProgress;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;

public class EditProfileActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener, IEditProfileView {
    private static final String TAG = "EditProfileActivity";
    PlacesClient placesClient;
    List<Place.Field> placeField = Arrays.asList(Place.Field.ID, Place.Field.NAME,Place.Field.ADDRESS);
    AutocompleteSupportFragment placesFragment;
    private CustomProgress customProgress;
    private int mYear, mMonth, mDay;
    private EditProfilePresenter editProfilePresenter;
    @BindView(R.id.linearLoc)
    LinearLayout linearLoc;
    @BindView(R.id.btn_findcurrentplace)
    Button btn_findcurrentplace;
    @BindView(R.id.imgLocationPicker)
    ImageView imgLocationPicker;
    @BindView(R.id.editTextProfileName)
    EditText etName;
    @BindView(R.id.editTextProfileAge)
    EditText etAge;
    @BindView(R.id.editTextProfileDob)
    EditText etDob;
    @BindView(R.id.editTextProfileLocation)
    EditText etLocation;
    @BindView(R.id.editTextProfileDLNo)
    EditText etDrivingLicense;
    @BindView(R.id.editTextProfileIdNo)
    EditText etIdentificationNo;
    @BindView(R.id.radio_gender)
    RadioGroup radio_gender;
    @BindView(R.id.radio_marital_status)
    RadioGroup radio_maritalStatus;
    @BindView(R.id.radio_smoke)
    RadioGroup radio_smoke;
    @BindView(R.id.radio_drink)
    RadioGroup radio_drink;
    @BindView(R.id.radio_specs)
    RadioGroup radio_specs;
    @BindView(R.id.textViewMarital)
    TextView textViewMarital;
    @BindView(R.id.textViewDrink)
    TextView textViewDrink;
    @BindView(R.id.textViewGender)
    TextView textViewGender;
    @BindView(R.id.textViewSpec)
    TextView textViewSpec;
    @BindView(R.id.textViewSmoke)
    TextView textViewSmoke;
    @BindView(R.id.textViewBloodGroup)
    TextView textViewBloodGroup;
    @BindView(R.id.imgDob)
    ImageView imgDob;
    @BindView(R.id.imgUpdateProfile)
    ImageView imgUpdateProfile;
    @BindView(R.id.spinnerBloodGroup)
    Spinner spinnerBloodGroup;
    private String placeAddress;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_profile);
        setSupportActionBar(toolbar);
        initViews();
        radioButtonsClicks();
        initPlaces();
        setupPlacesAutoComplete();

    }

    private void initPlaces() {
        Places.initialize(this, getString(R.string.places_api_key));
        placesClient = Places.createClient(this);

    }

    private void setupPlacesAutoComplete() {
        placesFragment = (AutocompleteSupportFragment)
                getSupportFragmentManager().findFragmentById(R.id.places_autocomplete_fragment);
        placesFragment.setPlaceFields(placeField);
        placesFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(@NonNull Place place) {
                Log.d(TAG, "Place is: " + place.getName()+"\n"+place.getAddress());
                etLocation.setText(place.getAddress());
                linearLoc.setVisibility(View.GONE);
            }

            @Override
            public void onError(@NonNull Status status) {
                Toast.makeText(EditProfileActivity.this, status.getStatusMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }


    private void radioButtonsClicks() {
        //handling radio button clicks
        radio_gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) findViewById(checkedId);
                textViewGender.setText(rb.getText().toString());
            }
        });
        radio_gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) findViewById(checkedId);
                textViewGender.setText(rb.getText().toString());
            }
        });
        radio_maritalStatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) findViewById(checkedId);
                textViewMarital.setText(rb.getText().toString());
            }
        });
        radio_smoke.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) findViewById(checkedId);
                textViewSmoke.setText(rb.getText().toString());
            }
        });
        radio_drink.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) findViewById(checkedId);
                textViewDrink.setText(rb.getText().toString());
            }
        });
        radio_specs.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) findViewById(checkedId);
                textViewSpec.setText(rb.getText().toString());
            }
        });


    }

    private void initViews() {
        //initialising views
        editProfilePresenter = new EditProfilePresenter(this, this);
        ButterKnife.bind(this);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        Log.d(TAG, "Edit Profile Activity started");
        imgDob.setOnClickListener(this);
        imgUpdateProfile.setOnClickListener(this);
        customProgress = CustomProgress.getInstance();
        spinnerBloodGroup.setOnItemSelectedListener(this);
        imgLocationPicker.setOnClickListener(this);
        btn_findcurrentplace.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.imgDob) {
            //perform date check and set in edit text.
            showDatepicker();

        } else if (v.getId() == R.id.imgUpdateProfile) {
            validateFields();
        } else if (v.getId() == R.id.imgLocationPicker) {
            linearLoc.setVisibility(View.VISIBLE);

        } else if (v.getId() == R.id.btn_findcurrentplace) {
            findCurrentLocation();
        }


    }

    private void findCurrentLocation() {

        FindCurrentPlaceRequest request = FindCurrentPlaceRequest.builder(placeField).build();

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Task<FindCurrentPlaceResponse> task = placesClient.findCurrentPlace(request);
        task.addOnCompleteListener(new OnCompleteListener<FindCurrentPlaceResponse>() {
            @Override
            public void onComplete(@NonNull Task<FindCurrentPlaceResponse> task) {
                if(task.isSuccessful())
                {
                    FindCurrentPlaceResponse response=task.getResult();
                    if (response != null) {
                        Collections.sort(response.getPlaceLikelihoods(), new Comparator<PlaceLikelihood>() {
                            @Override
                            public int compare(PlaceLikelihood o1, PlaceLikelihood o2) {
                                return new Double(o1.getLikelihood()).compareTo(o2.getLikelihood());
                            }
                        });


                    Collections.reverse(response.getPlaceLikelihoods());
                    placeAddress=response.getPlaceLikelihoods().get(0).getPlace().getAddress();
                    Log.d(TAG,"Place address is "+placeAddress);
                    etLocation.setText(placeAddress);
                    linearLoc.setVisibility(View.GONE);


                }

            }}
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(EditProfileActivity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                Log.d(TAG,e.getLocalizedMessage());

            }
        });
    }

    private void showCustomeAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MyApp.getContext());
        // Get the layout inflater
        LayoutInflater inflater = (this).getLayoutInflater();
        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the
        // dialog layout
        builder.setTitle("");
        builder.setCancelable(false);
        builder.setView(inflater.inflate(R.layout.custom_location_search, null))
                // Add action buttons
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
    builder.create();
    builder.show();
    }




    private void validateFields() {
        //now we will validate all fields
        String name=etName.getText().toString();
        String age=etAge.getText().toString().trim();
        String dob=etDob.getText().toString();
        String loc=etLocation.getText().toString().trim();
        String dlNo=etDrivingLicense.getText().toString().trim();
        String idNo=etIdentificationNo.getText().toString().trim();
        String gender=textViewGender.getText().toString().trim();
        String marital_status=textViewMarital.getText().toString().trim();
        String smoke=textViewSmoke.getText().toString().trim();
        String drink=textViewDrink.getText().toString().trim();
        String specs=textViewSpec.getText().toString().trim();
        String blood_group=textViewBloodGroup.getText().toString().trim();



        if(TextUtils.isEmpty(name))
        {
            etName.setError("Name Required*");
        }
        else if(TextUtils.isEmpty(dob))
        {
            Toasty.warning(this,"Date Of Birth Required*").show();


        }

        else if(TextUtils.isEmpty(loc))
        {
            etLocation.setError("Location Required*");
        }
        else if(TextUtils.isEmpty(dlNo))
        {
            etDrivingLicense.setError("Driving License Required*");
        }
        else if(TextUtils.isEmpty(idNo))
        {
            etIdentificationNo.setError("Identifiation No Required*");
        }
        else if(TextUtils.isEmpty(gender))
        {
            Toasty.warning(this,"Gender Required*").show();
        }
        else if(TextUtils.isEmpty(blood_group))
        {
            Toasty.warning(this,"Blood Group Required*").show();
        }
        else if(TextUtils.isEmpty(marital_status))
        {
            Toasty.warning(this,"Marital Status Required*").show();
        }
        else if(TextUtils.isEmpty(smoke))
        {
            Toasty.warning(this,"Smoke Information Required*").show();
        }
        else if(TextUtils.isEmpty(drink))
        {
            Toasty.warning(this,"Drink information Required*").show();
        }
        else if(TextUtils.isEmpty(specs))
        {
            Toasty.warning(this,"Spectacles information Required*").show();
        }
        else
        {
            Log.d(TAG,"Fields validated");
            Log.d(TAG,"Data Passed To Presenter");
            editProfilePresenter.requestEditProfile(name,dob,age,loc,dlNo,idNo,gender,blood_group,marital_status,
                    smoke,drink,specs);

        }

    }

    private void showDatepicker() {
        // Get Current Date
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dateDialog = new DatePickerDialog(EditProfileActivity.this, datePickerListener, mYear, mMonth, mDay);
        dateDialog.getDatePicker().setMaxDate(new Date().getTime());
        dateDialog.show();
    }
    private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
            Calendar c = Calendar.getInstance();
            c.set(Calendar.YEAR, year);
            c.set(Calendar.MONTH, month);
            c.set(Calendar.DAY_OF_MONTH, day);
            String format = new SimpleDateFormat("dd/MM/YYYY").format(c.getTime());
            int age=calculateAge(c.getTimeInMillis());
            if(age<1)
            {
                Log.d(TAG,"ERR");
                Toasty.warning(EditProfileActivity.this,"Enter Correct Date Of Birth*").show();
                etAge.setText("");
                etDob.setText("");
            }
            else if(age<10)
            {
                Toasty.warning(EditProfileActivity.this,"Age Below 10 not allowed*").show();
                Log.d(TAG,"ERR");
                etAge.setText("");
                etDob.setText("");

            }
            else
            {
                etDob.setText(format);
                etAge.setText(Integer.toString(calculateAge(c.getTimeInMillis())));
            }

        }
    };

    int calculateAge(long date){
        Calendar dob = Calendar.getInstance();
        dob.setTimeInMillis(date);
        Calendar today = Calendar.getInstance();
        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);
        if(today.get(Calendar.DAY_OF_MONTH) < dob.get(Calendar.DAY_OF_MONTH)){
            age--;
        }

        return age;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Object item = parent.getItemAtPosition(position);
        textViewBloodGroup.setText(item.toString());

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void startProgress() {
        customProgress.showProgress(this,"updating profile, please wait...",false);


    }

    @Override
    public void stopProgress() {
        customProgress.hideProgress();

    }

    @Override
    public void onComplete(String message) {
        Toasty.success(this,message).show();
        finish();

    }

}

