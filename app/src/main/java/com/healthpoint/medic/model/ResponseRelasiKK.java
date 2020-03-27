package com.healthpoint.medic.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseRelasiKK {
    @SerializedName("data")
    private List<RelasiKK> daftarRelasiKK;

    public List<RelasiKK> getDaftarRelasiKK() {
        return daftarRelasiKK;
    }
}