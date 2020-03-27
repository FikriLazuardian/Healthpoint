package com.healthpoint.medic;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.healthpoint.medic.model.DataPengguna;
import com.healthpoint.medic.model.ResponseDataPengguna;
import com.healthpoint.medic.network.APIClient;
import com.healthpoint.medic.network.APIInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UbahProfile extends AppCompatActivity {
    Context mContext;
    TextView nip,namaPengguna,status,alamat,no_hp,username;
    Bundle mBundle;
    Button btnBack;
    private APIInterface service;
    DataPengguna dataPengguna;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah_profile);

        mContext=this;
        service = APIClient.createService(APIInterface.class);
        mBundle = getIntent().getExtras();

        nip = (TextView) findViewById(R.id.tvnip);
        namaPengguna = (TextView) findViewById(R.id.tvnama);
        status = (TextView) findViewById(R.id.tvstatus);
        alamat = (TextView) findViewById(R.id.tvalamat);
        no_hp = (TextView) findViewById(R.id.tvnohp);
        username = (TextView) findViewById(R.id.tvusername);
        btnBack = (Button) findViewById(R.id.back);


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent profile = new Intent (mContext,Dashboard.class);
                profile.putExtras(mBundle);
                startActivity(profile);
                finish();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        LoadProfile(mBundle.getInt("id_pengguna"));
    }
    private void LoadProfile(int idPengguna){
        Call<DataPengguna> call = service.getPengguna(idPengguna);
        call.enqueue(new Callback<DataPengguna>() {
            @Override
            public void onResponse(Call<DataPengguna> call, Response<DataPengguna> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                          dataPengguna = response.body();
                          nip.setText(dataPengguna.getNip());
                          namaPengguna.setText(dataPengguna.getNamaPengguna());
                          status.setText(Integer.toString(dataPengguna.getStatus()));
                          alamat.setText(dataPengguna.getAlamat());
                          no_hp.setText(dataPengguna.getNoHp());
                          username.setText(dataPengguna.getUsername());

                            }


                }else{
                    Toast.makeText(mContext, "Data Pengguna belum ada", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<DataPengguna> call, Throwable t) {

            }
        });
    }
}
