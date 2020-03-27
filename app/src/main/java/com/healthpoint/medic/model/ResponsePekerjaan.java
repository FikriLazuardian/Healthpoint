package com.healthpoint.medic.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponsePekerjaan {
    @SerializedName("data")
    private List<Pekerjaan> daftarPekerjaan;

    public List<Pekerjaan> getDaftarPekerjaan() {
        return daftarPekerjaan;
    }
}