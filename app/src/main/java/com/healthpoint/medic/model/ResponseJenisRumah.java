package com.healthpoint.medic.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseJenisRumah {
    @SerializedName("data")
    private List<JenisRumah> daftarJenisRumah;

    public List<JenisRumah> getDaftarJenisRumah() {
        return daftarJenisRumah;
    }
}