package com.healthpoint.medic.model;

import com.google.gson.annotations.SerializedName;

public class RelasiKK {
    @SerializedName("id_relasi")
    private int idRelasi;
    @SerializedName("opsi_relasi")
    private String opsiRelasi;

    public void setIdRelasi(int idRelasi) {
        this.idRelasi = idRelasi;
    }

    public int getIdRelasi() {
        return idRelasi;
    }

    public void setOpsiRelasi(String opsiRelasi) {
        this.opsiRelasi = opsiRelasi;
    }

    public String getOpsiRelasi() {
        return opsiRelasi;
    }

    @Override
    public String toString() {
        return getOpsiRelasi();
    }
}
