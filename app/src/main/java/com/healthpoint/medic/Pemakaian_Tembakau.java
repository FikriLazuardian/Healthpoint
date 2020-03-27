package com.healthpoint.medic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.healthpoint.medic.model.ResponseRokokSebulan;
import com.healthpoint.medic.network.APIClient;
import com.healthpoint.medic.network.APIInterface;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Pemakaian_Tembakau extends AppCompatActivity {
    Spinner spinRokok;
    Button btnRokok;
    //String[] rokok = {"Ya, Setiap hari", "Ya, Kadang - kadang", "Tidak, Tapi dulu merokok setiap hari", "Tidak, Tapi dulu kadang - kadang", "Tidak pernah sama sekali"};
    //ArrayAdapter<String> adapter;
    private ArrayList<ResponseRokokSebulan> rokokArrayList;
    private ArrayList<String> rokok = new ArrayList<String>();
    APIInterface service;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pemakaian__tembakau);
        initViews();
        service = APIClient.createService(APIInterface.class);
        //adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, rokok);
        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //spinRokok.setAdapter(adapter);
        loadSpinner();
        btnRokok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }
    public void initViews() {
        spinRokok = (Spinner) findViewById(R.id.spinrokok1bln);
        btnRokok = (Button) findViewById(R.id.btnrokok);
    }

   private void loadSpinner() {

       Call<ResponseRokokSebulan> callRokok = service.getAPIRokok();
       callRokok.enqueue(new Callback<ResponseRokokSebulan>() {
           @Override
           public void onResponse(Call<ResponseRokokSebulan> call, Response<ResponseRokokSebulan> response) {

               if (response.isSuccessful()) {
                   if (response.body() != null) {
                      Log.i("OnSuccess", response.body().toString());

                       String jsonresponse = response.body().toString();
                       spinJSONRokok(jsonresponse);

                   } else {
                       Log.i("onEmptyResponse","Returned empty response");
                   }
               }
           }

           @Override
           public void onFailure(Call<ResponseRokokSebulan> call, Throwable t) {
           }
       });
   }
      private void spinJSONRokok(String response)
       {
           try{
               rokokArrayList = new ArrayList<>();
               JSONArray dataArray = new JSONArray(response) ;
               for (int i = 0; i< dataArray.length(); i++){
                   ResponseRokokSebulan responseRokok = new ResponseRokokSebulan();
                   JSONObject dataobj = dataArray.getJSONObject(i);
                   responseRokok.setIdRokok1bulan(dataobj.getInt("id_rokok1bulan"));
                   responseRokok.setOpsiRokok1bulan(dataobj.getString("opsi_rokok1bulan"));

                   rokokArrayList.add(responseRokok);
              }
               for(int i = 0; i < rokokArrayList.size(); i++){
                   rokok.add(rokokArrayList.get(i).getOpsiRokok1bulan().toString());
              }
              ArrayAdapter<String> agamaArrayAdapter = new ArrayAdapter<String>(Pemakaian_Tembakau.this,android.R.layout.simple_spinner_item,rokok);
              agamaArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
              spinRokok.setAdapter(agamaArrayAdapter);

           }catch(JSONException e){

           }
       }


}
