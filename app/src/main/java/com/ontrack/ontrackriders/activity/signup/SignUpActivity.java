package com.ontrack.ontrackriders.activity.signup;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;


import com.ontrack.ontrackriders.R;
import com.ontrack.ontrackriders.activity.home.HomeActivity;
import com.ontrack.ontrackriders.activity.login.LoginActivity;
import com.ontrack.ontrackriders.utils.CustomProgress;
import com.ontrack.ontrackriders.utils.PasswordValidator;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;


public class SignUpActivity extends AppCompatActivity implements View.OnClickListener,ISignupView {
    @BindView(R.id.btn_signup)
    AppCompatButton buttonSignup;
    @BindView(R.id.editTextName) EditText etName;
    @BindView(R.id.editTextEmail) EditText etEmail;
    @BindView(R.id.editTextPass) EditText etPass;
    @BindView(R.id.editTextCpass) EditText etCpass;
    @BindView(R.id.textViewButtonLogin)
    TextView textViewButtonLogin;
    private static final String TAG="SignUpActivity";
    private CustomProgress customProgress;
    private PasswordValidator passwordValidator;
    private SignupPresenter signupPresenter;
    private static final String NAME_PATTERN="^[\\p{L} .'-]+$";
    private static final String EMAIL_PATTERN="^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        initViews();
    }

    private void initViews() {
        //initialising views
        ButterKnife.bind(this);
        buttonSignup.setOnClickListener(this);
        textViewButtonLogin.setOnClickListener(this);
        customProgress=CustomProgress.getInstance();
        signupPresenter=new SignupPresenter(this,this);
        passwordValidator=new PasswordValidator();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        Log.d(TAG, "Signup Activity started");
    }


    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btn_signup)
        {
            validateFields();
        }
        else if(v.getId()==R.id.textViewButtonLogin)
        {
            startActivity(new Intent(this, LoginActivity.class)
            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
            finish();
        }
    }
    private void validateFields() {
        String name=etName.getText().toString();
        String email=etEmail.getText().toString().trim();
        String password=etPass.getText().toString().trim();
        String confirm_password=etCpass.getText().toString().trim();
        Log.d(TAG,"Validating Fields");

        if(TextUtils.isEmpty(name))
        {
            etName.setError("Name Required*");
        }
        else if(!name.matches(NAME_PATTERN))
        {
            Toasty.warning(this,"Invalid Name").show();
        }
        else if(TextUtils.isEmpty(email))
        {
            etEmail.setError("Email Required*");
        }
        else if(!email.matches(EMAIL_PATTERN))
        {
            Toasty.error(this,"Invalid Email").show();
        }
        else if(TextUtils.isEmpty(password))
        {
            etPass.setError("Password Required*");
        }
        else if(password.length()!=6)
        {
            Toasty.error(this,"Password length should be of 6 characters").show();
        }

        else if(TextUtils.isEmpty(confirm_password))
        {
            etCpass.setError("Confirm Password*");
        }
        else if(!confirm_password.matches(password))
        {
            Toasty.warning(this,"Password do not match").show();
        }
        else
        {
            Log.d(TAG,"Fields validated");
            Log.d(TAG,"Data Passed To Presenter");
            signupPresenter.requestRegister(name,email,password);
        }


}

    @Override
    public void startProgress() {
        customProgress.showProgress(this,"Signing up, please wait...",false);
    }

    @Override
    public void stopProgress() {
        customProgress.hideProgress();
    }

    @Override
    public void onComplete(String message) {
        Toasty.success(this,message).show();
        Intent intent=new Intent(this, HomeActivity.class)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
