package com.healthpoint.medic.model;

import com.google.gson.annotations.SerializedName;

public class ResponseLapKader {

    @SerializedName("message")
    private String message;

    @SerializedName("data")
    private DataKader dataKader;

    public void setMessage(String message){
        this.message = message;
    }
    public String getMessage(){
        return message;
    }

    public DataKader getDataKader(){
        return dataKader;
    }

    public void setDataKader(DataKader dataKader){
        this.dataKader = dataKader;
    }

}
