package com.healthpoint.medic.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Anggota_Keluarga {
    @SerializedName("nik")
    @Expose
    private String nik;
    @SerializedName("nama")
    @Expose
    private String nama;
    @SerializedName("tempat_lahir")
    @Expose
    private String tempat_lahir;
    @SerializedName("tanggal_lahir")
    @Expose
    private String tanggal_lahir;
    @SerializedName("jenis_kelamin")
    @Expose
    private String jenis_kelamin;
    @SerializedName("umur")
    @Expose
    private int umur;
    @SerializedName("agama")
    @Expose
    private String agama;
    @SerializedName("status_kawin")
    @Expose
    private String status_kawin;
    @SerializedName("hubungan_kk")
    @Expose
    private String hubungan_kk;
    @SerializedName("pendidikan")
    @Expose
    private String pendidikan;
    @SerializedName("pekerjaan")
    @Expose
    private String pekerjaan;
    @SerializedName("jkn")
    @Expose
    private String jkn;
    @SerializedName("hamil")
    @Expose
    private String hamil;
    @SerializedName("disabilitas")
    @Expose
    private String disabilitas;


    public String getNik() {
        return nik;
    }
    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getNama() { return nama; }
    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTempat_lahir() {
        return tempat_lahir;
    }
    public void setTempat_lahir(String tempat_lahir) { this.tempat_lahir = tempat_lahir; }

    public String getTanggal_lahir() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }
    public void setTanggal_lahir(String tanggal_lahir) { this.tanggal_lahir = tanggal_lahir; }

    public String getJenis_kelamin() {
        return jenis_kelamin;
    }
    public void setJenis_kelamin(String jenis_kelamin) { this.jenis_kelamin = jenis_kelamin; }

    public int getUmur() {
        return umur;
    }
    public void setUmur(int umur) { this.umur = umur; }

    public String getAgama() {
        return agama;
    }
    public void setAgama(String agama) { this.agama = agama; }

    public String getStatus_kawin() {
        return status_kawin;
    }
    public void setStatus_kawin(String status_kawin) { this.status_kawin = status_kawin; }

    public String getHubungan_kk() {
        return hubungan_kk;
    }
    public void setHubungan_kk(String hubungan_kk) { this.hubungan_kk = hubungan_kk; }

    public String getPendidikan() {
        return pendidikan;
    }
    public void setPendidikan(String pendidikan) { this.pendidikan = pendidikan; }

    public String getPekerjaan() {
        return pekerjaan;
    }
    public void setPekerjaan(String pekerjaan) { this.pekerjaan = pekerjaan; }

    public String getJkn() {
        return jkn;
    }
    public void setJkn(String jkn) { this.jkn = jkn; }

    public String getHamil() {
        return hamil;
    }
    public void setHamil(String hamil) { this.hamil = hamil; }

    public String getDisabilitas(){return disabilitas;}
    public void setDisabilitas(String disabilitas){this.disabilitas = disabilitas;}
    @Override
    public String toString() {
        return "Post{" +
                "nik='" + nik +
                ", nama='" + nama +
                ", tempat_lahir=" + tempat_lahir +
                ", tanggal_lahir=" + tanggal_lahir +
                ",jenis_kelamin="+ jenis_kelamin +
                ",umur="+ umur +
                ",agama="+ agama +
                ",status_kawin="+ status_kawin +
                ",hubungan_kk="+ hubungan_kk +
                ",pendidikan="+ pendidikan +
                ",pekerjaan="+ pekerjaan +
                ",jkn="+ jkn +
                ",hamil="+ hamil +
                ",disabilitas="+ disabilitas +
                '}';
}
}
