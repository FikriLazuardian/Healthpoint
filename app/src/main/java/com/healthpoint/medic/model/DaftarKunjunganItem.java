package com.healthpoint.medic.model;

import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DaftarKunjunganItem {

    @SerializedName("periode")
    private String periode;

    @SerializedName("tanggal_senin")
    private String tanggal_senin;

    @SerializedName("tanggal_minggu")
    private String tanggal_minggu;

    public void setPeriode(String periode){
        this.periode = periode;
    }
    public String getPeriode(){
        return periode;
    }

    public void setTanggal_senin(String tanggal_senin){
        this.tanggal_senin = tanggal_senin;
    }
    public String getTanggal_senin(){
        return tanggal_senin;
    }

    public void setTanggal_minggu(String tanggal_minggu){
        this.tanggal_minggu = tanggal_minggu;
    }
    public String getTanggal_minggu(){
        return tanggal_minggu;
    }

    public String getFormattedTanggal(){
        return convertDateFormat(tanggal_senin)+"- "+convertDateFormat(tanggal_minggu);
    }

    @Override
    public String toString(){
        return getFormattedTanggal();
    }

    private String convertDateFormat(String inputDateString) {
        Date date = null;
        String outputDateString = null;

        SimpleDateFormat ymdFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        SimpleDateFormat dmyFormat = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());

        try {
            date = ymdFormat.parse(inputDateString);
            outputDateString = dmyFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return outputDateString;
    }
}
