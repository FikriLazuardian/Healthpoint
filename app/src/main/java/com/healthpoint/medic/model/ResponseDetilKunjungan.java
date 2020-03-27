package com.healthpoint.medic.model;

import com.google.gson.annotations.SerializedName;

public class ResponseDetilKunjungan {
    @SerializedName("data")
    private KunjunganItem data;

    @SerializedName("message")
    private String message;

    public void setData(KunjunganItem data){this.data = data;}
    public KunjunganItem getData(){
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
                "ResponseDetilKunjungan{"+
                        "data = '" + data + '\'' +
                        ",message = '" + message + '\'' +
                        "}";
    }

}
