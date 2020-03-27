package com.healthpoint.medic.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseJkn {
    @SerializedName("data")
    private List<Jkn> daftarJkn;

    public List<Jkn> getDaftarJkn() {
        return daftarJkn;
    }
}
