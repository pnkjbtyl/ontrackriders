package com.ontrack.ontrackriders.activity;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ontrack.ontrackriders.R;

import java.util.ArrayList;

public class VehiclesAdapter extends RecyclerView.Adapter<VehiclesAdapter.VehiclesViewHolder> {



    private ArrayList<MyPojo> pojos;

    public static class VehiclesViewHolder extends RecyclerView.ViewHolder{

        public View view;
        public VehiclesViewHolder(View v){
            super(v);
            view = v;
        }
    }

    public VehiclesAdapter(ArrayList<MyPojo> pojos)
    {
       this.pojos=pojos;
    }
    @NonNull
    @Override
    public VehiclesAdapter.VehiclesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View veh = inflater.inflate(R.layout.vehicles_list_layout,viewGroup,false);
            return new VehiclesViewHolder(veh);
    }

    @Override
    public void onBindViewHolder(@NonNull VehiclesViewHolder vehiclesViewHolder, int i) {

        TextView reg_no = (TextView) vehiclesViewHolder.view.findViewById(R.id.veh_regno);
        TextView maker = (TextView) vehiclesViewHolder.view.findViewById(R.id.veh_maker);
        TextView model = (TextView) vehiclesViewHolder.view.findViewById(R.id.veh_model);
        TextView modelyear = (TextView) vehiclesViewHolder.view.findViewById(R.id.veh_modelyear);

        reg_no.setText(pojos.get(i).getReg_no());
        maker.setText(pojos.get(i).getMaker());
        model.setText(pojos.get(i).getModel());
        modelyear.setText(pojos.get(i).getModelyear());

    }


    @Override
    public int getItemCount()
    {
        return pojos.size();
    }


}
