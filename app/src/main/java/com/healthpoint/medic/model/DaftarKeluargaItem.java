package com.healthpoint.medic.model;

import com.google.gson.annotations.SerializedName;

public class DaftarKeluargaItem {

    @SerializedName("no_kk")
    private String noKK;

    @SerializedName("nama_kk")
    private String namaKK;

    @SerializedName("alamat")
    private String alamat;

    @SerializedName("tgl_catat")
    private String tglCatat;

    @SerializedName("n_anggota")
    private int nAnggota;

    public void setNoKK(String noKK){
        this.noKK = noKK;
    }
    public String getNoKK(){
        return noKK;
    }

    public void setNamaKK(String namaKK){
        this.noKK = namaKK;
    }
    public String getNamaKK(){
        return namaKK;
    }

    public void setAlamat(String alamat){
        this.alamat = alamat;
    }
    public String getAlamat(){
        return alamat;
    }

    public void setTglCatat(String tglCatat){
        this.tglCatat = tglCatat;
    }
    public String getTglCatat(){
        return tglCatat;
    }

    public void setNAnggota(int nAnggota){
        this.nAnggota = nAnggota;
    }
    public int getNAnggota(){
        return nAnggota;
    }

    @Override
    public String toString(){
        return
                "DaftarKeluargaItem{"+
                        "no_kk = '" + noKK + '\'' +
                        ",alamat = '" + alamat + '\'' +
                        "}";
    }
}
