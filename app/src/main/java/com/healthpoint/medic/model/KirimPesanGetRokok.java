package com.healthpoint.medic.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class KirimPesanGetRokok {

    public ArrayList<KirimPesanGetRokok.SpinRokok> getSpinRokokArrayList() {
        return spinRokokArrayList;
    }

    public void setSpinRokokArrayList(ArrayList<KirimPesanGetRokok.SpinRokok> couponsListingModalArrayList) {
        this.spinRokokArrayList = couponsListingModalArrayList;
    }


    private ArrayList<KirimPesanGetRokok.SpinRokok> spinRokokArrayList;
    public class SpinRokok {
        public String getId_rokok1bulan() {
            return Id_rokok1bulan;
        }

        public void setId_rokok1bulan(String id_rokok1bulan) {
            Id_rokok1bulan = id_rokok1bulan;
        }

        public String getOpsi_rokok1bulan() {
            return Opsi_rokok1bulan;
        }

        public void setOpsi_rokok1bulan(String opsi_rokok1bulan) {
            Opsi_rokok1bulan = opsi_rokok1bulan;
        }

        @SerializedName("Id_rokok1bulan")
        private String Id_rokok1bulan;

        @SerializedName("Opsi_rokok1bulan")
        private String Opsi_rokok1bulan;

    }
}
