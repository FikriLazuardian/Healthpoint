package com.healthpoint.medic.model;


import com.google.gson.annotations.SerializedName;


public class DataItem{

	@SerializedName("rt")
	private int rt;

	@SerializedName("id_catat")
	private Object idCatat;

	@SerializedName("rw")
	private int rw;

	@SerializedName("kode_pos")
	private String kodePos;

	@SerializedName("lon")
	private Object lon;

	@SerializedName("alamat")
	private String alamat;

	@SerializedName("jamban")
	private boolean jamban;

	@SerializedName("ada_saluran_limbah")
	private boolean adaSaluranLimbah;

	@SerializedName("tgl_catat")
	private String tglCatat;

	@SerializedName("no_kk")
	private String noKk;

	@SerializedName("sumber_air")
	private String sumberAir;

	@SerializedName("ada_saluran_sampah")
	private boolean adaSaluranSampah;

	@SerializedName("lat")
	private Object lat;

	@SerializedName("id_kelurahan")
	private String idKelurahan;

	@SerializedName("makanan_pokok")
	private String makananPokok;

	public void setRt(int rt){
		this.rt = rt;
	}

	public int getRt(){
		return rt;
	}

	public void setIdCatat(Object idCatat){
		this.idCatat = idCatat;
	}

	public Object getIdCatat(){
		return idCatat;
	}

	public void setRw(int rw){
		this.rw = rw;
	}

	public int getRw(){
		return rw;
	}

	public void setKodePos(String kodePos){
		this.kodePos = kodePos;
	}

	public String getKodePos(){
		return kodePos;
	}

	public void setLon(Object lon){
		this.lon = lon;
	}

	public Object getLon(){
		return lon;
	}

	public void setAlamat(String alamat){
		this.alamat = alamat;
	}

	public String getAlamat(){
		return alamat;
	}

	public void setJamban(boolean jamban){
		this.jamban = jamban;
	}

	public boolean isJamban(){
		return jamban;
	}

	public void setAdaSaluranLimbah(boolean adaSaluranLimbah){
		this.adaSaluranLimbah = adaSaluranLimbah;
	}

	public boolean isAdaSaluranLimbah(){
		return adaSaluranLimbah;
	}

	public void setTglCatat(String tglCatat){
		this.tglCatat = tglCatat;
	}

	public String getTglCatat(){
		return tglCatat;
	}

	public void setNoKk(String noKk){
		this.noKk = noKk;
	}

	public String getNoKk(){
		return noKk;
	}

	public void setSumberAir(String sumberAir){
		this.sumberAir = sumberAir;
	}

	public String getSumberAir(){
		return sumberAir;
	}

	public void setAdaSaluranSampah(boolean adaSaluranSampah){
		this.adaSaluranSampah = adaSaluranSampah;
	}

	public boolean isAdaSaluranSampah(){
		return adaSaluranSampah;
	}

	public void setLat(Object lat){
		this.lat = lat;
	}

	public Object getLat(){
		return lat;
	}

	public void setIdKelurahan(String idKelurahan){
		this.idKelurahan = idKelurahan;
	}

	public String getIdKelurahan(){
		return idKelurahan;
	}

	public void setMakananPokok(String makananPokok){
		this.makananPokok = makananPokok;
	}

	public String getMakananPokok(){
		return makananPokok;
	}


}