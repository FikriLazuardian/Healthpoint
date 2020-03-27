package com.healthpoint.medic.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DaftarRumah {

    @SerializedName("id_kunjungan")
    @Expose
    private Integer idKunjungan;
    @SerializedName("tgl_kunjungan")
    @Expose
    private String tglKunjungan;
    @SerializedName("n_wadah")
    @Expose
    private Integer nWadah;
    @SerializedName("n_wadah_jentik")
    @Expose
    private Integer nWadahJentik;
    @SerializedName("id_rumah")
    @Expose
    private Integer idRumah;
    @SerializedName("id_jenis_rumah")
    @Expose
    private Integer idJenisRumah;
    @SerializedName("id_pengguna")
    @Expose
    private Integer idPengguna;
    @SerializedName("jenis_rumah")
    @Expose
    private String jenisRumah;
    @SerializedName("nama_pemilik")
    @Expose
    private String namaPemilik;
    @SerializedName("alamat")
    @Expose
    private String alamatRumah;


    public Integer getIdKunjungan() {
        return idKunjungan;
    }

    public void setIdKunjungan(Integer idKunjungan) {
        this.idKunjungan = idKunjungan;
    }

    public String getTglKunjungan() {
        return tglKunjungan;
    }

    public void setTglKunjungan(String tglKunjungan) {
        this.tglKunjungan = tglKunjungan;
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

    public Integer getIdRumah() {
        return idRumah;
    }

    public void setIdRumah(Integer idRumah) {
        this.idRumah = idRumah;
    }

    public Integer getIdJenisRumah() {
        return idJenisRumah;
    }

    public void setIdJenisRumah(Integer idJenisRumah) {
        this.idJenisRumah = idJenisRumah;
    }

    public Integer getIdPengguna() {
        return idPengguna;
    }

    public void setIdPengguna(Integer idPengguna) {
        this.idPengguna = idPengguna;
    }

    public String getJenisRumah() {
        return jenisRumah;
    }

    public void setJenisRumah(String jenisRumah) {
        this.jenisRumah = jenisRumah;
    }

    public String getNamaPemilik() {
        return namaPemilik;
    }

    public void setNamaPemilik(String namaPemilik) {
        this.namaPemilik = namaPemilik;
    }


    public String getAlamat() {
        return alamatRumah;
    }
    public void setAlamat(String alamatRumah){
        this.alamatRumah = alamatRumah;
    }
}

