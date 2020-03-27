package com.healthpoint.medic.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseDaftarRumah {
    @SerializedName("data")
    private List<DaftarRumahItem> daftarRumah;

    @SerializedName("message")
    private String message;

    public void setDaftarRumah(List<DaftarRumahItem> daftarRumah){this.daftarRumah = daftarRumah;}
    public List<DaftarRumahItem> getDaftarRumah(){
        return daftarRumah; }

    public void setMessage(String message){
        this.message = message;
    }
    public String getMessage(){
        return message;
    }
}
