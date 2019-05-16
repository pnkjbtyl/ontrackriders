package com.ontrack.ontrackriders.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;


import com.ontrack.ontrackriders.R;
import com.ontrack.ontrackriders.activity.home.HomeActivity;
import com.ontrack.ontrackriders.activity.login.LoginActivity;
import com.ontrack.ontrackriders.activity.signup.SignUpActivity;
import com.ontrack.ontrackriders.utils.Pref;

public class SplashActivity extends AppCompatActivity {
    private static final String TAG="SplashActivity";
    private static final int SPLASH_TIME=3000;
    private static String USER_EMAIL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Log.d(TAG,"Splash Activity Started");
        navigateToSignup();
        checkUser();
    }

    private void checkUser() {
        Log.d(TAG,"Checking user is logged in info");
        USER_EMAIL= Pref.getUserEmail(this);
        if(USER_EMAIL!=null)
        {
            Log.d(TAG,"Logged In=> "+USER_EMAIL);
            Intent intent=new Intent(SplashActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        }
        else
        {
            //navigating to signup
            Intent intent=new Intent(SplashActivity.this, LoginActivity.class);
            startActivity(intent);
            Log.d(TAG,"No user");
            finish();
        }
    }

    private void navigateToSignup() {
        //created a new handler

        Handler h=new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {

            }
        },SPLASH_TIME);



    }


}
