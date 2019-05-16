package com.ontrack.ontrackriders.activity;

public class MyPojo {

    private String reg_no;
    private String maker;
    private String model;
    private String modelyear;

    public MyPojo(String reg_no, String maker, String model, String modelyear){
        this.reg_no = reg_no;
        this.maker = maker;
        this.model = model;
        this.modelyear = modelyear;
    }

    public String getReg_no() {
        return reg_no;
    }

    public String getMaker() {
        return maker;
    }

    public String getModel() {
        return model;
    }

    public String getModelyear() {
        return modelyear;
    }
}
