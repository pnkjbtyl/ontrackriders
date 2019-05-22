package com.ontrack.ontrackriders.activity.fragment_vehicle;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.ontrack.ontrackriders.MyApp;
import com.ontrack.ontrackriders.R;
import com.ontrack.ontrackriders.activity.VehicleResponse;
import com.ontrack.ontrackriders.activity.VehiclesAdapter;
import com.ontrack.ontrackriders.activity.VehiclesList;
import com.ontrack.ontrackriders.activity.VehiclesViewHolder;
import com.ontrack.ontrackriders.activity.vehicle_register.VehicleProfileActivity;
import com.ontrack.ontrackriders.activity.vehicle_register.VehicleRegisterActivity;
import com.ontrack.ontrackriders.webservice.Ret;
import com.ontrack.ontrackriders.webservice.WebInterface;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VehicleFragment extends Fragment implements View.OnClickListener, VehiclesViewHolder.OnItemListener {
    @BindView(R.id.imageButtonAddVehicle)
    ImageButton imageButtonAddVehicle;
    private RecyclerView vehicleslist;
    private RecyclerView.LayoutManager layoutManager;
    private static final String TAG="VehicleFragment";
    private  List<String> item;
    private VehiclesAdapter vehiclesAdapter;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG,"Opened Vehicle Fragment");
        View view= inflater.inflate(R.layout.fragment_vehicle,container,false);
        ButterKnife.bind(this,view);
        vehicleslist = view.findViewById(R.id.vehicleslists);
        return view;
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imageButtonAddVehicle.setOnClickListener(this);
        WebInterface webInterface = Ret.getClient().create(WebInterface.class);
        Call<VehicleResponse> response = webInterface.getVehicleData();
        response.enqueue(new Callback<VehicleResponse>() {
            @Override
            public void onResponse(Call<VehicleResponse> call, Response<VehicleResponse> response) {
                if(response.body()!=null) {
                   item=new ArrayList<>();
                    item.add(response.body().toString());
                    showIt(response.body());
                }
                else
                {
                    Log.d(TAG,"ERROR");
                }
            }

            @Override
            public void onFailure(Call<VehicleResponse> call, Throwable t) {
                Toast.makeText(MyApp.getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("Error is : ",t.getMessage());
            }
        });
    }

    private void showIt(VehicleResponse body) {
        item.clear();
        vehiclesAdapter = new VehiclesAdapter(body,MyApp.getContext(),this);
        layoutManager = new LinearLayoutManager(MyApp.getContext());
        vehicleslist.setLayoutManager(layoutManager);
        vehicleslist.setHasFixedSize(true);
        vehicleslist.setAdapter(vehiclesAdapter);
        //vehiclesAdapter.notifyDataSetChanged();
        vehicleslist.getAdapter().notifyDataSetChanged();
    }


    @Override
    public void onClick(View v) {
        //navigate to add vehicle activity
        if(v.getId()==R.id.imageButtonAddVehicle)
        {
            startActivity(new Intent(MyApp.getContext(), VehicleRegisterActivity.class));
        }
    }


    @Override
    public void onItemClick(int position) {

        String id =Integer.toString(position);
        Log.d("Position is : ",id);
    }
}
