package com.healthpoint.medic.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponsePendidikan {
    @SerializedName("data")
    private List<Pendidikan> daftarPendidikan;

    public List<Pendidikan> getDaftarPendidikan() {
        return daftarPendidikan;
    }
}