package com.healthpoint.medic.model;

public class ResponseRokokSebulan{
	private int idRokok1bulan;
	private String opsiRokok1bulan;

	public void setIdRokok1bulan(int idRokok1bulan){
		this.idRokok1bulan = idRokok1bulan;
	}

	public int getIdRokok1bulan(){
		return idRokok1bulan;
	}

	public void setOpsiRokok1bulan(String opsiRokok1bulan){
		this.opsiRokok1bulan = opsiRokok1bulan;
	}

	public String getOpsiRokok1bulan(){
		return opsiRokok1bulan;
	}

	@Override
 	public String toString(){
		return 
			"ResponseRokokSebulan{" + 
			"id_rokok1bulan = '" + idRokok1bulan + '\'' + 
			",opsi_rokok1bulan = '" + opsiRokok1bulan + '\'' + 
			"}";
		}
}
