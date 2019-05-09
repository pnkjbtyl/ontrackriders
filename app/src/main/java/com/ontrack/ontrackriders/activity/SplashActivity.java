package com.ontrack.ontrackriders.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.ontrack.ontrackriders.R;
import com.ontrack.ontrackriders.activity.login.LoginActivity;
import com.ontrack.ontrackriders.activity.signup.SignUpActivity;

public class SplashActivity extends AppCompatActivity {
    private static final String TAG="SplashActivity";
    private static final int SPLASH_TIME=3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Log.d(TAG,"Splash Activity Started");
        navigateToSignup();
    }

    private void navigateToSignup() {
        //created a new handler

        Handler h=new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                //navigating to signup
                Intent intent=new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);
                Log.d(TAG,"Navigation to signup activity success");
                finish();
            }
        },SPLASH_TIME);



    }


}
