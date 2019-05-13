package com.ontrack.ontrackriders.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.ontrack.ontrackriders.R;

public class VehicleRegisterActivity extends AppCompatActivity {

    EditText reg_no,reg_name,make,model,modelyear,color;
    Spinner type_vehicle,type_body,seat_cap,engine_cc,fuel_type,interior;
    RadioGroup pets,music,smoking;

    String reg_no_string,reg_name_string,make_string,model_string,modelyear_string,color_string;

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

        pets = (RadioGroup)findViewById(R.id.radiogroup1);
        music = (RadioGroup)findViewById(R.id.radiogroup2);
        smoking = (RadioGroup)findViewById(R.id.radiogroup3);

        veh_reg_btn = (Button)findViewById(R.id.veh_reg_btn);

        veh_reg_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                reg_no_string = reg_no.getText().toString();
                reg_name_string = reg_name.getText().toString();
                make_string = make.getText().toString();
                model_string = model.getText().toString();
                modelyear_string = modelyear.getText().toString();
                color_string = color.getText().toString();
            }
        });


    }
}
