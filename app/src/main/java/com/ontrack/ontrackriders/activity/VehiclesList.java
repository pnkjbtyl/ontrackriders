package com.ontrack.ontrackriders.activity;

import android.app.LauncherActivity;
import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonObject;
import com.ontrack.ontrackriders.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class VehiclesList extends AppCompatActivity {

    private static final String URL = "http://192.168.1.5:3000/vehicles";

    private RecyclerView vehicleslist;
    private RecyclerView.Adapter vehiclesAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private ArrayList<MyPojo> pojos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicles_list);


        vehicleslist = (RecyclerView) findViewById(R.id.vehicleslist);
        vehicleslist.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        vehicleslist.setLayoutManager(layoutManager);

        //String[] maker = {"Honda","Bajaj","Hero"};
        //String[] model = {"Activa","CT100","Splendor"};

        ArrayList<MyPojo> pojos = new ArrayList<MyPojo>();

        loadRecyclerViewData();
    }
    private void loadRecyclerViewData(){
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading Data....");
        progressDialog.show();



        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray array = jsonObject.getJSONArray("data");

                            for(int i=0; i<array.length();i++)
                            {
                                JSONObject object =array.getJSONObject(i);
                                MyPojo pojoitem = new MyPojo(
                                        object.getString("registration_no"),
                                        object.getString("make"),
                                        object.getString("model"),
                                        object.getString("model_year")
                                );
                                pojos.add(pojoitem);
                            }
                            vehiclesAdapter = new VehiclesAdapter(pojos);
                            vehicleslist.setAdapter(vehiclesAdapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(VehiclesList.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.d("Error is ",error.getMessage());

                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
