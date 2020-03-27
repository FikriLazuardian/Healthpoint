package com.healthpoint.medic.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseAnggotaKeluarga {
    @SerializedName("data")
    private List<DaftarIndividuItem> daftarAnggota;

    @SerializedName("message")
    private String message;

    public void setDaftarAnggota(List<DaftarIndividuItem> daftarAnggota){this.daftarAnggota = daftarAnggota;}
    public List<DaftarIndividuItem> getDaftarAnggota(){
        return daftarAnggota; }
    public void setMessage(String message){
        this.message = message;
    }
    public String getMessage(){
        return message;
    }

    @Override
    public String toString(){
        return
                "DaftarIndividuItem{"+
                        "data = '" + daftarAnggota + '\'' +
                        ",message = '" + message + '\'' +
                        "}";
    }
}
