package com.ontrack.ontrackriders.activity.add_route;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.ontrack.ontrackriders.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.galaxyofandroid.spinerdialog.OnSpinerItemClick;
import in.galaxyofandroid.spinerdialog.SpinnerDialog;

public class AddRouteActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG="AddRouteActivity";
    ArrayList<String> items=new ArrayList<>();
    SpinnerDialog spinnerDialog;
    @BindView(R.id.imageViewSelectVehicle)
    ImageView imageViewSelectVehicle;
    @BindView(R.id.editTextVehicleSelect)
    EditText editTextVehicleSelect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_route);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        //initialising views
        imageViewSelectVehicle.setOnClickListener(this);
        showSpinnerItems();

    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.imageViewSelectVehicle)
        {
            Log.d(TAG,"Opening list to search for vehicle");
            spinnerDialog.showSpinerDialog();
        }

    }

    private void showSpinnerItems() {
        items.add("Delhi");
        items.add("Bengaluru");
        items.add("Hyderabad");
        items.add("Ahmedabad");
        items.add("Chennai");
        items.add("Kolkata");
        items.add("Surat");
        items.add("Pune");
        items.add("Jaipur");
        items.add("Lucknow");
        items.add("Kanpur");


        spinnerDialog=new SpinnerDialog(AddRouteActivity.this,items,"Select or Search Vehicle","Close");// With No Animation
        spinnerDialog=new SpinnerDialog(AddRouteActivity.this,items,"Select or Search Vehicle",R.style.DialogAnimations_SmileWindow,"Close");// With  Animation

        spinnerDialog.setCancellable(true); // for cancellable
        spinnerDialog.setShowKeyboard(false);// for open keyboard by default

        spinnerDialog.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String item, int position) {
                Toast.makeText(AddRouteActivity.this, item + "  " + position+"", Toast.LENGTH_SHORT).show();
                editTextVehicleSelect.setText(item);

            }
        });
    }
}
