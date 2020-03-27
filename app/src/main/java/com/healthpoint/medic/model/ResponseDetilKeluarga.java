package com.healthpoint.medic.model;

import com.google.gson.annotations.SerializedName;

public class ResponseDetilKeluarga {
    @SerializedName("data")
    private DaftarKeluargaItem detilKeluarga;

    @SerializedName("message")
    private String message;

    public void setDetilKeluarga(DaftarKeluargaItem detilKeluarga){this.detilKeluarga = detilKeluarga;}
    public DaftarKeluargaItem getDetilKeluarga(){
        return detilKeluarga; }

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
                        "data = '" + detilKeluarga + '\'' +
                        ",message = '" + message + '\'' +
                        "}";
    }

}
