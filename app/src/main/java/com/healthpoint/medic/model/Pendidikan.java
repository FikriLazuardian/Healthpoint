package com.healthpoint.medic.model;

import com.google.gson.annotations.SerializedName;

public class Pendidikan {
    @SerializedName("id_pendidikan")
    private int idPendidikan;
    @SerializedName("opsi_pendidikan")
    private String opsiPendidikan;

    public void setIdPendidikan(int idPendidikan){
        this.idPendidikan = idPendidikan;
    }

    public int getIdPendidikan(){
        return idPendidikan;
    }

    public void setOpsiPendidikan(String opsiPendidikan){
        this.opsiPendidikan = opsiPendidikan;
    }

    public String getOpsiPendidikan(){
        return opsiPendidikan;
    }

    @Override
    public String toString(){
        return getOpsiPendidikan();
    }
}
