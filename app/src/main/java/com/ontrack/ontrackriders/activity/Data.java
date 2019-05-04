
package com.ontrack.ontrackriders.activity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("Time")
    @Expose
    private String time;
    @SerializedName("String")
    @Expose
    private String string;
    @SerializedName("Server")
    @Expose
    private String server;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

}
