package com.healthpoint.medic.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DataKader {
    @SerializedName("tgl_senin")
    @Expose
    private String tglSenin;
    @SerializedName("tgl_minggu")
    @Expose
    private String tglMinggu;
    @SerializedName("jumlah_wadah")
    @Expose
    private Integer jumlahWadah;
    @SerializedName("jumlah_jentik")
    @Expose
    private Integer jumlahJentik;
    @SerializedName("rumah_diperiksa")
    @Expose
    private Integer rumahDiperiksa;
    @SerializedName("n_tanpa_jentik")
    @Expose
    private Integer nTanpaJentik;
    @SerializedName("persentase")
    @Expose
    private Integer persentase;
    @SerializedName("persentase_bebas_jentik")
    @Expose
    private Integer persentaseBebasJentik;
    @SerializedName("daftar_rumah")
    @Expose
    private List<DaftarRumah> daftarRumah = null;
    @SerializedName("rekap_perjenisrumah")
    @Expose
    private List<RekapPerjenisrumah> rekapPerjenisrumah = null;

    public String getTglSenin() {
        return tglSenin;
    }

    public void setTglSenin(String tglSenin) {
        this.tglSenin = tglSenin;
    }

    public String getTglMinggu() {
        return tglMinggu;
    }

    public void setTglMinggu(String tglMinggu) {
        this.tglMinggu = tglMinggu;
    }

    public Integer getJumlahWadah() {
        return jumlahWadah;
    }

    public void setJumlahWadah(Integer jumlahWadah) {
        this.jumlahWadah = jumlahWadah;
    }

    public Integer getJumlahJentik() {
        return jumlahJentik;
    }

    public void setJumlahJentik(Integer jumlahJentik) {
        this.jumlahJentik = jumlahJentik;
    }

    public Integer getRumahDiperiksa() {
        return rumahDiperiksa;
    }

    public void setRumahDiperiksa(Integer rumahDiperiksa) {
        this.rumahDiperiksa = rumahDiperiksa;
    }

    public Integer getNTanpaJentik() {
        return nTanpaJentik;
    }

    public void setNTanpaJentik(Integer nTanpaJentik) {
        this.nTanpaJentik = nTanpaJentik;
    }

    public Integer getPersentase() {
        return persentase;
    }

    public void setPersentase(Integer persentase) {
        this.persentase = persentase;
    }

    public Integer getPersentaseBebasJentik() {
        return persentaseBebasJentik;
    }

    public void setPersentaseBebasJentik(Integer persentaseBebasJentik) {
        this.persentaseBebasJentik = persentaseBebasJentik;
    }

    public List<DaftarRumah> getDaftarRumah() {
        return daftarRumah;
    }

    public void setDaftarRumah(List<DaftarRumah> daftarRumah) {
        this.daftarRumah = daftarRumah;
    }

    public List<RekapPerjenisrumah> getRekapPerjenisrumah() {
        return rekapPerjenisrumah;
    }

    public void setRekapPerjenisrumah(List<RekapPerjenisrumah> rekapPerjenisrumah) {
        this.rekapPerjenisrumah = rekapPerjenisrumah;
    }
}
