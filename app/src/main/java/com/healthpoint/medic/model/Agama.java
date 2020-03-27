package com.healthpoint.medic.model;

import com.google.gson.annotations.SerializedName;

public class Agama {
    @SerializedName("id_agama")
    private int idAgama;

    @SerializedName("opsi_agama")
    private String opsiAgama;

    public void setIdAgama(int idAgama){
        this.idAgama = idAgama;
    }

    public int getIdAgama(){
        return idAgama;
    }

    public void setOpsiAgama(String opsiAgama){
        this.opsiAgama = opsiAgama;
    }

    public String getOpsiAgama(){
        return opsiAgama;
    }

    @Override
    public String toString(){
        return getOpsiAgama();
    }
}
