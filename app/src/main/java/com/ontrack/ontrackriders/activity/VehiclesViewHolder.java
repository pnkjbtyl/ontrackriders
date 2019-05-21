package com.ontrack.ontrackriders.activity;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.ontrack.ontrackriders.R;

public class VehiclesViewHolder extends RecyclerView.ViewHolder {

    TextView reg_no, maker, model, modelyear;

    public VehiclesViewHolder(@NonNull View itemView) {
        super(itemView);

        reg_no = (TextView) itemView.findViewById(R.id.veh_regno);
        maker = (TextView) itemView.findViewById(R.id.veh_maker);
        model = (TextView) itemView.findViewById(R.id.veh_model);
        modelyear = (TextView) itemView.findViewById(R.id.veh_modelyear);

    }
}
