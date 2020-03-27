package com.healthpoint.medic.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseDaftarKunjungan {
    @SerializedName("data")
    private List<DaftarKunjunganItem> daftarPeriode;

    @SerializedName("message")
    private String message;

    public void setDaftarPeriode(List<DaftarKunjunganItem> daftarPeriode){this.daftarPeriode = daftarPeriode;}
    public List<DaftarKunjunganItem> getDaftarPeriode(){
        return daftarPeriode; }

    public void setMessage(String message){
        this.message = message;
    }
    public String getMessage(){
        return message;
    }
}
