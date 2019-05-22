package com.ontrack.ontrackriders.activity;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.ontrack.ontrackriders.R;
import com.ontrack.ontrackriders.webservice.Ret;
import com.ontrack.ontrackriders.webservice.Retro;
import com.ontrack.ontrackriders.webservice.WebInterface;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class VehiclesList extends AppCompatActivity {


    private RecyclerView vehicleslist;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicles_list);
        vehicleslist = (RecyclerView) findViewById(R.id.vehicleslist);
        vehicleslist.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        vehicleslist.setLayoutManager(layoutManager);


        WebInterface webInterface = Ret.getClient().create(WebInterface.class);

        Call<VehicleResponse> response = webInterface.getVehicleData();

        response.enqueue(new Callback<VehicleResponse>() {
            @Override
            public void onResponse(Call<VehicleResponse> call, Response<VehicleResponse> response) {
                showIt(response.body());
            }

            @Override
            public void onFailure(Call<VehicleResponse> call, Throwable t) {
                Toast.makeText(VehiclesList.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("Error is : ",t.getMessage());
            }
        });
    }

    private void showIt(VehicleResponse body) {

//        VehiclesAdapter vehiclesAdapter = new VehiclesAdapter(body,getApplicationContext());
//        vehicleslist.setAdapter(vehiclesAdapter);
    }
}
