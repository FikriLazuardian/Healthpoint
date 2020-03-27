package com.healthpoint.medic.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PostDataAnggota_Keluarga {
    @SerializedName("nik")
    @Expose
    private String nik;
    @SerializedName("no_kk")
    @Expose
    private String noKk;
    @SerializedName("nama")
    @Expose
    private String nama;
    @SerializedName("tgl_lahir")
    @Expose
    private String tglLahir;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("id_relasi")
    @Expose
    private Integer idRelasi;
    @SerializedName("id_agama")
    @Expose
    private Integer idAgama;
    @SerializedName("id_pendidikan")
    @Expose
    private Integer idPendidikan;
    @SerializedName("id_pekerjaan")
    @Expose
    private Integer idPekerjaan;
    @SerializedName("id_status_kawin")
    @Expose
    private Integer idStatusKawin;
    @SerializedName("id_jkn")
    @Expose
    private Integer idJkn;
    @SerializedName("sedang_hamil")
    @Expose
    private String sedangHamil;
    @SerializedName("disabilitas")
    @Expose
    private String disabilitas;
    @SerializedName("id_cucitangan")
    @Expose
    private Integer idCucitangan;
    @SerializedName("id_lokasi_bab")
    @Expose
    private Integer idLokasiBab;
    @SerializedName("id_sikat_gigi")
    @Expose
    private Integer idSikatGigi;
    @SerializedName("id_waktu_sikatgigi")
    @Expose
    private Integer idWaktuSikatgigi;
    @SerializedName("id_rokok_1bulanterakhir")
    @Expose
    private Integer idRokok1bulanterakhir;
    @SerializedName("usia_mulai_rokok")
    @Expose
    private Integer usiaMulaiRokok;
    @SerializedName("isian1")
    @Expose
    private Integer isian1;
    @SerializedName("isian2")
    @Expose
    private Integer isian2;
    @SerializedName("id_ada_batuk")
    @Expose
    private Integer idAdaBatuk;
    @SerializedName("id_gejala_batuk")
    @Expose
    private Integer idGejalaBatuk;
    @SerializedName("id_pernah_diagnosa_tb")
    @Expose
    private Integer idPernahDiagnosaTb;
    @SerializedName("sudah_dokter_periksa")
    @Expose
    private Boolean sudahDokterPeriksa;
    @SerializedName("jenis_periksa")
    @Expose
    private String jenisPeriksa;
    @SerializedName("konsumsi_obat_tb")
    @Expose
    private String konsumsiObatTb;
    @SerializedName("id_status_sembuh")
    @Expose
    private Integer idStatusSembuh;

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getNoKk() {
        return noKk;
    }

    public void setNoKk(String noKk) {
        this.noKk = noKk;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTglLahir() {
        return tglLahir;
    }

    public void setTglLahir(String tglLahir) {
        this.tglLahir = tglLahir;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getIdRelasi() {
        return idRelasi;
    }

    public void setIdRelasi(Integer idRelasi) {
        this.idRelasi = idRelasi;
    }

    public Integer getIdAgama() {
        return idAgama;
    }

    public void setIdAgama(Integer idAgama) {
        this.idAgama = idAgama;
    }

    public Integer getIdPendidikan() {
        return idPendidikan;
    }

    public void setIdPendidikan(Integer idPendidikan) {
        this.idPendidikan = idPendidikan;
    }

    public Integer getIdPekerjaan() {
        return idPekerjaan;
    }

    public void setIdPekerjaan(Integer idPekerjaan) {
        this.idPekerjaan = idPekerjaan;
    }

    public Integer getIdStatusKawin() {
        return idStatusKawin;
    }

    public void setIdStatusKawin(Integer idStatusKawin) {
        this.idStatusKawin = idStatusKawin;
    }

    public Integer getIdJkn() {
        return idJkn;
    }

    public void setIdJkn(Integer idJkn) {
        this.idJkn = idJkn;
    }

    public String getSedangHamil() {
        return sedangHamil;
    }

    public void setSedangHamil(String sedangHamil) {
        this.sedangHamil = sedangHamil;
    }

    public String getDisabilitas() {
        return disabilitas;
    }

    public void setDisabilitas(String disabilitas) {
        this.disabilitas = disabilitas;
    }

    public Integer getIdCucitangan() {
        return idCucitangan;
    }

    public void setIdCucitangan(Integer idCucitangan) {
        this.idCucitangan = idCucitangan;
    }

    public Integer getIdLokasiBab() {
        return idLokasiBab;
    }

    public void setIdLokasiBab(Integer idLokasiBab) {
        this.idLokasiBab = idLokasiBab;
    }

    public Integer getIdSikatGigi() {
        return idSikatGigi;
    }

    public void setIdSikatGigi(Integer idSikatGigi) {
        this.idSikatGigi = idSikatGigi;
    }

    public Integer getIdWaktuSikatgigi() {
        return idWaktuSikatgigi;
    }

    public void setIdWaktuSikatgigi(Integer idWaktuSikatgigi) {
        this.idWaktuSikatgigi = idWaktuSikatgigi;
    }

    public Integer getIdRokok1bulanterakhir() {
        return idRokok1bulanterakhir;
    }

    public void setIdRokok1bulanterakhir(Integer idRokok1bulanterakhir) {
        this.idRokok1bulanterakhir = idRokok1bulanterakhir;
    }

    public Integer getUsiaMulaiRokok() {
        return usiaMulaiRokok;
    }

    public void setUsiaMulaiRokok(Integer usiaMulaiRokok) {
        this.usiaMulaiRokok = usiaMulaiRokok;
    }

    public Integer getIsian1() {
        return isian1;
    }

    public void setIsian1(Integer isian1) {
        this.isian1 = isian1;
    }

    public Integer getIsian2() {
        return isian2;
    }

    public void setIsian2(Integer isian2) {
        this.isian2 = isian2;
    }

    public Integer getIdAdaBatuk() {
        return idAdaBatuk;
    }

    public void setIdAdaBatuk(Integer idAdaBatuk) {
        this.idAdaBatuk = idAdaBatuk;
    }

    public Integer getIdGejalaBatuk() {
        return idGejalaBatuk;
    }

    public void setIdGejalaBatuk(Integer idGejalaBatuk) {
        this.idGejalaBatuk = idGejalaBatuk;
    }

    public Integer getIdPernahDiagnosaTb() {
        return idPernahDiagnosaTb;
    }

    public void setIdPernahDiagnosaTb(Integer idPernahDiagnosaTb) {
        this.idPernahDiagnosaTb = idPernahDiagnosaTb;
    }

    public Boolean getSudahDokterPeriksa() {
        return sudahDokterPeriksa;
    }

    public void setSudahDokterPeriksa(Boolean sudahDokterPeriksa) {
        this.sudahDokterPeriksa = sudahDokterPeriksa;
    }

    public String getJenisPeriksa() {
        return jenisPeriksa;
    }

    public void setJenisPeriksa(String jenisPeriksa) {
        this.jenisPeriksa = jenisPeriksa;
    }

    public String getKonsumsiObatTb() {
        return konsumsiObatTb;
    }

    public void setKonsumsiObatTb(String konsumsiObatTb) {
        this.konsumsiObatTb = konsumsiObatTb;
    }

    public Integer getIdStatusSembuh() {
        return idStatusSembuh;
    }

    public void setIdStatusSembuh(Integer idStatusSembuh) {
        this.idStatusSembuh = idStatusSembuh;
    }
}
