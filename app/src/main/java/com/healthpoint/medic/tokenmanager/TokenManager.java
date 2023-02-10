package com.healthpoint.medic.tokenmanager;


import android.content.SharedPreferences;
import android.util.Log;

import com.healthpoint.medic.model.AccessToken;



public class TokenManager {
   private static final String TAG = "TokenManager";
   private SharedPreferences prefs;
   private SharedPreferences.Editor editor;

   private static TokenManager INSTANCE = null;

   private TokenManager(SharedPreferences prefs){
       this.prefs = prefs;
       this.editor = prefs.edit();
   }

    public static synchronized TokenManager getInstance(SharedPreferences prefs){
        if(INSTANCE == null){
            INSTANCE = new TokenManager(prefs);
        }
        return INSTANCE;
    }
   public void saveToken(AccessToken token){
       editor.putString("ACCESS_TOKEN",token.getAccessToken()).commit();
       editor.putString("USERNAME",token.getUsername()).commit();
   }

   public void deleteToken(){
       editor.remove("ACCESS_TOKEN").commit();
       editor.remove("USERNAME").commit();
   }
   public AccessToken getToken(){
       AccessToken token = new AccessToken();
       token.setAccessToken(prefs.getString("ACCESS_TOKEN", null));
       token.setUsername(prefs.getString("USERNAME",null));
       Log.w(TAG,"ACCESS_TOKEN" + token.getAccessToken());
       return token;
   }

}
