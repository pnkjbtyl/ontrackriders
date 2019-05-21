package com.ontrack.ontrackriders.activity.fragment_trip;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ontrack.ontrackriders.R;

public class TripFragment extends Fragment {
    private static final String TAG="TripFragment";
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG,"Opened Trip Fragment");
        View view= inflater.inflate(R.layout.fragment_trips,container,false);
        return view;

    }
}
