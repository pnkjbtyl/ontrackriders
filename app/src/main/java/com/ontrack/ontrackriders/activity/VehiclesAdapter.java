package com.ontrack.ontrackriders.activity;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ontrack.ontrackriders.R;


public class VehiclesAdapter extends RecyclerView.Adapter<VehiclesViewHolder> {



    private VehicleResponse pojos;
    private Context context;

    public VehiclesAdapter(VehicleResponse pojos, Context context)
    {
       this.pojos=pojos;
       this.context = context;
    }
    @NonNull
    @Override
    public VehiclesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.vehicles_list_layout,viewGroup,false);

            return new VehiclesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VehiclesViewHolder vehiclesViewHolder, int i) {

        vehiclesViewHolder.reg_no.setText(pojos.getData().get(i).getRegistrationNo());
        vehiclesViewHolder.maker.setText(pojos.getData().get(i).getMake());
        vehiclesViewHolder.model.setText(pojos.getData().get(i).getModel());
        vehiclesViewHolder.modelyear.setText(pojos.getData().get(i).getModelYear());

    }


    @Override
    public int getItemCount()
    {
            return pojos.getData().size();

    }
}
