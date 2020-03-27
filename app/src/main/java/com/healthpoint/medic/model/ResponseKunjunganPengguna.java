package com.healthpoint.medic.model;

import com.google.gson.annotations.SerializedName;

public class ResponseKunjunganPengguna {
    @SerializedName("data")
    private KunjunganPengguna data;

    @SerializedName("message")
    private String message;

    public void setMessage(String message){
        this.message = message;
    }
    public String getMessage(){
        return message;
    }

    public void setData(KunjunganPengguna data){
        this.data = data;
    }
    public KunjunganPengguna getData(){
        return data;
    }
}
