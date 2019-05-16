package com.ontrack.ontrackriders.activity.fragment_routes;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ontrack.ontrackriders.R;

public class RouteFragment extends Fragment {
    private static final String TAG="RouteFragment";
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG,"Opened Routes Fragment");
        View view= inflater.inflate(R.layout.fragment_routes,container,false);
        return view;

    }
}
