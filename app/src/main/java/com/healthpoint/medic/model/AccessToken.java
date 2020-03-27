package com.healthpoint.medic.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AccessToken {
    @SerializedName("user")
    @Expose
    private String username;

    @SerializedName("id_pengguna")
    @Expose
    private int id_pengguna;

    @SerializedName("type")
    @Expose
    private String tokenType;

    @SerializedName("expires_in")
    @Expose
    private int expiresIn;

   @SerializedName("token")
   @Expose
   private String accessToken;

    @SerializedName("refreshToken")
    @Expose
    private String refreshToken;

    @SerializedName("message")
    private String message;

    public String getUsername(){return username;}
    public void setUsername(String username){this.username = username;}

    public int getIdPengguna(){return id_pengguna;}
    public void setIdPengguna(int id_pengguna){this.id_pengguna = id_pengguna;}

    public String getTokenType() {
        return tokenType;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public void setMessage(String message){
        this.message = message;
    }
    public String getMessage(){
        return message;
    }

}
