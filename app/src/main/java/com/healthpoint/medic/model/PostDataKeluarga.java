package com.healthpoint.medic.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PostDataKeluarga {
    @SerializedName("no_kk")
    @Expose
    private String noKk;
    @SerializedName("alamat")
    @Expose
    private String alamat;
    @SerializedName("rw")
    @Expose
    private String rw;
    @SerializedName("rt")
    @Expose
    private String rt;
    @SerializedName("kode_pos")
    @Expose
    private String kodePos;
    @SerializedName("id_kelurahan")
    @Expose
    private String idKelurahan;
    @SerializedName("makanan_pokok")
    @Expose
    private String makananPokok;
    @SerializedName("sumber_air")
    @Expose
    private String sumberAir;
    @SerializedName("jamban")
    @Expose
    private String jamban;
    @SerializedName("ada_saluran_sampah")
    @Expose
    private String adaSaluranSampah;
    @SerializedName("ada_saluran_limbah")
    @Expose
    private String adaSaluranLimbah;
    @SerializedName("lat")
    @Expose
    private String latitude;
    @SerializedName("lon")
    @Expose
    private String longitude;

    public String getNoKk() {
        return noKk;
    }

    public void setNoKk(String noKk) {
        this.noKk = noKk;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getRw() {
        return rw;
    }

    public void setRw(String rw) {
        this.rw = rw;
    }

    public String getRt() {
        return rt;
    }

    public void setRt(String rt) {
        this.rt = rt;
    }

    public String getKodePos() {
        return kodePos;
    }

    public void setKodePos(String kodePos) {
        this.kodePos = kodePos;
    }

    public String getIdKelurahan() {
        return idKelurahan;
    }

    public void setIdKelurahan(String idKelurahan) {
        this.idKelurahan = idKelurahan;
    }

    public String getMakananPokok() {
        return makananPokok;
    }

    public void setMakananPokok(String makananPokok) {
        this.makananPokok = makananPokok;
    }

    public String getSumberAir() {
        return sumberAir;
    }

    public void setSumberAir(String sumberAir) {
        this.sumberAir = sumberAir;
    }

    public String getJamban() {
        return jamban;
    }

    public void setJamban(String jamban) {
        this.jamban = jamban;
    }

    public String getAdaSaluranSampah() {
        return adaSaluranSampah;
    }

    public void setAdaSaluranSampah(String adaSaluranSampah) {
        this.adaSaluranSampah = adaSaluranSampah;
    }

    public String getAdaSaluranLimbah() {
        return adaSaluranLimbah;
    }

    public void setAdaSaluranLimbah(String adaSaluranLimbah) {
        this.adaSaluranLimbah = adaSaluranLimbah;
    }
    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
