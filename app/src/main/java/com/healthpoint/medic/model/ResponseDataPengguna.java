package com.healthpoint.medic.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseDataPengguna {
    @SerializedName("data")
    private DataPengguna data;

    @SerializedName("message")
    private String message;

    public void setData(DataPengguna data){this.data = data;}
    public DataPengguna getData(){
        return data; }

    public void setMessage(String message){
        this.message = message;
    }
    public String getMessage(){
        return message;
    }

    @Override
    public String toString(){
        return
                "ResponseRingkasanKunjungan{"+
                        "data = '" + data + '\'' +
                        ",message = '" + message + '\'' +
                        "}";
    }

}
