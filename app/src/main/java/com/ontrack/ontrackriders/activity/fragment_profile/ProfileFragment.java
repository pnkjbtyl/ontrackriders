package com.ontrack.ontrackriders.activity.fragment_profile;

import android.content.Intent;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



import com.ontrack.ontrackriders.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProfileFragment extends Fragment implements View.OnClickListener {
    @BindView(R.id.btn_edit_profile)
    AppCompatButton btnEditProfile;
    private static final String TAG="ProfileFragment";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG,"Opened Profile Fragment");
        View view= inflater.inflate(R.layout.fragment_profile,container,false);
        ButterKnife.bind(this,view);
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnEditProfile.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        Log.d(TAG,"Edit Profile Activity Opens");
        Intent intent=new Intent(getActivity(),EditProfileActivity.class);
        startActivity(intent);
    }
}
