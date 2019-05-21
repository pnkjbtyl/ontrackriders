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

public class RetroWithRefresh {
    private static Retrofit retrofit = null;
    public static Retrofit getClient() {
        if (retrofit == null) {
            final OkHttpClient.Builder httpClient = new OkHttpClient.Builder();


            httpClient.authenticator(new Authenticator() {
                @Override
                public Request authenticate(Route route, Response response) throws IOException {
                    if (response.code() == 401) {
                        // If both the original call and the call with refreshed token failed,
                        // it will probably keep failing, so don't try again.


                        // We need a new client, since we don't want to make another call using our client with access token
                        retrofit = new Retrofit.Builder()
                                .baseUrl(BASE_URL)
                                .addConverterFactory(GsonConverterFactory.create())
                                .addConverterFactory(ScalarsConverterFactory.create())
                                .build();
                        WebInterface tokenClient = retrofit.create(WebInterface.class);

                        Call<RefreshToken> call = tokenClient.requestRefreshToken(Pref.getUserRefreshToken(MyApp.getContext()));

                        try {
                            retrofit2.Response<RefreshToken> tokenResponse = call.execute();
                            Log.e("refresh", " code" + tokenResponse.code());
                            Log.e("request", "url" + call.request().url());
                            if (tokenResponse.code() == 200) {
                                // save new access token
                                String accessToken = tokenResponse.body().getData().getToken();
                                Log.d("ACCESS:",accessToken);
                                String refreshToken = tokenResponse.body().getData().getRefreshSecretKey();
                                Pref.removeToken(MyApp.getContext());
                                Pref.removeRefreshToken(MyApp.getContext());

                                Pref.putToken(MyApp.getContext(), accessToken);
                                Pref.putRefreshToken(MyApp.getContext(), refreshToken);
                                return response.request().newBuilder()
//                                    .header("Authorization", newToken.getTokenType() + " " + newToken.getAccessToken())
                                        .header("x-access-code", accessToken)
                                        .build();
                            }

                        } catch (IOException e) {
                            return null;
                        }
                    } else if (response.code() == 200) {
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
                    }
                    return null;
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