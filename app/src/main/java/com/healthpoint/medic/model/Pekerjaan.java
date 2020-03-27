package com.healthpoint.medic.model;

import com.google.gson.annotations.SerializedName;

public class Pekerjaan {
    @SerializedName("id_pekerjaan")
    private int idPekerjaan;
    @SerializedName("opsi_pekerjaan")
    private String opsiPekerjaan;

    public void setIdPekerjaan(int idPekerjaan){
        this.idPekerjaan = idPekerjaan;
    }

    public int getIdPekerjaan(){
        return idPekerjaan;
    }

    public void setOpsiPekerjaan(String opsiPekerjaan){
        this.opsiPekerjaan = opsiPekerjaan;
    }

    public String getOpsiPekerjaan(){
        return opsiPekerjaan;
    }

    @Override
    public String toString(){
        return getOpsiPekerjaan();
    }
}
