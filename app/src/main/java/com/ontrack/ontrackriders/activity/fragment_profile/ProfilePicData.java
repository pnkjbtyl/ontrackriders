package com.ontrack.ontrackriders.activity.fragment_profile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProfilePicData {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("fileoriginalname")
    @Expose
    private String fileoriginalname;
    @SerializedName("filename")
    @Expose
    private String filename;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFileoriginalname() {
        return fileoriginalname;
    }

    public void setFileoriginalname(String fileoriginalname) {
        this.fileoriginalname = fileoriginalname;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

}


