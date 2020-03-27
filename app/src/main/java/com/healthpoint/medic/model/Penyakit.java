package com.healthpoint.medic.model;

public class Penyakit {
    private int id_sakit;
    private int nik;
    private String nama;
    private String batuk;
    private String gejala_batuk;
    private String terdiagnosa_tb;
    private String periksa_dokter;
    private String cara_pemeriksaan;
    private String konsumsi_obat;
    private String terdiagnosa_sembuh;

    public int getId_sakit() {
        return id_sakit;
    }
    public void setId_sakit(int id_sakit) {
        this.id_sakit = id_sakit;
    }

    public int getNik() { return nik; }
    public void setNik(int nik) {
        this.nik = nik;
    }

    public String getNama() {
        return nama;
    }
    public void setNama(String email) {
        this.nama = nama;
    }

    public String getBatuk() {
        return batuk;
    }
    public void setBatuk(String password) { this.batuk = batuk; }

    public String getGejala_batuk() {
        return gejala_batuk;
    }
    public void setGejala_batuk(String gejala_batuk) { this.gejala_batuk = gejala_batuk; }

    public String getTerdiagnosa_tb() {
        return terdiagnosa_tb;
    }
    public void setTerdiagnosa_tb(String terdiagnosa_tb) { this.terdiagnosa_tb = terdiagnosa_tb; }

    public String getPeriksa_dokter() {
        return periksa_dokter;
    }
    public void setPeriksa_dokter(String periksa_dokter) { this.periksa_dokter = periksa_dokter; }

    public String getCara_pemeriksaan() {
        return cara_pemeriksaan;
    }
    public void setCara_pemeriksaan(String password) { this.cara_pemeriksaan = cara_pemeriksaan; }

    public String getKonsumsi_obat() {
        return konsumsi_obat;
    }
    public void setKonsumsi_obat(String password) { this.konsumsi_obat = konsumsi_obat; }

    public String getTerdiagnosa_sembuh() {
        return terdiagnosa_sembuh;
    }
    public void setTerdiagnosa_sembuh(String terdiagnosa_sembuh) { this.terdiagnosa_sembuh = terdiagnosa_sembuh; }
}
