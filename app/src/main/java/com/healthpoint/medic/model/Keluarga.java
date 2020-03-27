package com.healthpoint.medic.model;

public class Keluarga {
    private String no_kk;
    private String rw;
    private String rt;
    private int no_rmh;
    private int kode_pos;
    private String sumber_air;
    private String jamban;
    private String saluran_pembuangansmph;
    private String saluran_pembuanganair;

    public String getNo_kk() {
        return no_kk;
    }
    public void setNo_kk(String no_kk) {
        this.no_kk = no_kk;
    }

    public String getRw() {
        return rw;
    }
    public void setRw(String rw) { this.rw = rw; }

    public String getRt() {
        return rt;
    }
    public void setRt(String rt) { this.rt = rt; }

    public int getNo_rmh() {
        return no_rmh;
    }
    public void setNo_rmh(int no_rmh) { this.no_rmh = no_rmh; }

    public int getKode_pos() {
        return kode_pos;
    }
    public void setKode_pos(int kode_pos) { this.kode_pos = kode_pos; }

    public String getSumber_air() {
        return sumber_air;
    }
    public void setSumber_air(String sumber_air) { this.sumber_air = sumber_air; }

    public String getJamban() {
        return jamban;
    }
    public void setJamban(String jamban) { this.jamban = jamban; }

    public String getSaluran_pembuangansmph() {
        return saluran_pembuangansmph;
    }
    public void setSaluran_pembuangansmph(String saluran_pembuangansmph) { this.saluran_pembuangansmph = saluran_pembuangansmph; }

    public String getSaluran_pembuanganair() {
        return saluran_pembuanganair;
    }
    public void setSaluran_pembuanganair(String saluran_pembuanganair) { this.saluran_pembuanganair = saluran_pembuanganair; }

}
