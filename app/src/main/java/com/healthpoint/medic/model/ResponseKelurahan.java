package com.healthpoint.medic.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseKelurahan {
    @SerializedName("data")
    private List<Kelurahan> daftarKelurahan;

    public List<Kelurahan> getDaftarKelurahan() {
        return daftarKelurahan;
    }
}
