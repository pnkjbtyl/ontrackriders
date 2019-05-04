package com.ontrack.ontrackriders.webservice;

import com.ontrack.ontrackriders.activity.Result;

import retrofit2.Call;
import retrofit2.http.GET;

public interface WebInterface {

    @GET("api/list")
    Call<Result> getData();
}
