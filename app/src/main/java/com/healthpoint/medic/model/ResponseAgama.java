package com.healthpoint.medic.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseAgama {
    @SerializedName("data")
    private List<Agama> daftarAgama;

    public List<Agama> getDaftarAgama() {
        return daftarAgama;
    }
}