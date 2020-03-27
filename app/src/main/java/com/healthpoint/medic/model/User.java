package com.healthpoint.medic.model;

public class User {

    private int id;
    private String alamat;
    private String nik;
    private String nip;
    private String name;
    private String password;
    private String username;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setNama(String name) {
        this.name = name;
    }

    public void setNik(String nik){this.nik = nik;}
    public String getNik(){return nik;}

    public void setNip(String nip){this.nip = nip;}
    public String getNip(){return nip;}

    public String getAlamat() {
        return alamat;
    }
    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername(){return username;}
    public void  setUsername(String username){this.username = username;}
}
