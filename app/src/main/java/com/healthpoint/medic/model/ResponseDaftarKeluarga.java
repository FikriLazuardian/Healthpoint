package com.healthpoint.medic.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseDaftarKeluarga {
    @SerializedName("data")
    private List<DaftarKeluargaItem> daftarKeluarga;

    @SerializedName("message")
    private String message;

    public void setDaftarKeluarga(List<DaftarKeluargaItem>daftarKeluarga){this.daftarKeluarga = daftarKeluarga;}
    public List<DaftarKeluargaItem> getDaftarKeluarga(){
        return daftarKeluarga; }
    public void setMessage(String message){
        this.message = message;
    }
    public String getMessage(){
        return message;
    }

    @Override
    public String toString(){
        return
                "ResponseDaftarKeluarga{"+
                        "data = '" + daftarKeluarga + '\'' +
                        ",message = '" + message + '\'' +
                        "}";
    }

}
