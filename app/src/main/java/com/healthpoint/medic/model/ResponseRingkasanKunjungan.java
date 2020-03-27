package com.healthpoint.medic.model;

import com.google.gson.annotations.SerializedName;

public class ResponseRingkasanKunjungan {
    @SerializedName("data")
    private RingkasanKunjungan data;

    @SerializedName("message")
    private String message;

    public void setData(RingkasanKunjungan data){this.data = data;}
    public RingkasanKunjungan getData(){
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
