package com.healthpoint.medic.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListJenisRw {
    @SerializedName("id_jenis_rumah")
    @Expose
    private Integer idJenisRumah;
    @SerializedName("n_wadah")
    @Expose
    private Integer nWadah;
    @SerializedName("n_wadah_jentik")
    @Expose
    private Integer nWadahJentik;

    public Integer getIdJenisRumah() {
        return idJenisRumah;
    }

    public void setIdJenisRumah(Integer idJenisRumah) {
        this.idJenisRumah = idJenisRumah;
    }

    public Integer getNWadah() {
        return nWadah;
    }

    public void setNWadah(Integer nWadah) {
        this.nWadah = nWadah;
    }

    public Integer getNWadahJentik() {
        return nWadahJentik;
    }

    public void setNWadahJentik(Integer nWadahJentik) {
        this.nWadahJentik = nWadahJentik;
    }

}
