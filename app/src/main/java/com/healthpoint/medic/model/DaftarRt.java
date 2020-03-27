package com.healthpoint.medic.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DaftarRt {
    @SerializedName("rt")
    @Expose
    private Integer rt;
    @SerializedName("jumlah_wadah")
    @Expose
    private String jumlahWadah;
    @SerializedName("jumlah_jentik")
    @Expose
    private String jumlahJentik;
    @SerializedName("rumah_diperiksa")
    @Expose
    private String rumahDiperiksa;
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
    private List<DaftarRumahKoor> daftarRumah = null;
    @SerializedName("rekap_perjenis")
    @Expose
    private List<RekapPerjeni> rekapPerjenis = null;

    public Integer getRt() {
        return rt;
    }

    public void setRt(Integer rt) {
        this.rt = rt;
    }

    public String getJumlahWadah() {
        return jumlahWadah;
    }

    public void setJumlahWadah(String jumlahWadah) {
        this.jumlahWadah = jumlahWadah;
    }

    public String getJumlahJentik() {
        return jumlahJentik;
    }

    public void setJumlahJentik(String jumlahJentik) {
        this.jumlahJentik = jumlahJentik;
    }

    public String getRumahDiperiksa() {
        return rumahDiperiksa;
    }

    public void setRumahDiperiksa(String rumahDiperiksa) {
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

    public List<DaftarRumahKoor> getDaftarRumah() {
        return daftarRumah;
    }

    public void setDaftarRumah(List<DaftarRumahKoor> daftarRumah) {
        this.daftarRumah = daftarRumah;
    }

    public List<RekapPerjeni> getRekapPerjenis() {
        return rekapPerjenis;
    }

    public void setRekapPerjenis(List<RekapPerjeni> rekapPerjenis) {
        this.rekapPerjenis = rekapPerjenis;
    }

}
