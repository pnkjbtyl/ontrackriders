package com.ontrack.ontrackriders.activity.fragment_vehicle;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.ontrack.ontrackriders.R;

import butterknife.BindView;
import butterknife.ButterKnife;
public class VehicleFragment extends Fragment implements View.OnClickListener {
    @BindView(R.id.imageButtonAddVehicle)
    ImageButton imageButtonAddVehicle;
    private static final String TAG="VehicleFragment";
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG,"Opened Vehicle Fragment");
        View view= inflater.inflate(R.layout.fragment_vehicle,container,false);
        ButterKnife.bind(this,view);
        return view;
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imageButtonAddVehicle.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //navigate to add vehicle activity
    }
}
