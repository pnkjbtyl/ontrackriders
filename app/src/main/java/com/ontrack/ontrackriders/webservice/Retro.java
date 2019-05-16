package com.ontrack.ontrackriders.webservice;

import android.app.Application;
import android.util.Log;

import com.ontrack.ontrackriders.MyApp;
import com.ontrack.ontrackriders.utils.Pref;

import java.io.IOException;

import okhttp3.Authenticator;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import static com.ontrack.ontrackriders.webservice.IBaseUrl.BASE_URL;
public class Retro  {
    private static Retrofit retrofit = null;
    public static Retrofit getClient()
    {   if(retrofit== null)
    {

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                    .addConverterFactory(ScalarsConverterFactory.create())
                     .build();
        }
        return retrofit;



        }}




