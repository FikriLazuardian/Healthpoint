package com.healthpoint.medic.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseLapKoor {
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private DataKoor data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataKoor getDataKoor() {
        return data;
    }

    public void setDataKoor(DataKoor data) {
        this.data = data;
    }

}

