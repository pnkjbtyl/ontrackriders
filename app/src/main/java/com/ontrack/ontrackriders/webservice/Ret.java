package com.ontrack.ontrackriders.webservice;

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

public class Ret {
    private static Retrofit retrofit = null;
    public static Retrofit getClient()
    {   if(retrofit== null)
    {
        final OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Interceptor.Chain chain) throws IOException {
                Request original = chain.request();
                Request request = original.newBuilder()
                        .header("x-access-code", Pref.getUserToken(MyApp.getContext()))
                        .method(original.method(), original.body())
                        .build();

                return chain.proceed(request);
            }
        });

        OkHttpClient client = httpClient.build();
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .client(client)
                .build();
    }
        return retrofit;

    }}