package com.ontrack.ontrackriders.activity;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ontrack.ontrackriders.MyApp;
import com.ontrack.ontrackriders.R;
import com.ontrack.ontrackriders.activity.vehicle_register.VehicleProfileActivity;
import com.ontrack.ontrackriders.activity.vehicle_register.VehicleRegisterActivity;


public class VehiclesAdapter extends RecyclerView.Adapter<VehiclesViewHolder> {


    private VehiclesViewHolder.OnItemListener onItemListener;
    private VehicleResponse pojos;
    private Context context;

    public VehiclesAdapter(VehicleResponse pojos, Context context, VehiclesViewHolder.OnItemListener onItemListener)
    {
       this.pojos=pojos;
       this.context = context;
       this.onItemListener=onItemListener;
    }
    @NonNull
    @Override
    public VehiclesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.vehicles_list_layout,viewGroup,false);

            return new VehiclesViewHolder(view,onItemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull VehiclesViewHolder vehiclesViewHolder, final int i) {

        final String id = pojos.getData().get(i).getId().toString();
        Toast.makeText(context, id, Toast.LENGTH_SHORT).show();
        vehiclesViewHolder.reg_no.setText(pojos.getData().get(i).getRegistrationNo());
        vehiclesViewHolder.maker.setText(pojos.getData().get(i).getMake());
        vehiclesViewHolder.model.setText(pojos.getData().get(i).getModel());
        vehiclesViewHolder.modelyear.setText(pojos.getData().get(i).getModelYear());

        vehiclesViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = pojos.getData().get(i).getId().toString();
                Intent intent = new Intent(context, VehicleProfileActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("ID",id);
                context.startActivity(intent);
            }
        });

    }


    @Override
    public int getItemCount()
    {
            return pojos.getData().size();

    }
}
