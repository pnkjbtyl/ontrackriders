package com.ontrack.ontrackriders.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.TextView;

import com.ontrack.ontrackriders.R;
import com.ontrack.ontrackriders.activity.home.HomeActivity;
import com.ontrack.ontrackriders.activity.login.LoginActivity;
import com.ontrack.ontrackriders.utils.Pref;
public class SplashActivity extends AppCompatActivity {
    private static final String TAG="SplashActivity";
    private static final int SPLASH_TIME=1800;
    private static String USER_EMAIL;
    private static final int REQUEST_PERMISSIONS = 20;
    private SparseIntArray mErrorString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Log.d(TAG,"Splash Activity Started");
        checkRuntimePermission();
    }

    private void checkRuntimePermission() {
        mErrorString = new SparseIntArray();
        int currentapiVersion = Build.VERSION.SDK_INT;
        if (currentapiVersion >= Build.VERSION_CODES.M) {
            String[] array = {Manifest.permission.READ_EXTERNAL_STORAGE,
                     Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION
            };
            requestAppPermissions(array, R.string.permission, REQUEST_PERMISSIONS);
        } else {
            onPermissionsGranted(REQUEST_PERMISSIONS);
        }
    }


    // check requested permissions are on or off
    public void requestAppPermissions(final String[] requestedPermissions, final int stringId, final int requestCode) {
        mErrorString.put(requestCode, stringId);
        int permissionCheck = PackageManager.PERMISSION_GRANTED;
        boolean shouldShowRequestPermissionRationale = false;
        for (String permission : requestedPermissions) {
            permissionCheck = permissionCheck + ContextCompat.checkSelfPermission(this, permission);
            shouldShowRequestPermissionRationale = shouldShowRequestPermissionRationale || ActivityCompat.shouldShowRequestPermissionRationale(this, permission);
        }
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            if (shouldShowRequestPermissionRationale) {
                Snackbar snack = Snackbar.make(findViewById(android.R.id.content), stringId, Snackbar.LENGTH_INDEFINITE);
                View view = snack.getView();
                TextView tv = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
                tv.setTextColor(Color.WHITE);
                snack.setAction("GRANT", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ActivityCompat.requestPermissions(SplashActivity.this, requestedPermissions, requestCode);
                    }
                }).show();
            } else {
                ActivityCompat.requestPermissions(this, requestedPermissions, requestCode);
            }
        } else {
            onPermissionsGranted(requestCode);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        int permissionCheck = PackageManager.PERMISSION_GRANTED;
        for (int permission : grantResults) {
            permissionCheck = permissionCheck + permission;
        }
        if ((grantResults.length > 0) && permissionCheck == PackageManager.PERMISSION_GRANTED) {
            onPermissionsGranted(requestCode);
        } else {
            onPermissionsGranted(requestCode);
        }
    }

    // if permissions granted succesfully
    private void onPermissionsGranted(int requestcode) {
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                checkUser();
            }
        }, SPLASH_TIME);
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


}
