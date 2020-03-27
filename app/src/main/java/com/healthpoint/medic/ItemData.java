package com.healthpoint.medic;

public class ItemData {
    private String Namamenu;
    private int Imagemenu;

    public ItemData(String Namamenu, int Imagemenu){
        this.Namamenu = Namamenu;
        this.Imagemenu = Imagemenu;
    }

    String getNamamenu(){return Namamenu;}

    int getImagemenu(){
        return Imagemenu;
    }
}
