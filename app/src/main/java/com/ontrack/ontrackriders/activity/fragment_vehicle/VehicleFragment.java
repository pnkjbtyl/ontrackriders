package com.ontrack.ontrackriders.activity.fragment_vehicle;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ontrack.ontrackriders.R;

public class VehicleFragment extends Fragment {
    private static final String TAG="VehicleFragment";
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG,"Opened Vehicle Fragment");
        View view= inflater.inflate(R.layout.fragment_vehicle,container,false);
        return view;

    }
}
