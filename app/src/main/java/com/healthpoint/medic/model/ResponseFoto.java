package com.healthpoint.medic.model;

import com.google.gson.annotations.SerializedName;

public class ResponseFoto {

    @SerializedName("message")
    private String message;

    @SerializedName("path_foto")
    private String pathFoto;

    @SerializedName("foto_pantau")
    private String fotoPantau;

    @SerializedName("foto_berantas")
    private String fotoBerantas;

    public void setPathFoto(String pathFoto){this.pathFoto = pathFoto;}
    public String getPathFoto(){
        return pathFoto; }

    public void setFotoPantau(String pathFoto){this.fotoPantau = pathFoto;}
    public String getFotoPantau(){
        return fotoPantau; }

    public void setFotoBerantas(String pathFoto){this.fotoBerantas = pathFoto;}
    public String getFotoBerantas(){
        return fotoBerantas; }

    public void setMessage(String message){
        this.message = message;
    }
    public String getMessage(){
        return message;
    }

    @Override
    public String toString(){
        return
                "DaftarIndividuItem{"+
                        "data = '" + pathFoto + '\'' +
                        ",message = '" + message + '\'' +
                        "}";
    }
}
