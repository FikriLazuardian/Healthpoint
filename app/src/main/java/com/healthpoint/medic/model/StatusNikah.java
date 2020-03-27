package com.healthpoint.medic.model;

import com.google.gson.annotations.SerializedName;

public class StatusNikah {
    @SerializedName("id_status_nikah")
    private int idStatusNikah;
    @SerializedName("opsi_status_nikah")
    private String opsiStatusNikah;

    public void setIdStatusNikah(int idStatusNikah){
        this.idStatusNikah = idStatusNikah;
    }

    public int getIdStatusNikah(){
        return idStatusNikah;
    }

    public void setOpsiStatusNikah(String opsiStatusNikah){
        this.opsiStatusNikah = opsiStatusNikah;
    }

    public String getOpsiStatusNikah(){
        return opsiStatusNikah;
    }

    @Override
    public String toString(){
        return getOpsiStatusNikah();
    }
}
