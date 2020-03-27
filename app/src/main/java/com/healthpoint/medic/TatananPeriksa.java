package com.healthpoint.medic;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.healthpoint.medic.model.KunjunganItem;
import com.healthpoint.medic.model.ResponseDetilKunjungan;
import com.healthpoint.medic.network.APIClient;
import com.healthpoint.medic.network.APIInterface;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TatananPeriksa extends AppCompatActivity {

    private ImageView ivPantau, ivBerantas;
    private TextView tvPemilik, tvAlamat, tvWadah, tvJentik, tvRT;
    private Context mContext;
    Button btnBack;
    private Bundle mBundle;
    private APIInterface service;
    private String idKunjungan;
    AlertDialog.Builder builder;
    AlertDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tatanan_periksa);

        mContext = this;
        service = APIClient.createService(APIInterface.class);

        ivPantau = (ImageView) findViewById(R.id.ivPantau);
        ivBerantas = (ImageView) findViewById(R.id.ivBerantas);
        tvPemilik = (TextView) findViewById(R.id.tvPemilik);
        tvAlamat = (TextView) findViewById(R.id.tvAlamat);
        tvWadah = (TextView) findViewById(R.id.tvWadah);
        tvJentik = (TextView) findViewById(R.id.tvJentik);
        tvRT = (TextView) findViewById(R.id.tvRT);

        builder = new AlertDialog.Builder(this);
        builder.setView(R.layout.progress);
        dialog = builder.create();
        btnBack = (Button) findViewById(R.id.back);

      Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

      btnBack.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              startActivity(new Intent(mContext,HomePageKunjungan.class).putExtras(mBundle));
              finish();
          }
      });

        mBundle = getIntent().getExtras();
        tvPemilik.setText(mBundle.getString("nama_pemilik"));
        tvAlamat.setText(mBundle.getString("alamat"));
        tvRT.setText(mBundle.getString("rt"));
        idKunjungan = mBundle.getString("id_kunjungan");
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (idKunjungan != "0") {
            GetDetilKunjunganRumah(idKunjungan);
        }
    }

    private void GetDetilKunjunganRumah(String id_kunjungan) {
        dialog.show();
        Call<ResponseDetilKunjungan> call = service.getDetilKunjungan(id_kunjungan);
        call.enqueue(new Callback<ResponseDetilKunjungan>() {
            @Override
            public void onResponse(Call<ResponseDetilKunjungan> call, Response<ResponseDetilKunjungan> response) {

                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        KunjunganItem detilKunjungan = response.body().getData();
                        tvWadah.setText(""+detilKunjungan.getNWadah());
                        tvJentik.setText(""+detilKunjungan.getNJentik());

                        if (detilKunjungan.getFotoPantau() != null && !detilKunjungan.getFotoPantau().isEmpty())
                            Picasso.with(getApplicationContext()).load("http://" + detilKunjungan.getFotoPantau()).fit().into(ivPantau);
                        if (detilKunjungan.getFotoBerantas() != null && !detilKunjungan.getFotoBerantas().isEmpty())
                            Picasso.with(getApplicationContext()).load("http://" + detilKunjungan.getFotoBerantas()).fit().into(ivBerantas);

                    }
                } else {
                    Toast.makeText(mContext, "Gagal mengambil Detail Kunjungan", Toast.LENGTH_LONG).show();
                }
                dialog.dismiss();
            }

            @Override
            public void onFailure(Call<ResponseDetilKunjungan> call, Throwable t) {

            }
        });
    }
}
