package com.healthpoint.medic.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PostDataJumantik {
    @SerializedName("lat")
    @Expose
    private String lat;
    @SerializedName("lon")
    @Expose
    private String lon;
    @SerializedName("alamat")
    @Expose
    private String alamat;
    @SerializedName("rw")
    @Expose
    private String rw;
    @SerializedName("rt")
    @Expose
    private String rt;
    @SerializedName("id_kelurahan")
    @Expose
    private String idKelurahan;
    @SerializedName("rumah_umum")
    @Expose
    private String rumahUmum;
    @SerializedName("in_outdoor")
    @Expose
    private String inOutdoor;
    @SerializedName("n_wadah")
    @Expose
    private String nWadah;
    @SerializedName("ada_jentik")
    @Expose
    private String adaJentik;
    @SerializedName("tgl_visit")
    @Expose
    private String tglVisit;

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getRt() {
        return rt;
    }

    public void setRt(String rt) {
        this.rt = rt;
    }

    public String getRw() {
        return rw;
    }

    public void setRw(String rw) {
        this.rw = rw;
    }

    public String getIdKelurahan() {
        return idKelurahan;
    }

    public void setIdKelurahan(String idKelurahan) {
        this.idKelurahan = idKelurahan;
    }

    public String getRumahUmum() {
        return rumahUmum;
    }

    public void setRumahUmum(String rumahUmum) {
        this.rumahUmum = rumahUmum;
    }

    public String getInOutdoor() {
        return inOutdoor;
    }

    public void setInOutdoor(String inOutdoor) {
        this.inOutdoor = inOutdoor;
    }

    public String getNWadah() {
        return nWadah;
    }

    public void setNWadah(String nWadah) {
        this.nWadah = nWadah;
    }

    public String getAdaJentik() {
        return adaJentik;
    }

    public void setAdaJentik(String adaJentik) {
        this.adaJentik = adaJentik;
    }

    public String getTglVisit() {
        return tglVisit;
    }

    public void SetTglVisit(String tglVisit) {
        this.tglVisit = tglVisit;
    }
}

