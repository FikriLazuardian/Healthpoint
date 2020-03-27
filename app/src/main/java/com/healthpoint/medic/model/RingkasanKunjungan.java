package com.healthpoint.medic.model;

import com.google.gson.annotations.SerializedName;

public class RingkasanKunjungan {

    @SerializedName("tgl_kunjungan")
    private String tglKunjungan;

    @SerializedName("jumlah_wadah")
    private String jumlahWadah;

    @SerializedName("jumlah_jentik")
    private String jumlahJentik;

    @SerializedName("n_tanpa_jentik")
    private String jumlahNoJentik;

    @SerializedName("rumah_diperiksa")
    private String rumahDiperiksa;

    @SerializedName("persentase_bebas_jentik")
    private int persenBebasJentik;

    public void setTglKunjungan (String tglKunjungan){this.tglKunjungan = tglKunjungan;}
    public String getTglKunjungan(){
        return tglKunjungan; }

    public void setJumlahWadah(String jumlahWadah) { this.jumlahWadah = jumlahWadah; }
    public String getJumlahWadah() {
        return jumlahWadah;
    }

    public void setJumlahJentik(String jumlahJentik){
        this.jumlahJentik = jumlahJentik;
    }
    public String getJumlahJentik(){
        return jumlahJentik;
    }

    public void setJumlahNoJentik(String jumlahNoJentik){
        this.jumlahNoJentik = jumlahNoJentik;
    }
    public String getJumlahNoJentik(){
        return jumlahNoJentik;
    }

    public void setRumahDiperiksa(String rumahDiperiksa){
        this.rumahDiperiksa = rumahDiperiksa;
    }
    public String getRumahDiperiksa(){
        return rumahDiperiksa;
    }

    public void setPersenBebasJentik (int persenBebasJentik){this.persenBebasJentik = persenBebasJentik;}
    public int getPersenBebasJentik() {
        return persenBebasJentik; }
}
