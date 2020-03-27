package com.healthpoint.medic.model;

import com.google.gson.annotations.SerializedName;

public class PostResponse {

    @SerializedName("message")
    private String message;

    public void setMessage(String message){
        this.message = message;
    }
    public String getMessage(){
        return message;
    }
}
