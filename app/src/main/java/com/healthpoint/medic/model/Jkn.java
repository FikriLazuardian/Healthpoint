package com.healthpoint.medic.model;

import com.google.gson.annotations.SerializedName;

public class Jkn {
    @SerializedName("id_jkn")
    private int idJkn;

    @SerializedName("opsi_jkn")
    private String opsiJkn;

    public void setIdJkn(int idJkn){
        this.idJkn = idJkn;
    }

    public int getIdJkn(){
        return idJkn;
    }

    public void setOpsiJkn(String opsiJkn){
        this.opsiJkn = opsiJkn;
    }

    public String getOpsiJkn(){
        return opsiJkn;
    }

    @Override
    public String toString(){
        return getOpsiJkn();
    }
}