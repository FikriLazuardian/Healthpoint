package com.healthpoint.medic.model;

import com.google.gson.annotations.SerializedName;

public class KunjunganPengguna {

    @SerializedName("jumlah_kunjungan")
    private int jumlahKunjungan;

    @SerializedName("tanggal_senin")
    private String tanggalSenin;

    @SerializedName("tanggal_minggu")
    private String tanggalMinggu;

    public void setJumlahKunjungan(int jumlahKunjungan){this.jumlahKunjungan = jumlahKunjungan;}
    public int getJumlahKunjungan(){
        return jumlahKunjungan; }

    public void setTanggalSenin(String tanggalSenin){
        this.tanggalSenin = tanggalSenin;
    }
    public String getTanggalSenin(){
        return tanggalSenin;
    }

    public void setTanggalMinggu(String tanggalMinggu){
        this.tanggalMinggu = tanggalMinggu;
    }
    public String getTanggalMinggu(){
        return tanggalMinggu;
    }
}
