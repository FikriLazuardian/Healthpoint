package com.healthpoint.medic.model;

import com.google.gson.annotations.SerializedName;

public class KunjunganItem {

    @SerializedName("id_kunjungan")
    private String idKunjungan;

    @SerializedName("tgl_kunjungan")
    private String tglKunjungan;

    @SerializedName("n_wadah")
    private int nWadah;

    @SerializedName("n_wadah_jentik")
    private int nJentik;

    @SerializedName("nama_pemilik")
    private String namaPemilik;

    @SerializedName("alamat")
    private String alamat;

    @SerializedName("foto_pantau")
    private String fotoPantau;

    @SerializedName("foto_berantas")
    private String fotoBerantas;

    public void setIdKunjungan (String idKunjungan){this.idKunjungan = idKunjungan;}
    public String getIdKunjungan() {
        return idKunjungan; }

    public void setTglKunjungan (String tglKunjungan){this.tglKunjungan = tglKunjungan;}
    public String getTglKunjungan(){
        return tglKunjungan; }

    public void setNWadah (int nWadah){this.nWadah = nWadah;}
    public int getNWadah() {
        return nWadah; }

    public void setNJentik (int nJentik){this.nJentik = nJentik;}
    public int getNJentik(){
        return nJentik; }

    public String getNamaPemilik() {
        return namaPemilik;
    }
    public void setNamaPemilik(String namaPemilik) { this.namaPemilik = namaPemilik; }

    public void setAlamat(String alamat){
        this.alamat = alamat;
    }
    public String getAlamat(){
        return alamat;
    }

    public void setFotoPantau(String fotoPantau){
        this.fotoPantau = fotoPantau;
    }
    public String getFotoPantau(){
        return fotoPantau;
    }

    public void setFotoBerantas(String fotoBerantas){
        this.fotoBerantas = fotoBerantas;
    }
    public String getFotoBerantas(){
        return fotoBerantas;
    }
}
