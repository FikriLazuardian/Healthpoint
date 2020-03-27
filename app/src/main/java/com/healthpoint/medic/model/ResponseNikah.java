package com.healthpoint.medic.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseNikah {
    @SerializedName("data")
    private List<StatusNikah> daftarStatusNikah;

    public List<StatusNikah> getDaftarStatusNikah() {
        return daftarStatusNikah;
    }
}