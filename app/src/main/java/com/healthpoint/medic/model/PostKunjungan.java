package com.healthpoint.medic.model;

import com.google.gson.annotations.SerializedName;

public class PostKunjungan {

    @SerializedName("message")
    private String message;

    @SerializedName("data")
    private KunjunganItem data;

    public void setMessage(String message){
        this.message = message;
    }
    public String getMessage(){
        return message;
    }

    public void setData(KunjunganItem data){this.data = data;}
    public KunjunganItem getData(){
        return data;
    }

    @Override
    public String toString(){
        return
                "PostKunjungan{"+
                        "data = '" + data + '\'' +
                        ",message = '" + message + '\'' +
                        "}";
    }
}
