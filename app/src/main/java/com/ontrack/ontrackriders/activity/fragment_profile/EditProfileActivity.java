package com.ontrack.ontrackriders.activity.fragment_profile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.ontrack.ontrackriders.R;

public class EditProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar_profile);
        setSupportActionBar(toolbar);
    }
}
