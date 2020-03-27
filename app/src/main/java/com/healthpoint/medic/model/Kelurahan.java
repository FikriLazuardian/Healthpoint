package com.healthpoint.medic.model;

import com.google.gson.annotations.SerializedName;

public class Kelurahan {
    @SerializedName("id_kelurahan")
    private String idKelurahan;

    @SerializedName("nama_kelurahan")
    private String namaKelurahan;

    public String getIdKelurahan() {
        return idKelurahan;
    }

    public void setIdKelurahan(String idKelurahan) {
        this.idKelurahan = idKelurahan;
    }

    public String getNamaKelurahan() {
        return namaKelurahan;
    }

    public void setNamaKelurahan(String namaKelurahan) {
        this.namaKelurahan = namaKelurahan;
    }

    @Override
    public String toString() {
        return getNamaKelurahan();
    }
}