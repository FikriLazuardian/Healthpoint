package com.healthpoint.medic.model;

import com.google.gson.annotations.SerializedName;

import java.util.Comparator;

public class DaftarRumahItem {

    @SerializedName("id_rumah")
    private int idRumah;

    @SerializedName("lat")
    private double lat;

    @SerializedName("lon")
    private double lon;

    @SerializedName("alamat")
    private String alamat;

    @SerializedName("rt")
    private int rt;

    @SerializedName("rw")
    private int rw;

    @SerializedName("id_kelurahan")
    private String idKelurahan;

    @SerializedName("nama_pemilik")
    private String namaPemilik;

    @SerializedName("id_kunjungan")
    private int idKunjungan;

    @SerializedName("id_jenis_rumah")
    private int idJenisRumah;

    @SerializedName("n_wadah")
    private int jmlhWadah;

    @SerializedName("n_wadah_jentik")
    private int jmlhWadahJentik;

    public void setIDrumah(int idRumah){
        this.idRumah = idRumah;
    }
    public int getIDrumah(){
        return idRumah;
    }

    public void setLat(double lat){
        this.lat = lat;
    }
    public double getLat(){
        return lat;
    }

    public void setLon(double lon){
        this.lon = lon;
    }
    public double getLon(){
        return lon;
    }

    public void setAlamat(String alamat){
        this.alamat = alamat;
    }
    public String getAlamat(){
        return alamat;
    }

    public int getRt() {
        return rt;
    }
    public void setRt(int rt) { this.rt = rt; }

    public int getRw() {
        return rw;
    }
    public void setRw(int rw) { this.rw = rw; }

    public String getNamaPemilik() {
        return namaPemilik;
    }
    public void setNamaPemilik(String namaPemilik) { this.namaPemilik = namaPemilik; }

    public void setIdKunjungan(int idKunjungan){
        this.idKunjungan = idKunjungan;
    }
    public int getIdKunjungan(){
        return idKunjungan;
    }

    public void setIdJenisRumah(int idJenisRumah){this.idJenisRumah = idJenisRumah;}
    public int getIdJenisRumah(){return idJenisRumah;}

    public void setJmlhWadah(int jmlhWadah){this.jmlhWadah=jmlhWadah;}
    public int getJmlhWadah(){return jmlhWadah;}

    public void setJmlhWadahJentik(int jmlhWadahJentik){this.jmlhWadahJentik = jmlhWadahJentik;}
    public int getJmlhWadahJentik(){return jmlhWadahJentik;}

    @Override
    public String toString(){
        return alamat;
    }

    public static final Comparator<DaftarRumahItem> BY_NAME_PEMILIK_ASCENDING = new Comparator<DaftarRumahItem>() {
        @Override
        public int compare(DaftarRumahItem daftarRumahItem, DaftarRumahItem t1) {
            return String.valueOf(daftarRumahItem.namaPemilik).compareTo(String.valueOf(t1.namaPemilik));
        }

    };

    public static final Comparator<DaftarRumahItem> BY_NAME_PEMILIK_DESCENDING = new Comparator<DaftarRumahItem>() {
        @Override
        public int compare(DaftarRumahItem daftarRumahItem, DaftarRumahItem t2) {

            return String.valueOf(t2.namaPemilik).compareTo(String.valueOf(daftarRumahItem.namaPemilik));
        }
    };

    public static final Comparator<DaftarRumahItem> BY_ALAMAT_ASCENDING = new Comparator<DaftarRumahItem>() {
        @Override
        public int compare(DaftarRumahItem daftarRumahItem, DaftarRumahItem a2) {

            return String.valueOf(daftarRumahItem.alamat).compareTo(String.valueOf(a2.alamat));
        }
    };
    public static final Comparator<DaftarRumahItem> BY_ALAMAT_DESCENDING = new Comparator<DaftarRumahItem>() {
        @Override
        public int compare(DaftarRumahItem daftarRumahItem, DaftarRumahItem t2) {

            return String.valueOf(t2.alamat).compareTo(String.valueOf(daftarRumahItem.alamat));
        }
    };
}
