package com.ontrack.ontrackriders.activity.vehicle_register;

public interface IVehicleRegisterPresenter {
    void requestVehicleRegister(String reg_no, String reg_name, String make, String type_veh, String type_body,
                                String model, String model_year, String color, String seat_cap, String engine_cc,
                                String fuel_type, String interior, String pets, String music, String smoke);

}
