package com.ontrack.ontrackriders.activity.login;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ontrack.ontrackriders.R;
import com.ontrack.ontrackriders.activity.home.HomeActivity;
import com.ontrack.ontrackriders.activity.signup.SignUpActivity;
import com.ontrack.ontrackriders.utils.CustomProgress;
import com.ontrack.ontrackriders.utils.Pref;
import com.ontrack.ontrackriders.webservice.Retro;
import com.ontrack.ontrackriders.webservice.WebInterface;
import org.json.JSONException;
import org.json.JSONObject;
import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener,ILoginView, Callback<ResponseBody> {
    private static final String TAG="LoginActivity";
    @BindView(R.id.etEmail) EditText etEmail;
    @BindView(R.id.etPassword) EditText etPassword;
    @BindView(R.id.bt_login)
    Button btLogin;
    @BindView(R.id.textViewForgotPassword)
    TextView textViewForgotPassword;
    @BindView(R.id.textViewSignup)
    TextView textViewSignup;
    private Dialog openDialog;
    private Dialog resetDialog;
    private String forgotPasswordEmail;
    private String resetPasswordEmail;
    private String resetPasswordId;
    private String resetNewPassword;
    private String resetConfirmPassword;
    private static String USER_EMAIL;
    private LoginPresenter loginPresenter;
    private CustomProgress customProgress;
    private static final String EMAIL_PATTERN="^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        initViews();
        checkUser();
    }
    private void checkUser() {
        Log.d(TAG,"Checking user is logged in info");
        USER_EMAIL= Pref.getUserEmail(this);
        if(USER_EMAIL==null)
        {
            Log.d(TAG,"No one is logged in=> "+USER_EMAIL);
        }
        }
    private void initViews() {
        //initialising views
        Log.d(TAG,"Login Activity Started");
        ButterKnife.bind(this);
        loginPresenter=new LoginPresenter(this,this);
        btLogin.setOnClickListener(this);
        textViewSignup.setOnClickListener(this);
        textViewForgotPassword.setOnClickListener(this);
        customProgress=CustomProgress.getInstance();
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.bt_login)
        {
            validateFields();

        }
        else if(v.getId()==R.id.textViewSignup)
        {
            startActivity(new Intent(this, SignUpActivity.class)
                    .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        }

        else if(v.getId()==R.id.textViewForgotPassword)
        {
            //forgot password initiation
            Log.d(TAG,"Start Forgot Password Dialog");
            showForgotPasswordDialog();
        }
    }

    private void showForgotPasswordDialog() {
        openDialog = new Dialog(this);
        openDialog.setContentView(R.layout.custom_forgot_password_dialog);
        openDialog.setTitle("Ontrack Mobile");
        final EditText etForgotPasswordEmail = (EditText) openDialog.findViewById(R.id.etForgotPasswordEmail);
        Button buttonForgotPasswordSubmit = (Button)openDialog.findViewById(R.id.buttonForgotPasswordSubmit);
        buttonForgotPasswordSubmit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                forgotPasswordEmail=etForgotPasswordEmail.getText().toString().trim();
                if(forgotPasswordEmail.isEmpty())
                {
                    Toasty.warning(LoginActivity.this,"Email Required*").show();
                }
                else {

                    StartForgotPasswordEmailRequest(forgotPasswordEmail);
                    Log.d(TAG,"EMAIL IS: "+forgotPasswordEmail );



                }
            }
        });
        openDialog.show();

    }

    private void StartResetPasswordRequest(String resetPasswordId,String resetPasswordEmail, String resetNewPassword) {

        //here we send the email to the server
        //now we will initialise Retrofit
        customProgress.showProgress(this,"Please wait",false);
        Log.d(TAG,"attemptimg to initialise retrofit");
        WebInterface webInterface= Retro.getClient().create(WebInterface.class);
        Log.d(TAG,"retrofit initialised");

        //creating request body to parse form data

        try {
            JSONObject paramObject = new JSONObject();
            paramObject.put("email", resetPasswordEmail);
            paramObject.put("key", resetPasswordId);
            paramObject.put("password", resetNewPassword);
            paramObject.put("confirm_password", resetNewPassword);
            RequestBody body = RequestBody.create(MediaType.parse("text/plain"),(paramObject).toString());
            Call<ResponseBody> call=webInterface.requestResetPassword(body);
            //exeuting the service
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.isSuccessful() && response.code() == 200) {
                        //if code is 200 and response is successfull means the user is logged in successfully

                        try {
                            JSONObject jObjSuccess = new JSONObject(response.body().string());
                            Toasty.info(LoginActivity.this, jObjSuccess.getString("message")).show();
                            Log.d("LoginActivity", jObjSuccess.getString("message"));
                            customProgress.hideProgress();
                            Handler h=new Handler();
                            h.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    if(resetDialog.isShowing())
                                    {
                                        resetDialog.dismiss();
                                        etEmail.setText(forgotPasswordEmail);
                                    }
                                }
                            },2000);

                        } catch (Exception e) {
                            Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                            customProgress.hideProgress();

                        }

                    }

                    else {
                        try{
                            JSONObject jObjError = new JSONObject(response.errorBody().string());
                            Toasty.info(LoginActivity.this, jObjError.getString("message")).show();
                            Log.d("LoginActivity", jObjError.getString("message"));
                            customProgress.hideProgress();
                        } catch (Exception e) {
                            Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                            customProgress.hideProgress();
                        }
                    }

                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Log.d(TAG,t.getLocalizedMessage());
                    Toast.makeText(LoginActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    customProgress.hideProgress();

                }
            });

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    private void StartForgotPasswordEmailRequest(String forgotPasswordEmail) {
               //here we send the email to the server
        //now we will initialise Retrofit
        customProgress.showProgress(this,"Please wait",false);
        Log.d(TAG,"attemptimg to initialise retrofit");
        WebInterface webInterface= Retro.getClient().create(WebInterface.class);
        Log.d(TAG,"retrofit initialised");

        //creating request body to parse form data

        try {
            JSONObject paramObject = new JSONObject();
            paramObject.put("email", forgotPasswordEmail);
            RequestBody body = RequestBody.create(MediaType.parse("text/plain"),(paramObject).toString());
            Call<ResponseBody> call=webInterface.requestForgotPassword(body);
            call.enqueue(this);


            //exeuting the service
        } catch (JSONException e) {
            e.printStackTrace();
        }

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


    @Override
    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
        if (response.isSuccessful() && response.code() == 200) {
            //if code is 200 and response is successfull means the user is logged in successfully

            try {
                JSONObject jObjSuccess = new JSONObject(response.body().string());
                Toasty.info(this, jObjSuccess.getString("message")).show();
                Log.d("LoginActivity", jObjSuccess.getString("message"));
                customProgress.hideProgress();
                Handler h=new Handler();
                h.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if(openDialog.isShowing())
                        {
                            openDialog.dismiss();
                            showResetPasswordDialog();
                        }
                    }
                },2000);

            } catch (Exception e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
                customProgress.hideProgress();

            }

        }

        else {
            try{
            JSONObject jObjError = new JSONObject(response.errorBody().string());
            Toasty.info(this, jObjError.getString("message")).show();
            Log.d("LoginActivity", jObjError.getString("message"));
                customProgress.hideProgress();
            } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
                customProgress.hideProgress();
            }
        }

    }

    private void showResetPasswordDialog() {
        resetDialog = new Dialog(this);
        resetDialog.setContentView(R.layout.custom_reset_password_dialog);
        resetDialog.setTitle("Ontrack Mobile");
        final EditText etResetPasswordEmail = (EditText) resetDialog.findViewById(R.id.etResetEmail);
        etResetPasswordEmail.setText(forgotPasswordEmail);
        final EditText etResetPasswordId=(EditText) resetDialog.findViewById(R.id.etResetPasswordId);
        final EditText etResetPassword=(EditText) resetDialog.findViewById(R.id.etResetPassword);
        final EditText etResetConfirmPassword=(EditText) resetDialog.findViewById(R.id.etResetConfirmPassword);
        Button buttonResetPasswordSubmit = (Button)resetDialog.findViewById(R.id.buttonResetPasswordSubmit);
        buttonResetPasswordSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetPasswordId=etResetPasswordId.getText().toString().trim();
                resetNewPassword=etResetPassword.getText().toString().trim();
                resetPasswordEmail=etResetPasswordEmail.getText().toString().trim();
                resetConfirmPassword=etResetConfirmPassword.getText().toString().trim();

                if(resetPasswordId.isEmpty())
                {
                    Toasty.warning(LoginActivity.this,"Id Required*").show();

                }
                else if(resetPasswordEmail.isEmpty())
                {
                    Toasty.warning(LoginActivity.this,"Email Required*").show();

                }
                else if(resetNewPassword.isEmpty())
                {
                    Toasty.warning(LoginActivity.this,"Password Required*").show();

                }
                else if(resetConfirmPassword.isEmpty())
                {
                    Toasty.warning(LoginActivity.this,"Confirm Password Required*").show();

                }
                else if(!resetNewPassword.equals(resetConfirmPassword))
                {
                    Toasty.info(LoginActivity.this,"Passwords Do Not Match").show();


                }
                else {

                    Log.d(TAG,"Fields Validated, sending password reset request");
                    StartResetPasswordRequest(resetPasswordId,resetPasswordEmail,resetNewPassword);


                }

            }
        });
        resetDialog.setCanceledOnTouchOutside(false);
        resetDialog.show();

    }

    @Override
    public void onFailure(Call<ResponseBody> call, Throwable t) {
        Log.d(TAG,t.getLocalizedMessage());
        Toast.makeText(this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
        customProgress.hideProgress();

    }
}
