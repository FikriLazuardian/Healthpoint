package com.healthpoint.medic.model;

import com.google.gson.annotations.SerializedName;

public class JenisRumah {
    @SerializedName("id_jenis_rumah")
    private int idJenisRumah;

    @SerializedName("jenis_rumah")
    private String jenisRumah;

    public void setIdJenisRumah(int idJenisRumah){
        this.idJenisRumah = idJenisRumah;
    }

    public int getIdJenisRumah(){
        return idJenisRumah;
    }

    public void setJenisRumah(String jenisRumah){
        this.jenisRumah = jenisRumah;
    }

    public String getJenisRumah(){
        return jenisRumah;
    }

    @Override
    public String toString(){
        return getJenisRumah();
    }
}
