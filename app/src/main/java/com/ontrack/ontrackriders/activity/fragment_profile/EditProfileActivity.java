package com.ontrack.ontrackriders.activity.fragment_profile;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.ontrack.ontrackriders.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;

public class EditProfileActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    private static final String TAG="EditProfileActivity";
    private int mYear, mMonth, mDay;
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



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar_profile);
        setSupportActionBar(toolbar);
        initViews();
        radioButtonsClicks();

    }

    private void radioButtonsClicks() {
        //handling radio button clicks
        radio_gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb= (RadioButton) findViewById(checkedId);
                textViewGender.setText( rb.getText().toString());
            }
        });
        radio_gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb= (RadioButton) findViewById(checkedId);
                textViewGender.setText( rb.getText().toString());
            }
        });
        radio_maritalStatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb= (RadioButton) findViewById(checkedId);
                textViewMarital.setText( rb.getText().toString());
            }
        });
        radio_smoke.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb= (RadioButton) findViewById(checkedId);
                textViewSmoke.setText( rb.getText().toString());
            }
        });
        radio_drink.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb= (RadioButton) findViewById(checkedId);
                textViewDrink.setText( rb.getText().toString());
            }
        });
        radio_specs.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb= (RadioButton) findViewById(checkedId);
                textViewSpec.setText( rb.getText().toString());
            }
        });


    }

    private void initViews() {
        //initialising views
        ButterKnife.bind(this);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        Log.d(TAG, "Edit Profile Activity started");
        imgDob.setOnClickListener(this);
        imgUpdateProfile.setOnClickListener(this);
        spinnerBloodGroup.setOnItemSelectedListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.imgDob)
        {
            //perform date check and set in edit text.
            showDatepicker();

        }
        else if (v.getId()==R.id.imgUpdateProfile)
        {
            validateFields();
        }


    }

    private void validateFields() {
        //now we will validate all fields
        String name=etName.getText().toString();
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
            Log.d(TAG,"Success");
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

}

