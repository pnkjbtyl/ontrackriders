package com.ontrack.ontrackriders.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.ontrack.ontrackriders.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG="LoginActivity";
    @BindView(R.id.etEmail) EditText etEmail;
    @BindView(R.id.etPassword) EditText etPassword;
    @BindView(R.id.btLogin) AppCompatButton btLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();
    }

    private void initViews() {
        //initialising views
        Log.d(TAG,"Login Activity Started");
        ButterKnife.bind(this);
        btLogin.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        validateFields();
    }

    private void validateFields() {

        String email=etEmail.getText().toString().trim();
        String password=etPassword.getText().toString().trim();

        Log.d(TAG,"Validating Fields");
    }
}
