package com.ontrack.ontrackriders.activity.login;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;


import com.ontrack.ontrackriders.R;
import com.ontrack.ontrackriders.utils.CustomProgress;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener,ILoginView {
    private static final String TAG="LoginActivity";
    @BindView(R.id.etEmail) EditText etEmail;
    @BindView(R.id.etPassword) EditText etPassword;
    @BindView(R.id.btn_login)
    AppCompatButton btLogin;
    private LoginPresenter loginPresenter;
    private CustomProgress customProgress;
    private static final String EMAIL_PATTERN="^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        initViews();
    }

    private void initViews() {
        //initialising views
        Log.d(TAG,"Login Activity Started");
        ButterKnife.bind(this);
        loginPresenter=new LoginPresenter(this,this);
        btLogin.setOnClickListener(this);
        customProgress=CustomProgress.getInstance();
    }

    @Override
    public void onClick(View v) {
        validateFields();
    }

    private void validateFields() {

        String email=etEmail.getText().toString().trim();
        String password=etPassword.getText().toString().trim();
        Log.d(TAG,"Validating Fields");

        if(TextUtils.isEmpty(email))
        {
            etEmail.setError("Email Required*");
        }

        else if(!email.matches(EMAIL_PATTERN))
        {
            Toasty.error(this,"Invalid Email").show();
        }
        else if(TextUtils.isEmpty(password))
        {
            etPassword.setError("Password Required*");
        }
        else {

            Log.d(TAG,"Fields validated");
            Log.d(TAG,"Data Passed To Presenter");
            loginPresenter.requestLogin(email,password);
        }
    }

    @Override
    public void startProgress() {
        customProgress.showProgress(this,"Signing in, please wait...",false);
    }

    @Override
    public void stopProgress() {
        customProgress.hideProgress();

    }

    @Override
    public void onComplete(String message) {
        Toasty.success(this,message).show();
    }
}
